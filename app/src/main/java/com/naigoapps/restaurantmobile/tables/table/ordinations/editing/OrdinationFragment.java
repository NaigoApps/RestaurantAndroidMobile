package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteDataFragment;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public class OrdinationFragment extends RemoteDataFragment<OrdinationDTO> {

    private OrdinationEditorViewModel editorViewModel;

    private RecyclerView list;
    private FloatingActionButton addButton;
    private RecyclerViewAdapter<EditablePhaseGroupAdapter.EditablePhaseGroupViewHolder, PhaseOrdersDTO> listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.crud_list_view, container, false);
        setHasOptionsMenu(true);

        list = result.findViewById(R.id.list);
        addButton = result.findViewById(R.id.btnAdd);

        editorViewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        list.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        listAdapter = new EditablePhaseGroupAdapter(this, list);
        list.setAdapter(listAdapter);

        addButton.setOnClickListener(evt -> NavHostFragment.findNavController(this)
                .navigate(OrdinationFragmentDirections.addOrdinationAction()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_ordination_editor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.confirmOrdinationItem) {
//            viewModel.confirmCurrentOrdination(ordinationDTO -> {
//                Navigation.findNavController(pager).navigateUp();
//            });
            return true;
        }
        return false;
    }

    @Override
    protected void onLoad(OrdinationDTO data) {
        editorViewModel.setCurrentOrdination(data);
        if (data.isEmpty()) {
            NavHostFragment.findNavController(this)
                    .navigate(OrdinationFragmentDirections.addOrdinationAction());
        }
    }

    @Override
    protected RemoteDataViewModel<OrdinationDTO> createViewModel() {
        OrdinationViewModel result = ViewModelProviders.of(this).get(OrdinationViewModel.class);
        if (getArguments() != null) {
            OrdinationFragmentArgs args = OrdinationFragmentArgs.fromBundle(getArguments());
            result.setTableUuid(args.getTableUuid());
            result.setOrdinationUuid(args.getOrdinationUuid());
        }
        return result;
    }
}
