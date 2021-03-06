package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;


@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					  @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
					  @RequestParam(value = "name", required = true) String name,
					  @RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot",archive);
		return "viewall-pilot";
	}
	
	@RequestMapping("/pilot/view/license-number/{licenseNumber}")
	public String viewPilot(@PathVariable String licenseNumber,Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("licenseNumber",licenseNumber);
		if(archive == null) {
			return "noPilot-withLN";
		}else {
			model.addAttribute("pilot",archive);
			return "view-pilot";
		}
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{newFlyHour}")
	public String updateFlyHour(@PathVariable String licenseNumber, @PathVariable Integer newFlyHour, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		if(archive == null) {
			model.addAttribute("licenseNumber",licenseNumber);
			return "noPilot-withLN";
		}else {
			archive.setFlyHour(newFlyHour);
			model.addAttribute("pilot",archive);
			return "update-berhasil";
		}
	}
	
	@RequestMapping("/pilot/delete/id/{id}")
	public String deletePilot(@PathVariable String id, Model model) {
		PilotModel archive = null; 
		for(int a = 0; a<pilotService.getPilotList().size();a++) {
			if(pilotService.getPilotList().get(a).getId().equalsIgnoreCase(id)) {
				archive = pilotService.getPilotDetailByLicenseNumber(pilotService.getPilotList().get(a).getLicenseNumber());
			}
			
		}
		model.addAttribute("pilot",archive);
		for(int x = 0;x<pilotService.getPilotList().size();x++) {
			if(pilotService.getPilotList().get(x).getId().equalsIgnoreCase(id)) {
				pilotService.getPilotList().remove(x);
			}
		}
		return "delete-berhasil";
		
	}
	
}
