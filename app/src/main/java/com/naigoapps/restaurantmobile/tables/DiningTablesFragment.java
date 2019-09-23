package com.naigoapps.restaurantmobile.tables;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteCrudListFragment;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.tasks.diningTables.DiningTableCreateTask;
import com.naigoapps.restaurantmobile.tasks.diningTables.DiningTableDeleteTask;
import com.naigoapps.restaurantmobile.viewmodels.DiningTablesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public class DiningTablesFragment extends RemoteCrudListFragment<DiningTableDTO> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected RecyclerViewAdapter<?, DiningTableDTO> createAdapter(RecyclerView view) {
        return new DiningTablesAdapter(this, view);
    }

    @Override
    protected RemoteDataViewModel<DiningTableDTO[]> createViewModel() {
        return ViewModelProviders.of(this).get(DiningTablesViewModel.class);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dining_tables, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sendMessageItem) {
            NavHostFragment.findNavController(this)
                    .navigate(DiningTablesFragmentDirections.sendGenericMessage());
            return true;
        }
        return false;
    }

    @Override
    public Integer getContextualMenuId() {
        return R.menu.crud_menu;
    }

    @Override
    public boolean onContextualMenuClick(int menuItemId) {
        boolean ok = false;
        switch (menuItemId) {
            case R.id.editItem:
                NavHostFragment.findNavController(DiningTablesFragment.this)
                        .navigate(DiningTablesFragmentDirections
                                .editDiningTable()
                                .setTableUuid(getSelection()));
                ok = true;
                break;
            case R.id.deleteItem:
                DiningTableDeleteTask deleteTask = new DiningTableDeleteTask(result -> {
                    refresh();
                    clearSelection();
                });
                deleteTask.setDiningTable(getSelection());
                showConfirmDialog("Eliminare il tavolo?", (d, w) -> {
                    deleteTask.execute();
                });
                ok = true;
                break;
        }
        return ok;
    }

    @Override
    public void onAdd() {
        NavHostFragment.findNavController(this)
                .navigate(DiningTablesFragmentDirections
                        .editDiningTable()
                        .setTableUuid(null));
    }

}
