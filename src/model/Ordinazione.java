package model;

import java.util.Objects;

public class Ordinazione {

	// ATTRIBUTI

	private int id;
	private int id_prenotazione;
	private int id_piatto;

	// GETTER SETTER

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_prenotazione() {
		return id_prenotazione;
	}

	public void setId_prenotazione(int id_prenotazione) {
		this.id_prenotazione = id_prenotazione;
	}

	public int getId_piatto() {
		return id_piatto;
	}

	public void setId_piatto(int id_piatto) {
		this.id_piatto = id_piatto;
	}

	@Override
	public String toString() {
		return "Ordinazione [id=" + id + ", id_prenotazione=" + id_prenotazione + ", id_piatto=" + id_piatto + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, id_piatto, id_prenotazione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordinazione other = (Ordinazione) obj;
		return id == other.id && id_piatto == other.id_piatto && id_prenotazione == other.id_prenotazione;
	}

	public Ordinazione(int id, int id_prenotazione, int id_piatto) {
		super();
		this.id = id;
		this.id_prenotazione = id_prenotazione;
		this.id_piatto = id_piatto;
	}

	public Ordinazione() {
	}

}