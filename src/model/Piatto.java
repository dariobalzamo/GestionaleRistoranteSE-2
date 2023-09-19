package model;

import java.util.Objects;

public class Piatto {
	
	// ATTRIBUTI 
	private int id;
	private String nome; 
	private double prezzo;

	
	// GETTER E SETTER
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public double getPrezzo() 
	{
		return prezzo;
	}

	public void setPrezzo(double prezzo) 
	{
		this.prezzo = prezzo;
	} 
	
	@Override
	public String toString() 
	{
		return "   " + id + "   " + nome + "   " + prezzo + " €"; 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nome, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piatto other = (Piatto) obj;
		return id == other.id && Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo);
	}

	public Piatto() 
	{
		
	}
	
	public Piatto(int id, String nome, double prezzo) {
		this.id = id;
		this.nome = nome; 
		this.prezzo = prezzo;
	}
}