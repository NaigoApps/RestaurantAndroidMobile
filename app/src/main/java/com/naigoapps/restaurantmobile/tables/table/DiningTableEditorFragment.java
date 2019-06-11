package com.naigoapps.restaurantmobile.tables.table;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.tasks.DiningTableCreateTask;
import com.naigoapps.restaurantmobile.tasks.DiningTableEditTask;
import com.naigoapps.restaurantmobile.viewmodels.DiningTableViewModel;
import com.naigoapps.restaurantmobile.viewmodels.TablesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.WaitersViewModel;
import com.naigoapps.restaurantmobile.widgets.RemoteSpinnerAdapter;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

public class DiningTableEditorFragment extends Fragment {

    private static final int SPINNER_ITEM = com.google.android.material.R.layout.support_simple_spinner_dropdown_item;

    private TextInputEditText coverChargesView;
    private Spinner tableSpinner;
    private Spinner waiterSpinner;

    private OrdinationsAdapter listAdapter;

    public DiningTableEditorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dining_table_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        coverChargesView = view.findViewById(R.id.coverChargesField);
        tableSpinner = view.findViewById(R.id.tableField);
        waiterSpinner = view.findViewById(R.id.waiterField);

        if (getActivity() != null) {
            WaitersViewModel waitersViewModel = WaitersViewModel.get(getActivity());
            waiterSpinner.setAdapter(new RemoteSpinnerAdapter<>(getActivity(), SPINNER_ITEM, waitersViewModel));

            TablesViewModel tablesViewModel = ViewModelProviders.of(getActivity()).get(TablesViewModel.class);
            tableSpinner.setAdapter(new RemoteSpinnerAdapter<>(getActivity(), SPINNER_ITEM, tablesViewModel));
        }

        FloatingActionButton abortBtn = view.findViewById(R.id.abortDiningTable);
        abortBtn.setOnClickListener(evt -> Navigation.findNavController(view).navigateUp());
        FloatingActionButton confirmBtn = view.findViewById(R.id.confirmDiningTable);

        String tableUuid;
        if (getArguments() != null && (tableUuid = getArguments().getString("table")) != null) {
            DiningTableViewModel viewModel = ViewModelProviders.of(getActivity()).get(DiningTableViewModel.class);
            viewModel.setTableUuid(tableUuid);
            viewModel.load(getActivity(), this::loadDiningTable);

            confirmBtn.setOnClickListener(evt -> {
                DiningTableEditTask tableEditTask = new DiningTableEditTask(getActivity(), result -> {
                    if (result != null) {
                        Navigation.findNavController(view).navigateUp();
                    }
                });
                DiningTableDTO dto = createDiningTable();
                if (dto != null) {
                    dto.setUuid(viewModel.getTableUuid());
                    tableEditTask.setDiningTable(dto);
                    tableEditTask.execute();
                }
            });

        } else {
            confirmBtn.setOnClickListener(evt -> {
                DiningTableCreateTask tableCreateTask = new DiningTableCreateTask(getActivity(), result -> {
                    if (result != null) {
                        Navigation.findNavController(view).navigateUp();
                    }
                });
                DiningTableDTO dto = createDiningTable();
                if (dto != null) {
                    tableCreateTask.setDiningTable(dto);
                    tableCreateTask.execute();
                }
            });
        }
    }

    private DiningTableDTO createDiningTable() {
        try {
            DiningTableDTO dto = new DiningTableDTO();
            dto.setCoverCharges(Integer.parseInt(coverChargesView.getText().toString()));
            WaiterDTO w = (WaiterDTO) waiterSpinner.getSelectedItem();
            dto.setWaiter(w);
            RestaurantTableDTO rt = (RestaurantTableDTO) tableSpinner.getSelectedItem();
            dto.setTable(rt);
            return dto;
        } catch (Exception ex) {
            Log.e("Error", "Error", ex);
            Toast.makeText(getContext(), "Dati non validi", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private void loadDiningTable(DiningTableDTO dto) {
        waiterSpinner.setVisibility(View.GONE);
        tableSpinner.setVisibility(View.GONE);
        coverChargesView.setText(String.format(Locale.getDefault(), "%d", dto.getCoverCharges()));
        WaitersViewModel.get(getActivity()).get(getActivity(), dtos -> {
            waiterSpinner.setSelection(dtos.indexOf(dto.getWaiter()));
            waiterSpinner.setVisibility(View.VISIBLE);
        });
        TablesViewModel.get(getActivity()).get(getActivity(), dtos -> {
            tableSpinner.setSelection(dtos.indexOf(dto.getTable()));
            tableSpinner.setVisibility(View.VISIBLE);
        });
    }
}
