package com.miner.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.miner.Locomotive;
import com.miner.entity.Jogador;
import com.miner.entity.Partida;

public class MinerExecution_1412 {
	public static void main(String[] args) {
		Locomotive loco = new Locomotive();
		Map<String, Partida> mapGames = new HashMap<>();
		Map<String, Partida> mapGamesGanhos = new HashMap<>();
		Map<String, Partida> mapGamesPerdidos = new HashMap<>();
		Bot miner = new Bot();
		loco.click(By
				.cssSelector("html body.por form#frmMain div center div div ul.lpnm li a.lpdgl"));

		loco.click(By.xpath(Bot.XPATH_MENU_AO_VIVO));
		loco.click(By.xpath(Bot.XPATH_LINK_FUTEBOL));

		loco.click(By.xpath(Bot.XPATH_MENU_AO_VIVO_HM_BIG_BUTTON));
		// funcionando
		loco.click(By.xpath(Bot.XPATH_MENU_EVENTO));
		// Fecha Aba Futebol
		miner.fecharAba(Bot.FUTEBOL, loco);
		BigDecimal valorPote = new BigDecimal(90);
		while (true) {
			try {
				loco.driver.switchTo().defaultContent();
				// Fecha Aba Futebol
				miner.fecharAba(Bot.FUTEBOL, loco);
				WebElement abaPartida = loco.getWebElement(By.xpath(Bot.XPATH_LINK_TAB_TENIS));
				loco.scrollTo(abaPartida);
				// teste performance
				// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_LIST_PLAYER_NAME)));
				List<WebElement> listNamesPlayers = abaPartida.findElements(By.xpath(Bot.XPATH_LIST_PLAYER_NAME_UM));
				int size = listNamesPlayers.size();
				for (int indexNamesPlayers = 0; indexNamesPlayers < (size > 6 ? 6
						: size); indexNamesPlayers++) {
					WebElement player = listNamesPlayers.get(indexNamesPlayers);
					loco.scrollTo(player);
					// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(Miner.XPATH_GAME_BY_PLAYER,
					// player.getText().toString()))));
					List<WebElement> gameActual = abaPartida.findElements(By.xpath(String.format(Bot.XPATH_GAME_BY_PLAYER,player.getText().toString())));
					for (int indexGameAtual = 0; indexGameAtual < gameActual.size(); indexGameAtual++) {
						
						WebElement elementGameAtual = gameActual.get(indexGameAtual);
						WebElement campeonato = player.findElements(By.xpath("../../../../../*")).get(0);

						String nomeCampeonato = campeonato.getText();
						loco.scrollTo(elementGameAtual);
						elementGameAtual.click();
						String[] playersNamesActual = elementGameAtual.getText().split("\n");
						// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_PONTOS_PLAYERS)));
						String[] pontos = elementGameAtual.findElement(By.xpath(Bot.XPATH_PONTOS_PLAYERS)).getText().split("\n");
						WebElement gameEmExecucao = null;
						String stringNumeroJogoAtual = "";
						int numeroPartidafinalizada = 1;// se n�o houver,
														// poss�vel estar no
														// primeiro, validar
														// regra
						// System.out.println(gameEmExecucao.getText());
						try {
							// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\\\"ipe-SummaryButton_Label \\\"]")));
							gameEmExecucao = elementGameAtual.findElement(By.xpath("//div[@class=\"ipe-SummaryButton_Label \"]"));
							stringNumeroJogoAtual = gameEmExecucao.getText().split("-")[0].split("Jogo")[1];
							numeroPartidafinalizada = Integer.parseInt(loco
									.getOnlyNumbers(stringNumeroJogoAtual));
						} catch (Exception e) {
							// System.out.println("Erro - In�cio de jogo:"+playersNamesActual[0]+" VS "+playersNamesActual[1]);
							continue;
						}

						Integer numeroPartidaAtual = 0;
						try {
							loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_LINK_ABA_DEUCE)));
							List<WebElement> abaDeuce = loco.getWebElements(By.xpath(Bot.XPATH_LINK_ABA_DEUCE));
							WebElement deuce = abaDeuce.get(0);
							
							if (abaDeuce.size() > 1) {
								for (int i = 0; i < abaDeuce.size(); i++) {
									numeroPartidaAtual = Integer.parseInt(loco.getOnlyNumbers(abaDeuce.get(i).getText().substring(0, 2)));
									Integer numeroJogoDeuce = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));
									
									if (numeroPartidaAtual < numeroJogoDeuce) {
										deuce = abaDeuce.get(i);
									}
								}
							}

							loco.scrollTo(deuce);
							numeroPartidaAtual = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));

							String keyEntrySet = Bot.getEntrySetName(nomeCampeonato, playersNamesActual[0],playersNamesActual[1]);
							
							//cria nova estrutura
							if (!mapGames.containsKey(keyEntrySet)
									|| (mapGames.get(keyEntrySet) == null 
									|| mapGames.get(keyEntrySet).getNumeroPartidaFinalizada() != numeroPartidafinalizada)) {
								Partida partida = new Partida();
								partida.setCampeonato(nomeCampeonato);
								partida.setNumeroPartidaAnterior(new Integer(mapGames.get(keyEntrySet) == null ? numeroPartidaAtual: mapGames.get(keyEntrySet).getNumeroPartida()));
								partida.setNumeroPartida(numeroPartidaAtual);
								partida.setPartidaFinalizada(numeroPartidafinalizada);
								partida.setJogador(new Jogador());
								partida.getJogador().setNome(playersNamesActual[0]);
								partida.getJogador().setPontos(pontos[0]);
								partida.setJogadorAdversario(new Jogador());
								partida.getJogadorAdversario().setNome(playersNamesActual[1]);
								partida.getJogadorAdversario().setPontos(pontos[1]);
								partida.setPartidaAnterior(mapGames.get(keyEntrySet) == null ? null: mapGames.get(keyEntrySet));
								//partida.setQuantidadeEmpates(mapGames.get(keyEntrySet) == null ? 0: mapGames.get(keyEntrySet).getQuantidadeEmpates());//add 14/12 20:41
								mapGames.put(keyEntrySet, partida);
							} else {
								Partida partida = mapGames.get(keyEntrySet);
								partida.getJogador().setPontos(pontos[0]);
								partida.getJogadorAdversario().setPontos(pontos[1]);
							}
							
							if (mapGames.get(keyEntrySet).getJogador().getPontos().equals("40")
									&& mapGames.get(keyEntrySet).getJogadorAdversario().getPontos().equals("40")
									&& mapGames.get(keyEntrySet).isPartidaBloqueada()
									&& mapGames.get(keyEntrySet).getNumeroPartidaFinalizada() == numeroPartidafinalizada) {
								mapGames.get(keyEntrySet).setPartidaGanha(false);
								// int numeroEmpates =  mapGames.get(keyEntrySet).getQuantidadeEmpates()==null?0:mapGames.get(keyEntrySet).getQuantidadeEmpates();

								System.out.println("numero empate teste: "+mapGames.get(keyEntrySet).getQuantidadeEmpates());
								
								//estava zerando o contador de partidas empatadas
								/*int numeroEmpates = 0;
								mapGames.get(keyEntrySet).setQuantidadeEmpates(numeroEmpates);*/
								
								mapGames.get(keyEntrySet).setPartidaEmpatada(true);
								
								mapGamesPerdidos.put(keyEntrySet,mapGames.get(keyEntrySet));
								
								if (mapGamesPerdidos.get(keyEntrySet) != null) {
									mapGamesPerdidos.get(keyEntrySet).setQuantidadeEmpates(mapGamesPerdidos.get(keyEntrySet).getQuantidadeEmpates()+1);
								}

								// quando cirar uma nova, quando zerar os pontos
								// e mudar o jogo, n�o pode estar block
								// mapGames.get(keyEntrySet).setPartidaBloqueada(false);
								System.out.println("Partida Perdida");
								System.out.println("Numero Partida: "
										+ numeroPartidaAtual);
								System.out.println("Jogadores: "+ mapGames.get(keyEntrySet).getJogador().getNome()+ " VS "+ mapGames.get(keyEntrySet).getJogadorAdversario().getNome());
								System.out.println("Pontos: " + pontos[0]+ " - " + pontos[1]);
								System.out.println("N�mero empates seguidos: "+ mapGames.get(keyEntrySet).getQuantidadeEmpates());
								System.out.println("Valor Aposta: "+ mapGames.get(keyEntrySet).getValorAposta());
								System.out.println("Retorno Aposta: "+ mapGames.get(keyEntrySet).getValorRetornoAposta());
								// valorPote = valorPote.add(new
								// BigDecimal(-3));
								System.out.println("Valor Atual: " + valorPote);
								System.out.println("Horario: "+ mapGames.get(keyEntrySet).getHorarioPartida());
								System.out.println("\n");
								// mapGamesPerdidos.put(keyEntrySet+"_numeropartida"+numeroPartidaAtual,
								// mapGames.get(keyEntrySet));
								
								
								mapGames.remove(keyEntrySet);
								continue;
								// }else
								// if(mapGames.get(keyEntrySet).getNumeroPartidaAnterior()
								// < numeroPartida) {
							} else if (mapGames.get(keyEntrySet).getPartidaAnterior() != null
									&& mapGames.get(keyEntrySet).getPartidaAnterior().getNumeroPartidaFinalizada() < numeroPartidafinalizada
									&& mapGames.get(keyEntrySet).getPartidaAnterior().isPartidaBloqueada()
							/* && mapGames.get(keyEntrySet).isPartidaBloqueada() */) {
								// System.out.println("Trocando de partida de "+mapGamesGanhos.get(keyEntrySet).getNumeroPartidaAnterior()+" para "+mapGames.get(keyEntrySet).getNumeroPartida());
								
								valorPote = valorPote.add(mapGames.get(keyEntrySet).getPartidaAnterior().getValorRetornoAposta());

								System.out.println("Aposta ganha");
								System.out.println("Trocando de partida de "+ mapGames.get(keyEntrySet).getPartidaAnterior().getNumeroPartidaFinalizada()+ " para " + numeroPartidafinalizada);
								System.out.println("Numero Partida ganha: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getNumeroPartidaFinalizada());
								System.out.println("Numero proxima Partida "+ numeroPartidafinalizada);
								System.out.println("Jogadores: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getJogador().getNome()+ " VS "+ mapGames.get(keyEntrySet).getPartidaAnterior().getJogadorAdversario().getNome());
								System.out.println("Valor Aposta: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getValorAposta());
								System.out.println("Retorno Aposta: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getValorRetornoAposta());
								System.out.println("Valor anterior: "+ valorPote);
								System.out.println("Valor Atual: " + valorPote);
								System.out.println("Horario: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getHorarioPartida());
								System.out.println("\n");

								mapGames.get(keyEntrySet).setNumeroPartida(numeroPartidaAtual);
								mapGames.get(keyEntrySet).setPartidaGanha(true);
								mapGames.get(keyEntrySet).setPartidaBloqueada(false);
								mapGames.get(keyEntrySet).setNumeroPartidaAnterior(numeroPartidaAtual);
								mapGamesGanhos.put(keyEntrySet + "_numeropartida"+ numeroPartidaAtual, mapGames.get(keyEntrySet).getPartidaAnterior());
								mapGames.get(keyEntrySet).setPartidaAnterior(null);
								mapGames.remove(keyEntrySet);
								mapGamesPerdidos.remove(keyEntrySet);
								continue;
								// se apenas for 40-40n n�o fazer jogada
							} else if (mapGames.get(keyEntrySet).getJogador().getPontos().equals("40")
									&& mapGames.get(keyEntrySet).getJogadorAdversario().getPontos().equals("40")
									|| (mapGames.get(keyEntrySet).getJogador().getPontos().equals("A") 
									|| mapGames.get(keyEntrySet).getJogadorAdversario().getPontos().equals("A"))) {
								continue;
							}
							// se excedeu o limite de jogadas por empates
							if (mapGamesPerdidos.get(keyEntrySet) != null
									&& mapGamesPerdidos.get(keyEntrySet) != null
									&& !mapGamesPerdidos.get(keyEntrySet).isPartidaGanha()
									&& mapGamesPerdidos.get(keyEntrySet).getQuantidadeEmpates() > Bot.MAXIMO_EMPATES) {
								mapGamesPerdidos.remove(keyEntrySet);
								mapGames.remove(keyEntrySet);
								continue;
							}
							
							
							if (mapGames.get(keyEntrySet) != null
									&& mapGames.get(keyEntrySet).isPartidaBloqueada()) {
								continue;
							}

							loco.scrollTo(deuce);

							String classAbaDeuce = "";
							try {
								classAbaDeuce = deuce.findElement(By.xpath(Bot.XPATH_PARENT)).getAttribute("class");
							} catch (Exception e) {
								System.out.println("Falha ao obter parent class da aba Deuce. "
												+ Bot.XPATH_PARENT
												+ "\n"
												+ e.getMessage());
							}
							if (!classAbaDeuce.contains(Bot.STRING_OPEN)) {
								deuce.click();
							}

							loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_SPAN_ODDS)));
							WebElement webElementValorOdds = deuce.findElements(By.xpath("../.."+ Bot.XPATH_SPAN_ODDS)).get(1);
							loco.scrollTo(webElementValorOdds);
							webElementValorOdds.click();
							WebElement iframeBoletim = loco.getWebElement(By.xpath(Bot.XPATH_FRAME_BOLETIM));
							Locomotive switchToFrame = loco.switchToFrame(iframeBoletim);
							List<WebElement> listTodasApostas = switchToFrame.getWebElements(By.xpath(Bot.XPATH_LI_TODAS_APOSTAS));

							List<WebElement> listsApostasIndividuais = listTodasApostas
									.get(0).findElements(
											By.xpath(Bot.XPATH_LI));

							for (int i = 0; i < listsApostasIndividuais.size(); i++) {
								WebElement apostaSelecionada = listsApostasIndividuais
										.get(i);
								loco.scrollTo(apostaSelecionada);
								BigDecimal valorAposta = BigDecimal.ZERO;
								if (mapGamesPerdidos.get(keyEntrySet) != null
										&& mapGamesPerdidos.get(keyEntrySet) != null
										&& mapGamesPerdidos.get(keyEntrySet).isPartidaEmpatada()
										&& mapGamesPerdidos.get(keyEntrySet).getQuantidadeEmpates() <= Bot.MAXIMO_EMPATES) {
									valorAposta = miner.calcularValorAposta(mapGamesPerdidos.get(keyEntrySet).getValorAposta(), true, mapGamesPerdidos.get(keyEntrySet)).add(new BigDecimal(Bot.VALOR_PADRAO_APOSTA));
									
								} else
									valorAposta = miner.calcularValorAposta(Bot.VALOR_PADRAO_APOSTA, false, mapGamesPerdidos.get(keyEntrySet));
								
								mapGames.get(keyEntrySet).setValorAposta(valorAposta);
								
								
								if(valorAposta.compareTo(valorPote)>1){
									System.out.println("Pote insuficiente: Aposta: "+valorAposta+" - Pote: "+valorPote);
									continue;
								}
								
								loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_INPUT)));
								apostaSelecionada.findElement(By.xpath(Bot.XPATH_INPUT)).sendKeys(valorAposta.toString());
								WebElement webElementRetorno = apostaSelecionada.findElement(By.xpath("//div[@class='bs-StakeToReturn']"));
								String valor = webElementRetorno.getText().substring(Bot.STRING_RETORNOS.length());
								mapGames.get(keyEntrySet).setValorRetornoAposta(new BigDecimal(valor.replace(",", ".").trim()));
								mapGames.get(keyEntrySet).setNumeroPartida(numeroPartidaAtual);
								mapGames.get(keyEntrySet).setPartidaBloqueada(true);
								
								boolean producao = false;
								if (producao) {
									boolean apostaEfetuada = false;
									while (!apostaEfetuada) {
										try {
											// loco.waitForElement(By.xpath("//div[@class='bs-Btn bs-BtnHover']"));
											loco.getWebElement(By.xpath("//div[@class='bs-Btn bs-BtnHover']")).click();
											apostaEfetuada = true;
										} catch (Exception e) {
											// loco.waitForElement(By.xpath("//a[@class='acceptChanges bs-BtnWrapper']"));
											loco.getWebElement(By.xpath("//a[@class='acceptChanges bs-BtnWrapper']")).click();
										}
									}
									loco.waitForElement(By.xpath("//span[@class='br-Header_Done']"));
									loco.getWebElement(By.xpath("//span[@class='br-Header_Done']")).click();
								} else {
									loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_REMOVER_TODAS_APOSTAS)));
									WebElement btnRemoverApostas = loco.getWebElement(By.xpath(Bot.XPATH_REMOVER_TODAS_APOSTAS));
									loco.scrollTo(btnRemoverApostas);
									btnRemoverApostas.click();
								}
								loco.driver.switchTo().defaultContent();
								
								//Set Horario Aposta
								WebElement horarioAposta = loco.getWebElement(By.xpath("//time[@class='hm-Clock ']"));
								valorPote = valorPote.subtract(new BigDecimal(Bot.VALOR_PADRAO_APOSTA));
								mapGames.get(keyEntrySet).setHorarioPartida(horarioAposta == null ? "": horarioAposta.getText());
								
								System.out.println("Partida bloqueada - jogo Realizado");
								System.out.println("partida apostada: "+ mapGames.get(keyEntrySet).getNumeroPartida());
								System.out.println("Jogadores: "+ mapGames.get(keyEntrySet).getJogador().getNome()
										+ " VS "+ mapGames.get(keyEntrySet).getJogadorAdversario().getNome());
								System.out.println("Entrou na Partida "+ mapGames.get(keyEntrySet).getJogador().getPontos()
										+ " - "+ mapGames.get(keyEntrySet).getJogadorAdversario().getPontos());
								System.out.println("Valor Aposta: "+ valorAposta);
								System.out.println("Retorno: "+ mapGames.get(keyEntrySet).getValorRetornoAposta());
								System.out.println("Valor Anterior: "+ valorPote.add(new BigDecimal(Bot.VALOR_PADRAO_APOSTA)));
								System.out.println("Valor Atual: " + valorPote);
								System.out.println("Horario: "
										+ horarioAposta.getText());
								System.out.println("\n");
							}
						} catch (Exception e) {
							// System.err.println(e.getMessage());
						}
					}
					loco.driver.switchTo().defaultContent();
				}
			} catch (Exception e) {
				loco.driver.navigate().refresh();
				// e.printStackTrace();
				// System.out.println(e.getMessage());
			}
		}
	}
}