package com.myStudies.pjt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.myStudies.pjt.entities.Produit;

@CrossOrigin("*")
@RepositoryRestResource
public interface productRepository extends JpaRepository<Produit, Long>{
        @RestResource(path="/byDesig")
		public List<Produit> findByDesig(@Param("mc") String desig);
        
        @RestResource(path="/byDesigContains")
		public Page<Produit> findByDesigContains(@Param("mc") String desig, Pageable pgb);
    
    }
