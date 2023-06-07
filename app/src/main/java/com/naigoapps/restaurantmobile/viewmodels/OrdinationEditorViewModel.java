package com.naigoapps.restaurantmobile.viewmodels;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.dto.PhaseDTO;
import com.naigoapps.restaurantmobile.tasks.OrdinationLoadTask;

import java.util.ArrayList;
import java.util.List;

public class OrdinationEditorViewModel extends ViewModel {

    private MutableLiveData<OrdinationDTO> currentOrdination;

    private PhaseDTO selectedPhase;
    private CategoryDTO selectedCategory;

    public OrdinationEditorViewModel() {
        currentOrdination = new MutableLiveData<>();
    }

    public void setSelectedPhase(PhaseDTO phase) {
        this.selectedPhase = phase;
    }

    public PhaseDTO getSelectedPhase() {
        return selectedPhase;
    }

    public void setSelectedCategory(CategoryDTO selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public CategoryDTO getSelectedCategory() {
        return selectedCategory;
    }

    public MutableLiveData<OrdinationDTO> getCurrentOrdination() {
        return currentOrdination;
    }

    public void addDish(DishDTO dish) {
        this.currentOrdination.getValue().addDish(selectedPhase, dish);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void addDish(DishDTO dish, int quantity, List<AdditionDTO> additions, String notes) {
        this.currentOrdination.getValue().addDish(selectedPhase, dish, quantity, new ArrayList<>(additions), notes);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void reduceGroup(OrdersGroupDTO dto) {
        this.currentOrdination.getValue().reduceGroup(dto);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void increaseGroup(OrdersGroupDTO dto) {
        this.currentOrdination.getValue().increaseGroup(dto);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void loadOrdination(String table, String ordination) {
        OrdinationLoadTask loadTask = new OrdinationLoadTask(currentOrdination::setValue, table, ordination);
        loadTask.execute();
    }

    public int getQuantityOf(CategoryDTO categoryDTO) {
        OrdinationDTO dto = currentOrdination.getValue();
        if (dto != null) {
            return dto.getQuantityOf(categoryDTO);
        }
        return 0;
    }

    public static String formatGroup(OrdersGroupDTO group) {
        return formatGroup(group, false, true);
    }

    public static String formatGroup(OrdersGroupDTO group, boolean phase, boolean name) {
        List<String> parts = new ArrayList<>();
        if (phase) {
            parts.add(group.getPhaseName() + ": ");
        }
        parts.add(String.valueOf(group.getQuantity()));
        if (name) {
            parts.add(group.getDish().getName());
        }
        for (AdditionDTO addition : group.getAdditions()) {
            parts.add(addition.getName());
        }
        if (group.getNotes() != null) {
            parts.add(group.getNotes());
        }
        return TextUtils.join(" ", parts);
    }

}
