/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naigoapps.restaurantmobile.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author naigo
 */
public class PhaseOrdersDTO extends DTO {

    private PhaseDTO phase;

    private List<OrdersGroupDTO> orders;

    public PhaseOrdersDTO() {
        orders = new ArrayList<>();
    }

    public PhaseDTO getPhase() {
        return phase;
    }

    public void setPhase(PhaseDTO phase) {
        this.phase = phase;
    }

    public List<OrdersGroupDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersGroupDTO> orders) {
        this.orders = orders;
    }

    public int getQuantityOf(DishDTO dish){
        int quantity = 0;
        for(OrdersGroupDTO og : orders){
            if(og.getDish().equals(dish)){
                quantity += og.getQuantity();
            }
        }
        return quantity;
    }

    public int getQuantityOf(CategoryDTO category){
        int quantity = 0;
        for(OrdersGroupDTO og : orders){
            if(og.getDish().getCategoryId().equals(category.getUuid())){
                quantity += og.getQuantity();
            }
        }
        return quantity;
    }

    public void addDish(DishDTO dish, List<AdditionDTO> additions, String notes, float price){
        OrdersGroupDTO target = null;
        for (OrdersGroupDTO og : orders){
            if(og.match(dish, additions, notes, price)){
                target = og;
            }
        }
        if(target == null){
            target = new OrdersGroupDTO();
            target.setPhaseName(phase.getName());
            target.setDish(dish);
            target.setAdditions(new ArrayList<>(additions));
            target.setNotes(notes);
            target.setPrice(price);
            orders.add(target);
        }
        target.setQuantity(target.getQuantity() + 1);
    }

    public void removeDish(DishDTO dish, List<AdditionDTO> additions, String notes, float price){
        OrdersGroupDTO target = null;
        for (OrdersGroupDTO og : orders){
            if(og.match(dish, additions, notes, price)){
                target = og;
            }
        }
        if(target == null){
            return;
        }
        target.setQuantity(target.getQuantity() - 1);
        if(target.getQuantity() == 0){
            orders.remove(target);
        }
    }

    public void reduceGroup(OrdersGroupDTO dto){
        dto.setQuantity(dto.getQuantity() - 1);
        if(dto.getQuantity() == 0){
            orders.remove(dto);
        }
    }
}
