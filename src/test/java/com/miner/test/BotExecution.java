package com.miner.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.google.common.base.Strings;
import com.miner.Locomotive;
import com.miner.entity.Jogador;
import com.miner.entity.Partida;
import com.miner.exceptions.AbaTenisTimeOut;

public class BotExecution {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Locomotive loco = new Locomotive();
		Map<String, Partida> mapGames = new HashMap<String, Partida>();
		Map<String, Partida> mapGamesGanhos = new HashMap<String, Partida>();
		Map<String, Partida> mapGamesPerdidos = new HashMap<String, Partida>();
		Map<String, Partida> mapGamesApostados = new HashMap<String, Partida>();
		
		/*Predicate<String> testeFiler = Pattern
                .compile(".+?(?=[0-9])")
                .asPredicate();*/

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
				if(!loco.isPresent(By.xpath("//div[@class='ipe-EventViewView ']"))){
					continue;
				}
				
				loco.driver.switchTo().defaultContent();

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
				}
				String[] split = {""};
				while(true){
					split = abaPartida.getText().split("\n");
					List<String> asList = Arrays.asList(split);
					Optional<String> findAny = asList.stream().filter(str -> !str.contains("ITF") && !str.contains("ATP") && !str.contains("WTA") && !str.contains("Ténis")).findAny()	;
					
					 String jogadoresEPontos = asList.stream()               
				                .filter(str -> 
				                		{
				                			if(!str.contains("ITF") && !str.contains("ATP") && !str.contains("WTA") && !str.contains("Ténis")){
				                				mapGames.put(str.split("\n")[0], new Partida());
				                				return true;
				                			}return false;
				                			
				                		}
				                		) 
				                //.filter(testeFiler)
				                .collect(Collectors.joining("\n"));
	
				 	String[] splitPorQuebraLinha = jogadoresEPontos.split("\n");
					
				 	for (int i = 0; i < splitPorQuebraLinha.length; i= i+8) {
				 		String jogador = splitPorQuebraLinha[i];
				 		String jogadorAdversario = splitPorQuebraLinha[i+1];
				 		String pontoJogador = splitPorQuebraLinha[i+6];
				 		String pontoJogadorAdversario = splitPorQuebraLinha[i+7];
				 		
				 		
				 		/*if(		(pontoJogador.equals("40") || pontoJogador.equals("A"))
							&& 	(pontoJogadorAdversario.equals("40") || pontoJogadorAdversario.equals("A"))){*/
				 			bot.teste(loco, jogador, jogadorAdversario, pontoJogador, pontoJogadorAdversario);
				 		//}
				 		
					}
				}    
			}
	}
}