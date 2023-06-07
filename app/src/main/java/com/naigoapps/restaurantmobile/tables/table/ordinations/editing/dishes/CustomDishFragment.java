package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RemoteDataFragment;
import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.tables.table.DiningTableFragmentDirections;
import com.naigoapps.restaurantmobile.viewmodels.DishViewModel;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

import java.util.List;

public class CustomDishFragment extends RemoteDataFragment<DishDTO> {

    private OrdinationEditorViewModel ordinationViewModel;

    private TextInputEditText quantityField;
    private TextInputEditText notesField;
    private RecyclerView additionsView;

    private AdditionsListAdapter additionsListAdapter;

    public CustomDishFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_dish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quantityField = view.findViewById(R.id.quantityField);
        notesField = view.findViewById(R.id.notesField);
        additionsView = view.findViewById(R.id.additionsList);
        additionsView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void onLoad(DishDTO data) {
        additionsListAdapter = new AdditionsListAdapter(this, additionsView, data.getUuid());
        additionsView.setAdapter(additionsListAdapter);
        additionsListAdapter.refresh();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ordinationViewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);
    }

    @Override
    protected RemoteDataViewModel<DishDTO> createViewModel() {
        DishViewModel viewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        String dish = CustomDishFragmentArgs.fromBundle(getArguments()).getDishUuid();
        viewModel.setDishUuid(dish);
        return viewModel;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_custom_dish, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.confirmCustomDish){
            String notes = notesField.getText().toString();
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityField.getText().toString());
            }catch (NumberFormatException ex){
                Log.e("Error", "Error", ex);
            }
            List<AdditionDTO> additions = additionsListAdapter.getSelectedDatas();
            if(quantity > 0) {
                ordinationViewModel.addDish(getData(), quantity, additions, notes);
                NavHostFragment.findNavController(this).navigateUp();
            }
            return true;
        }
        return false;
    }

}
