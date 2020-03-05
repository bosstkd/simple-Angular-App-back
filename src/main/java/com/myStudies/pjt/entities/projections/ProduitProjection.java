package com.myStudies.pjt.entities.projections;

import org.springframework.data.rest.core.config.Projection;

import com.myStudies.pjt.entities.Produit;

@Projection(name="P1", types = Produit.class)
public interface ProduitProjection {
	public String getDesig();
	public double getPrix();
}
