package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	String contoh = "prinantFix";
	@RequestMapping("/viral")
	public String index() {
		return "viral"; /*ini untuk htmlnya*/
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
	@RequestMapping(value = {"/challenge", "/challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name",name.get());
		}
		else {
			model.addAttribute("name","KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String viralGenerator(@RequestParam(value = "a", required = false, defaultValue = "0") String a, @RequestParam(value = "b", required = false, defaultValue = "0") String b, Model model) {
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		
		
		int first = Integer.parseInt(a);
		int second = Integer.parseInt(b);
		
		String annisa = " h";

		if(first == 0) {
			first++;
			for(int index=0; index< first; index++) {
				annisa = annisa + "m";
			}
		}else {
			for(int index=0; index< first; index++) {
				annisa = annisa + "m";
			}
		}
		
		String printFix= " ";
		if(second == 0) {
			second++;
			for(int indexlagi=0; indexlagi< second; indexlagi++) {
				printFix += annisa;
			}
		}else {
			for(int indexlagi=0; indexlagi< second; indexlagi++) {
				printFix += annisa;
			}
		}
		
		model.addAttribute("printdong", printFix);
		return "generator";
	}
	
}
