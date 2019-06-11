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
public enum DiningTableStatus {
    OPEN("APERTO"), CLOSING("IN CHIUSURA"), CLOSED("CHIUSO");
    
    private String name;
    
    DiningTableStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static DiningTableStatus fromName(String name){
        DiningTableStatus result = null;
        for(DiningTableStatus status : DiningTableStatus.values()){
            if(status.getName().equals(name)){
                result = status;
            }
        }
        return result;
    }
    
}
