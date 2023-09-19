package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connettore.Connettore;
import model.Conto;
import model.Prenotazione;

public final class DaoOrdinazione implements IDaoOrdinazione {

	private static DaoOrdinazione instance = null;

	public static DaoOrdinazione getInstance() {
		if (instance == null)
			instance = new DaoOrdinazione();
		return instance;
	}

	private DaoOrdinazione() {
	}

	// METODI CRUD PRENOTAZIONE 
	
	@Override
	public void creaPrenotazione(int id_tavolo) {
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione();  
		String sql = "insert into prenotazioni(id_tavolo, pagato) values (?, ?)"; 
		try {
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, id_tavolo);
			ps.setBoolean(2, false);
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
	public Prenotazione getPrenotazione(int id_tavolo) {
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione();  
		ResultSet rs = null;
		Prenotazione prenotazione = null;
		
		String sql = "Select * from prenotazioni where id_tavolo = ? AND pagato = ?"; 
		try {
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, id_tavolo);
			ps.setBoolean(2, false);
			rs = ps.executeQuery();
			if(rs.next()) {
				prenotazione = new Prenotazione();
				prenotazione.setId(rs.getInt("id"));
				prenotazione.setId_tavolo(rs.getInt("id_tavolo"));
				prenotazione.setPagato(rs.getBoolean("pagato"));
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
		return prenotazione;
	}
	
	public void updatePrenotazione(int id_tavolo){
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione(); 
		String sql = "update prenotazioni set pagato = ? where id_tavolo = ?"; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, id_tavolo);
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

	// METODI CRUD ORDINAZIONE PIATTI
	
	@Override
	public void insertOrder(Prenotazione p, int id_piatto) {
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione();  
		String sql = "insert into ordinazioni(id_prenotazione, id_piatto) values (?, ?)"; 
		try {
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, p.getId());
			ps.setInt(2, id_piatto);
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

	// SCONTRINO ==> CONTO
	
	@Override
	public Conto visualizzaConto(int id_prenotazione) {
		PreparedStatement ps = null;
		Connettore c = Connettore.get_instance(); 
		Connection conn = c.apri_connessione();  
		ResultSet rs = null;
		Conto conto= new Conto();
		boolean flag = true;
		double totale=0;
		List<String> piatti = new ArrayList<String>(); 
		List<Double> prezzi = new ArrayList<Double>();
		List<Integer> quantita = new ArrayList<Integer>();
		
		String sql = "SELECT *,COUNT(piatto) AS quantita,SUM(prezzo) as totale from v_conto where  v_conto.numero_prenotazione = ? and pagato = false GROUP BY piatto"; 
		try {
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, id_prenotazione);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(flag) {
					conto.setNumero_prenotazione(rs.getInt("numero_prenotazione"));
					conto.setNumero_tavolo(rs.getInt("numero_tavolo"));
					conto.setPagato(rs.getBoolean("pagato"));
					flag = false;
				}
				piatti.add(rs.getString("piatto"));
				quantita.add(rs.getInt("quantita"));
				prezzi.add(rs.getDouble("prezzo"));
				totale+=rs.getDouble("totale");
			}
			conto.setPiatti(piatti);
			conto.setPrezzi(prezzi);
			conto.setQuantita(quantita);
			conto.setTotale(totale);
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
		return conto;
	}

}