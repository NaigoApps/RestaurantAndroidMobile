package com.naigoapps.restaurantmobile.tables.table;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.tables.IdentifiableCallback;
import com.naigoapps.restaurantmobile.tables.RemoteDataFragment;
import com.naigoapps.restaurantmobile.viewmodels.DiningTableViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class DiningTableFragment extends RemoteDataFragment<DiningTableDTO> {

    private TextView titleView;
    private SwipeRefreshLayout listContainer;
    private FloatingActionButton createOrdinationButton;

    private OrdinationsAdapter listAdapter;

    public DiningTableFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dining_table_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView = view.findViewById(R.id.title);

        listContainer = view.findViewById(R.id.ordinationsListContainer);
        listContainer.setOnRefreshListener(this::refresh);

        RecyclerView list = view.findViewById(R.id.ordinationsList);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        listAdapter = new OrdinationsAdapter(list);
        list.setAdapter(listAdapter);

        createOrdinationButton = view.findViewById(R.id.btnAddOrdination);
        createOrdinationButton.setOnClickListener(evt -> Navigation.findNavController(createOrdinationButton).navigate(R.id.ordinationEditorFragment));

    }

    @Override
    protected void onLoad(DiningTableDTO data) {
        titleView.setText(data.format());

        OrdinationDTO[] newData = data.getOrdinations().stream().toArray(OrdinationDTO[]::new);

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(
                new IdentifiableCallback(
                        listAdapter.getData(),
                        newData));
        result.dispatchUpdatesTo(listAdapter);
        listAdapter.updateData(newData);
        listContainer.setRefreshing(false);
    }

    @Override
    protected RemoteViewModel<DiningTableDTO> getViewModel() {
        String tableUuid = getArguments().getString("uuid");
        DiningTableViewModel model = ViewModelProviders.of(getActivity()).get(DiningTableViewModel.class);
        model.setTableUuid(tableUuid);
        return model;
    }

}
