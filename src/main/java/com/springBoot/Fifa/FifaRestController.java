package com.springBoot.Fifa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.VoetbalService;

@RestController
@RequestMapping("/fifaDetail")
public class FifaRestController {
	@Autowired
	private VoetbalService voetbalService;
	
	@GetMapping("/{givenId}")
	public ResponseEntity<Object> getLands(@PathVariable String givenId) {
		Object obj;
		try {
			obj = voetbalService.getWedstrijd(givenId).getWedstrijd().getLanden();
		} catch(Exception e) {
			String[] string = new String[] {"Oeps ...", "Error 404", "not found"};
			obj = string;
		}
		return ResponseEntity.ok(obj);
	}
}
