package domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

//Een wedstrijd
@Entity
@Embeddable
public class Wedstrijd implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id; // unieke sleutel
	private String[] landen; // 2 landen van de wedstrijd
	private int dag; // dag van de wedstrijd
	private int uur; // uur van de wedstrijd

	public Wedstrijd() {
	}

	public Wedstrijd(String id, String[] landen, int dag, int uur) {
		this.id = id;
		this.landen = landen;
		this.dag = dag;
		this.uur = uur;
	}

	public String getId() {
		return id;
	}

	public String[] getLanden() {
		return landen;
	}

	public String getDag() {
		String dagen = String.format("%d", dag);
		if (dagen.length() < 4)
			return String.format("%s-%s", dagen.substring(0, 1), dagen.substring(1));
		return String.format("%s-%s", dagen.subSequence(0, 2), dagen.substring(2));
	}

	public int getUur() {
		return uur;
	}

	@Override
	public String toString() {
		String dagen = String.format("%d", dag);
		if (dagen.length() < 4)
			return String.format("%s vs %s op %s-%s", landen[0], landen[1], dagen.substring(0, 1), dagen.substring(1));
		return String.format("%s vs %s op %s-%s", landen[0], landen[1], dagen.subSequence(0, 2), dagen.substring(2));
	}
}
