package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connettore.Connettore;
import model.Piatto;

// DAO: Data Access Object -> oggetto con cui ci permette di accedere al database
public final class DaoPiatto implements IDaoPiatto {
	
	private static DaoPiatto instance = null;    // Unica instanza della classe 
	
	public static DaoPiatto get_instance() {
		if(instance == null)
			instance = new DaoPiatto();
		return instance; 
	}
	
	private DaoPiatto() {
		
	}

	
	// METODI CRUD
	@Override
	public void add_piatto(Piatto p) 
	{
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); // Mi prendo l'oggetto Connettore
		Connection conn = c.apri_connessione(); // Apro la connessione 
		String sql = "insert into piatti (nome, prezzo) values (?, ?)"; // scrivo il comando sql
		try {
			ps = conn.prepareStatement(sql); 
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPrezzo());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Piatto get_piatto(int id) {
		Piatto piatto = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // resultset recupera i dati dal database, motivo per il quale ha metodi getter con cui è possibile recuperare i dati di un'entità 
		Connettore c = Connettore.get_instance(); // Mi prendo l'oggetto Connettore
		Connection conn = c.apri_connessione(); // Apro la connessione 
		String sql = "Select * from piatti where id = ?"; // scrivo il comando sql
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery(); // Eseguo la Query sql e inizializzo la variabile ResultSet
			if (rs.next()) // next() è un metodo booleano, ritorna true se l'elemento è presente altrimenti false, rappresenta il cursore del prospetto della select
			{
				piatto = new Piatto(); 
				piatto.setId(rs.getInt("id"));
				piatto.setNome(rs.getString("nome"));
				piatto.setPrezzo(rs.getDouble("prezzo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return piatto;
	}

	@Override
	public List<Piatto> get_piatti() {
		List<Piatto> piatti = new ArrayList<Piatto>();
		Piatto piatto = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // resultset recupera i dati dal database, motivo per il quale ha metodi getter con cui è possibile recuperare i dati di un'entità 
		Connettore c = Connettore.get_instance(); // Mi prendo l'oggetto Connettore
		Connection conn = c.apri_connessione(); // Apro la connessione 
		String sql = "Select * from piatti"; // scrivo il comando sql
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); // Eseguo la Query sql e inizializzo la variabile ResultSet
			while(rs.next()) // il cursore next() man mano che si sposta da una riga all'altra ritorna true e mi estrapola i dati dal database
			{
				// Aogni iterazione creo un nuovo oggetto e lo popolo con i 
				//dati estrapolati dal database, infine lo aggiungo alla mia lista di piatti
				piatto = new Piatto();
				piatto.setId(rs.getInt("id"));
				piatto.setNome(rs.getString("nome"));
				piatto.setPrezzo(rs.getDouble("prezzo"));
				piatti.add(piatto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return piatti;
	}

	@Override
	public void update_piatto(Piatto p) {
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione(); 
		String sql = "update piatti set nome = ?, prezzo = ? where id = ?"; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPrezzo());
			ps.setInt(3, p.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete_piatto(int id) {
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione();  
		String sql = "delete from piatti where id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
