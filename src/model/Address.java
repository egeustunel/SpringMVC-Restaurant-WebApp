package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Embeddable
@Table(name = "Customer")
public class Address implements Serializable {
	// floor, building,	room no.
	@Column(name = "floor")
	private int floor;
	@Column(name = "building")
	private String building;
	@Column(name = "roomNo")
	private int roomNo;
	
	public Address(){};
	public Address(int floor, String building, int roomNo) 
	{	
		this.floor = floor;
		this.building = building;
		this.roomNo = roomNo;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	

}
