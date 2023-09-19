package service;

import model.Prenotazione;
import model.Conto;

public interface IDaoOrdinazione {

	// ######### PRENOTAZIONE ##########	
	
	// CREATE PRENOTAZIONE TAVOLO
	public void creaPrenotazione(int id_tavolo);
	
	// READ ONE PRENOTAZIONE
	public Prenotazione getPrenotazione(int id_tavolo);

	// UPDATE PRENOTAZIONE
	public void updatePrenotazione(int id_tavolo);
		
	// ######### ORDINE ##########	
		
	// CREATE ORDINAZIONE
	public void insertOrder(Prenotazione p, int id_piatto);
	
	// READ CONTO	
	public Conto visualizzaConto(int id_tavolo);
	
	
}
