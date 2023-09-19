package model;

import java.util.Objects;

public class Tavolo {

	// ATTRIBUTI
	private int id;
	private int numeroPosti;
	private boolean occupato;

	// GETTER SETTER
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public Tavolo() {

	}

	public Tavolo(int id, int numeroPosti, boolean occupato) {
		this.id = id;
		this.numeroPosti = numeroPosti;
		this.occupato = occupato;
	}

	@Override
	public String toString() {
		return "   " + id + "      " + numeroPosti + "            " + occupato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numeroPosti, occupato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		return id == other.id && numeroPosti == other.numeroPosti && occupato == other.occupato;
	}	
}