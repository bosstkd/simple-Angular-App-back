package com.myStudies.pjt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
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

import com.myStudies.pjt.employee.EmployeeService;
import com.myStudies.pjt.employee.model.Employee;
import com.myStudies.pjt.entities.Produit;
import com.myStudies.pjt.repository.productRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

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
	
	
	@Autowired
	EmployeeService eservice;
	
	@GetMapping("/jasper")
	public String jasperReportTest() throws JRException, IOException {
		
		try {
			createPdfReport(eservice.findAll());
			System.out.println("File successfully saved at the given path.");
		} catch (final Exception e) {
			System.out.println("Some error has occured while preparing the employee pdf report.");
			e.printStackTrace();
		}
		
		return "ok";
	}
	
	// Method to create the pdf file using the employee list datasource.
		private void createPdfReport(final List<Employee> employees) throws JRException {
			
			final InputStream stream = this.getClass().getResourceAsStream("/report.jrxml");
			final JasperReport report = JasperCompileManager.compileReport(stream);
			final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);
			final Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "Mahmoudi Mohammed El Amine just for test");
			final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
			JasperExportManager.exportReportToPdfFile(print, "C:/fileTestSpring/Employee_report.pdf");
			
		}
	
}




