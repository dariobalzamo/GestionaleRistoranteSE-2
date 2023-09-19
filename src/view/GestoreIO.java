package view;

import java.util.List;
import java.util.Scanner;
import model.*;

public final class GestoreIO implements IGestoreIO{

	private static GestoreIO instance = null;   
	
	public static GestoreIO get_instance() {
		if(instance == null)
			instance = new GestoreIO();
		return instance; 
	}
	
	private GestoreIO() {
		
	}

	//########## FUNZIONALITA' DEL PROGRAMMA ############
	
	@Override
	public String leggiStringa(String messaggio) {
		Scanner input = new Scanner(System.in);
		System.out.print(messaggio);
		return input.nextLine();
	}

	@Override
	public int leggiIntero(String messaggio) {
		Scanner input = new Scanner(System.in);
		System.out.print(messaggio);
		return Integer.parseInt(input.nextLine());
	}

	public boolean leggiBooleano(String messaggio) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print(messaggio);
		return Boolean.parseBoolean(input.nextLine());
	}
	
	@Override
	public double leggiDecimale(String messaggio) {
		Scanner input = new Scanner(System.in);
		System.out.print(messaggio);
		return Double.parseDouble(input.nextLine());
	}
	
	@Override
	public void stampaMessaggio(String messaggio) {
		System.out.println(messaggio);
		
	}

	@Override
	public void menu_piatti(List<Piatto> piatti) 
	{
		int i;
		System.out.println("################## MENU PIATTI ##################");
		System.out.println("|  id  |   Piatto   |   Prezzo   |");
		for (i = 0; i < piatti.size(); i++) 
			System.out.println(piatti.get(i).toString());
	}
	@Override

	public void form(Object o) {
		if(o instanceof Piatto) {
			System.out.println("#### INSERIMENTO PIATTO ####");
			((Piatto)o).setNome(leggiStringa("Nome piatto: "));
			((Piatto)o).setPrezzo(leggiDecimale("Prezzo: "));
		} else if (o instanceof Tavolo) {
			System.out.println("#### INSERIMENTO TAVOLO ####");
			((Tavolo)o).setNumeroPosti(leggiIntero("Coperto: "));
			((Tavolo)o).setOccupato(leggiBooleano("Occupato: "));
		}		
	}

	@Override
	public void gui() {
		System.out.println("######################  RISTORANTE DA DARIO  ######################");
		System.out.println("1) RISTORATORE");
		System.out.println("2) OPERAZIONI CLIENTE");
		System.out.println("0) ESCI");
	}

	@Override
	public void gui_ristoratore() {
		System.out.println("######################  GESTIONE RISTORANTE  ######################");
		System.out.println("1) INSERIMENTO PIATTO");
		System.out.println("2) MODIFICA PIATTO");
		System.out.println("3) CERCA PIATTO");
		System.out.println("4) MENU PIATTI RISTORANTE");
		System.out.println("5) ELIMINA PIATTO");
		System.out.println("*******************************************************************");
		System.out.println("6) INSERIMENTO TAVOLO");
		System.out.println("7) MODIFICA TAVOLO");
		System.out.println("8) CERCA TAVOLO");
		System.out.println("9) VISUALIZZA TAVOLI");
		System.out.println("10) ELIMINA TAVOLO");
		System.out.println(" ");
		System.out.println("0) INDIETRO");
	}
	
	@Override
	public void gui_cliente() {
	System.out.println("######################  GESTIONE UTENTE  ######################");
	System.out.println("1) PRENOTAZIONE");
	System.out.println("2) ORDINAZIONE");
	System.out.println("3) PAGA IL CONTO ALLA CASSA");
	System.out.println("0) ESCI");
	}

	@Override
	public void scheda(Piatto p) {
		System.out.println("|  id  |  Piatto  |  Prezzo  |");
		System.out.println(p.toString());
	}

	@Override
	public void scheda(Tavolo t) {
		System.out.println("|  Id  |  N. coperti  |  Occupato  |");
		System.out.println(t.toString());
	}

	@Override
	public void overview_tavoli(List<Tavolo> tavoli) {
		int i;
		System.out.println("|  Id  |  N. coperti  |  Occupato  |");
		for(i = 0; i < tavoli.size(); i++)
			System.out.println(tavoli.get(i).toString());
	}

	public void scontrino(Conto conto) {
		System.out.println(conto.toString());
	}
	
}