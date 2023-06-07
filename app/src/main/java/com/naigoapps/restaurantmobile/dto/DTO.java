/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naigoapps.restaurantmobile.dto;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author naigo
 */
public class DTO implements Identifiable{

    private String uuid;

    public DTO() {
        this.uuid = UUID.randomUUID().toString();
    }

    public DTO(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DTO)) return false;
        DTO dto = (DTO) o;
        return Objects.equals(uuid, dto.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
