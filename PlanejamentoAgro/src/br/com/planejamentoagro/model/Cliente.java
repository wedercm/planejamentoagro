package br.com.planejamentoagro.model;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.planejamentoagro.inteface.EntidadePersistivel;

public class Cliente implements Serializable, EntidadePersistivel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String nomeFazenda;
	private String municipio;
	private ArrayList<Talhao> arrayTalhao = new ArrayList<Talhao>(); 
	public Cliente()
	{
//		this.nome = "";
//		this.nomeFazenda = "";
//		this.municipio = "";
	}
	public Cliente(String nome, String nomeFazenda, String municipio){
		this.nome = nome;
		this.nomeFazenda = nomeFazenda;
		this.municipio = municipio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome()
	{
		return this.nome;
	}
	public String getNomeFazenda()
	{
		return this.nomeFazenda;
	}

	public String getMunicipio() {
		return municipio;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setnomeFazenda(String nomeFazenda)
	{
		this.nomeFazenda = nomeFazenda;
	}
	public void setmunicipio(String municipio) {
		this.municipio = municipio;
	}
	public void addTalhao(Talhao talhao){
		arrayTalhao.add(talhao);
	}
	public ArrayList<Talhao> getTalhoes()
	{
		return this.arrayTalhao;
	}
	public String getInformacoes()
	{
		return 	"\nNome: " + this.nome+
				"\nFazenda: " + this.nomeFazenda+
				"\nMunicípio: "+this.municipio;
	}
	
	@Override
	public String toString(){
		return this.nome;
	}
}
