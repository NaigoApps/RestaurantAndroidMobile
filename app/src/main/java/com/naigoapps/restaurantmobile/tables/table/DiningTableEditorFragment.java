package com.naigoapps.restaurantmobile.tables.table;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RemoteDataFragment;
import com.naigoapps.restaurantmobile.dto.DiningTableSkeletonDTO;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.tables.DiningTableEditTask;
import com.naigoapps.restaurantmobile.tasks.diningTables.DiningTableCreateTask;
import com.naigoapps.restaurantmobile.viewmodels.DiningTableSkeletonViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;
import com.naigoapps.restaurantmobile.viewmodels.TablesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.WaitersViewModel;
import com.naigoapps.restaurantmobile.widgets.SpinnerAdapter;

import java.util.List;
import java.util.Locale;

public class DiningTableEditorFragment extends RemoteDataFragment<DiningTableSkeletonDTO> {

    private static final int SPINNER_ITEM = com.google.android.material.R.layout.support_simple_spinner_dropdown_item;

    private TextInputEditText coverChargesView;
    private Spinner tableSpinner;
    private SpinnerAdapter<RestaurantTableDTO> tableSpinnerAdapter;
    private Spinner waiterSpinner;
    private SpinnerAdapter<WaiterDTO> waiterSpinnerAdapter;

    private FloatingActionButton abortBtn;
    private FloatingActionButton confirmBtn;

    private WaitersViewModel waitersViewModel;
    private TablesViewModel tablesViewModel;

    private String diningTableUuid;

    public DiningTableEditorFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            diningTableUuid = DiningTableEditorFragmentArgs.fromBundle(getArguments()).getTableUuid();
        }
        waitersViewModel = ViewModelProviders.of(this).get(WaitersViewModel.class);
        tablesViewModel = ViewModelProviders.of(this).get(TablesViewModel.class);
        waitersViewModel.refresh();
        tablesViewModel.refresh();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dining_table_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        coverChargesView = view.findViewById(R.id.coverChargesField);
        tableSpinner = view.findViewById(R.id.tableField);
        waiterSpinner = view.findViewById(R.id.waiterField);

        abortBtn = view.findViewById(R.id.abortDiningTable);
        confirmBtn = view.findViewById(R.id.confirmDiningTable);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        waiterSpinnerAdapter = new SpinnerAdapter<>(getContext(), SPINNER_ITEM);
        waiterSpinner.setAdapter(waiterSpinnerAdapter);
        tableSpinnerAdapter = new SpinnerAdapter<>(getContext(), SPINNER_ITEM);
        tableSpinner.setAdapter(tableSpinnerAdapter);

        abortBtn.setOnClickListener(evt -> NavHostFragment
                .findNavController(DiningTableEditorFragment.this).navigateUp());

        confirmBtn.setOnClickListener(evt -> {
            DiningTableSkeletonDTO body = buildDiningTable();
            if(body != null) {
                Consumer<DiningTableSkeletonDTO> onOk = dto -> NavHostFragment
                        .findNavController(DiningTableEditorFragment.this).navigateUp();

                if (diningTableUuid != null) {
                    DiningTableEditTask task = new DiningTableEditTask(onOk);
                    task.setDiningTable(body);
                    task.execute();
                } else {
                    DiningTableCreateTask task = new DiningTableCreateTask(onOk);
                    task.setDiningTable(body);
                    task.execute();
                }
            }
        });

        waitersViewModel.register(this, this::onLoadWaiters);
        tablesViewModel.register(this, this::onLoadTables);
    }

    @Override
    protected void onLoad(DiningTableSkeletonDTO data) {
        if (data != null) {
            coverChargesView.setText(String.format(Locale.getDefault(), "%d", data.getCoverCharges()));
            waiterSpinner.setSelection(waiterSpinnerAdapter.getPosition(data.getWaiter()));
            tableSpinner.setSelection(tableSpinnerAdapter.getPosition(data.getTable()));
        }
    }

    private void onLoadWaiters(List<WaiterDTO> waiters) {
        waiterSpinnerAdapter.update(waiters);
        if (getData() != null) {
            waiterSpinner.setSelection(waiterSpinnerAdapter.getPosition(getData().getWaiter()));
        }
    }

    private void onLoadTables(List<RestaurantTableDTO> tables) {
        tableSpinnerAdapter.update(tables);
        if (getData() != null) {
            tableSpinner.setSelection(tableSpinnerAdapter.getPosition(getData().getTable()));
        }
    }

    @Override
    protected RemoteDataViewModel<DiningTableSkeletonDTO> createViewModel() {
        DiningTableSkeletonViewModel viewModel = ViewModelProviders.of(this).get(DiningTableSkeletonViewModel.class);
        viewModel.setTableUuid(diningTableUuid);
        return viewModel;
    }

    private DiningTableSkeletonDTO buildDiningTable() {
        try {
            int ccs = Integer.parseInt(coverChargesView.getText().toString());
            WaiterDTO waiterDTO = waiterSpinnerAdapter.getItem(waiterSpinner.getSelectedItemPosition());
            RestaurantTableDTO tableDTO = tableSpinnerAdapter.getItem(tableSpinner.getSelectedItemPosition());

            DiningTableSkeletonDTO body = new DiningTableSkeletonDTO();
            body.setUuid(diningTableUuid);
            body.setCoverCharges(ccs);
            body.setWaiter(waiterDTO);
            body.setTable(tableDTO);
            return body;
        } catch (Exception ex) {
            Log.w("Error", ex.getMessage());
        }
        return null;
    }
}