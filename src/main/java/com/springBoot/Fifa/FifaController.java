package com.springBoot.Fifa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.WedstrijdTicket;
import service.VoetbalService;
import validator.FifaValidation;

@Controller
public class FifaController {
	private String stadium;
	private WedstrijdTicket match;
	private String id;
	@Autowired
	private VoetbalService voetbalService;
	@Autowired
	private FifaValidation fifaValidation;

	@ModelAttribute("stadiumList")
	public List<String> populateStadiums() {
		return voetbalService.getStadiumList();
	}

	@ModelAttribute("matchList")
	public List<WedstrijdTicket> populateMatches(@ModelAttribute FifaCommand fifaCommand) {
		if (fifaCommand.getSelectedStadium() == null)
			return null;
		return voetbalService.getWedstrijdenByStadium(fifaCommand.getSelectedStadium());
	}

	@GetMapping("/fifa")
	public String showFormPage(@RequestParam(value = "verkocht", defaultValue = "0", required = true) String verkocht,
			@RequestParam(value = "uitverkocht", defaultValue = "false", required = true) String uitverkocht,
			Model model) {
		model.addAttribute("fifaCommand", new FifaCommand());
		model.addAttribute("verkocht", verkocht);
		model.addAttribute("uitverkocht", uitverkocht);
		return "form";
	}

	@PostMapping("/fifa")
	public String onSubmit(@ModelAttribute FifaCommand fifaCommand, Model model) {
		stadium = fifaCommand.getSelectedStadium();
		return "result";
	}

	@PostMapping("/fifa/**")
	public String showTicketPage(@ModelAttribute FifaCommand fifaCommand, Model model) {
		id = fifaCommand.getSelectedMatch();
		match = voetbalService.getWedstrijd(id);
		model.addAttribute("match", match);
		model.addAttribute("stadium", stadium);
		if (voetbalService.getWedstrijd(id).getTickets() <= 0)
			return "redirect:/fifa?uitverkocht=true";
		return "ticket";
	}

	@PostMapping("/fifa/check")
	public String processRegistration(@Valid FifaCommand fifaCommand, BindingResult result, Model model) {
		model.addAttribute("match", match);
		model.addAttribute("stadium", stadium);
		fifaValidation.validate(fifaCommand, result);
		if (result.hasErrors())
			return "ticket";
		
		return String.format("redirect:/fifa?verkocht=%d", voetbalService.ticketsBestellen(id, Integer.parseInt(fifaCommand.getTicket())));
	}
	
}
