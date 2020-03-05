package com.myStudies.pjt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.myStudies.pjt.entities.Produit;
import com.myStudies.pjt.repository.productRepository;

@SpringBootApplication
public class EtudeSpringAngularApplication  implements CommandLineRunner{

	@Autowired
	private productRepository prRepo;
	
	// interface de configuration de spring data rest
	@Autowired
	private RepositoryRestConfiguration restConfig;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EtudeSpringAngularApplication.class, args);
	}

    @Override
	public void run(String... args) throws Exception {
    	
    	restConfig.exposeIdsFor(Produit.class);
    	
		// TODO Auto-generated method stub
		prRepo.save(new Produit("IMPRIMANTE DELL", 20000, 4));
		prRepo.save(new Produit("IMPRIMANTE HP", 65000, 10));
		prRepo.save(new Produit("IMPRIMANTE LENOVO", 12000, 18));
		prRepo.save(new Produit("IMPRIMANTE TOSHIBA", 5690, 1));
		prRepo.save(new Produit("PC DELL", 120000, 6));
		prRepo.save(new Produit("PC HP", 150200, 1));
		prRepo.save(new Produit("PC LENOVO", 60000, 8));
		prRepo.save(new Produit("PC TOSHIBA", 55200, 12));
		
		prRepo.save(new Produit("PHOTO DELL", 65000, 6));
		prRepo.save(new Produit("PHOTO HP", 25400, 1));
		prRepo.save(new Produit("PHOTO LENOVO", 220, 8));
		prRepo.save(new Produit("PHOTO TOSHIBA", 120, 12));
		
		prRepo.findAll().forEach(p->System.out.println(p.getDesig()+" "+p.getPrix()));
		
	}

}
