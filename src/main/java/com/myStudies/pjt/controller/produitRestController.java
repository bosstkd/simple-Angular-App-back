package com.myStudies.pjt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		
		File convertFile = new File("C:/fileTestSpring/"+file.getOriginalFilename());
	
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
	}
	
}




