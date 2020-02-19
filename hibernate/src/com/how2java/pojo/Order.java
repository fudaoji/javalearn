package com.how2java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
	int id;
	float price;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="price")
	public int getPrice() {
		return id;
	}
	public void setPrice(float amount) {
		this.price = amount;
	}
	
	@Override
    public String toString() {
        return "" + price;
    }


}
