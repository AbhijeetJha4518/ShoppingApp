package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
private int productId;
private String producrName;
private int productPrice;
private String productCategory;
private String productValidity;
}
