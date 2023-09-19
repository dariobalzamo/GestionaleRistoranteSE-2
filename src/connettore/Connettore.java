package connettore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connettore {
	
	private static Connettore instance = null;    // Unica instanza della classe 
	private Connection conn = null;
	
	// CREDENZIALI DBMS
	private final String PATHJDBC = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/ristorante_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String USER = "root";
	private final String PASS = "";
	
	public static Connettore get_instance() {
		if(instance == null)
			instance = new Connettore();
		return instance; 
	}
	
	private Connettore() 
	{
		
	}


	public Connection apri_connessione() 
	{
		try 
		{
			Class.forName(PATHJDBC);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} 
		catch (ClassNotFoundException e) // Se sbaglio il path del Driver 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) // Se sbaglio le credenziali 
		{
			e.printStackTrace();
		}
		return conn;
	}
}
