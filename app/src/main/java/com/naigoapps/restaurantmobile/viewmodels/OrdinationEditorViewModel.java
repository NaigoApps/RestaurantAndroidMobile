package com.naigoapps.restaurantmobile.viewmodels;

import android.text.TextUtils;

import androidx.core.util.Consumer;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdinationEditorViewModel extends ViewModel {

    private MutableLiveData<OrdinationDTO> currentOrdination;

    private PhaseDTO selectedPhase;
    private CategoryDTO selectedCategory;
    private DishDTO selectedDish;
    private Set<AdditionDTO> selectedAdditions;
    private int selectedQuantity;
    private String selectedNotes;

    public OrdinationEditorViewModel() {
        currentOrdination = new MutableLiveData<>();
        selectedAdditions = new HashSet<>();
    }

    public void setSelectedNotes(String selectedNotes) {
        this.selectedNotes = selectedNotes;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public void setSelectedDish(DishDTO dish) {
        this.selectedDish = dish;
        this.selectedNotes = null;
        this.selectedQuantity = 1;
        selectedAdditions = new HashSet<>();
    }

    public DishDTO getSelectedDish() {
        return selectedDish;
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

    public void setSelectedAdditions(List<AdditionDTO> additions) {
        this.selectedAdditions = new HashSet<>();
        if (additions != null) {
            this.selectedAdditions.addAll(additions);
        }
    }

    public MutableLiveData<OrdinationDTO> getCurrentOrdination() {
        return currentOrdination;
    }

    public void setCurrentOrdination(OrdinationDTO currentOrdination) {
        this.currentOrdination.setValue(currentOrdination);
    }

    public void addDish(DishDTO dish) {
        this.currentOrdination.getValue().addDish(selectedPhase, dish);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void addSelectedDish() {
        this.currentOrdination.getValue().addDish(selectedPhase, selectedDish, selectedQuantity, selectedAdditions, selectedNotes);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void removeDish(DishDTO dish) {
        this.currentOrdination.getValue().removeDish(selectedPhase, dish);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void reduceGroup(OrdersGroupDTO dto){
        this.currentOrdination.getValue().reduceGroup(dto);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void increaseGroup(OrdersGroupDTO dto){
        this.currentOrdination.getValue().increaseGroup(dto);
        this.currentOrdination.setValue(this.currentOrdination.getValue());
    }

    public void confirmCurrentOrdination(Consumer<OrdinationDTO> onCreate) {
//        OrdinationCreateTask task = new OrdinationEditTask(onCreate, currentOrdination.getValue());
//        task.execute();
    }

    public void loadOrdination(String table, String ordination) {
        OrdinationLoadTask loadTask = new OrdinationLoadTask(currentOrdination::setValue, table, ordination);
        loadTask.execute();
    }

    public int getQuantityOf(CategoryDTO categoryDTO){
        OrdinationDTO dto = currentOrdination.getValue();
        if(dto != null){
            return dto.getQuantityOf(categoryDTO);
        }
        return 0;
    }

    public static String formatGroup(OrdersGroupDTO group) {
        return formatGroup(group, false, true);
    }

    public static String formatGroup(OrdersGroupDTO group, boolean phase, boolean name) {
        List<String> parts = new ArrayList<>();
        if(phase){
            parts.add(group.getPhaseName() + ": ");
        }
        parts.add(String.valueOf(group.getQuantity()));
        if(name) {
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
