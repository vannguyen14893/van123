package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Color implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name_color")
	private String nameColor;
	private Integer total;
//	@ManyToOne
//	@JoinColumn(name="product_id")
//	@JsonIgnoreProperties("colors")
//    private Product product;
	@ManyToOne
	@JoinColumn(name = "size_id")
	@JsonIgnoreProperties("colors")
	private Size size;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameColor() {
		return nameColor;
	}

	public void setNameColor(String nameColor) {
		this.nameColor = nameColor;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	public Integer getTotal() {
		return total;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Color(Integer id, String nameColor, Integer total, Size size) {
		super();
		
		this.nameColor = nameColor;
		this.total = total;
		this.size = size;
	}

	public Color(Integer id, String nameColor, Integer total) {
		super();
		this.id = id;
		this.nameColor = nameColor;
		this.total = total;
	}

	public Color() {
		super();
	}

}
