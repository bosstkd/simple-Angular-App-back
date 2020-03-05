package com.myStudies.pjt.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Produit implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 
     private Long id;
	 private String desig;
	 private double prix;
	 private int qte;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public Produit(String desig, double prix, int qte) {
		super();
		this.desig = desig;
		this.prix = prix;
		this.qte = qte;
	}
	 
	
	public Produit() {
		
	}
	
}
