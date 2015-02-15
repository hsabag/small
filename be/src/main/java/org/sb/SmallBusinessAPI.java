package org.sb;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.sb.data.Company;
import org.sb.data.Manager;
import org.sb.data.WorkCard;
import org.sb.persist.data.CompanyEntity;
import org.sb.persist.data.WorkCardEntity;
import org.sb.persist.data.WorkCardSummary;
import org.sb.persist.CompanyRepository;
import org.sb.persist.WorkCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SmallBusinessAPI {
	
	@Autowired
	CompanyRepository companyRep;
	
	@Autowired
	WorkCardRepository workCardRep;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping("/health")
	String health(){
		return "ok";
	}
	
	@RequestMapping(value= "/company", method = RequestMethod.POST)
	ResponseEntity<String> registerCompany(@RequestBody Company company){
		CompanyEntity entity = new CompanyEntity();
		entity.setEntity(company);
		Manager manager = company.getManager();
		try {
			manager.setPassword(Encryption.cryptWithMD5(manager.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			return new ResponseEntity<String>(null, null , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		CompanyEntity savedEntity = companyRep.save(entity);
		return new ResponseEntity<String>(savedEntity.getId(), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/company/{name}", method = RequestMethod.GET)
	ResponseEntity<Company> getCompany(@PathVariable String name){
		CompanyEntity savedEntity = companyRep.findByName(name);
		Company entity = savedEntity.getEntity();
		entity.getManager().setPassword(null);
		return new ResponseEntity<Company>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/workcard", method = RequestMethod.POST)
	ResponseEntity<String> openWorkCard(@RequestBody WorkCard card){
		CompanyEntity company = this.companyRep.findByName(card.getCompanyName());
		if (company == null)
		{
			MultiValueMap<String, String> error = new LinkedMultiValueMap<String, String>();
			error.add("error", "No such company: "+card.getCompanyName());
			return new ResponseEntity<String>(null, error, HttpStatus.BAD_REQUEST);
		}
		WorkCardEntity entity = new WorkCardEntity();
		entity.setEntity(card);
		WorkCardEntity createdEntity = this.workCardRep.save(entity );
		Set<WorkCardSummary> workCards = company.getWorkCards();
		if (workCards == null) workCards = new HashSet<>();
		WorkCardSummary summary = new WorkCardSummary();
		summary.setWorkCardId(createdEntity.getId());
		summary.setCustomerFullName(card.getCustomerInfo().getFullName());
		workCards.add(summary);
		company.setWorkCards(workCards);
		this.companyRep.save(company);
		return new ResponseEntity<String>(createdEntity.getId(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/workcards/{company}", method = RequestMethod.GET)
	ResponseEntity<Set<WorkCardSummary>> getCompanyWorkCards(@PathVariable String company){
		CompanyEntity companyEntity = this.companyRep.findByName(company);
		if (companyEntity == null)
		{
			MultiValueMap<String, String> error = new LinkedMultiValueMap<String, String>();
			error.add("error", "No such company: "+company);
			return new ResponseEntity<Set<WorkCardSummary>>(null, error, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Set<WorkCardSummary>>(companyEntity.getWorkCards(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/workcard/{id}", method = RequestMethod.GET)
	ResponseEntity<WorkCard> searchWorkCard(@PathVariable String id){
		WorkCardEntity entity =  this.workCardRep.findOne(id);
		return new ResponseEntity<WorkCard>(entity.getEntity(), HttpStatus.OK);
	}


}
