package br.com.planejamentoagro.model;

import java.io.Serializable;

import br.com.planejamentoagro.inteface.EntidadePersistivel;

public class Informacoes implements Serializable, EntidadePersistivel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022529434091468289L;
	private int id;
	private int idTalhao;
	private String dataVisita;
	private String informacoes;
	public Informacoes(int id, int idTalhao, String dataVisita, String informacoes) {
		this.id = id;
		this.idTalhao = idTalhao;
		this.dataVisita = dataVisita;
		this.informacoes = informacoes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTalhao() {
		return idTalhao;
	}
	public void setIdTalhao(int idTalhao) {
		this.idTalhao = idTalhao;
	}
	public String getDataVisita() {
		return dataVisita;
	}
	public void setDataVisita(String dataVisita) {
		this.dataVisita = dataVisita;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
}
