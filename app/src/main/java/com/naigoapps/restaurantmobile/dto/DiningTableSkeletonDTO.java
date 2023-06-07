/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naigoapps.restaurantmobile.dto;

import com.naigoapps.restaurantmobile.common.Utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author naigo
 */
public class DiningTableSkeletonDTO extends DTO {

	private String eveningId;

	private float coverCharge;

	private int coverCharges;

	private WaiterDTO waiter;
	
	private LocalDateTime openingTime;

	private RestaurantTableDTO table;

	private DiningTableStatus status;
	
	private double total;

	public String getEveningId() {
		return eveningId;
	}

	public void setEveningId(String eveningId) {
		this.eveningId = eveningId;
	}

	public int getCoverCharges() {
		return coverCharges;
	}

	public void setCoverCharges(int coverCharges) {
		this.coverCharges = coverCharges;
	}

	public WaiterDTO getWaiter() {
		return waiter;
	}

	public void setWaiter(WaiterDTO waiter) {
		this.waiter = waiter;
	}

	public LocalDateTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalDateTime openingTime) {
		this.openingTime = openingTime;
	}

	public RestaurantTableDTO getTable() {
		return table;
	}

	public void setTable(RestaurantTableDTO table) {
		this.table = table;
	}

	public DiningTableStatus getStatus() {
		return status;
	}

	public void setStatus(DiningTableStatus status) {
		this.status = status;
	}

	public void setCoverCharge(float coverCharge) {
		this.coverCharge = coverCharge;
	}

	public float getCoverCharge() {
		return coverCharge;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getTotal() {
		return total;
	}

	public String format() {
		return new StringBuilder()
				.append(Utils.string(table)).append(' ')
				.append(Utils.string(waiter)).append(' ')
				.append(DateTimeFormat.forPattern("'('HH:mm')'").print(openingTime))
				.toString();
	}
}
