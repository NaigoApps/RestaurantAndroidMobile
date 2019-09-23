package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.categories;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteCrudListFragment;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.tables.table.DiningTableFragmentDirections;
import com.naigoapps.restaurantmobile.tables.table.OrdinationEditTask;
import com.naigoapps.restaurantmobile.viewmodels.CategoriesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public class CategoriesFragment extends RemoteCrudListFragment<CategoryDTO> {

    private OrdinationEditorViewModel viewModel;

    public CategoriesFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showConfirmDialog("Annullare le modifiche?", (d, w) -> NavHostFragment
                        .findNavController(CategoriesFragment.this)
                        .navigateUp());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_ordination_editor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.confirmOrdinationItem) {
            OrdinationEditTask task = new OrdinationEditTask(result -> {
                if (result != null) {
                    NavHostFragment.findNavController(this).navigateUp();
//                    NavHostFragment.findNavController(this)
//                            .navigate(CategoriesMenuFragmentDirections
//                                    .confirmOrdinationFromCategories(result.getDiningTableId())/*,
//                        new NavOptions.Builder().setPopUpTo(R.id.diningTableFragment, false)*/);
                } else {
                    Toast.makeText(getContext(), R.string.ordinationConfirmError, Toast.LENGTH_LONG);
                }
            });
            OrdinationDTO data = viewModel.getCurrentOrdination().getValue();
            task.setContent(data);
            task.execute();
            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnAdd).setVisibility(View.GONE);
        RecyclerView listView = view.findViewById(R.id.list);
        listView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected RemoteDataViewModel<CategoryDTO[]> createViewModel() {
        return ViewModelProviders.of(getActivity()).get(CategoriesViewModel.class);
    }

    @Override
    protected RecyclerViewAdapter<?, CategoryDTO> createAdapter(RecyclerView view) {
        return new CategoriesAdapter(this, view, viewModel);
    }

    @Override
    public void onAdd() {
        //Nothing to do
    }

    @Override
    public Integer getContextualMenuId() {
        return null;
    }

    @Override
    public boolean onContextualMenuClick(int menuItemId) {
        return false;
    }

}
