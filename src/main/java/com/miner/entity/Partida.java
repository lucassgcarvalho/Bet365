package com.miner.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


public class Partida {

	private String 			chavePartida;
	private String 			campeonato;
	private Jogador 		jogador;
	private Jogador 		jogadorAdversario;
	private Genero 			genero;
	private Integer			quantidadeEmpates;
	private boolean 		partidaEmpatada;
	private boolean 		sequenciaEmpates;
	private Integer 		quantidadeSequenciaVitorias;
	private boolean 		partidaEmAndamento;
	private boolean 		partidaGanha;
	private boolean 		partidaBloqueada;
	private int 			numeroPartida;
	private int 			numeroPartidaAnterior;
	private BigDecimal 		valorAposta;
	private BigDecimal 		valorRetornoAposta;
	private BigDecimal 		lucro;
	private BigDecimal 		valorOdds;
	private Partida  		partidaAnterior;
	private int				numeroPartidaFinalizada;
	private Integer			numeroPartidaEmpatada;
	private String			HorarioPartida;
	private boolean 		partidaEmpatadaProcessada;
	private boolean         partidaInadequada;
	private Map<Integer, Partida> 	mapPartidasExecutadas;
	private ArrayList<BigDecimal>	listValoresPartidasEmpatadas;   
	
	
	public BigDecimal getValorOdds() {
		return valorOdds;
	}
	public void setValorOdds(BigDecimal valorOdds) {
		this.valorOdds = valorOdds;
	}
	public BigDecimal getLucro() {
		return lucro;
	}
	public void setLucro(BigDecimal Lucro) {
		this.lucro = Lucro;
	}
	public ArrayList<BigDecimal> getListValoresPartidasEmpatadas() {
		return listValoresPartidasEmpatadas;
	}
	public void setListValoresPartidasEmpatadas(
			ArrayList<BigDecimal> listValoresPartidasEmpatadas) {
		this.listValoresPartidasEmpatadas = listValoresPartidasEmpatadas;
	}
	public Integer getNumeroPartidaEmpatada() {
		return numeroPartidaEmpatada;
	}
	public void setNumeroPartidaEmpatada(Integer numeroPartidaEmpatada) {
		this.numeroPartidaEmpatada = numeroPartidaEmpatada;
	}
	public boolean isPartidaInadequada() {
		return partidaInadequada;
	}
	public void setPartidaInadequada(boolean partidaInadequada) {
		this.partidaInadequada = partidaInadequada;
	}
	public Integer getQuantidadeSequenciaVitorias() {
		return quantidadeSequenciaVitorias;
	}
	public void setQuantidadeSequenciaVitorias(Integer quantidadeSequenciaVitorias) {
		this.quantidadeSequenciaVitorias = quantidadeSequenciaVitorias;
	}
	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}
	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}
	public boolean isPartidaEmpatadaProcessada() {
		return partidaEmpatadaProcessada;
	}
	public void setPartidaEmpatadaProcessada(boolean partidaEmpatadaProcessada) {
		this.partidaEmpatadaProcessada = partidaEmpatadaProcessada;
	}
	public void setNumeroPartidaFinalizada(int numeroPartidaFinalizada) {
		this.numeroPartidaFinalizada = numeroPartidaFinalizada;
	}
	public String getHorarioPartida() {
		return HorarioPartida;
	}
	public void setHorarioPartida(String horarioPartida) {
		HorarioPartida = horarioPartida;
	}
	public String getChavePartida() {
		return chavePartida;
	}
	public void setChavePartida(String chavePartida) {
		this.chavePartida = chavePartida;
	}
	public String getCampeonato() {
		return campeonato;
	}
	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Jogador getJogadorAdversario() {
		return jogadorAdversario;
	}
	public void setJogadorAdversario(Jogador jogadorAdversario) {
		this.jogadorAdversario = jogadorAdversario;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Integer getQuantidadeEmpates() {
		return quantidadeEmpates;
	}
	public void setQuantidadeEmpates(Integer quantidadeEmpates) {
		this.quantidadeEmpates = quantidadeEmpates;
	}
	public boolean isPartidaEmpatada() {
		return partidaEmpatada;
	}
	public void setPartidaEmpatada(boolean partidaEmpatada) {
		this.partidaEmpatada = partidaEmpatada;
	}
	public boolean isSequenciaEmpates() {
		return sequenciaEmpates;
	}
	public void setSequenciaEmpates(boolean sequenciaEmpates) {
		this.sequenciaEmpates = sequenciaEmpates;
	}
	public boolean isPartidaGanha() {
		return partidaGanha;
	}
	public void setPartidaGanha(boolean partidaGanha) {
		this.partidaGanha = partidaGanha;
	}
	public boolean isPartidaBloqueada() {
		return partidaBloqueada;
	}
	public void setPartidaBloqueada(boolean partidaBloqueada) {
		this.partidaBloqueada = partidaBloqueada;
	}
	public int getNumeroPartida() {
		return numeroPartida;
	}
	public void setNumeroPartida(int numeroPartida) {
		this.numeroPartida = numeroPartida;
	}
	public int getNumeroPartidaAnterior() {
		return numeroPartidaAnterior;
	}
	public void setNumeroPartidaAnterior(int numeroPartidaAnterior) {
		this.numeroPartidaAnterior = numeroPartidaAnterior;
	}
	public BigDecimal getValorAposta() {
		return valorAposta;
	}
	public void setValorAposta(BigDecimal valorAposta) {
		this.valorAposta = valorAposta;
	}
	public BigDecimal getValorRetornoAposta() {
		return valorRetornoAposta;
	}
	public void setValorRetornoAposta(BigDecimal valorRetornoAposta) {
		this.valorRetornoAposta = valorRetornoAposta;
	}
	public Partida getPartidaAnterior() {
		return partidaAnterior;
	}
	public void setPartidaAnterior(Partida partidaAnterior) {
		this.partidaAnterior = partidaAnterior;
	}
	public int getNumeroPartidaFinalizada() {
		return numeroPartidaFinalizada;
	}
	public void setPartidaFinalizada(int numeroPartidaFinalizada) {
		this.numeroPartidaFinalizada = numeroPartidaFinalizada;
	}
	public Map<Integer, Partida> getMapPartidasExecutadas() {
		return mapPartidasExecutadas;
	}
	public void setMapPartidasExecutadas(Map<Integer, Partida> mapPartidasExecutadas) {
		this.mapPartidasExecutadas = mapPartidasExecutadas;
	}
	

}
