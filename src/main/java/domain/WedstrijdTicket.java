package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Aantal tickets beschikbaar per wedstrijd
@Entity
@Table(name = "wedstrijden")
@NamedQueries({
		@NamedQuery(name = "WedstrijdTicket.getTicketsById", query = "select wt.tickets from WedstrijdTicket wt where wt.wedstrijd.id = :id"),
		@NamedQuery(name = "WedstrijdTicket.getWedstrijdTicketById", query = "select wt from WedstrijdTicket wt where wt.wedstrijd.id = :id")
})
public class WedstrijdTicket implements Serializable {
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Wedstrijd wedstrijd;
	private int tickets; // aantal tickets beschikbaar
	private final int DEFAULT_TICKETS = 1;
	private final int DEFAULT_CODE1 = 10;
	private final int DEFAULT_CODE2 = 20;

	public WedstrijdTicket(Wedstrijd wedstrijd, int tickets) {
		this.wedstrijd = wedstrijd;
		this.tickets = tickets;
	}

	protected WedstrijdTicket() {
	}
	
	public int getDEFAULT_TICKETS() {
		return DEFAULT_TICKETS;
	}

	public int getDEFAULT_CODE1() {
		return DEFAULT_CODE1;
	}

	public int getDEFAULT_CODE2() {
		return DEFAULT_CODE2;
	}

	public int getTickets() {
		return tickets;
	}
	
	public int getId() {
		return Integer.parseInt(wedstrijd.getId());
	}

	public Wedstrijd getWedstrijd() {
		return wedstrijd;
	}

	// We willen 'aantal' tickets kopen
	public int ticketsKopen(int aantal) {
		if (aantal <= 0) {
			return -1;
		}

		// Nog voldoende tickets
		if (tickets >= aantal) {
			tickets -= aantal;
			return aantal;
		}

		// Niet meer voldoende tickets
		int gekocht = tickets;
		tickets = 0;
		return gekocht;
	}

	public boolean uitverkocht() {
		return tickets == 0;
	}
}
