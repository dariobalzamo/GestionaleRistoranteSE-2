package model;

import java.util.List;
import java.util.Objects;

public class Conto {
	
	// ATTTRIBUTI 
	
	private int numero_tavolo;
	private int numero_prenotazione;
	private double totale;
	private List<String> piatti; 
	private List<Double> prezzi;
	private List<Integer> quantita;
	private boolean pagato;
	
	// GETTER SETTER 
	
	public int getNumero_tavolo() {
		return numero_tavolo;
	}
	public void setNumero_tavolo(int numero_tavolo) {
		this.numero_tavolo = numero_tavolo;
	}
	public int getNumero_prenotazione() {
		return numero_prenotazione;
	}
	public void setNumero_prenotazione(int numero_prenotazione) {
		this.numero_prenotazione = numero_prenotazione;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public List<String> getPiatti() {
		return piatti;
	}
	public void setPiatti(List<String> piatti) {
		this.piatti = piatti;
	}
	public List<Double> getPrezzi() {
		return prezzi;
	}
	public void setPrezzi(List<Double> prezzo) {
		this.prezzi = prezzo;
	}
	public List<Integer> getQuantita() {
		return quantita;
	}
	public void setQuantita(List<Integer> quantita) {
		this.quantita = quantita;
	}
	public boolean isPagato() {
		return pagato;
	}
	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}
	
	
	@Override
	public String toString() {
		return "Conto [numero_tavolo=" + numero_tavolo + ", numero_prenotazione=" + numero_prenotazione + ", totale="
				+ totale + ", piatti=" + piatti + ", prezzi=" + prezzi + ", quantita=" + quantita + ", pagato=" + pagato
				+ "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(numero_prenotazione, numero_tavolo, pagato, piatti, prezzi, quantita, totale);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conto other = (Conto) obj;
		return numero_prenotazione == other.numero_prenotazione && numero_tavolo == other.numero_tavolo
				&& pagato == other.pagato && Objects.equals(piatti, other.piatti)
				&& Objects.equals(prezzi, other.prezzi) && Objects.equals(quantita, other.quantita)
				&& Double.doubleToLongBits(totale) == Double.doubleToLongBits(other.totale);
	}
	
	public Conto(int numero_tavolo, int numero_prenotazione, double totale, List<String> piatti, List<Double> prezzi,
			List<Integer> quantita, boolean pagato) {
		super();
		this.numero_tavolo = numero_tavolo;
		this.numero_prenotazione = numero_prenotazione;
		this.totale = totale;
		this.piatti = piatti;
		this.prezzi = prezzi;
		this.quantita = quantita;
		this.pagato = pagato;
	}
	
	public Conto() {
		
	}
	
}