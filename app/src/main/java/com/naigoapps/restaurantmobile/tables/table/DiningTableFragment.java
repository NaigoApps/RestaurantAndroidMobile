package com.naigoapps.restaurantmobile.tables.table;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteCrudListFragment;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.tables.table.ordinations.OrdinationsAdapter;
import com.naigoapps.restaurantmobile.tasks.OrdinationCreateTask;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationsViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public class DiningTableFragment extends RemoteCrudListFragment<OrdinationDTO> {

    private String diningTableUuid;

    public DiningTableFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        diningTableUuid = getTableUuid(getArguments());
    }

    @Override
    protected RecyclerViewAdapter<?, OrdinationDTO> createAdapter(RecyclerView view) {
        return new OrdinationsAdapter(this, view);
    }

    @Override
    public void onAdd() {
        OrdinationCreateTask task = new OrdinationCreateTask(ordination -> NavHostFragment
                .findNavController(this)
                .navigate(DiningTableFragmentDirections
                        .editOrdination(diningTableUuid, ordination.getUuid())));
        task.setDiningTableUuid(diningTableUuid);
        task.execute();
    }

    @Override
    public Integer getContextualMenuId() {
        return R.menu.menu_dining_table;
    }

    @Override
    public boolean onContextualMenuClick(int menuItemId) {
        boolean ok = false;
        switch (menuItemId) {
            case R.id.printItem:
                OrdinationPrintTask printTask = new OrdinationPrintTask(result -> {
                    refresh();
                    clearSelection();
                });
                printTask.setDiningTable(diningTableUuid);
                printTask.setOrdination(getSelection());
                printTask.execute();
                ok = true;
                break;
            case R.id.editItem:

                NavHostFragment.findNavController(DiningTableFragment.this)
                        .navigate(DiningTableFragmentDirections.editOrdination(diningTableUuid, getSelection()));
                ok = true;
                break;
            case R.id.deleteItem:
                OrdinationDeleteTask deleteTask = new OrdinationDeleteTask(result -> {
                    refresh();
                    clearSelection();
                });
                deleteTask.setDiningTable(diningTableUuid);
                deleteTask.setOrdination(getSelection());
                showConfirmDialog("Eliminare la comanda?", (d, w) -> {
                    deleteTask.execute();
                });
                ok = true;
                break;
        }
        return ok;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected RemoteDataViewModel<OrdinationDTO[]> createViewModel() {
        OrdinationsViewModel viewModel = ViewModelProviders.of(this).get(OrdinationsViewModel.class);
        viewModel.setDiningTableUuid(diningTableUuid);
        return viewModel;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dining_tables, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sendMessageItem) {
            NavHostFragment.findNavController(this)
                    .navigate(DiningTableFragmentDirections.sendTableMessage()
                            .setTableUuid(diningTableUuid));
            return true;
        }
        return false;
    }

    private static String getTableUuid(Bundle arguments) {
        if (arguments != null) {
            return DiningTableFragmentArgs.fromBundle(arguments).getTableUuid();
        }
        return null;
    }

}
