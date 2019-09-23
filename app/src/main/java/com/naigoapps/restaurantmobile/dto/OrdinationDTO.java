/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naigoapps.restaurantmobile.dto;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author naigo
 */
public class OrdinationDTO extends DTO {

    private String diningTableId;

    private LocalDateTime creationTime;

    private List<PhaseOrdersDTO> orders;

    private boolean dirty;

    public OrdinationDTO() {
        orders = new ArrayList<>();
    }

    public String getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(String diningTableId) {
        this.diningTableId = diningTableId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public List<PhaseOrdersDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<PhaseOrdersDTO> orders) {
        this.orders = orders;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public int getQuantityOf(CategoryDTO category) {
        int quantity = 0;
        for (PhaseOrdersDTO po : orders) {
            quantity += po.getQuantityOf(category);
        }
        return quantity;
    }

    public int getQuantityOf(DishDTO dish) {
        int quantity = 0;
        for (PhaseOrdersDTO po : orders) {
            quantity += po.getQuantityOf(dish);
        }
        return quantity;
    }

    public void addDish(PhaseDTO phase, DishDTO dish) {
        addDish(phase, dish, 1, new HashSet<>(), null);
    }

    public void addDish(PhaseDTO phase, DishDTO dish, int quantity, Set<AdditionDTO> additions, String notes) {
        PhaseOrdersDTO target = null;
        for (PhaseOrdersDTO po : orders) {
            if (po.getPhase().equals(phase)) {
                target = po;
            }
        }
        if (target == null) {
            target = new PhaseOrdersDTO();
            target.setPhase(phase);
            orders.add(target);
        }
        float additionalPrice = 0;
        for (AdditionDTO addition : additions) {
            additionalPrice += addition.getPrice();
        }
        for (int i = 0; i < quantity; i++) {
            target.addDish(dish, additions, notes, dish.getPrice() + additionalPrice);
        }
        Collections.sort(orders, (o1, o2) -> Integer.compare(o1.getPhase().getPriority(), o2.getPhase().getPriority()));
    }

    public void removeDish(PhaseDTO phase, DishDTO dish) {
        PhaseOrdersDTO target = null;
        for (PhaseOrdersDTO po : orders) {
            if (po.getPhase().equals(phase)) {
                target = po;
            }
        }
        if (target == null) {
            return;
        }
        target.removeDish(dish, new HashSet<>(), null, dish.getPrice());
        if (target.getOrders().isEmpty()) {
            orders.remove(target);
        }
        Collections.sort(orders, (o1, o2) -> Integer.compare(o1.getPhase().getPriority(), o2.getPhase().getPriority()));
    }

    public String format() {
        return new StringBuilder()
                .append("Comanda delle ")
                .append(DateTimeFormat.forPattern("HH:mm").print(creationTime))
                .toString();
    }

    public OrdersGroupDTO[] getGroups(DishDTO dto) {
        List<OrdersGroupDTO> groups = new ArrayList<>();
        for(PhaseOrdersDTO phaseGroup : orders){
            for(OrdersGroupDTO group : phaseGroup.getOrders()){
                if(group.getDish().equals(dto)){
                    groups.add(group);
                }
            }
        }
        return groups.toArray(new OrdersGroupDTO[0]);
    }

    public void reduceGroup(OrdersGroupDTO dto) {
        PhaseOrdersDTO phaseTarget = null;
        for (PhaseOrdersDTO po : orders) {
            for(OrdersGroupDTO group : po.getOrders()) {
                if (group.equals(dto)) {
                    phaseTarget = po;
                }
            }
        }
        if (phaseTarget == null) {
            return;
        }
        phaseTarget.reduceGroup(dto);
        if (phaseTarget.getOrders().isEmpty()) {
            orders.remove(phaseTarget);
        }
        Collections.sort(orders, (o1, o2) -> Integer.compare(o1.getPhase().getPriority(), o2.getPhase().getPriority()));
    }

    public void increaseGroup(OrdersGroupDTO dto) {
        dto.setQuantity(dto.getQuantity() + 1);
        Collections.sort(orders, (o1, o2) -> Integer.compare(o1.getPhase().getPriority(), o2.getPhase().getPriority()));
    }

    public boolean isEmpty(){
        return orders.isEmpty();
    }
}
