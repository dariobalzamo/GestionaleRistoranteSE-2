package service;

import java.util.ArrayList;
import java.util.List;

import model.Conto;
import model.Ordinazione;
import model.Tavolo;

public final class Utility {

	private static Utility instance = null;

	public static Utility getInstance() {
		if (instance == null)
			instance = new Utility();
		return instance;
	}

	private Utility() {
	}

	
	// METODI DI UTILITY
	
	public int assegnaTavolo(List<Tavolo> tavoli, int numPersone) {
		int i, j, posti, numeroTavolo = 0;
		Tavolo tavolo;	
		
		for(i = 0; i <= 2; i++ ) {
			posti = numPersone+i;
			for (j = 0; j < tavoli.size(); j++) {
				tavolo = tavoli.get(j);
				if( !(tavolo.isOccupato()) && tavolo.getNumeroPosti() == posti)
					return numeroTavolo = tavolo.getId();
			}
		}
		return numeroTavolo;
	}
	
	
	public void cambioOccupazione(Tavolo tavolo) {
		
		if(tavolo.isOccupato())
			tavolo.setOccupato(false);
		else
			tavolo.setOccupato(true);
	}
	
}