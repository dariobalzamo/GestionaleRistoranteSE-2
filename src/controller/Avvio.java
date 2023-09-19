package controller;

import java.util.*;
import model.*;
import service.DaoOrdinazione;
import service.DaoPiatto;
import service.DaoTavolo;
import service.Utility;
import view.GestoreIO;

public class Avvio {
	public static void main(String[] args) {

		// ############## VARIABILI PROGRAMMA ################

		GestoreIO io = GestoreIO.get_instance();
		DaoPiatto dao_piatto = DaoPiatto.get_instance();
		DaoTavolo dao_tavolo = DaoTavolo.get_instance();
		DaoOrdinazione dao_ordine = DaoOrdinazione.getInstance();
		Utility utility = Utility.getInstance();
		Piatto piatto;
		Tavolo tavolo;
		Conto conto;
		Prenotazione prenotazione;
		int scelta, scelta_2, scelta_3, id_piatto, id_tavolo, n_posti;
		String risposta;
		List<Piatto> piatti;
		List<Tavolo> tavoli;

		// ################# BUSINESS LOGIC #################

		do {
			io.gui();
			scelta = io.leggiIntero(">> ");
			switch (scelta) {
			case 1: /* ###### GESTIONALE RISTORATORE ###### */
				do {
					io.gui_ristoratore();
					scelta_2 = io.leggiIntero(">> ");
					switch (scelta_2) {
					/* ################# PIATTI ################# */
					case 1: // INSERIMENTO PIATTO
						piatto = new Piatto();
						io.form(piatto);
						dao_piatto.add_piatto(piatto);
						io.stampaMessaggio("Inserimento avvenuto con successo !");
						break;
					case 2: // MODIFICA PIATTO
						id_piatto = io.leggiIntero("ID: ");
						piatto = dao_piatto.get_piatto(id_piatto);
						io.form(piatto);
						dao_piatto.update_piatto(piatto);
						io.stampaMessaggio("Modifica avvenuta con successo !");
						break;
					case 3:// CERCA PIATTO
						id_piatto = io.leggiIntero("ID: ");
						piatto = dao_piatto.get_piatto(id_piatto);
						if (piatto != null)
							io.scheda(piatto);
						else
							io.stampaMessaggio("Piatto non trovato.");
						break;
					case 4: // VISUALIZZAZIONE MENU PIATTI
						piatti = dao_piatto.get_piatti();
						io.menu_piatti(piatti);
						break;
					case 5: // ELIMINAZIONE PIATTO
						id_piatto = io.leggiIntero("ID: ");
						piatto = dao_piatto.get_piatto(id_piatto);
						if (piatto != null) {
							dao_piatto.delete_piatto(id_piatto);
							io.stampaMessaggio("Eliminazione piatto avvenuta con successo !");
						} else {
							io.stampaMessaggio("Piatto non trovato.");
						}
						break;

					/* ################# TAVOLI ################# */

					case 6: // INSERIMENTO TAVOLO
						tavolo = new Tavolo();
						io.form(tavolo);
						dao_tavolo.add_tavolo(tavolo);
						io.stampaMessaggio("Inserimento avvenuto con successo !");
						break;
					case 7: // MODIFICA TAVOLO
						id_tavolo = io.leggiIntero("ID: ");
						tavolo = dao_tavolo.get_tavolo(id_tavolo);
						io.form(tavolo);
						dao_tavolo.update_tavolo(tavolo);
						io.stampaMessaggio("Modifica avvenuta con successo !");
						break;
					case 8: // CERCA TAVOLO
						id_tavolo = io.leggiIntero("ID: ");
						tavolo = dao_tavolo.get_tavolo(id_tavolo);
						if (tavolo != null) {
							io.scheda(tavolo);
						} else {
							io.stampaMessaggio("Tavolo non trovato.");
						}
						break;
					case 9: // VISUALIZZAZIONE TAVOLI
						tavoli = dao_tavolo.get_tavoli();
						io.overview_tavoli(tavoli);
						break;
					case 10: // ELIMINA TAVOLO
						id_tavolo = io.leggiIntero("ID: ");
						tavolo = dao_tavolo.get_tavolo(id_tavolo);
						if (tavolo != null) {
							dao_tavolo.delete_tavolo(id_tavolo);
							io.stampaMessaggio("Eliminazione tavolo avvenuto con successo.");
						} else {
							io.stampaMessaggio("Tavolo non trovato.");
						}
						break;
					default:
						if (scelta_2 != 0)
							io.stampaMessaggio("Inserire un'opzione valida.");
					}
				}while(scelta_2 != 0);
				break;
			case 2: /* ###### OPERAZIONI CLIENTE ###### */

				do {
					io.gui_cliente();
					scelta_3 = io.leggiIntero(">> ");
					switch (scelta_3) {
					case 1: // PRENOTAZIONE TAVOLO --> CODICE PRENOTAZIONE UNIVOCA 
						n_posti = io.leggiIntero("Numero persone da prenotare: ");
						tavoli = dao_tavolo.get_tavoli();
						// Assegnazione automatica del tavolo, in base al numero di persone e di tavoli disponibili nel ristorante
						id_tavolo = utility.assegnaTavolo(tavoli, n_posti); 
						if (id_tavolo != 0) {
							tavolo = dao_tavolo.get_tavolo(id_tavolo);
							utility.cambioOccupazione(tavolo); // Modifica in tavolo occupato a True
							dao_tavolo.update_tavolo(tavolo);
							dao_ordine.creaPrenotazione(id_tavolo); // Creazione prenotazione tavolo.
							io.stampaMessaggio("Prenotazione avvenuta con successo !");
							io.stampaMessaggio("Il tuo tavolo è il numero " + id_tavolo);
						} else {
							io.stampaMessaggio(
									"Attualmente non ci sono posti disponibili. La preghiamo di attendere, grazie...");
						}
						break;
					case 2: // ORDINAZIONE PIATTI AL TAVOLO 
						id_tavolo = io.leggiIntero("Numero tavolo: ");
						prenotazione = dao_ordine.getPrenotazione(id_tavolo);
						if (prenotazione != null) {
							piatti = dao_piatto.get_piatti();
							do {
								io.menu_piatti(piatti); // visualizzo il menu al tavolo per ordinare un piatto
								id_piatto = io.leggiIntero("ID piatto da ordinare: ");
								dao_ordine.insertOrder(prenotazione, id_piatto);
								io.stampaMessaggio("Ordinazione inviata in cucina.");
								risposta = io.leggiStringa("Vuoi ordinare ancora ? (SI/NO) ");
							} while (risposta.toLowerCase().equals("si"));
						}else
							io.stampaMessaggio("Non esiste alcuna prenotazione per questo tavolo.");
						break;
					case 3: // PAGAMENTO CONTO AL POS
						id_tavolo = io.leggiIntero("Numero tavolo: ");
						prenotazione = dao_ordine.getPrenotazione(id_tavolo);
						if (prenotazione != null) {
							conto = dao_ordine.visualizzaConto(prenotazione.getId());
							io.scontrino(conto);
							risposta = io.leggiStringa("Vuoi pagare ? (SI/NO) ");
							if(risposta.toUpperCase().equals("SI")) {
								tavolo = dao_tavolo.get_tavolo(id_tavolo);
								utility.cambioOccupazione(tavolo);
								dao_tavolo.update_tavolo(tavolo);
								dao_ordine.updatePrenotazione(id_tavolo);
								io.stampaMessaggio("Pagamento eseguito con successo !");
							} else
								io.stampaMessaggio("Operazione annullata.");
						}else
							io.stampaMessaggio("Non esiste alcuna prenotazione per questo tavolo.");
						break;
					default:
						if (scelta_3 != 0)
							io.stampaMessaggio("Inserire un'opzione valida.");
					}
				} while (scelta_3 != 0);
			default:
				if (scelta != 0)
					io.stampaMessaggio("Inserire un'opzione valida.");
			}
		} while (scelta != 0);
		io.stampaMessaggio("BUONA GIORNATA, ALLA PROSSIMA !!");
	}
}