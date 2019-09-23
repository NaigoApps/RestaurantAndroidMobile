package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes.AdditionsListAdapter;
import com.naigoapps.restaurantmobile.viewmodels.AdditionsViewModel;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class CustomDishFragment extends DialogFragment {

    private TextInputEditText quantityField;
    private TextInputEditText notesField;
    private RecyclerView additionsList;

    private OrdinationEditorViewModel ordinationViewModel;
    private AdditionsViewModel additionsViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ordinationViewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);
        additionsViewModel = AdditionsViewModel.get(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.custom_dish, null);

        quantityField = dialogView.findViewById(R.id.quantityField);
        quantityField.setText("1");
        notesField = dialogView.findViewById(R.id.notesField);
        additionsList = dialogView.findViewById(R.id.additionsList);
        additionsList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        DishDTO selectedDish = ordinationViewModel.getSelectedDish();
        additionsViewModel.setDish(selectedDish.getUuid());
        additionsViewModel.refresh();

        AdditionsListAdapter additionsAdapter = new AdditionsListAdapter(this, additionsList);
        additionsList.setAdapter(additionsAdapter);

        builder.setView(dialogView)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    String notes = notesField.getText().toString();
                    int quantity = Integer.parseInt(quantityField.getText().toString());
                    ordinationViewModel.setSelectedNotes(!notes.isEmpty() ? notes : null);
                    ordinationViewModel.setSelectedQuantity(quantity);
                    ordinationViewModel.setSelectedAdditions(additionsAdapter.getSelectedDatas());
                    ordinationViewModel.addSelectedDish();
                    dialog.cancel();
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> dialog.cancel());
        return builder.create();
    }
}
