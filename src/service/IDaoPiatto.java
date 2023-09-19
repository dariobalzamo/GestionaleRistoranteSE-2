package service;

import java.util.List;
import model.*;

public interface IDaoPiatto{

	// 1) Create: Insert 
	public void add_piatto(Piatto p);
	
	// 2) Read one
	public Piatto get_piatto(int id);
	
	// 2.1) Read all
	public List<Piatto> get_piatti();
	
	// 3) Update
	public void update_piatto(Piatto p);
	
	// 4) Delete
	public void delete_piatto(int id);
	
}
