package com.naigoapps.restaurantmobile.dto;

public class WrapperDTO<T> {
    private T value;

    public WrapperDTO() {

    }

    public WrapperDTO(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
