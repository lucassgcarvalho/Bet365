package com.miner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.miner.Locomotive;
import com.miner.entity.Jogador;
import com.miner.entity.Partida;
import com.miner.test.Bot;


public class Teste {
	
	
	
	
	
	

	public static void main(String[] args) {
		Locomotive loco = new Locomotive();
		Map<String, Partida> mapGames = new HashMap<>();
		Map<String, Partida> mapGamesGanhos = new HashMap<>();
		Map<String, Partida> mapGamesPerdidos = new HashMap<>();
		Bot miner =  new Bot();
		
		loco.click(By.cssSelector("html body.por form#frmMain div center div div ul.lpnm li a.lpdgl"));

		loco.click(By.xpath(Bot.XPATH_MENU_AO_VIVO));
		
		loco.click(By.xpath(Bot.XPATH_LINK_FUTEBOL));

		loco.click(By.xpath(Bot.XPATH_MENU_AO_VIVO_HM_BIG_BUTTON));
		
		// funcionando
		loco.click(By.xpath(Bot.XPATH_MENU_EVENTO));
		
		//Fecha Aba Futebol
		miner.fecharAba(Bot.FUTEBOL, loco);
		
		BigDecimal valorPote = new BigDecimal(1000);
		
		while (true) {
			try {
				
				loco.driver.switchTo().defaultContent();
				
				WebElement abaPartida = loco.getWebElement(By.xpath(Bot.XPATH_LINK_TAB_TENIS));
				
				loco.scrollTo(abaPartida);
				
				loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_LIST_PLAYER_NAME_UM)));
				List<WebElement> listNamesPlayers = abaPartida.findElements(By.xpath(Bot.XPATH_LIST_PLAYER_NAME_UM));
				int size = listNamesPlayers.size();
				for (int indexNamesPlayers = 0; indexNamesPlayers < (size>4?size/2:size); indexNamesPlayers++) {
					WebElement player = listNamesPlayers.get(indexNamesPlayers);
					System.out.println(player.getText());
					//loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(Miner.XPATH_GAME_BY_PLAYER, player.getText().toString()))));
					List<WebElement> gameActual = abaPartida.findElements(By.xpath(String.format(Bot.XPATH_GAME_BY_PLAYER, player.getText().toString())));
					
					for (int indexGameAtual = 0; indexGameAtual < gameActual.size(); indexGameAtual++) {
						WebElement elementGameAtual = gameActual.get(indexGameAtual);
						WebElement campeonato = player.findElements(By.xpath("../../../../../*")).get(0);

						String nomeCampeonato = campeonato.getText();
						loco.scrollTo(elementGameAtual);
						elementGameAtual.click();
						
						String[] playersNamesActual = elementGameAtual.getText().split("\n");
						//loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_PONTOS_PLAYERS)));
						String[] pontos = elementGameAtual.findElement(By.xpath(Bot.XPATH_PONTOS_PLAYERS)).getText().split("\n");
						
						
						WebElement gameEmExecucao = elementGameAtual.findElement(By.xpath("//div[@class=\"ipe-SummaryButton_Label \"]"));
						//System.out.println(gameEmExecucao.getText());
						String stringNumeroJogoAtual = gameEmExecucao.getText().split("-")[0].split("Jogo")[1];
						int numeroJogoExecucao = Integer.parseInt(loco.getOnlyNumbers(stringNumeroJogoAtual));

						
						Integer numeroPartida = 0;
						
						try {
							//loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_LINK_ABA_DEUCE)));
							List<WebElement> abaDeuce = loco.getWebElements(By.xpath(Bot.XPATH_LINK_ABA_DEUCE));
							WebElement deuce = abaDeuce.get(0);
							if(abaDeuce.size()>1) {
								for (int i = 0; i < abaDeuce.size(); i++) {
									numeroPartida = Integer.parseInt(loco.getOnlyNumbers(abaDeuce.get(i).getText().substring(0, 2)));
									Integer numeroJogoDeuce = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));
									if(numeroPartida<numeroJogoDeuce){
										deuce = abaDeuce.get(i);
									}
								}
							}

							numeroPartida = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));

							String keyEntrySet = Bot.getEntrySetName(nomeCampeonato, playersNamesActual[0], playersNamesActual[1]);
							
							if (!mapGames.containsKey(keyEntrySet) || ( mapGames.get(keyEntrySet) == null || mapGames.get(keyEntrySet).getNumeroPartida() != numeroPartida)) {
								Partida partida = new Partida();
								partida.setCampeonato(nomeCampeonato);
								partida.setNumeroPartidaAnterior(new Integer(mapGames.get(keyEntrySet)==null?numeroPartida:mapGames.get(keyEntrySet).getNumeroPartida()));
								partida.setNumeroPartida(numeroPartida);
								
								partida.setJogador(new Jogador());
								partida.getJogador().setNome(playersNamesActual[0]);
								partida.getJogador().setPontos(pontos[0]);
								
								partida.setJogadorAdversario(new Jogador());
								partida.getJogadorAdversario().setNome(playersNamesActual[1]);
								partida.getJogadorAdversario().setPontos(pontos[1]);
								
								partida.setPartidaAnterior(mapGames.get(keyEntrySet)==null?null:mapGames.get(keyEntrySet));
								
								mapGames.put(keyEntrySet, partida);
								
							} else {
								Partida partida = mapGames.get(keyEntrySet);
								partida.getJogador().setPontos(pontos[0]);
								partida.getJogadorAdversario().setPontos(pontos[1]);
							}
							
							System.out.println(mapGames.get(keyEntrySet).getCampeonato());
							System.out.println("Jogo Execu��o: "+numeroJogoExecucao);
							System.out.println(mapGames.get(keyEntrySet).getJogador().getNome() + " VS "
									+ mapGames.get(keyEntrySet).getJogadorAdversario().getNome() + "\n"
									+ mapGames.get(keyEntrySet).getJogador().getPontos() + "\n"
									+ mapGames.get(keyEntrySet).getJogadorAdversario().getPontos());
							System.out.println("\n");
							
						/*	boolean showGanhos =true;
							if(showGanhos) {
								System.out.println(mapGamesGanhos.get(keyEntrySet).getCampeonato());
								System.out.println(mapGamesGanhos.get(keyEntrySet).getJogador().getNome() + " VS "
										+ mapGamesGanhos.get(keyEntrySet).getJogadorAdversario().getNome() + "\n"
										+ mapGamesGanhos.get(keyEntrySet).getJogador().getPontos() + "\n"
										+ mapGamesGanhos.get(keyEntrySet).getJogadorAdversario().getPontos());
								System.out.println("Numero Aposta"+mapGamesGanhos.get(keyEntrySet).getNumeroPartida());
								System.out.println("Aposta: "+mapGamesGanhos.get(keyEntrySet).getValorAposta());
								System.out.println("Retorno Aposta :"+mapGamesGanhos.get(keyEntrySet).getValorRetornoAposta());
								System.out.println("\n");
							}else {
								System.out.println(mapGames.get(keyEntrySet).getCampeonato());
								System.out.println(mapGames.get(keyEntrySet).getJogador().getNome() + " VS "
										+ mapGames.get(keyEntrySet).getJogadorAdversario().getNome() + "\n"
										+ mapGames.get(keyEntrySet).getJogador().getPontos() + "\n"
										+ mapGames.get(keyEntrySet).getJogadorAdversario().getPontos());
								System.out.println("Numero Aposta"+mapGames.get(keyEntrySet).getNumeroPartida());
								System.out.println("Aposta: "+mapGames.get(keyEntrySet).getValorAposta());
								System.out.println("Retorno Aposta :"+mapGames.get(keyEntrySet).getValorRetornoAposta());
								System.out.println("\n");
							}*/
							
							
							/*
							for (Entry<String, Partida> tenis : mapGames.entrySet()) {
									
								if (!abaPartida.getText().contains(tenis.getValue().getJogador().getNome()) && !abaPartida
										.getText().contains(tenis.getValue().getJogadorAdversario().getNome())) {
									mapGames.remove(tenis.getKey());
								}
								System.out.println("KEY_ENTRY_SET " + tenis.getKey());
								System.out.println(tenis.getValue().getCampeonato());
								System.out.println(tenis.getValue().getJogador().getNome() + " VS "
										+ tenis.getValue().getJogadorAdversario().getNome() + "\n"
										+ tenis.getValue().getJogador().getPontos() + "\n"
										+ tenis.getValue().getJogadorAdversario().getPontos());
								System.out.println("Numero Aposta"+tenis.getValue().getNumeroPartida());
								System.out.println("Aposta: "+tenis.getValue().getValorAposta());
								System.out.println("Retorno Aposta :"+tenis.getValue().getValorRetornoAposta());
								
								System.out.println("\n");
								loco.wait(2000);
								
							}*/
							
							
						} catch (Exception e) {
							//System.out.println("Erro ao acessar deuce "+e.getCause()+" - "+e.getMessage()+"\n");
						}
						
					}
					
					loco.driver.switchTo().defaultContent();
				}
				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
	}

	
	
	
	
	
	
	
	
	/*public static void main(String[] args) {
		Locomotive loco = new Locomotive();
		loco.click(By.cssSelector("html body.por form#frmMain div center div div ul.lpnm li a.lpdgl"));
		loco.click(By.xpath("/html/body/div[1]/div//nav/a[.='Ao-Vivo']"));
		try {
			loco.click(By.xpath(
					"/html/body/div[1]/div/div[2]/div[1]/div/div/div[2]/div[1]/div/div/div/div[3]//span[.='Futebol']"));
		} catch (Exception e) {
			// loco.click(By.xpath("//div[@class='ip-ControlBar_BBarItem
			// wl-ButtonBar_Selected ' and @style and @xpath='1' and text()='Evento']"));
		}
		try {
			loco.click(By.xpath("//a[@class='hm-BigButton ' and @style and text()='Ao-Vivo']"));
		} catch (Exception e2) {

		}
		try {
			// funcionando
			loco.click(By.xpath("//div[@class=\"ip-ControlBar_BBarItem \" and text()=\"Evento\"]"));
			// loco.click(By.xpath("//div[@class='ip-ControlBar_BBarItem ' and @style and
			// text()='Evento']"));
		} catch (Exception e) {
		}
		Map<String, Partida> mapGames = new HashMap<>();
		List<WebElement> temas = loco.getWebElements(By.xpath(
				"//div[contains(@class,'ipn-Classification') and contains(@class,'ipn-Classification-open')]/div[contains(@class,'ipn-ClassificationButton') and contains(@class,'ipn-ClassificationButton_Classification')]"));
		for (WebElement webElement : temas) {
			if ("Futebol".equalsIgnoreCase(webElement.findElement(By.xpath("../*")).getText())) {
				webElement.findElement(By.xpath("../*")).click();
				break;
			}
		}
		
		while (true) {
			try {
				// ((JavascriptExecutor)
				// loco.driver).executeScript("arguments[0].scrollIntoView(true);",
				// "//div[@class='ipn-Classification ipn-Classification-open
				// '][./div[@class='ipn-ClassificationButton
				// ipn-ClassificationButton_Classification-13 ']]");
				// div[@class="ipo-TeamPoints_TeamScore ipo-TeamPoints_TeamScore-teamtwo "]
				// span[ @class="ipo-TeamStack_TeamWrapper" and @style and text()="Lucas
				// Miedler" @class="ipo-TeamPoints_TeamScore ipo-TeamPoints_TeamScore-teamtwo "]

				// pega cada jogo
				// div[@class="ipn-FixtureButton " and @xpath="1"]
				// pega os pontos dos jogos
				// span[@class='ipn-ScoreDisplayTennis_CurrentGame']/div[1]
				// span[@class='ipn-ScoreDisplayTennis_CurrentGame']/div[2]
				WebElement webElement = loco.getWebElement(By.xpath(
						"//div[@class='ipn-Classification ipn-Classification-open '][./div[@class='ipn-ClassificationButton ipn-ClassificationButton_Classification-13 ']]"));
				((JavascriptExecutor) loco.driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
				// WebElement webElement = loco.getWebElement(By.xpath(""));
				// obtem os nomes
				// webElement.findElements(By.xpath(".//*[@class='ipn-TeamStack_Team']")).get(2).getText();
				// webElement.findElements(By.xpath(".//*[@class='ipn-TeamStack_Team' and
				// text()='Markus Eriksson']"))
				// obtem o jovo pesquisado pelo nome do jogador
				// List<WebElement> findElements =
				// webElement.findElements(By.xpath(".//*[@class='ipn-TeamStack_Team' and
				// text()='Roberto O-Olmedo']/../..")).get(0).getText();
				// obtem os nomes dos jogadores
				// List<WebElement> findElements =
				// webElement.findElements(By.xpath(".//div[contains(@class,'ipn-FixtureButton')]/div[contains(@class,'ipn-TeamStack')]/div[@class='ipn-TeamStack_Team']")).size()get(2).getText();
				loco.driver.switchTo().defaultContent();
				WebElement webElement = loco.getWebElement(By.xpath(
						"//div[@class='ipn-Classification ipn-Classification-open '][./div[@class='ipn-ClassificationButton ipn-ClassificationButton_Classification-13 ']]"));
				((JavascriptExecutor) loco.driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
				
				List<WebElement> listNamesPlayers = webElement.findElements(By.xpath(".//div[contains(@class,'ipn-FixtureButton')]/div[contains(@class,'ipn-TeamStack')]/div[@class='ipn-TeamStack_Team']"));
				// listNamesPlayers.get().findElements(By.xpath("../../../../../*")).get(0).getText();
				// System.out.println(listNamesPlayers.get(0).getText());
				
				
				
				for (int indexNamesPlayers = 0; indexNamesPlayers < listNamesPlayers.size(); indexNamesPlayers++) {
					WebElement player = listNamesPlayers.get(indexNamesPlayers);
					
					List<WebElement> gameActual = webElement.findElements(By.xpath(String.format(
							".//*[@class='ipn-TeamStack_Team' and text()=\"%s\"]/../..", player.getText().toString())));
					
					for (int indexGameAtual = 0; indexGameAtual < gameActual.size(); indexGameAtual++) {
						WebElement elementGameAtual = gameActual.get(indexGameAtual);
						WebElement webElementJogador = player.findElements(By.xpath("../../../../../*")).get(0);

						String championship = webElementJogador.getText();
						elementGameAtual.click();
						
						try {
							List<WebElement> webElementDeuce = loco
									.getWebElements(By.xpath("//span[contains(text(),\"Deuce\")]"));
							
							
							for (int indexDeuce = 0; indexDeuce < webElementDeuce.size(); indexDeuce++) {
								((JavascriptExecutor) loco.driver).executeScript("arguments[0].scrollIntoView(true);", webElementDeuce.get(0));
								WebElement elementDeuce = webElementDeuce.get(indexDeuce);
								System.out.println(elementDeuce.getText());
								
								elementDeuce.click();
								
								String classElement = elementDeuce.findElement(By.xpath("./parent::*")).getAttribute("class");
								
								if(classElement.contains("open")) {
									elementDeuce.click();	
								}
								
								//obtem o valor do odds
								WebElement webElementValorOdds = elementDeuce.findElements(By.xpath("//span[@class=\"gl-ParticipantCentered_Odds\"]")).get(1);
								
								System.out.println("Valor Odds: "+webElementValorOdds.getText());
								webElementValorOdds.click();
								
								WebElement spanValor = elementDeuce.findElement(By.xpath(
										"//div[@class=\"gl-ParticipantCentered gl-Participant_General gl-ParticipantCentered_NoHandicap gl-Market_CN-2 \"][2]"));
								
								//spanValor.click();
								String[] splitSpanValor = spanValor.getText().split("\n");
								String valor = splitSpanValor[1];
								System.out.println(valor);
								
								// WebElement findElement2 =
								// findElement.findElement(By.xpath(".//span[@class=\"gl-ParticipantCentered_Odds\"]"));
								// System.out.println(findElement2.getText());
								
								WebElement iframeBoletim = loco.getWebElement(By.xpath("//iframe[@class=\"bw-BetslipWebModule_Frame \"]"));
								Locomotive switchToFrame = loco.switchToFrame(iframeBoletim);
								
								
								List<WebElement> listTodasApostas = switchToFrame.getWebElements(By.xpath("//li[@class='single-section bs-StandardBet']"));
								List<WebElement> listsApostasIndividuais = listTodasApostas.get(0).findElements(By.xpath(".//li"));
								
								for (int i = 0; i < listsApostasIndividuais.size(); i++) {
									WebElement apostaSelecionada = listsApostasIndividuais.get(i);
									apostaSelecionada.findElement(By.xpath(".//input")).sendKeys("12");
									System.out.println(apostaSelecionada.getText());
									
								}
								
							}
							loco.driver.switchTo().defaultContent();
						} catch (Exception e) {
							System.out.println("Erro ao acessar deuce");
						}
						String[] playersNamesActual = elementGameAtual.getText().split("\n");
						String[] pontos = elementGameAtual.findElement(By.xpath(
								".//*[@class='ipn-ScoreDisplayTennis_CurrentGame'][./div[@class='ipn-ScoreDisplayTennis_CurrentPoint']]"))
								.getText().split("\n");
						String keyEntrySet = championship.trim() + playersNamesActual[0].trim()
								+ playersNamesActual[1].trim();
						if (!mapGames.containsKey(keyEntrySet)) {
							Partida tenis = new Partida();
							tenis.setNomeCampeonato(championship);
							tenis.setJogador(new Jogador());
							tenis.getJogador().setNome(playersNamesActual[0]);
							tenis.getJogador().setPontos(Integer.parseInt(pontos[0]));
							tenis.setJogadorAdversario(new Jogador());
							tenis.getJogadorAdversario().setNome(playersNamesActual[1]);
							tenis.getJogadorAdversario().setPontos(Integer.parseInt(pontos[1]));
							mapGames.put(keyEntrySet, tenis);
						} else {
							Partida tenis = mapGames.get(keyEntrySet);
							tenis.getJogador().setPontos(Integer.parseInt(pontos[0]));
							tenis.getJogadorAdversario().setPontos(Integer.parseInt(pontos[1]));
						}
						// System.out.println(game.getText());
						// #bsDiv > ul > li.single-section.bs-StandardBet > ul > li
						//loco.getWebElements(By.cssSelector("#bsDiv>ul>li.single-section.bs-StandardBet>ul>li"));
						
						
						//switchToFrame.getWebElements(By.xpath("//*[@id=\"bsDiv\"]./li[@class='single-section bs-StandardBet']")).get(1).getText();

						
						//@SuppressWarnings("all")
						//List<WebElement> listBoletim = (List<WebElement>) loco.getJavaScriptExecutor().executeScript("return $(\"#bsDiv>ul>li>ul>li>\")");
						List<WebElement> listBoletim = (List<WebElement>) ((JavascriptExecutor) loco.driver)
								.executeScript("return $('#bsDiv>ul>li.single-section.bs-StandardBet>ul>li')");
						//System.out.println(listBoletim.get(0).getText());
						for (Entry<String, Partida> tenis : mapGames.entrySet()) {
							if (!webElement.getText().contains(tenis.getValue().getJogador().getNome()) && !webElement
									.getText().contains(tenis.getValue().getJogadorAdversario().getNome())) {
								mapGames.remove(tenis.getKey());
							}
							
							 * if(tenis.getValue().getJogador().getNome().contains("Jack Murray") ||
							 * tenis.getValue().getJogadorAdversario().getNome().contains("Jack Murray")) {
							 
							System.out.println("KEY_ENTRY_SET " + tenis.getKey());
							System.out.println(tenis.getValue().getNomeCampeonato());
							System.out.println(tenis.getValue().getJogador().getNome() + " VS "
									+ tenis.getValue().getJogadorAdversario().getNome() + "\n"
									+ tenis.getValue().getJogador().getPontos() + "\n"
									+ tenis.getValue().getJogadorAdversario().getPontos());
							// loco.getWebElement(By.xpath("//span[@class='gl-MarketGroupButton_Text' and
							// @style and contains(text(),'Deuce')]"));
							System.out.println("\n");
							
							 * if(tenis.getValue().getJogador().getPontos() == 40 &&
							 * tenis.getValue().getJogadorAdversario().getPontos() == 40) {
							 * JOptionPane.showMessageDialog(null,
							 * "Perdeu!! Jogue novamente!"+tenis.getValue().getJogador().getNome() + " VS "+
							 * tenis.getValue().getJogadorAdversario().getNome()+
							 * "\n"+tenis.getValue().getJogador().getPontos()+"\n"+
							 * tenis.getValue().getJogadorAdversario().getPontos()); }
							 
							// }
						}
						// System.out.println("ponto 1= "+pontos[0]);
						// System.out.println("ponto 2= "+pontos[1]);
					}
					
				}
				
				
				listNamesPlayers.forEach(player -> {
					List<WebElement> gameActual = webElement.findElements(By.xpath(String.format(
							".//*[@class='ipn-TeamStack_Team' and text()=\"%s\"]/../..", player.getText().toString())));
					
					 * System.out.println(webElement.getText());
					 * System.out.println(gameActual.get(0).getText());
					 * System.out.println(gameActual.get(1).getText());
					 
					// pega o campeonato
					// System.out.println(player.findElements(By.xpath("../../../../../*")).get(0).getText()+"\n");
					// System.out.println(player.getText()+"\n");
					
					gameActual.forEach(game -> {
						WebElement webElementJogador = player.findElements(By.xpath("../../../../../*")).get(0);
						// webElementJogador.click();
						String championship = webElementJogador.getText();
						game.click();
						try {
							List<WebElement> webElementDeuce = loco
									.getWebElements(By.xpath("//span[contains(text(),\"Deuce\")]"));
							((JavascriptExecutor) loco.driver).executeScript("arguments[0].scrollIntoView(true);", webElementDeuce);
							webElementDeuce.forEach(element -> {
								System.out.println(element.getText());
								element.click();
								WebElement spanValor = element.findElement(By.xpath(
										"//div[@class=\"gl-ParticipantCentered gl-Participant_General gl-ParticipantCentered_NoHandicap gl-Market_CN-2 \"][2]"));
								spanValor.click();
								String[] splitSpanValor = spanValor.getText().split("\n");
								String valor = splitSpanValor[1];
								System.out.println(valor);
								// WebElement findElement2 =
								// findElement.findElement(By.xpath(".//span[@class=\"gl-ParticipantCentered_Odds\"]"));
								// System.out.println(findElement2.getText());
							});
						} catch (Exception e) {
							System.out.println("Erro ao acessar deuce");
						}
						String[] playersNamesActual = game.getText().split("\n");
						String[] pontos = game.findElement(By.xpath(
								".//*[@class='ipn-ScoreDisplayTennis_CurrentGame'][./div[@class='ipn-ScoreDisplayTennis_CurrentPoint']]"))
								.getText().split("\n");
						String keyEntrySet = championship.trim() + playersNamesActual[0].trim()
								+ playersNamesActual[1].trim();
						if (!mapGames.containsKey(keyEntrySet)) {
							Tenis tenis = new Tenis();
							tenis.setNomeCampeonato(championship);
							tenis.setJogador(new Jogador());
							tenis.getJogador().setNome(playersNamesActual[0]);
							tenis.getJogador().setPontos(Integer.parseInt(pontos[0]));
							tenis.setJogadorAdversario(new Jogador());
							tenis.getJogadorAdversario().setNome(playersNamesActual[1]);
							tenis.getJogadorAdversario().setPontos(Integer.parseInt(pontos[1]));
							mapGames.put(keyEntrySet, tenis);
						} else {
							Tenis tenis = mapGames.get(keyEntrySet);
							tenis.getJogador().setPontos(Integer.parseInt(pontos[0]));
							tenis.getJogadorAdversario().setPontos(Integer.parseInt(pontos[1]));
						}
						// System.out.println(game.getText());
						// #bsDiv > ul > li.single-section.bs-StandardBet > ul > li
						//loco.getWebElements(By.cssSelector("#bsDiv>ul>li.single-section.bs-StandardBet>ul>li"));
						
						
						@SuppressWarnings("all")
						List<WebElement> listBoletim = (List<WebElement>) loco.getJavaScriptExecutor().executeScript("return $(\"#bsDiv>ul>li.single-section.bs-StandardBet>ul>li>\")");
						List<WebElement> listBoletim = (List<WebElement>) ((JavascriptExecutor) loco.driver)
								.executeScript("return $('#bsDiv>ul>li.single-section.bs-StandardBet>ul>li')");
						System.out.println(listBoletim.get(0).getText());
						for (Entry<String, Tenis> tenis : mapGames.entrySet()) {
							if (!webElement.getText().contains(tenis.getValue().getJogador().getNome()) && !webElement
									.getText().contains(tenis.getValue().getJogadorAdversario().getNome())) {
								mapGames.remove(tenis.getKey());
							}
							
							 * if(tenis.getValue().getJogador().getNome().contains("Jack Murray") ||
							 * tenis.getValue().getJogadorAdversario().getNome().contains("Jack Murray")) {
							 
							System.out.println("KEY_ENTRY_SET " + tenis.getKey());
							System.out.println(tenis.getValue().getNomeCampeonato());
							System.out.println(tenis.getValue().getJogador().getNome() + " VS "
									+ tenis.getValue().getJogadorAdversario().getNome() + "\n"
									+ tenis.getValue().getJogador().getPontos() + "\n"
									+ tenis.getValue().getJogadorAdversario().getPontos());
							// loco.getWebElement(By.xpath("//span[@class='gl-MarketGroupButton_Text' and
							// @style and contains(text(),'Deuce')]"));
							System.out.println("\n");
							
							 * if(tenis.getValue().getJogador().getPontos() == 40 &&
							 * tenis.getValue().getJogadorAdversario().getPontos() == 40) {
							 * JOptionPane.showMessageDialog(null,
							 * "Perdeu!! Jogue novamente!"+tenis.getValue().getJogador().getNome() + " VS "+
							 * tenis.getValue().getJogadorAdversario().getNome()+
							 * "\n"+tenis.getValue().getJogador().getPontos()+"\n"+
							 * tenis.getValue().getJogadorAdversario().getPontos()); }
							 
							// }
						}
						// System.out.println("ponto 1= "+pontos[0]);
						// System.out.println("ponto 2= "+pontos[1]);
					});
				});
				// System.out.println(webElement.getText().split("ITF"));
				// loco.click(By.xpath("//span[text()='Ténis']"));
				// loco.wait(3);
				// loco.click(By.xpath("//div[contains(@class,'ipn-Classification') and
				// contains(@class,'ipn-Classification-open')]/div[contains(@class,'ipn-ClassificationButton')
				// and
				// contains(@class,'ipn-ClassificationButton_Classification')]//span[@class='ipn-ClassificationButton_Label
				// ' and @style and text()='Ténis']"));
				// OK
				// div[contains(@class,'ipn-Classification') and
				// contains(@class,'ipn-Classification-open')]/div[contains(@class,'ipn-ClassificationButton')
				// and
				// contains(@class,'ipn-ClassificationButton_Classification')]//span[@class="ipn-ClassificationButton_Label
				// " and @style and text()="Ténis"]
				//// span[@class="ipn-ClassificationButton_Label " and @style and
				// text()="Ténis"]
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
*/
}
