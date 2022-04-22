package com.springBoot.Fifa;

public class FifaCommand {
	private String selectedStadium;
	private String selectedMatch;
	private String email;
	private String ticket;
	private String voetbalCode1;
	private String voetbalCode2;

	public String getSelectedStadium() {
		return selectedStadium;
	}

	public void setSelectedStadium(String selectedStadium) {
		this.selectedStadium = selectedStadium;
	}
	
	public String getSelectedMatch() {
		return selectedMatch;
	}
	
	public void setSelectedMatch(String selectedMatch) {
		this.selectedMatch = selectedMatch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getVoetbalCode1() {
		return voetbalCode1;
	}

	public void setVoetbalCode1(String voetbalCode1) {
		this.voetbalCode1 = voetbalCode1;
	}

	public String getVoetbalCode2() {
		return voetbalCode2;
	}

	public void setVoetbalCode2(String voetbalCode2) {
		this.voetbalCode2 = voetbalCode2;
	}
	
}
