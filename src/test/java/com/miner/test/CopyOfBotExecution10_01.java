package com.miner.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.miner.Locomotive;
import com.miner.entity.Jogador;
import com.miner.entity.Partida;
import com.miner.exceptions.AbaTenisTimeOut;

public class CopyOfBotExecution10_01 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locomotive loco = new Locomotive();
		Map<String, Partida> mapGames = new HashMap<String, Partida>();
		Map<String, Partida> mapGamesGanhos = new HashMap<String, Partida>();
		Map<String, Partida> mapGamesPerdidos = new HashMap<String, Partida>();
		Map<String, Partida> mapGamesApostados = new HashMap<String, Partida>();
		
		

		Map<String, Partida> mapGamesBloqueadosPermanentemente = new HashMap<String, Partida>();
		
		Bot bot = new Bot();
		loco.click(By
				.cssSelector("html body.por form#frmMain div center div div ul.lpnm li a.lpdgl"));
		//colocar wait aqui, browse fica demorando pra carregar essa porra e nao acha o ao vivo
		loco.waitForElement(By.xpath(Bot.XPATH_MENU_AO_VIVO));
		loco.click(By.xpath(Bot.XPATH_MENU_AO_VIVO));//colocar outro wait pra esperar o futebol
		//loco.click(By.xpath(Miner.XPATH_LINK_FUTEBOL));

		//loco.click(By.xpath(Miner.XPATH_MENU_AO_VIVO_HM_BIG_BUTTON));
		// funcionando
		loco.waitForElement(By.xpath(Bot.XPATH_MENU_EVENTO));
		loco.click(By.xpath(Bot.XPATH_MENU_EVENTO));
		
		// Fecha Aba Futebol
		bot.fecharAba(Bot.FUTEBOL, loco);
		boolean robotHomolog = false;
		BigDecimal valorPote = new BigDecimal(500);
		RobotMessage robotMessage = new RobotMessage();
		robotMessage.initRobot(robotHomolog);
		robotMessage.sendMessageRobotAuto("Robot Iniciado!");
		
		while (true) {
			try {
				if(!loco.isPresent(By.xpath("//div[@class='ipe-EventViewView ']"))){
					continue;
				}
				loco.driver.switchTo().defaultContent();
				// Fecha Aba Futebol
				bot.fecharAba(Bot.FUTEBOL, loco);
				
				
				//inutilizado por conta deste metodo : bot.partidaEncerradaNaoFinalizada(mapGamesApostados, abaPartida.getText(), valorPote, robotMessage);
				if(!loco.isPresent(By.xpath(Bot.XPATH_LINK_TAB_TENIS))){
					for (Entry<String, Partida> entry : mapGames.entrySet()){
						valorPote = bot.finalizarPartidasEncerradasNaoProcessadas(entry.getValue(), valorPote);
						mapGames.remove(entry.getKey());
					}
				}
				
				WebElement abaPartida = null;
				
				try {
					abaPartida = loco.getWebElement(By.xpath(Bot.XPATH_LINK_TAB_TENIS));	
				} catch (TimeoutException e) {
					valorPote = bot.partidaEncerradaNaoFinalizada(mapGames, "", valorPote, robotMessage);
					throw new AbaTenisTimeOut("Aba Tênis não está visível.");
				}
				abaPartida.getText().split("\n");
				loco.scrollTo(abaPartida);
				// teste performance
				// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_LIST_PLAYER_NAME)));
				List<WebElement> listNamesPlayers = abaPartida.findElements(By.xpath(Bot.XPATH_LIST_PLAYER_NAME_UM));
				
				valorPote = bot.partidaEncerradaNaoFinalizada(mapGamesApostados, abaPartida.getText(), valorPote, robotMessage);
				
				int size = listNamesPlayers.size();
				//(size > 9 ? 9: size)
				
				//for (int indexNamesPlayers = 0; indexNamesPlayers < (size >= 3 ? 3
					//	: size) ; indexNamesPlayers++) {
				
				for (int indexNamesPlayers = 0; indexNamesPlayers <  (size >= 10 ? 10: size); indexNamesPlayers++) {
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
						String[] playersNamesActual = elementGameAtual
								.getText().split("\n");
						
						//testes
						/*if(!playersNamesActual[0].contains("Jan Satral")){
							continue;
						}*/
						
						// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_PONTOS_PLAYERS)));
						String[] pontos = elementGameAtual.findElement(By.xpath(Bot.XPATH_PONTOS_PLAYERS)).getText().split("\n");
						WebElement gameEmExecucao = null;
						String stringNumeroJogoFinalizado = "";
						int numeroPartidafinalizada = 1;
						
						try {
							// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\\\"ipe-SummaryButton_Label
							// \\\"]")));
							//loco.waitForElement(By.xpath("//div[@class=\"ipe-SummaryButton_Label \"]"));
							gameEmExecucao = elementGameAtual.findElement(By.xpath("//div[@class=\"ipe-SummaryButton_Label \"]"));
							stringNumeroJogoFinalizado = gameEmExecucao.getText().split("-")[0].split("Jogo")[1];
							numeroPartidafinalizada = Integer.parseInt(loco.getOnlyNumbers(stringNumeroJogoFinalizado));
							
						} catch (Exception e) {
							 //e.printStackTrace();
							// System.out.println("Erro - In�cio de jogo:"+playersNamesActual[0]+"
							// VS
							// "+playersNamesActual[1]);
							continue;
						}

						Integer numeroApostaAtual = 0;
						WebElement deuce = null;
						try {
							deuce = bot.getAbaDeuce(loco, playersNamesActual[0], playersNamesActual[1], numeroApostaAtual);
							if(deuce==null)
								continue;
							
							loco.scrollTo(deuce);
							numeroApostaAtual = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));

							String keyEntrySet = Bot.getEntrySetName(nomeCampeonato, playersNamesActual[0],playersNamesActual[1]);
							
							
							if(mapGamesBloqueadosPermanentemente != null &&  mapGamesBloqueadosPermanentemente.get(keyEntrySet) != null)
								continue;
							
							
							
							// cria nova estrutura
							if (!mapGames.containsKey(keyEntrySet)
									|| (mapGames.get(keyEntrySet) == null 
										|| mapGames.get(keyEntrySet).getNumeroPartidaFinalizada() != numeroPartidafinalizada)) {
								
								Partida partida = new Partida();
								partida.setCampeonato(nomeCampeonato);
								partida.setNumeroPartidaAnterior(new Integer(mapGames.get(keyEntrySet) == null ? numeroApostaAtual: mapGames.get(keyEntrySet).getNumeroPartida()));
								partida.setNumeroPartida(numeroApostaAtual);
								partida.setPartidaFinalizada(numeroPartidafinalizada);
								partida.setJogador(new Jogador());
								partida.getJogador().setNome(playersNamesActual[0]);
								partida.getJogador().setPontos(pontos[0]);
								partida.setJogadorAdversario(new Jogador());
								partida.getJogadorAdversario().setNome(playersNamesActual[1]);
								partida.getJogadorAdversario().setPontos(pontos[1]);

								if(mapGames.get(keyEntrySet)!=null){
									partida.setNumeroPartidaEmpatada(mapGames.get(keyEntrySet).getNumeroPartidaEmpatada());
									partida.setQuantidadeEmpates(mapGames.get(keyEntrySet).getQuantidadeEmpates() == null ? null: mapGames.get(keyEntrySet).getQuantidadeEmpates());		
								}
								
								if(mapGames.get(keyEntrySet) != null
										&& mapGames.get(keyEntrySet).isPartidaBloqueada()){
									partida.setPartidaAnterior(mapGames.get(keyEntrySet));
								}
								mapGames.put(keyEntrySet, partida);
							} else {
								Partida partida = mapGames.get(keyEntrySet);
								partida.getJogador().setPontos(pontos[0]);
								partida.getJogadorAdversario().setPontos(pontos[1]);
								
								/*partida.getJogador().setPontos("15");
								partida.getJogadorAdversario().setPontos("40");*/
							}
							
							Partida partidaAtual = mapGames.get(keyEntrySet);
							
						    if(partidaAtual != null && partidaAtual.isPartidaInadequada())
						    	continue;
						    
						    //if(partidaAtual.getQuantidadeEmpates() != null && partidaAtual.getQuantidadeEmpates() >= 2)
						    	//System.out.println("Empate: "+partidaAtual.getQuantidadeEmpates() + " Partida: "+partidaAtual.getNumeroPartida()+" - "+partidaAtual.getJogador().getNome()+" - "+partidaAtual.getJogadorAdversario().getNome() );
						    	
						    
						    if (((partidaAtual.getJogador().getPontos().equals("40")
									||partidaAtual.getJogador().getPontos().equals("A"))
								&& ( partidaAtual.getJogadorAdversario().getPontos().equals("40")
										|| partidaAtual.getJogadorAdversario().getPontos().equals("A"))
								&& !partidaAtual.isPartidaBloqueada()
								&& !partidaAtual.isPartidaEmpatadaProcessada()
								&& ( partidaAtual.getNumeroPartidaEmpatada() == null || partidaAtual.getNumeroPartidaEmpatada() < numeroPartidafinalizada )
								/*&& partidaAtual.getNumeroPartidaFinalizada() != numeroPartidafinalizada*/)
								|| (	partidaAtual.getPartidaAnterior()!=null
										&& partidaAtual.getPartidaAnterior().isPartidaBloqueada()
										&& !partidaAtual.getPartidaAnterior().isPartidaEmpatadaProcessada()
										&& (partidaAtual.getPartidaAnterior().getJogador().getPontos().equals("40")
												|| partidaAtual.getPartidaAnterior().getJogador().getPontos().equals("A"))
										&& (partidaAtual.getPartidaAnterior().getJogadorAdversario().getPontos().equals("40")
												|| partidaAtual.getPartidaAnterior().getJogadorAdversario().getPontos().equals("A"))
										&&( partidaAtual.getPartidaAnterior().getNumeroPartidaEmpatada() == null 
												|| partidaAtual.getPartidaAnterior().getNumeroPartidaEmpatada() < numeroPartidafinalizada )
									)) {
						    	mapGames.get(keyEntrySet).setNumeroPartidaEmpatada(numeroPartidafinalizada);
						    	mapGames.get(keyEntrySet).setQuantidadeEmpates(partidaAtual.getQuantidadeEmpates() == null ? 1: mapGames.get(keyEntrySet).getQuantidadeEmpates() + 1);		
						    }else
						    // processa as perdas
						    if (((partidaAtual.getJogador().getPontos().equals("40")
										||partidaAtual.getJogador().getPontos().equals("A"))
									&& ( partidaAtual.getJogadorAdversario().getPontos().equals("40")
											|| partidaAtual.getJogadorAdversario().getPontos().equals("A"))
									&& partidaAtual.isPartidaBloqueada()
									&& !partidaAtual.isPartidaEmpatadaProcessada()
									&& partidaAtual.getNumeroPartidaFinalizada() == numeroPartidafinalizada)
									|| (	partidaAtual.getPartidaAnterior()!=null
											&& partidaAtual.getPartidaAnterior().isPartidaBloqueada()
											&& !partidaAtual.getPartidaAnterior().isPartidaEmpatadaProcessada()
											&& (partidaAtual.getPartidaAnterior().getJogador().getPontos().equals("40")
													|| partidaAtual.getPartidaAnterior().getJogador().getPontos().equals("A"))
											&& (partidaAtual.getPartidaAnterior().getJogadorAdversario().getPontos().equals("40")
													|| partidaAtual.getPartidaAnterior().getJogadorAdversario().getPontos().equals("A"))
										)) {
						    	if(bot.isPartidaEmpatada(partidaAtual.getPartidaAnterior(), numeroPartidafinalizada)){
									
									partidaAtual.setQuantidadeEmpates(partidaAtual.getPartidaAnterior().getQuantidadeEmpates()==null?1:partidaAtual.getPartidaAnterior().getQuantidadeEmpates() + 1);
									partidaAtual.getPartidaAnterior().setPartidaEmpatadaProcessada(true);

									System.out.println("(Jogo Anterior)");
									bot.getMensagemPartidaPerdida(partidaAtual, valorPote.toString(), keyEntrySet, numeroApostaAtual.toString());
									mapGamesPerdidos.put(keyEntrySet, partidaAtual.getPartidaAnterior());
									partidaAtual.setPartidaAnterior(null);
									partidaAtual.setQuantidadeSequenciaVitorias(null);
									if(partidaAtual.getListValoresPartidasEmpatadas()==null){
										partidaAtual.setListValoresPartidasEmpatadas(new ArrayList<BigDecimal>());
									}
									partidaAtual.getListValoresPartidasEmpatadas().add(partidaAtual.getValorAposta());
									mapGamesApostados.remove(keyEntrySet);
									//mapGames.remove(keyEntrySet);
									continue;
								}else{
									bot.processarApostaPerdida(partidaAtual, mapGamesPerdidos, keyEntrySet, valorPote.toString(), numeroApostaAtual.toString(), robotMessage, mapGamesApostados);
									continue;
								}
								
							} else if (bot.isPartidaGanhnaNaoApostada(partidaAtual, numeroPartidafinalizada, numeroApostaAtual)) {
								partidaAtual.setQuantidadeSequenciaVitorias(partidaAtual.getQuantidadeSequenciaVitorias()==null?1:partidaAtual.getQuantidadeSequenciaVitorias()+1);
								mapGamesApostados.remove(keyEntrySet);
								mapGamesPerdidos.remove(keyEntrySet);
								mapGames.remove(keyEntrySet);
								continue;
							}else if (bot.isPartidaGanhna(partidaAtual, numeroPartidafinalizada, numeroApostaAtual)) {
								valorPote = bot.processarApostaGanha(robotMessage, mapGames, mapGamesPerdidos, valorPote, keyEntrySet, loco, numeroPartidafinalizada);
								partidaAtual.setQuantidadeSequenciaVitorias(partidaAtual.getQuantidadeSequenciaVitorias()==null?1:partidaAtual.getQuantidadeSequenciaVitorias()+1);
								mapGamesApostados.remove(keyEntrySet);
								mapGamesPerdidos.remove(keyEntrySet);
								mapGames.remove(keyEntrySet);
								continue;
								
							}
						    
						    if (bot.isGame40_A_A_40(partidaAtual)) {
								continue;
							}

							//ok
							//WebElement nomeJogadorSacando = loco.getWebElement(By.xpath("//div[@class='ml13-CommonAnimations_Asset ml13-CommonAnimations_Icon ml13-ServiceEvent_Service']/../../*"));
							//if(loco.isPresent(By.xpath("//div[@class='ml13-CommonAnimations_Asset ml13-CommonAnimations_Icon ml13-ServiceEvent_Service']"))) {
								
							//}
							//WebElement nomeJogadorSacando = loco.getWebElement(By.xpath("//div[@class='ml13-CommonAnimations_Asset ml13-CommonAnimations_Icon ml13-ServiceEvent_Service']"));
							
							//webElement.findElement(By.xpath("../*"));
							
							/*if(partidaAtual !=null
									 && partidaAtual.getQuantidadeSequenciaVitorias()>=2){
								
							}*/
							
							
							
							//if(!bot.isGameEstrategiaJogadorComMaisPontosDeSet(partidaAtual, loco)
							//		&& ( mapGamesPerdidos.get(keyEntrySet) == null )){
							//	continue;
							//}
							
							/*if (!bot.isGameEstrategia40_15_0_40(partidaAtual)) {
								continue;
							}*/
							
							
							if(partidaAtual.getQuantidadeEmpates() == null ||
									partidaAtual.getQuantidadeEmpates() <= 1){  
								continue;
							}
							
							
							
							if(mapGames.containsKey(keyEntrySet)
								&& bot.aguardarProximaPartidaSeguida(mapGames.get(keyEntrySet), numeroApostaAtual)){
								continue;
							}
							
							//se ja houver aposta naquela partida, vai para o proximo jogo
							if (bot.isApostaJaRealizada(mapGames, keyEntrySet)) {
								continue;
							}
							
							// se excedeu o limite de jogadas por empates
							if (bot.isMaximoEmpatesPartida(partidaAtual)) {
								robotMessage.sendMessageRobotAuto(bot.getMensagemPartidaBloqueadaExcessoEmaptes(partidaAtual));
								mapGamesBloqueadosPermanentemente.put(keyEntrySet, partidaAtual);
								mapGamesPerdidos.remove(keyEntrySet);
								//mapGames.remove(keyEntrySet);
								partidaAtual.getPartidaAnterior().setQuantidadeEmpates(0);
								partidaAtual.getPartidaAnterior().setPartidaEmpatada(false);
								partidaAtual.setPartidaEmpatada(false);
								partidaAtual.setValorAposta(BigDecimal.ZERO);
								partidaAtual.setQuantidadeEmpates(0);
								continue;
							}
							
							//double Check Aba Deuce
							deuce = bot.getAbaDeuce(loco, playersNamesActual[0], playersNamesActual[1], numeroApostaAtual);
							if(deuce==null)
								continue;
							
							loco.scrollTo(deuce);

							try {
								bot.clickAbaDeuce(deuce);
							} catch (Exception e) {
								continue;
							}
							
							try {
								bot.clickOdds(deuce, loco);
							} catch (Exception e) {
								continue;
							}
							
							WebElement iframeBoletim = loco.getWebElement(By.xpath(Bot.XPATH_FRAME_BOLETIM));
							Locomotive switchToFrame = loco.switchToFrame(iframeBoletim);
							
							switchToFrame.waitForElement(By.xpath(Bot.XPATH_LI_TODAS_APOSTAS), 500l);
							List<WebElement> listTodasApostas = switchToFrame.getWebElements(By.xpath(Bot.XPATH_LI_TODAS_APOSTAS));
							
							List<WebElement> listsApostasIndividuais = listTodasApostas.get(0).findElements(By.xpath(Bot.XPATH_LI));
							
							if(listsApostasIndividuais.size() > 1){
								bot.limparJogadas(listsApostasIndividuais, loco);
								throw new Exception("lista de apostas acima de 1");
							}
							
							for (int i = 0; i < listsApostasIndividuais.size(); i++) {
								WebElement apostaSelecionada = listsApostasIndividuais.get(i);
								loco.scrollTo(apostaSelecionada);
							
								/*BigDecimal valorOdds = bot.convertStringToBigDecimal(apostaSelecionada.findElement(By.xpath(".//*[./div[@class='bs-Odds']]")).getText());
								BigDecimal valorAposta = BigDecimal.ZERO;
								
								if (partidaAtual.getPartidaAnterior() != null
										&& partidaAtual.getPartidaAnterior().isPartidaEmpatada()
										&& partidaAtual.getPartidaAnterior().getQuantidadeEmpates() <= Bot.MAXIMO_EMPATES) {
									valorAposta = bot.calcularValorAposta(partidaAtual.getPartidaAnterior().getValorAposta(), true, partidaAtual);
								} else
									valorAposta = bot.calcularValorAposta(Bot.VALOR_PADRAO_APOSTA, false, partidaAtual);
								
								partidaAtual.setValorAposta(valorAposta);
								*/
								
								//removido 09/01, quando nao se tem valor para aposta, o site permite ficar negativo, levando em conta que a aposta x6 fica 403.7 e recupera apenas 0.16 centavos
								/*if(bot.isPoteInsuficiente(partidaAtual, valorPote, valorAposta, robotMessage)){
									continue;
								}*/
								
								/*loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_INPUT)));
								apostaSelecionada.findElement(By.xpath(Bot.XPATH_INPUT)).sendKeys(valorAposta.toString());
								WebElement webElementRetorno = apostaSelecionada.findElement(By.xpath("//div[@class='bs-StakeToReturn']"));
								 */								

								//partidaAtual.setNumeroPartida(numeroApostaAtual);
								
								WebElement horarioAposta = null;
								
								if (bot.executarAposta(mapGames, loco, keyEntrySet, Bot.SIMULACAO, apostaSelecionada)) {

									partidaAtual.setLucro(partidaAtual.getValorRetornoAposta().subtract(partidaAtual.getValorAposta()));
									partidaAtual.setPartidaBloqueada(true);
									
									loco.driver.switchTo().defaultContent();
									
									horarioAposta = loco.getWebElement(By.xpath(Bot.XPATH_TIME_SERVER));
									valorPote = valorPote.subtract(partidaAtual.getValorAposta()).setScale(2, RoundingMode.HALF_UP);
									partidaAtual.setHorarioPartida(horarioAposta == null ? "": horarioAposta.getText());
									
									mapGamesApostados.put(keyEntrySet, mapGames.get(keyEntrySet));
									
									robotMessage.sendMessageRobotAuto(bot.getMensagemPartidaApostada(partidaAtual, valorPote, keyEntrySet, horarioAposta));
								} else {
									System.out.println("Aposta não realizada.");
									System.out.println("Jogadores: "+ partidaAtual.getJogador().getNome()
											+ " VS "+ partidaAtual.getJogadorAdversario().getNome());
									System.out.println("Partida "+ partidaAtual.getJogador().getPontos()
											+ " - "+ partidaAtual.getJogadorAdversario().getPontos());
									continue;
								}
								
							}
						} catch (StaleElementReferenceException e) {
							//loco.driver.navigate().refresh();
							//e.printStackTrace();
						}catch (Exception e) {
							loco.driver.navigate().refresh();
							//e.printStackTrace();
						}
					}
					loco.driver.switchTo().defaultContent();
				}
			} 
			catch(AbaTenisTimeOut erro) {
			}catch (Exception e) {
				loco.driver.navigate().refresh();
				//e.printStackTrace();
				// System.out.println(e.getMessage());
			}
			
		}
	}

}