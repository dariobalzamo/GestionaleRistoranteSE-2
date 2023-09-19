package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connettore.Connettore;
import model.Tavolo;

public final class DaoTavolo implements IDaoTavolo {

	private static DaoTavolo instance = null; 
	
	public static DaoTavolo get_instance() 
	{
		if (instance == null)
			instance = new DaoTavolo();
		return instance;
	}
	
	private DaoTavolo(){
		
	}

	
	// METODI CRUD
	@Override
	public void add_tavolo(Tavolo t) 
	{
		Connettore c = Connettore.get_instance();
		Connection conn = c.apri_connessione();
		PreparedStatement ps = null;
		String sql = "Insert into tavoli(numeroPosti, occupato) values(?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getNumeroPosti());
			ps.setBoolean(2, t.isOccupato());
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
	public Tavolo get_tavolo(int id) 
	{
		Connettore c = Connettore.get_instance();
		Connection conn = c.apri_connessione();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Tavolo tavolo = null;
		String sql = "Select * from tavoli where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				tavolo = new Tavolo();
				tavolo.setId(rs.getInt("id"));
				tavolo.setNumeroPosti(rs.getInt("numeroPosti"));
				tavolo.setOccupato(rs.getBoolean("occupato"));
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
		return tavolo;
	}

	@Override
	public List<Tavolo> get_tavoli() 
	{
		Connettore c = Connettore.get_instance();
		Connection conn = c.apri_connessione();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Tavolo tavolo = null;
		List<Tavolo> tavoli = new ArrayList<Tavolo>();
		String sql = "Select * from tavoli";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				tavolo = new Tavolo();
				tavolo.setId(rs.getInt("id"));
				tavolo.setNumeroPosti(rs.getInt("numeroPosti"));
				tavolo.setOccupato(rs.getBoolean("occupato"));
				tavoli.add(tavolo);
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
		return tavoli;
	}

	@Override
	public void update_tavolo(Tavolo t) 
	{
		Connettore c = Connettore.get_instance();
		Connection conn = c.apri_connessione();
		PreparedStatement ps = null;
		String sql = "Update tavoli set numeroPosti = ?, occupato = ? where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getNumeroPosti());
			ps.setBoolean(2, t.isOccupato());
			ps.setInt(3, t.getId());
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
	public void delete_tavolo(int id) 
	{
		Connettore c = Connettore.get_instance();
		Connection conn = c.apri_connessione();
		PreparedStatement ps = null;
		String sql = "delete from tavoli where id = ?";
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
	
	@Override
	public void cambiaOccupazione(int numeroTavolo,boolean valore) {
		Connettore c = Connettore.get_instance();
		Connection conn = c.apri_connessione();
		PreparedStatement ps = null;
		
		String sql = "UPDATE tavoli SET occupato = ? where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, valore);
			ps.setInt(2, numeroTavolo);
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
