package com.miner.entity;

import java.util.List;

public class Jogador {

	private String nome;
	private List<Game> listGame;
	private String pontos;
	private Integer primeiroSet;
	private Integer segundoSet;
	private Integer terceiroSet;

	public Integer getSegundoSet() {
		return segundoSet;
	}

	public void setSegundoSet(Integer segundoSet) {
		this.segundoSet = segundoSet;
	}

	public Integer getTerceiroSet() {
		return terceiroSet;
	}

	public void setTerceiroSet(Integer terceiroSet) {
		this.terceiroSet = terceiroSet;
	}

	public Integer getPrimeiroSet() {
		return primeiroSet;
	}

	public void setPrimeiroSet(Integer primeiroSet) {
		this.primeiroSet = primeiroSet;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Game> getListGame() {
		return listGame;
	}

	public void setListGame(List<Game> listGame) {
		this.listGame = listGame;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}
	
}
