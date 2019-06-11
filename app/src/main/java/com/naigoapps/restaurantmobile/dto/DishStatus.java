/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naigoapps.restaurantmobile.dto;

/**
 *
 * @author naigo
 */
public enum DishStatus {
    ACTIVE("ATTIVO"), SUSPENDED("SOSPESO"), REMOVED("RIMOSSO");
    
    private String name;
    
    DishStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static DishStatus fromName(String name){
        DishStatus result = null;
        for(DishStatus status : DishStatus.values()){
            if(status.getName().equals(name)){
                result = status;
            }
        }
        return result;
    }
    
}
