package com.myStudies.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myStudies.pjt.entities.Produit;
import com.myStudies.pjt.repository.productRepository;

@CrossOrigin("*")
@RestController
public class produitRestController {
	
	@Autowired
	private productRepository pr;

	@GetMapping("/prd")
	public List<Produit> listProduits(){
		return pr.findAll();
	}
	
	@GetMapping("/prd/{id}")
	public Produit prd(@PathVariable(name="id") Long id) {
		return pr.findById(id).get();
	}
	
	@PutMapping("/prdUpdate/{id}")
	public Produit updatePrd(@PathVariable(name="id") Long id,@RequestBody Produit p) {
		p.setId(id);
		return pr.save(p);
	}
	
	
	@PostMapping("/prdInsert")
	public Produit insertPrd(@RequestBody Produit p) {
		return pr.save(p);
	}
	
	@DeleteMapping("/prdSupprimer/{id}")
	public boolean updatePrd(@PathVariable(name="id") Long id) {
		
		try {
			pr.deleteById(id); 
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
}




