package com.naigoapps.restaurantmobile.tables;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.tasks.DiningTableDeleteTask;
import com.naigoapps.restaurantmobile.viewmodels.DiningTablesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class DiningTablesFragment extends RemoteRecyclerViewFragment<DiningTableDTO> {

    public DiningTablesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dining_tables_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton newDiningTableButton = view.findViewById(R.id.btnAddTable);

        newDiningTableButton.setOnClickListener(evt ->
                Navigation.findNavController(view).navigate(R.id.diningTableEditor));
    }

    @Override
    protected RecyclerViewAdapter<?, DiningTableDTO> createAdapter(RecyclerView view) {
        return new DiningTablesAdapter(view);
    }

    @Override
    protected RemoteViewModel<DiningTableDTO[]> getViewModel() {
        return ViewModelProviders.of(getActivity()).get(DiningTablesViewModel.class);
    }

    @Override
    protected ActionMode.Callback createActionMode() {
        return new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.dining_table_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean ok = false;
                if (DiningTablesFragment.this.getView() != null) {
                    View v = DiningTablesFragment.this.getView();
                    switch (item.getItemId()) {
                        case R.id.editDiningTableItem:
                            Bundle bundle = new Bundle();
                            bundle.putString("table", getSelection());
                            Navigation.findNavController(v).navigate(R.id.diningTableEditor, bundle);
                            ok = true;
                            break;
                        case R.id.deleteDiningTableItem:
                            showConfirmDialog("Eliminare il tavolo?", (d, w) -> {
                                DiningTableDeleteTask deleteTask = new DiningTableDeleteTask(getActivity(), result -> {
                                    refresh();
                                    clearSelection();
                                });
                                deleteTask.setDiningTable(getSelection());
                                deleteTask.execute();
                            });
                            ok = false;
                            break;
                    }
                }
                if(ok){
                    clearSelection();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                DiningTablesFragment.this.onDestroyActionMode();
            }
        };
    }
}
