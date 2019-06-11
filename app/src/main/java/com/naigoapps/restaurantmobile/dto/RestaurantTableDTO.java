/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naigoapps.restaurantmobile.dto;

import androidx.annotation.NonNull;

/**
 *
 * @author naigo
 */
public class RestaurantTableDTO extends DTO{
    private String name;

    public RestaurantTableDTO() {
        super();
    }

    public RestaurantTableDTO(String uuid, String name) {
        super(uuid);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
