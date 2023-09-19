package service;

import java.util.List;
import model.Tavolo;

public interface IDaoTavolo {
	
	// 1) Create: Insert 
	public void add_tavolo(Tavolo t);
		
	// 2) Read one
	public Tavolo get_tavolo(int id);
		
	// 2.1) Read all
	public List<Tavolo> get_tavoli();
		
	// 3) Update
	public void update_tavolo(Tavolo t);
		
	// 4) Delete
	public void delete_tavolo(int id);
	
	public void cambiaOccupazione(int numeroTavolo, boolean valore);
}
