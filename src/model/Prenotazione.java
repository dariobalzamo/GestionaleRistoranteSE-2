package model;

import java.util.Objects;

public class Prenotazione {

	// ATTRIBUTI 
	
	private int id;
	private int id_tavolo;
	private boolean pagato;
	
	// GETTER SETTER	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_tavolo() {
		return id_tavolo;
	}

	public void setId_tavolo(int id_tavolo) {
		this.id_tavolo = id_tavolo;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	

	@Override
	public String toString() {
		return "Prenotazione [id=" + id + ", id_tavolo=" + id_tavolo + ", pagato=" + pagato + "]";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, id_tavolo, pagato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		return id == other.id && id_tavolo == other.id_tavolo && pagato == other.pagato;
	}

	
	public Prenotazione(int id, int id_tavolo, boolean pagato) {
		super();
		this.id = id;
		this.id_tavolo = id_tavolo;
		this.pagato = pagato;
	}

	public Prenotazione() {
	}
}