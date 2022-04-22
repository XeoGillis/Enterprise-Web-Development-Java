package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stadiums")
@NamedQueries({ @NamedQuery(name = "Stadium.findAll", query = "select s.naam from Stadium s order by s.naam asc"),
	@NamedQuery(name = "Stadium.findByName", query = "select s.wedstrijdTickets from Stadium s where s.naam = :name")})
public class Stadium {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naam;
	@OneToMany(orphanRemoval = true)
	private List<WedstrijdTicket> wedstrijdTickets;

	protected Stadium() {
	}

	public Stadium(String naam, List<WedstrijdTicket> wedstrijdTickets) {
		this.naam = naam;
		this.wedstrijdTickets = wedstrijdTickets;
	}
}
