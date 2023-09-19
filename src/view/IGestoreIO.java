package view;

import java.util.List;

import model.*;

public interface IGestoreIO {

	public String leggiStringa(String messaggio);
	public int leggiIntero(String messaggio);
	public double leggiDecimale(String messaggio);
	public boolean leggiBooleano(String messaggio);
	public void menu_piatti(List<Piatto> piatti);
	public void overview_tavoli(List<Tavolo> tavoli);
	public void scheda(Piatto p);
	public void scheda(Tavolo t);
	public void gui();
	public void gui_ristoratore();
	public void gui_cliente();
	public void form(Object o);
	public void stampaMessaggio(String messaggio);
}


