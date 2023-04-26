package com.marketingapp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.marketingapp7.dto.LeadDto;
import com.marketingapp7.entity.Lead;
import com.marketingapp7.service.LeadService;
import com.marketingapp7.util.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	 
	@Autowired
	private EmailService emailService;
	
	//http://localhost:8080/view
	@RequestMapping("/view")
	public String viewLeadPage(){
		return "create_lead"; // page name
		// request dispatcher
	}
	// Read the data three ways
	
	//http://localhost:8080/saveLead
	@RequestMapping("/saveLead")     // first ways
	public String saveOneLead(Lead lead, Model model ) {
		leadService.saveLead(lead);
		emailService.sendEmail(lead.getEmail(), "Welcome", "hello ram");
		model.addAttribute("msg", "lead is saved");		
		
//		System.out.println(lead.getFirstName); // check the data console check the data backend
//		System.out.println(lead.getLastName);
//		System.out.println(lead.getEmail);
//		System.out.println(lead.getMobile);
//		
		return "create_lead";	
		
	}
//	//http://localhost:8080/saveLead
//		@RequestMapping("/saveLead") // second ways
//		public String saveOneLead(
//				@RequestParam("firstName") String firstName, // 
//				@RequestParam("LastNameName") String firstName,
//				@RequestParam("email") String firstName,
//				@RequestParam("mobile") long firstName,				
//				Model model ) {
//			Lead lead=new Lead();
//			lead.setFirstName(firstName);
//			lead.setLastName(lastName);
//			lead.setEmail(email);
//			lead.setMobile(mobile);
//			leadService.saveLead(lead);
//			emailService.sendEmail(lead.getEmail(), "Welcome", "hello ram");
//			model.addAttribute("msg", "lead is saved");				
//			return "create_lead";	
//		}
	//http://localhost:8080/saveLead
	// what is @modelAttribute: it bind froms datawith a entity object in the handler method
//		@RequestMapping("/saveLead")
//		public String saveOneLead(@ModelAttribute Lead lead, Model model ) {
//			leadService.saveLead(lead);
//			emailService.sendEmail(lead.getEmail(), "Welcome", "hello ram");
//			model.addAttribute("msg", "lead is saved");	
//			return "create_lead";		
//		}
	
	//http://localhost:8080/saveLead
//		@RequestMapping("/saveLead") // third ways
//		public String saveOneLead(LeadDto dto, Model model ) {//Dto: data transfer object
//			Lead lead=new Lead();
//			lead.setFirstName(dto.getFirstName());
//			lead.setLastName(dto.getLastName());
//			lead.setEmail(dto.getEmail());
//			lead.setMobile(dto.getMobile());
//			
//			leadService.saveLead(lead);
//			emailService.sendEmail(lead.getEmail(), "Welcome", "hello ram");
//			model.addAttribute("msg", "lead is saved");	
//			return "create_lead";		
//		}
//			
	
	
	//http://localhost:8080/listall
	@RequestMapping("/listall")
	public String getAllLeads( Model model) {
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads",leads);
		System.out.println(leads);
		return "list_leads";
	}
	@RequestMapping("/delete")
	public String deleteLeadById(@RequestParam("id")long id, Model model) {
		leadService.deleteLead(id);
		
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads",leads); 
		System.out.println(leads);
		return "list_leads";
	}
	@RequestMapping("/update") 
	public String getLeadById(@RequestParam("id") long id, Model model) {
		Lead lead = leadService.getLeadById(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	@RequestMapping("/updateLead")
	public String updateOneLead(Lead lead, Model model ) {
		leadService.saveLead(lead);
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads", leads);
		System.out.println(leads);
		return "list_leads";
}
}
