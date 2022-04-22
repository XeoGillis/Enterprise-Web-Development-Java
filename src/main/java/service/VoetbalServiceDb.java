package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;

import domain.Stadium;
import domain.Wedstrijd;
import domain.WedstrijdTicket;

public class VoetbalServiceDb implements VoetbalService {
	private EntityManager em;

	public VoetbalServiceDb() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fifa");
		em = emf.createEntityManager();
		seed();
	}

	@Override
	public List<String> getStadiumList() {
		return em.createNamedQuery("Stadium.findAll", String.class).getResultList();
	}

	@Override
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium) {
		List<WedstrijdTicket> wt = em.createNamedQuery("Stadium.findByName", WedstrijdTicket.class).setParameter("name", stadium).getResultList();
		return wt.stream().sorted(Comparator.comparing(w -> w.getId())).collect(Collectors.toList());
	}

	@Override
	public WedstrijdTicket getWedstrijd(String id) {
		return em.createNamedQuery("WedstrijdTicket.getWedstrijdTicketById", WedstrijdTicket.class)
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public int ticketsBestellen(String id, int teBestellen) {
		WedstrijdTicket wt = getWedstrijd(id);
		em.getTransaction().begin();
		int aantal = wt.ticketsKopen(teBestellen);
		em.getTransaction().commit();
		return aantal;
	}

	private void seed() {
		List<WedstrijdTicket> alThumamaStadium = new ArrayList<>();
		alThumamaStadium.add(new WedstrijdTicket(new Wedstrijd("1", new String[] {"Senegal", "Nederland"}, 2111, 13), 18));
		alThumamaStadium.add(new WedstrijdTicket(new Wedstrijd("11", new String[] {"Spanje", "ICP 2"}, 2311, 19), 9));
		alThumamaStadium.add(new WedstrijdTicket(new Wedstrijd("18", new String[] {"Qatar", "Senegal"}, 2511, 16), 12));
		alThumamaStadium.add(new WedstrijdTicket(new Wedstrijd("26", new String[] {"België", "Marokko"}, 2711, 16), 14));
		alThumamaStadium.add(new WedstrijdTicket(new Wedstrijd("36", new String[] {"Iran", "USA"}, 2911, 22), 9));
		alThumamaStadium.add(new WedstrijdTicket(new Wedstrijd("42", new String[] {"Canada", "Marokko"}, 112, 18), 13));
		
		List<WedstrijdTicket> khalifaInternationalStadium = new ArrayList<>();
		khalifaInternationalStadium.add(new WedstrijdTicket(new Wedstrijd("2", new String[] {"Engeland", "Iran"}, 2111, 16), 13));
		khalifaInternationalStadium.add(new WedstrijdTicket(new Wedstrijd("10", new String[] {"Duitsland", "Japan"}, 2311, 16), 12));
		khalifaInternationalStadium.add(new WedstrijdTicket(new Wedstrijd("19", new String[] {"Nederland", "Ecuador"}, 2511, 19), 18));
		khalifaInternationalStadium.add(new WedstrijdTicket(new Wedstrijd("27", new String[] {"Kroatië", "Canada"}, 2711, 19), 13));
		khalifaInternationalStadium.add(new WedstrijdTicket(new Wedstrijd("33", new String[] {"Ecuador", "Senegal"}, 2911, 18), 14));
		khalifaInternationalStadium.add(new WedstrijdTicket(new Wedstrijd("43", new String[] {"Japan", "Spanje"}, 112, 22), 10));
		
		List<WedstrijdTicket> alBaytStadium = new ArrayList<>();
		alBaytStadium.add(new WedstrijdTicket(new Wedstrijd("3", new String[] {"Qatar", "Ecuador"}, 2111, 19), 12));
		alBaytStadium.add(new WedstrijdTicket(new Wedstrijd("9", new String[] {"Marokko", "Canada"}, 2311, 22), 13));
		alBaytStadium.add(new WedstrijdTicket(new Wedstrijd("20", new String[] {"Engeland", "USA"}, 2511, 22), 10));
		alBaytStadium.add(new WedstrijdTicket(new Wedstrijd("28", new String[] {"Spanje", "Duitsland"}, 2711, 22), 12));
		alBaytStadium.add(new WedstrijdTicket(new Wedstrijd("34", new String[] {"Nederland", "Qatar"}, 2911, 18), 16));
		alBaytStadium.add(new WedstrijdTicket(new Wedstrijd("44", new String[] {"ICP 2", "Duitsland"}, 112, 22), 11));
		
		List<WedstrijdTicket> ahmadBinAliStadium = new ArrayList<>();
		ahmadBinAliStadium.add(new WedstrijdTicket(new Wedstrijd("4", new String[] {"USA", "EUR"}, 2111, 22), 6));
		ahmadBinAliStadium.add(new WedstrijdTicket(new Wedstrijd("12", new String[] {"België", "Nederland"}, 2111, 13), 18));
		ahmadBinAliStadium.add(new WedstrijdTicket(new Wedstrijd("17", new String[] {"EUR", "Iran"}, 2511, 13), 9));
		ahmadBinAliStadium.add(new WedstrijdTicket(new Wedstrijd("25", new String[] {"Japan", "ICP 2"}, 2711, 13), 9));
		ahmadBinAliStadium.add(new WedstrijdTicket(new Wedstrijd("35", new String[] {"EUR", "Engeland"}, 2911, 22), 10));
		ahmadBinAliStadium.add(new WedstrijdTicket(new Wedstrijd("41", new String[] {"Kroatië", "België"}, 112, 18), 14));
		
		List<WedstrijdTicket> lusailStadium = new ArrayList<>();
		lusailStadium.add(new WedstrijdTicket(new Wedstrijd("5", new String[] {"Argentinië", "Saoedi-Arabië"}, 2211, 13), 20));
		lusailStadium.add(new WedstrijdTicket(new Wedstrijd("16", new String[] {"Brazilië", "Servië"}, 2411, 22), 12));
		lusailStadium.add(new WedstrijdTicket(new Wedstrijd("24", new String[] {"Argentinië", "Mexico"}, 2611, 22), 15));
		lusailStadium.add(new WedstrijdTicket(new Wedstrijd("32", new String[] {"Portugal", "Urugay"}, 2811, 22), 15));
		lusailStadium.add(new WedstrijdTicket(new Wedstrijd("40", new String[] {"Saoedi-Arabië", "Mexico"}, 3011, 22), 17));
		lusailStadium.add(new WedstrijdTicket(new Wedstrijd("48", new String[] {"Kameroen", "Brazilië"}, 212, 22), 14));
		
		List<WedstrijdTicket> educationCityStadium = new ArrayList<>();
		educationCityStadium.add(new WedstrijdTicket(new Wedstrijd("6", new String[] {"Denemarken", "Tunesië"}, 2211, 16), 14));
		educationCityStadium.add(new WedstrijdTicket(new Wedstrijd("14", new String[] {"Urugay", "Korea"}, 2411, 16), 20));
		educationCityStadium.add(new WedstrijdTicket(new Wedstrijd("22", new String[] {"Poen", "Saoedi-Arabië"}, 2611, 16), 17));
		educationCityStadium.add(new WedstrijdTicket(new Wedstrijd("30", new String[] {"Korea", "Ghana"}, 2811, 16), 17));
		educationCityStadium.add(new WedstrijdTicket(new Wedstrijd("38", new String[] {"TUnesië", "Frankrijk"}, 3011, 18), 13));
		educationCityStadium.add(new WedstrijdTicket(new Wedstrijd("46", new String[] {"Korea", "Portugal"}, 212, 18), 21));
		
		List<WedstrijdTicket> stadium974 = new ArrayList<>();
		stadium974.add(new WedstrijdTicket(new Wedstrijd("7", new String[] {"Mexico", "Polen"}, 2211, 19), 12));
		stadium974.add(new WedstrijdTicket(new Wedstrijd("15", new String[] {"Portugal", "Ghana"}, 2411, 19), 13));
		stadium974.add(new WedstrijdTicket(new Wedstrijd("23", new String[] {"Frankrijk", "Denemarken"}, 2611, 19), 13));
		stadium974.add(new WedstrijdTicket(new Wedstrijd("31", new String[] {"Brazilië", "Zwitserland"}, 2811, 19), 17));
		stadium974.add(new WedstrijdTicket(new Wedstrijd("39", new String[] {"Polen", "Argenitinië"}, 3011, 22), 15));
		stadium974.add(new WedstrijdTicket(new Wedstrijd("47", new String[] {"Servië", "Zwitserland"}, 212, 22), 17));
		
		List<WedstrijdTicket> alJanoubStadium = new ArrayList<>();
		alJanoubStadium.add(new WedstrijdTicket(new Wedstrijd("8", new String[] {"Frankrijk", "ICP 1"}, 2211, 22), 10));
		alJanoubStadium.add(new WedstrijdTicket(new Wedstrijd("13", new String[] {"Zwitserland", "Kameroen"}, 2411, 13), 19));
		alJanoubStadium.add(new WedstrijdTicket(new Wedstrijd("21", new String[] {"Tunesië", "ICP 1"}, 2611, 13), 11));
		alJanoubStadium.add(new WedstrijdTicket(new Wedstrijd("29", new String[] {"Kameroen", "Servië"}, 2811, 13), 14));
		alJanoubStadium.add(new WedstrijdTicket(new Wedstrijd("37", new String[] {"ICP 1", "Denemarken"}, 3011, 18), 11));
		alJanoubStadium.add(new WedstrijdTicket(new Wedstrijd("45", new String[] {"Ghana", "Urugay"}, 212, 18), 12));
		
		em.getTransaction().begin();
		alThumamaStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Al Thumama Stadium", alThumamaStadium));
		khalifaInternationalStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Khalifa International Stadium", khalifaInternationalStadium));
		alBaytStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Al Bayt Stadium", alBaytStadium));
		ahmadBinAliStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Ahmad Bin Ali Stadium", ahmadBinAliStadium));
		lusailStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Lusail Stadium", lusailStadium));
		educationCityStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Education City Stadium", educationCityStadium));
		stadium974.forEach(s -> em.persist(s));
		em.persist(new Stadium("Stadium 974", stadium974));
		alJanoubStadium.forEach(s -> em.persist(s));
		em.persist(new Stadium("Al Janoub Stadium", alJanoubStadium));
		em.getTransaction().commit();
	}
}
