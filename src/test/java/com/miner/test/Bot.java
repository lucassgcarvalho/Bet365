package com.miner.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.miner.Locomotive;
import com.miner.entity.Partida;

/**
 * @author Lucas
 *
 */
public class Bot {
	
	private static java.text.DecimalFormat df = new java.text.DecimalFormat("##0,00");
	private static SimpleDateFormat dateFormat =  new  SimpleDateFormat("HH:mm:ss");

	public static final String VALOR_PADRAO_APOSTA = "3";
	
	public static final Integer MAXIMO_EMPATES = 5;
	
	/**
	 * "//div[@class='ipn-Classification ipn-Classification-open '][./div[@class='ipn-ClassificationButton ipn-ClassificationButton_Classification-13 ']]"
	 */
	public static final String XPATH_LINK_TAB_TENIS = "//div[@class='ipn-Classification ipn-Classification-open '][./div[@class='ipn-ClassificationButton ipn-ClassificationButton_Classification-13 ']]";

	/**
	 * Pre requisition to work {@link XPATH_LINK_TAB_TENIS}<br>
	 * .//div[contains(@class,
	 * 'ipn-FixtureButton')]/div[contains(@class,'ipn-TeamStack')]/div[@class='ipn-TeamStack_Team'][1]
	 * Pega na posi��o 1
	 */
	public static final String XPATH_LIST_PLAYER_NAME_UM = ".//div[contains(@class,'ipn-FixtureButton')]/div[contains(@class,'ipn-TeamStack')]/div[@class='ipn-TeamStack_Team'][1]";
	public static final String XPATH_LIST_PLAYER_NAME_DOIS = ".//div[contains(@class,'ipn-FixtureButton')]/div[contains(@class,'ipn-TeamStack')]/div[@class='ipn-TeamStack_Team'][2]";
	//public static final String XPATH_LIST_PLAYER_NAME = ".//div[contains(@class,'ipn-FixtureButton')]/div[contains(@class,'ipn-TeamStack')]/div[@class='ipn-TeamStack_Team']";

	/**
	 * 
	 * Pré Requisition {@link XPATH_LINK_TAB_TENIS}<br>
	 * ".//*[@class='ipn-TeamStack_Team' and text()=\"%s\"]/../.."
	 */
	public static final String XPATH_GAME_BY_PLAYER = ".//*[@class='ipn-TeamStack_Team' and text()=\"%s\"]/../..";

	/**
	 * "//span[contains(text(),\"Deuce\")]"
	 */
	public static final String XPATH_LINK_ABA_DEUCE = "//span[contains(text(),\"Deuce\")]";
	
	/**
	 * "//span[contains(text(),\"Deuce\") and contains(text(), ?)]"
	 */
	public static final String XPATH_LINK_ABA_DEUCE_NUMERO_DEUCE = "//span[contains(text(),\"Deuce\") and contains(text(), ?)]";


	public static final CharSequence STRING_OPEN = "open";
	
	/**
	 * "./parent::*"
	 */
	public static final String XPATH_PARENT = "./parent::*";

	/**
	 * //span[@class=\"gl-ParticipantCentered_Odds\"]
	 */
	public static final String XPATH_SPAN_ODDS = "//span[@class=\"gl-ParticipantCentered_Odds\"]";

	/**
	 * "//iframe[@class=\"bw-BetslipWebModule_Frame \"]"
	 */
	public static final String XPATH_FRAME_BOLETIM = "//iframe[@class=\"bw-BetslipWebModule_Frame \"]";

	/**
	 * "//li[@class='single-section bs-StandardBet']"
	 */
	public static final String XPATH_LI_TODAS_APOSTAS = "//li[@class='single-section bs-StandardBet']";

	/**
	 * ".//li"
	 */
	public static final String XPATH_LI = ".//li";

	/**
	 * ".//input"
	 */
	public static final String XPATH_INPUT = ".//input";

	/**
	 * ".//*[@class='ipn-ScoreDisplayTennis_CurrentGame'][./div[@class='ipn-ScoreDisplayTennis_CurrentPoint']]"
	 */
	public static final String XPATH_PONTOS_PLAYERS = ".//*[@class='ipn-ScoreDisplayTennis_CurrentGame'][./div[@class='ipn-ScoreDisplayTennis_CurrentPoint']]";

	public static final String STRING_RETORNOS = "Retornos";

	/**
	 * "//a[@class='bs-Header_RemoveAllLink']"
	 */
	public static final String XPATH_REMOVER_TODAS_APOSTAS = "//a[@class='bs-Header_RemoveAllLink']";

	/**
	 * 4
	 */
	private static final String STRING_QUATRO = "4";

	/**
	 * "//time[@class='hm-Clock ']"
	 */
	public static final String XPATH_TIME_SERVER = "//time[@class='hm-Clock ']";

	/**
	 * VALUE TRUE
	 * <b>True</b>: se executa as apostas de fato<br>
	 * <b>False</b>: se apenas simula nos objetos
	 */
	public static final boolean PRODUCAO = true;
	
	/**
	 * VALUE FALSE
	 */
	public static final boolean SIMULACAO = false;

	/**
	 * 2000l
	 */
	public static final Long DOIS_SEGUNDO_MILLI = 2000l;

	/**
	 * //div[text()='Aceitar Altera��es']
	 */
	public static final String BTN_ACEITAR_ALTERACOES_POR_DESCRICAO = "//div[text()='Aceitar Alterações']";

	public static final String ESTRATEGIA_40_0_40_15 = "40_15_40_0";
	
	/**
	 * Futebol
	 */
	public static String FUTEBOL = "Futebol";

	/**
	 * T�nis
	 */
	public static String TENIS = "Ténis";

	/**
	 * "/html/body/div[1]/div//nav/a[.='Ao-Vivo']"
	 */
	public static String XPATH_MENU_AO_VIVO = "/html/body/div[1]/div//nav/a[.='Ao-Vivo']";

	/**
	 * //a[@class='hm-BigButton ' and @style and text()='Ao-Vivo']
	 */
	public static String XPATH_MENU_AO_VIVO_HM_BIG_BUTTON = "//a[@class='hm-BigButton ' and @style and text()='Ao-Vivo']";

	/**
	 * "/html/body/div[1]/div/div[2]/div[1]/div/div/div[2]/div[1]/div/div/div/div[3]//span[.='Futebol']"
	 */
	public static String XPATH_LINK_FUTEBOL = "/html/body/div[1]/div/div[2]/div[1]/div/div/div[2]/div[1]/div/div/div/div[3]//span[.='Futebol']";

	/**
	 * "//div[@class=\"ip-ControlBar_BBarItem \" and text()=\"Evento\"]"
	 */
	public static String XPATH_MENU_EVENTO = "//div[@class=\"ip-ControlBar_BBarItem \" and text()=\"Evento\"]";

	/**
	 * "//div[contains(@class,'ipn-Classification') and contains(@class,'ipn-Classification-open')]/div[contains(@class,'ipn-ClassificationButton') and contains(@class,'ipn-ClassificationButton_Classification')]"
	 */
	public static String XPATH_MENUS_JOGOS = "//div[contains(@class,'ipn-Classification') and contains(@class,'ipn-Classification-open')]/div[contains(@class,'ipn-ClassificationButton') and contains(@class,'ipn-ClassificationButton_Classification')]";
	
	
	/**
	 * "//div[@class='ipe-SummaryButton_Label ']"
	 */
	public static String XPATH_NUMERO_JOGO_ATUAL_SUMMARY_BUTTON_LABEL = "//div[@class='ipe-SummaryButton_Label ']";

	/**
	 * //div[@class=\"ipe-TennisHeaderLayout \"]/div[3]<br>
	 * JOGADOR DE BAIXO: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[2] <br>
	 * JOGADOR DE CIMA: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[1]
	 */
	public static String XPATH_PLACAR_NOMES_JOGADORES = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[3]";
	
	public static String XPATH_PLACAR_NOMES_JOGADOR = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[3]/div[2]";
	
	public static String XPATH_PLACAR_NOMES_JOGADOR_ADVERSARIO = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[3]/div[3]";
	
	
	/**
	 * //div[@class=\"ipe-TennisHeaderLayout \"]/div[4]<br>
	 * JOGADOR DE BAIXO: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[2] <br>
	 * JOGADOR DE CIMA: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[1]
	 */
	public static String XPATH_PLACAR_PRIMEIRO_SET = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[4]";
	
	public static String XPATH_PLACAR_PRIMEIRO_SET_JOGADOR = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[4]/div[2]";
	
	public static String XPATH_PLACAR_PRIMEIRO_SET_JOGADOR_ADVERSARIO = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[4]/div[3]";
	
	/**
	 * //div[@class=\"ipe-TennisHeaderLayout \"]/div[5]<br>
	 * JOGADOR DE BAIXO: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[2] <br>
	 * JOGADOR DE CIMA: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[1]
	 */
	public static String XPATH_PLACAR_SEGUNDO_SET = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[5]";
	
	public static String XPATH_PLACAR_SEGUNDO_SET_JOGADOR = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[5]/div[2]";
	
	public static String XPATH_PLACAR_SEGUNDO_SET_JOGADOR_ADVERSARIO = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[5]/div[3]";
	
	/**
	 * //div[@class=\"ipe-TennisHeaderLayout \"]/div[6]<br>
	 * JOGADOR DE BAIXO: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[2] <br>
	 * JOGADOR DE CIMA: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[1]
	 * 
	 */
	public static String XPATH_PLACAR_TERCEIRO_SET = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[6]";
	
	public static String XPATH_PLACAR_TERCEIRO_SET_JOGADOR = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[6]/div[2]";
	
	public static String XPATH_PLACAR_TERCEIRO_SET_JOGADOR_ADVERSARIO = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[6]/div[3]";
	
	
	/**
	 * //div[@class=\"ipe-TennisHeaderLayout \"]/div[9]<br>
	 * JOGADOR DE BAIXO: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[2] <br>
	 * JOGADOR DE CIMA: //div[@class="ipe-TennisHeaderLayout "]/div[9]/div[1]
	 */
	public static String XPATH_PLACAR_SETS_GANHOS = "//div[@class=\"ipe-TennisHeaderLayout \"]/div[9]";
	
	public static String XPATH_SAQUE_LADO_ESQUERDO= "//div[(contains(@style,'left: 15'))]";
	
	public static String XPATH_SAQUE_LADO_DIREITO= "//div[(contains(@style,'left: 260'))]";

	
	
	
	public void fecharAba(String aba, Locomotive loco) {
		// Fecha Aba Futebol
		List<WebElement> temasJogos = loco.getWebElements(By.xpath(Bot.XPATH_MENUS_JOGOS));
		for (WebElement webElement : temasJogos) {
			if (aba.equalsIgnoreCase(webElement.findElement(By.xpath("../*")).getText())) {
				webElement.findElement(By.xpath("../*")).click();
				break;
			}
		}
	}
	
	
	public void printApostaGanha(Map<String, Partida> mapGames, BigDecimal valorPote,
			int numeroPartidafinalizada, String keyEntrySet,
			BigDecimal valorAnterior, WebElement horarioAposta) {
		System.out.println("Aposta ganha");
		/*System.out.println("Trocando de partida de "
				+ mapGames.get(keyEntrySet).getPartidaAnterior().getPartidaFinalizada()+ " para " + numeroPartidafinalizada);*/
		//System.out.println("Numero Partida ganha: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getPartidaFinalizada());
		System.out.println("Numero Partida ganha: "+ numeroPartidafinalizada);
		//System.out.println("Numero proxima Partida "+ numeroPartidafinalizada);
		System.out.println("Jogadores: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getJogador().getNome()
				+ " VS "+ mapGames.get(keyEntrySet).getPartidaAnterior().getJogadorAdversario().getNome());
		System.out.println("Valor Aposta: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getValorAposta());
		System.out.println("Retorno Aposta: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getValorRetornoAposta());
		System.out.println("Valor anterior: "+ valorAnterior);
		System.out.println("Valor Atual: " + valorPote);
		System.out.println("Horario local: "+ getDataLocalFormatada());
		System.out.println("Horario Aposta Ganha: "+ horarioAposta.getText());
		System.out.println("Horario Aposta Realizada: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getHorarioPartida());
		System.out.println("\n");
	}

	public static String getEntrySetName(String nomeCampeonato,
			String firstPlayer, String secondPlayer) {
		return nomeCampeonato.trim() + "_" + firstPlayer.trim() + "_"
				+ secondPlayer.trim();
	}
	
	public BigDecimal convertStringToBigDecimal(String valor) throws ParseException{
	   return new BigDecimal(df.parse(valor).toString()).setScale(2, RoundingMode.HALF_UP);    
	}
	
	public String convertBigDecimalToString(BigDecimal valor) throws ParseException{
	     return df.format(valor);     
	}
	
	public String priceWithDecimal (String price) {
		Locale.setDefault(new Locale("pt_BR"));
	    DecimalFormat formatter = new DecimalFormat("###.###,00");
	    return formatter.format(price);
	}
	
	public BigDecimal calcularValorAposta(String valorInicial, boolean empate, Partida partida) throws ParseException{
		BigDecimal valorInicialConvertido = convertStringToBigDecimal(valorInicial);
		return calcularValorAposta(valorInicialConvertido, empate, partida);
	}
	
	/**
	 * (valorBase * 4) + valorPadraoAposta
	 * 
	 * @param valorInicial
	 * @param empate
	 * @param partida 
	 * @return
	 * @throws ParseException
	 */
	/*public BigDecimal calcularValorAposta(BigDecimal valorInicial, boolean empate) throws ParseException{
		if(!empate)
			return  valorInicial.setScale(2, RoundingMode.HALF_UP);
		BigDecimal valorMultPorQuatro = valorInicial.multiply(new BigDecimal(STRING_QUATRO));
		return valorMultPorQuatro.add(valorInicial).add(valorMultPorQuatro.multiply(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP);
	}*/

	public BigDecimal calcularValorAposta(BigDecimal valorInicial, boolean empate, Partida partida) throws ParseException{
		/*if(!empate)
			return  valorInicial.setScale(2, RoundingMode.HALF_UP);
*/
		
		BigDecimal valorAposta  = BigDecimal.ZERO;
		if(partida.getPartidaAnterior()!=null){
			valorAposta = partida.getPartidaAnterior().getValorAposta();
		}else
			valorAposta = new BigDecimal(VALOR_PADRAO_APOSTA);
		
		BigDecimal valorOdds = partida.getValorOdds();
		BigDecimal valorPercentual = new BigDecimal("0.1");
		BigDecimal resultado = BigDecimal.ZERO;
		BigDecimal valorCalculadoAposta = BigDecimal.ZERO;
		BigDecimal valorCalculadoApostaOdds = BigDecimal.ZERO;
		boolean quit = false;
		try {
			while(!quit){
				valorCalculadoAposta = valorAposta.add(valorAposta.multiply(valorPercentual));
				valorCalculadoApostaOdds = valorCalculadoAposta.multiply(valorOdds);
				resultado = valorCalculadoApostaOdds;
				
				if(partida.getPartidaAnterior()!=null && partida.getPartidaAnterior().getListValoresPartidasEmpatadas()!=null){
					for (BigDecimal valor : partida.getPartidaAnterior().getListValoresPartidasEmpatadas()) {
						resultado = resultado.subtract(valor);
					}
				}
				//System.out.println(resultado);
				if( resultado.subtract(valorCalculadoAposta).signum() == 1 && resultado.subtract(valorCalculadoAposta).compareTo(new BigDecimal(2)) >= 0){
					quit = true;
//					/System.out.println("valorAposta sem 10% "+valorAposta);
				}else
					valorAposta = valorAposta.add(new BigDecimal("2"));
			}
			
			//System.out.println("valorCalculadoAposta: "+valorCalculadoAposta);
			//System.out.println("valorCalculadoApostaOdds: "+valorCalculadoApostaOdds);
			//System.out.println("resultado " +resultado);
			//System.out.println("Lucro "+resultado.subtract(valorCalculadoAposta));
		} catch (Exception e) {
			e.printStackTrace();
			return new BigDecimal(VALOR_PADRAO_APOSTA).setScale(2, RoundingMode.HALF_UP);
		}
		return valorCalculadoAposta.setScale(2, RoundingMode.HALF_UP);
	}

	
	
//	public BigDecimal calcularValorAposta(BigDecimal valorInicial, boolean empate) throws ParseException{
//		if(!empate)
//			return  valorInicial.setScale(2, RoundingMode.HALF_UP);
//		BigDecimal valorMultPorQuatro = valorInicial.multiply(new BigDecimal(STRING_QUATRO));
//		return valorMultPorQuatro.add(valorInicial).add(valorInicial.multiply(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP);
//	}
	
	
	
	

	/**
	 * XPATH_PARENT
	 * @param deuce
	 */
	public void clickAbaDeuce(WebElement deuce) {
		String classAbaDeuce = "";
		try {
			classAbaDeuce = deuce.findElement(By.xpath(Bot.XPATH_PARENT)).getAttribute("class");
		} catch (Exception e) {
			//System.out.println("Falha ao obter parent class da aba Deuce. "+ Bot.XPATH_PARENT+ "\n"+ e.getMessage());
		}
		if (!classAbaDeuce.contains(Bot.STRING_OPEN)) {
			deuce.click();
		}
	}


	/**
	 * XPATH_SPAN_ODDS
	 * @param deuce
	 * @param loco
	 */
	public void clickOdds(WebElement deuce, Locomotive loco)throws Exception {
		loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_SPAN_ODDS)));
		WebElement webElementValorOdds = deuce.findElements(By.xpath("../.."+ Bot.XPATH_SPAN_ODDS)).get(1);
		loco.scrollTo(webElementValorOdds);
		webElementValorOdds.click();
	}


	public boolean realizarAposta(Map<String, Partida> mapGames, Locomotive loco, String keyEntrySet, WebElement apostaSelecionada) {
		boolean apostaEfetuada = false;
		try {
			try {
				while (!apostaEfetuada) {
					try {
						//double Check
						if(!loco.isPresent(By.xpath(Bot.XPATH_LI_TODAS_APOSTAS))){
							throw new Exception();
						}else if (loco.driver.findElement(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO)).isDisplayed()) {
							loco.driver.findElement(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO)).click();
						}
						loco.waitForElement(By.xpath("//div[@class='bs-Btn bs-BtnHover']"));
						
						
						inserirValorAposta(loco, apostaSelecionada, mapGames.get(keyEntrySet));
						
						if (loco.isPresent(By.xpath("//div[@class='bs-Btn bs-BtnHover']"))) {
							WebElement btnApostar = loco.driver.findElement(By.xpath("//div[@class='bs-Btn bs-BtnHover']"));
						
							if(btnApostar == null || !btnApostar.isDisplayed()){
								throw new ElementNotVisibleException("btnApostar");
							}
							btnApostar.click();
							//loco.getWebElement(By.xpath("//div[@class='bs-Btn bs-BtnHover']")).click();
							loco.waitForElement(By.xpath("//span[@class='br-Header_Done']"), Bot.DOIS_SEGUNDO_MILLI);
							if (loco.isPresent(By.xpath("//span[@class='br-Header_Done']"))) {
								apostaEfetuada = true;
							}
						}
						if (loco.isPresent(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO))) {
							loco.driver.findElement(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO)).click();
							apostaEfetuada = false;
						}
					} catch (Exception e) {
						if(!loco.isPresent(By.xpath(Bot.XPATH_LI_TODAS_APOSTAS))){
							throw new Exception();
						}
						loco.waitForElement(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO));
						if (loco.isPresent(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO)) && loco.driver.findElement(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO)).isDisplayed()) {
							loco.driver.findElement(By.xpath(Bot.BTN_ACEITAR_ALTERACOES_POR_DESCRICAO)).click();
							//WebElement webElement = loco.getWebElement(By.xpath("//div[text()='Aceitar Altera��es']"));
							//webElement.click();
						}
						apostaEfetuada = false;
						// loco.waitForElement(By.xpath("//a[@class='acceptChanges
						// bs-BtnWrapper']"));
						/*
						 * WebElement
						 * btnAceitarAlteracaoAposta =
						 * loco.getWebElement(By.xpath(
						 * "//a[@class='acceptChanges bs-BtnWrapper']"
						 * ));
						 * btnAceitarAlteracaoAposta.click
						 * ();
						 */
					}
				}
			// loco.isPresent(By.xpath("//span[@class='br-Header_Done']"));
			//loco.getWebElement(By.xpath("//span[@class='br-Header_Done']")).click();
			} catch (Exception e) {
				e.printStackTrace();
				mapGames.remove(keyEntrySet);
				//mapGamesPerdidos.remove(keyEntrySet);
				loco.driver.navigate().refresh();
			}
		} catch (Exception e) {
			System.err.println("Erro ao realizar aposta");
		}
		return apostaEfetuada;
	}


	/**
	 * 
	 * Realiza a aposta de acordo com o par�metro passado
	 * @param mapGames
	 * @param loco
	 * @param keyEntrySet
	 * @return
	 * @throws ParseException 
	 */
	public boolean executarAposta(Map<String, Partida> mapGames, Locomotive loco, String keyEntrySet, boolean tipoAposta, WebElement apostaSelecionada) throws ParseException {
		if (Bot.PRODUCAO == tipoAposta) {
			return realizarAposta(mapGames, loco, keyEntrySet, apostaSelecionada);
		} else {
			
			inserirValorAposta(loco, apostaSelecionada, mapGames.get(keyEntrySet));
			
			loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_REMOVER_TODAS_APOSTAS)));
			WebElement btnRemoverApostas = loco.getWebElement(By.xpath(Bot.XPATH_REMOVER_TODAS_APOSTAS));
			loco.scrollTo(btnRemoverApostas);
			btnRemoverApostas.click();
			return true;
		}
	}

	
	/**
	 * set valor do lucro e retorno odds
	 * @param loco
	 * @param apostaSelecionada
	 * @param partidaAtual
	 * @return valor odds calculado
	 * @throws ParseException
	 */
	public BigDecimal inserirValorAposta(Locomotive loco, WebElement apostaSelecionada, Partida partidaAtual) throws ParseException{
		loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Bot.XPATH_INPUT)));
		
		String valorOdds = apostaSelecionada.findElement(By.xpath(".//*[./div[@class='bs-Odds']]")).getText();
		partidaAtual.setValorOdds(new BigDecimal(valorOdds));
		apostaSelecionada.findElement(By.xpath(Bot.XPATH_INPUT)).sendKeys(getValorApostaCalculado(partidaAtual).toString());
		
		WebElement webElementRetorno = apostaSelecionada.findElement(By.xpath("//div[@class='bs-StakeToReturn']"));
		String valor = webElementRetorno.getText().substring(Bot.STRING_RETORNOS.length());

		partidaAtual.setValorRetornoAposta(convertStringToBigDecimal(valor.trim()));
		partidaAtual.setLucro(partidaAtual.getValorRetornoAposta().subtract(partidaAtual.getValorAposta()));
		
		return convertStringToBigDecimal(valor.trim());
	}
	
	public BigDecimal getValorApostaCalculado(Partida partidaAtual) throws ParseException{
		BigDecimal valorAposta = BigDecimal.ZERO;
		if (partidaAtual.getPartidaAnterior() != null
				&& partidaAtual.getPartidaAnterior().isPartidaEmpatada()
				&& partidaAtual.getPartidaAnterior().getQuantidadeEmpates() <= Bot.MAXIMO_EMPATES) {
			valorAposta = calcularValorAposta(partidaAtual.getPartidaAnterior().getValorAposta(), true, partidaAtual);
		} else
			valorAposta = calcularValorAposta(Bot.VALOR_PADRAO_APOSTA, false, partidaAtual);
		
		partidaAtual.setValorAposta(valorAposta);
		return valorAposta;
	}
	

	public boolean isApostaJaRealizada(Map<String, Partida> mapGames, String keyEntrySet) {
		return mapGames.get(keyEntrySet) != null && mapGames.get(keyEntrySet).isPartidaBloqueada();
	}


	public String getDataLocalFormatada() {
		try {
			return dateFormat.format(System.currentTimeMillis());
		} catch (Exception e) {
			return "";
		}
	}


	/**
	 * EX: partidaFinalizada 10 - proximoPartida 12 = Return true
	 * 
	 * @param partidaAtual
	 * @param numeroApostaAtual
	 * @return
	 */
	public boolean aguardarProximaPartidaSeguida(Partida partidaAtual, Integer numeroApostaAtual) {
		return !(((partidaAtual.getNumeroPartidaFinalizada()+1) == numeroApostaAtual));
	}


	/**
	 * Return true se placar for 40-0 ou 40-15 ou 0-40 ou 15-40
	 * @param estrategia
	 * @param partida
	 * @return
	 */
	public boolean isGameEstrategia40_15_0_40(Partida partida) {
		return ((partida.getJogador().getPontos().equals("40")
					&& partida.getJogadorAdversario().getPontos().equals("0"))
					
					|| (partida.getJogador().getPontos().equals("0") 
							&& partida.getJogadorAdversario().getPontos().equals("40"))
					
					|| (partida.getJogador().getPontos().equals("40") 
							&& partida.getJogadorAdversario().getPontos().equals("15"))
					
					|| (partida.getJogador().getPontos().equals("15") 
							&& partida.getJogadorAdversario().getPontos().equals("40"))
					
					|| (partida.getPartidaAnterior()!=null && 
							(partida.getPartidaAnterior().isPartidaEmpatada()
								&& partida.getPartidaAnterior().isPartidaBloqueada())));
	}


	/**
	 * modificado para testes, não havia erros do modo anterior (aparentemente)
	 * 
	 * @param partida
	 * @return
	 */
	public boolean isGame40_A_A_40(Partida partida) {
		return (partida.getJogador().getPontos().equals("40")
				||partida.getJogador().getPontos().equals("A"))
			&& ( partida.getJogadorAdversario().getPontos().equals("40")
					|| partida.getJogadorAdversario().getPontos().equals("A"));
		/*return partida.getJogador().getPontos().equals("40")
				&& partida.getJogadorAdversario().getPontos().equals("40")
				|| (partida.getJogador().getPontos().equals("A") 
				|| partida.getJogadorAdversario().getPontos().equals("A"));*/
	}


	/**
	 * @param partidaAtual
	 * @return
	 */
	public boolean isMaximoEmpatesPartida(Partida partidaAtual) {
		return partidaAtual.getPartidaAnterior() != null 
				&& !partidaAtual.getPartidaAnterior().isPartidaGanha() 
				&& partidaAtual.getPartidaAnterior().getQuantidadeEmpates() != null
				&& partidaAtual.getPartidaAnterior().getQuantidadeEmpates() > MAXIMO_EMPATES;
		
	}


	public void printApostaPerdida(Partida partidaAtual, String valorPote, String numeroApostaAtual) {
		System.out.println("Aposta Perdida");
		System.out.println("Numero Partida: "+ numeroApostaAtual);
		System.out.println("Jogadores: "+ partidaAtual.getJogador().getNome()+ " VS "+ partidaAtual.getJogadorAdversario().getNome());
		System.out.println("Pontos: " + partidaAtual.getJogador().getPontos()+ " - " + partidaAtual.getJogadorAdversario().getPontos());
		System.out.println("Número empates seguidos: "+ partidaAtual.getQuantidadeEmpates());
		System.out.println("Valor Aposta: "+ partidaAtual.getValorAposta().setScale(2, RoundingMode.HALF_UP));
		System.out.println("Retorno Aposta: "+ partidaAtual.getValorRetornoAposta());
		// valorPote = valorPote.add(new
		// BigDecimal(-3));
		System.out.println("Valor Atual: " + valorPote);
		System.out.println("Horario servidor: "+ partidaAtual.getHorarioPartida());
		System.out.println("Horario local: "+ getDataLocalFormatada());
		System.out.println("\n");
	}


	public BigDecimal processarApostaGanha(RobotMessage robotMessage, Map<String, Partida> mapGames, 
			Map<String, Partida> mapGamesPerdidos, BigDecimal valorPote, String keyEntrySet, Locomotive loco, int numeroPartidafinalizada) {
		BigDecimal valorAnterior = valorPote;
		valorPote = valorPote.add(mapGames.get(keyEntrySet).getPartidaAnterior().getValorRetornoAposta());
		WebElement horarioAposta = loco.getWebElement(By.xpath(Bot.XPATH_TIME_SERVER));

		try {
			robotMessage.sendMessageRobotAuto(getMensagemPartidaGanha(mapGames, valorPote, numeroPartidafinalizada, keyEntrySet, valorAnterior, horarioAposta));
		} catch (Exception e) {
		}

		//partidaAtual.setNumeroPartida(numeroApostaAtual);
		//partidaAtual.setPartidaGanha(true);
		//partidaAtual.setPartidaBloqueada(false);
		//partidaAtual.setNumeroPartidaAnterior(numeroApostaAtual);
		//mapGamesGanhos.put(keyEntrySet + "_numeropartida"+ numeroPartidaAtual, partidaAtual.getPartidaAnterior());
		mapGames.get(keyEntrySet).setPartidaAnterior(null);

		//TODO: verificar se n�o � essa a operacao correta
//		/partidaAtual.setPartidaAnterior(null);
		return valorPote;
	}


	public boolean isPartidaGanhna(Partida partidaAtual, Integer numeroPartidafinalizada, Integer numeroApostaAtual) {
		/*&& ( mapGamesGanhos.get(keyEntrySet)==null 
		|| (mapGamesGanhos.get(keyEntrySet)!=null && mapGamesGanhos.get(keyEntrySet).isPartidaBloqueada()))*/
		return partidaAtual.getPartidaAnterior() != null
				&& partidaAtual.getPartidaAnterior().getNumeroPartidaFinalizada() < numeroPartidafinalizada
				&& partidaAtual.getPartidaAnterior().isPartidaBloqueada()
				&& !partidaAtual.getPartidaAnterior().isPartidaEmpatada()
				&& (partidaAtual.getNumeroPartidaFinalizada()+1) == numeroApostaAtual;
	}

	public boolean isPartidaGanhnaNaoApostada(Partida partidaAtual, Integer numeroPartidafinalizada, Integer numeroApostaAtual) {
		return partidaAtual.getPartidaAnterior() != null
				&& partidaAtual.getPartidaAnterior().getNumeroPartidaFinalizada() < numeroPartidafinalizada
				&& !partidaAtual.getPartidaAnterior().isPartidaBloqueada()
				&& !partidaAtual.getPartidaAnterior().isPartidaEmpatada()
				&& (partidaAtual.getNumeroPartidaFinalizada()+1) == numeroApostaAtual;
	}

	
	
	public void processarApostaPerdida(Partida partidaAtual, 
			Map<String, Partida> mapGamesPerdidos, String keyEntrySet, 
			String valorPote, String numeroApostaAtual, RobotMessage robotMessage, 
			Map<String, Partida> mapGamesApostados) {
		partidaAtual.setPartidaGanha(false);
		
		if(partidaAtual.getPartidaAnterior()!=null){
			partidaAtual.setQuantidadeEmpates(partidaAtual.getPartidaAnterior().getQuantidadeEmpates() + 1);
		}else{
			partidaAtual.setQuantidadeEmpates(
					partidaAtual.getQuantidadeEmpates() == null ? 1: partidaAtual.getQuantidadeEmpates() + 1);
		}
		
		try {
			robotMessage.sendMessageRobotAuto(getMensagemPartidaPerdida(partidaAtual, valorPote, keyEntrySet, numeroApostaAtual));
		} catch (Exception e) {
		}
		
		partidaAtual.setPartidaEmpatada(true);
		partidaAtual.setPartidaAnterior(null);
		partidaAtual.setPartidaEmpatadaProcessada(true);
		partidaAtual.setQuantidadeSequenciaVitorias(null);

		if(partidaAtual.getListValoresPartidasEmpatadas()==null){
			partidaAtual.setListValoresPartidasEmpatadas(new ArrayList<BigDecimal>());
		}
		partidaAtual.getListValoresPartidasEmpatadas().add(partidaAtual.getValorAposta());

		mapGamesPerdidos.put(keyEntrySet, partidaAtual);
		mapGamesApostados.remove(keyEntrySet);
	}


	public boolean isPartidaEmpatada(Partida partida, int numeroPartidafinalizada) {
		return partida!=null 
				&& partida.isPartidaBloqueada()
				&& !partida.isPartidaEmpatadaProcessada()
				&& partida.getNumeroPartidaFinalizada() < numeroPartidafinalizada;
	}


	public BigDecimal finalizarPartidasEncerradasNaoProcessadas(Partida partida, BigDecimal pote) {
		if(isApostaJaRealizada(partida) && !isGame40_A_A_40(partida)){
			System.out.println("Pote Anterior: "+pote);
			pote = pote.add(partida.getValorAposta());
			System.out.println("Pote Atual: "+pote);
		}
		return pote;
	}


	private boolean isApostaJaRealizada(Partida partida) {
		return partida != null && partida.isPartidaBloqueada();
	}


	public String getMensagemPartidaGanha(Map<String, Partida> mapGames, BigDecimal valorPote,
			int numeroPartidafinalizada, String keyEntrySet,
			BigDecimal valorAnterior, WebElement horarioAposta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Aposta ganha")
		.append("\n")
		.append("Numero Partida ganha: "+ numeroPartidafinalizada)
		.append("\n")
		.append("Jogadores: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getJogador().getNome()
				+ " VS "+ mapGames.get(keyEntrySet).getPartidaAnterior().getJogadorAdversario().getNome())
		.append("\n")
		.append("Valor Aposta: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getValorAposta())
		.append("\n")
		.append("Retorno Aposta: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getValorRetornoAposta())
		.append("\n")
		.append("Valor anterior: "+ valorAnterior)
		.append("\n")
		.append("Valor Atual: " + valorPote)
		.append("\n")
		.append("Valor Odds: " + mapGames.get(keyEntrySet).getPartidaAnterior().getValorOdds())
		.append("\n")
		.append("Lucro: " + mapGames.get(keyEntrySet).getPartidaAnterior().getLucro())
		.append("\n")
		.append("Horario local: "+ getDataLocalFormatada())
		.append("\n")
		.append("Horario Aposta Ganha: "+ horarioAposta.getText())
		.append("\n")
		.append("Horario Aposta Realizada: "+ mapGames.get(keyEntrySet).getPartidaAnterior().getHorarioPartida())
		.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String getMensagemPartidaPerdida(Partida partidaAtual, String valorPote, String keyEntrySet, String numeroApostaAtual) {
		StringBuilder sb = new StringBuilder();
		sb.append("Aposta Perdida")
		.append("\n")
		.append("Numero Partida: "+ numeroApostaAtual)
		.append("\n")
		.append("Jogadores: "+ partidaAtual.getJogador().getNome()+ " VS "+ partidaAtual.getJogadorAdversario().getNome())
		.append("\n")
		.append("Pontos: " + partidaAtual.getJogador().getPontos()+ " - " + partidaAtual.getJogadorAdversario().getPontos())
		.append("\n")
		.append("Número empates seguidos: "+ partidaAtual.getQuantidadeEmpates())
		.append("\n")
		.append("Valor Aposta: "+ partidaAtual.getValorAposta())
		.append("\n")
		.append("Retorno Aposta: "+ partidaAtual.getValorRetornoAposta())
		.append("\n")
		.append("Valor Atual: " + valorPote)
		.append("\n")
		.append("Valor Odds: " + partidaAtual.getValorOdds())
		.append("\n")
		.append("Lucro: " + partidaAtual.getLucro())
		.append("\n")
		.append("Horario servidor: "+ partidaAtual.getHorarioPartida())
		.append("\n")
		.append("Horario local: "+ getDataLocalFormatada())
		.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	public String getMensagemPartidaApostada(Partida partidaAtual, BigDecimal valorPote, String keyEntrySet, WebElement horarioAposta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Partida bloqueada - Aposta Realizada")
		.append("\n")
		.append("Partida apostada: "+ partidaAtual.getNumeroPartida())
		.append("\n")
		.append("Empates: "+partidaAtual.getQuantidadeEmpates())
		.append("\n")
		.append("Jogadores: "+ partidaAtual.getJogador().getNome()
				+ " VS "+ partidaAtual.getJogadorAdversario().getNome())
		.append("Entrou na Partida "+ partidaAtual.getJogador().getPontos()
				+ " - "+ partidaAtual.getJogadorAdversario().getPontos());
		
		if (partidaAtual.getPartidaAnterior() != null
				&& partidaAtual.getPartidaAnterior().isPartidaEmpatada()) {
			sb.append("\n");
			sb.append("Número empates seguidos: "+ partidaAtual.getPartidaAnterior().getQuantidadeEmpates());
		}
		
		sb.append("\n")
		.append("Valor Aposta: "+ partidaAtual.getValorAposta().setScale(2, RoundingMode.HALF_UP))
		.append("\n")
		.append("Retorno: "+ partidaAtual.getValorRetornoAposta())
		.append("\n")
		.append("Valor Odds: " + partidaAtual.getValorOdds())
		.append("\n")
		.append("Lucro: "+ partidaAtual.getLucro())
		.append("\n")
		.append("Valor Anterior: "+ valorPote.add(partidaAtual.getValorAposta().setScale(2, RoundingMode.HALF_UP)))
		.append("\n")
		.append("Valor Atual: " + valorPote)
		.append("\n")
		.append("Horário: "+ horarioAposta.getText())
		.append("\n")
		.append("Horario local: "+ getDataLocalFormatada())
		.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	public String getMensagemPartidaBloqueadaExcessoEmaptes(Partida partida) {
		StringBuilder sb = new StringBuilder();
		sb.append("Aposta Perdida")
		.append("\n")
		.append("Partida Bloqueada por excesso de emaptes: "+partida.getPartidaAnterior().getQuantidadeEmpates())
		.append("\n")
		.append("Jogadores: "+partida.getJogador().getNome()+" VS "+partida.getJogadorAdversario().getNome())
		.append("\n")
		.append("Número Partida: "+partida.getNumeroPartida())
		.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}


	public boolean isGameEstrategiaJogadorComMaisPontosDeSet(Partida partidaAtual, Locomotive loco) {
		
		if(loco.isPresent(By.xpath(XPATH_PLACAR_PRIMEIRO_SET_JOGADOR))
				&& loco.isPresent(By.xpath(XPATH_PLACAR_PRIMEIRO_SET_JOGADOR_ADVERSARIO))){
			WebElement placarPrimeiroSetJogador = loco.getWebElement(By.xpath(XPATH_PLACAR_PRIMEIRO_SET_JOGADOR));
			WebElement placarPrimeiroSetJogadorAdversario = loco.getWebElement(By.xpath(XPATH_PLACAR_PRIMEIRO_SET_JOGADOR_ADVERSARIO));
			partidaAtual.getJogador().setPrimeiroSet(placarPrimeiroSetJogador.getText().trim().isEmpty()?null:Integer.parseInt(placarPrimeiroSetJogador.getText()));
			partidaAtual.getJogadorAdversario().setPrimeiroSet(placarPrimeiroSetJogadorAdversario.getText().trim().isEmpty()?null:Integer.parseInt(placarPrimeiroSetJogadorAdversario.getText()));
			
			if(loco.isPresent(By.xpath(XPATH_PLACAR_SEGUNDO_SET_JOGADOR))
					&& loco.isPresent(By.xpath(XPATH_PLACAR_SEGUNDO_SET_JOGADOR_ADVERSARIO))){
				WebElement placarSegundoSetJogador = loco.getWebElement(By.xpath(XPATH_PLACAR_SEGUNDO_SET_JOGADOR));
				WebElement placarSegundoSetJogadorAdversario = loco.getWebElement(By.xpath(XPATH_PLACAR_SEGUNDO_SET_JOGADOR_ADVERSARIO));
				partidaAtual.getJogador().setSegundoSet(placarSegundoSetJogador.getText().trim().isEmpty()?null:Integer.parseInt(placarSegundoSetJogador.getText()));
				partidaAtual.getJogadorAdversario().setSegundoSet(placarSegundoSetJogadorAdversario.getText().trim().isEmpty()?null:Integer.parseInt(placarSegundoSetJogadorAdversario.getText()));
				
				if(loco.isPresent(By.xpath(XPATH_PLACAR_TERCEIRO_SET_JOGADOR))
						&& loco.isPresent(By.xpath(XPATH_PLACAR_TERCEIRO_SET_JOGADOR_ADVERSARIO))){
					WebElement placarTerceiroSetJogador = loco.getWebElement(By.xpath(XPATH_PLACAR_TERCEIRO_SET_JOGADOR));
					WebElement placarTerceiroSetJogadorAdversario = loco.getWebElement(By.xpath(XPATH_PLACAR_TERCEIRO_SET_JOGADOR_ADVERSARIO));
					partidaAtual.getJogador().setTerceiroSet(placarTerceiroSetJogador.getText().trim().isEmpty()?null:Integer.parseInt(placarTerceiroSetJogador.getText()));
					partidaAtual.getJogadorAdversario().setTerceiroSet(placarTerceiroSetJogadorAdversario.getText().trim().isEmpty()?null:Integer.parseInt(placarTerceiroSetJogadorAdversario.getText()));
					
					return ( isPrimeiroSetValido(partidaAtual) && isSegundoSetValido(partidaAtual) && isTerceiroSetValido(partidaAtual) );
				}
				return ( isPrimeiroSetValido(partidaAtual) && isSegundoSetValido(partidaAtual));
			}
			return ( isPrimeiroSetValido(partidaAtual));
		}
		//return ( isPrimeiroSetValido(partidaAtual) || isSegundoSetValido(partidaAtual) || isTerceiroSetValido(partidaAtual) );
		return false;
	}
	
	public boolean isPrimeiroSetValido(Partida partidaAtual) {
		if(partidaAtual.getJogador().getPrimeiroSet() !=null 
				&& partidaAtual.getJogadorAdversario().getPrimeiroSet() != null){
			if(getDifferenceBetweenNumber(partidaAtual.getJogador().getPrimeiroSet(), partidaAtual.getJogadorAdversario().getPrimeiroSet()) >= 2)
				return true;
		}
		return false;
	}
	
	public boolean isSegundoSetValido(Partida partidaAtual) {
		if(partidaAtual.getJogador().getSegundoSet() !=null 
				&& partidaAtual.getJogadorAdversario().getSegundoSet() != null){
			if(getDifferenceBetweenNumber(partidaAtual.getJogador().getSegundoSet(), partidaAtual.getJogadorAdversario().getSegundoSet()) <= 1)
				return false;
		}
		return true;
	}
	
	public boolean isTerceiroSetValido(Partida partidaAtual) {
		if(partidaAtual.getJogador().getTerceiroSet() !=null 
				&& partidaAtual.getJogadorAdversario().getTerceiroSet() != null){
			if(getDifferenceBetweenNumber(partidaAtual.getJogador().getTerceiroSet(), partidaAtual.getJogadorAdversario().getTerceiroSet()) <= 1)
				return false;
		}
		return true;
	}

	public Integer getDifferenceBetweenNumber(Integer a, Integer b){
		return Math.abs(a-b);
	}


	/**
	 * Encerra partidas, dentro do map passado como parametro, e envia mensagem para o bot telegram
	 * 
	 * partidas não encerradas são validadas a partidar da aba de tenis com a lista dos jogos.
	 * usar apenas com map de jogos apostados
	 * 
	 * @param mapGamesApostados
	 * @param todasAsPartidasEmTexto
	 * @param pote
	 * @param robotMessage
	 * @return
	 */
	public BigDecimal partidaEncerradaNaoFinalizada(Map<String, Partida> mapGamesApostados, String todasAsPartidasEmTexto, BigDecimal pote, RobotMessage robotMessage) {
		if(!mapGamesApostados.isEmpty()){
			for (Entry<String, Partida> gameApostado : mapGamesApostados.entrySet()){
				String[] split = gameApostado.getKey().split("_");
				String nomePrimeiroJogador = split[1];
				String nomeSegundoJogador = split[2];
				if(!todasAsPartidasEmTexto.contains(nomePrimeiroJogador) 
						&& !todasAsPartidasEmTexto.contains(nomeSegundoJogador)) {
					BigDecimal poteAnterior = pote;
					pote = pote.add(gameApostado.getValue().getValorAposta());
					robotMessage.sendMessageRobotAuto(getMensagemPartidaBloqueadaExcessoEmaptes(gameApostado.getValue(), poteAnterior, pote));
				
				    mapGamesApostados.remove(gameApostado.getKey());
				}
			}
		}
		return pote;
	}
	
	public String getMensagemPartidaBloqueadaExcessoEmaptes(Partida partida, BigDecimal poteAnterior, BigDecimal pote) {
		StringBuilder sb = new StringBuilder();
		sb.append("Aposta Encerrada ")
		.append("\n")
		.append("Partida: "+ partida.getNumeroPartida())
		.append("\n")
		.append("Jogadores: "+ partida.getJogador().getNome()
				+ " VS "+ partida.getJogadorAdversario().getNome())
		.append("Retornando Valor: "+partida.getValorAposta())
		.append("\n")
		.append("Pote Anterior: "+poteAnterior)
		.append("\n")
		.append("Pote Atual: "+pote)
		.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}


	public boolean isPoteInsuficiente(Partida partidaAtual,
			BigDecimal valorPote, BigDecimal valorAposta, RobotMessage robotMessage) {
		if(valorAposta.compareTo(valorPote) == 1){
			System.out.println("Pote insuficiente: Aposta: "+ valorAposta+ " - Pote: "+ valorPote);
			System.out.println("Jogadores: "+ partidaAtual.getJogador().getNome()
					+ " VS "+ partidaAtual.getJogadorAdversario().getNome());
			robotMessage.sendMessageRobotAuto(getMensagemSaldoInsuficiente(partidaAtual, valorAposta, valorPote));
			return true;
		}
		return false;
	}


	private String getMensagemSaldoInsuficiente(Partida partidaAtual, BigDecimal valorAposta, BigDecimal valorPote) {
		StringBuilder sb = new StringBuilder();
		sb.append("Pote insuficiente: Aposta: "+ valorAposta+ " - Pote: "+ valorPote)
		.append("\n")
		.append("Jogadores: "+ partidaAtual.getJogador().getNome()
				+ " VS "+ partidaAtual.getJogadorAdversario().getNome());
		System.out.println(sb.toString());
		return sb.toString();
	}


	public WebElement getAbaDeuce(Locomotive loco, String nomeJogador, String nomeJogadorAdversario, Integer numeroApostaAtual) {
		try {
			//loco.waitForElement(By.xpath(Bot.XPATH_LINK_ABA_DEUCE), 100l);
			WebElement deuce = null;
			if (!loco.isPresent(By.xpath(Bot.XPATH_LINK_ABA_DEUCE))) {
				return null;
				/*throw new Exception("Aba Deuce não está visível - "
								+ nomeJogador
								+ " VS "
								+ nomeJogadorAdversario);*/
			}
			// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_LINK_ABA_DEUCE)));
			List<WebElement> abaDeuce = loco.getWebElements(By.xpath(Bot.XPATH_LINK_ABA_DEUCE));
			deuce = abaDeuce.get(0);
			if (abaDeuce.size() > 1) {
				for (int i = 0; i < abaDeuce.size(); i++) {
					numeroApostaAtual = Integer.parseInt(loco.getOnlyNumbers(abaDeuce.get(i).getText().substring(0, 2)));
					Integer numeroJogoDeuce = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));
					if (numeroApostaAtual < numeroJogoDeuce) {
						deuce = abaDeuce.get(i);
					}
				}
			}
			return deuce;
		} catch (Exception e) {
			// e.printStackTrace();
			 return null;
		}
		//codigo anterior
		/*	try {
		if (!loco.isPresent(By.xpath(Bot.XPATH_LINK_ABA_DEUCE))) {
			throw new Exception("Aba Deuce não está visível - "
							+ playersNamesActual[0]
							+ " VS "
							+ playersNamesActual[1]);
		}
		// loco.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(Miner.XPATH_LINK_ABA_DEUCE)));
		List<WebElement> abaDeuce = loco.getWebElements(By.xpath(Bot.XPATH_LINK_ABA_DEUCE));
		deuce = abaDeuce.get(0);
		if (abaDeuce.size() > 1) {
			for (int i = 0; i < abaDeuce.size(); i++) {
				numeroApostaAtual = Integer.parseInt(loco.getOnlyNumbers(abaDeuce.get(i).getText().substring(0, 2)));
				Integer numeroJogoDeuce = Integer.parseInt(loco.getOnlyNumbers(deuce.getText().substring(0, 2)));
				if (numeroApostaAtual < numeroJogoDeuce) {
					deuce = abaDeuce.get(i);
				}
			}
		}
	} catch (Exception e) {
		 e.printStackTrace();
		continue;
	}*/

	}


	public void limparJogadas(List<WebElement> listTodasApostas, Locomotive loco) {
		if(listTodasApostas.size() > 1){
			for (int i = 0; i < listTodasApostas.size(); i++) {
				if(loco.isPresent(By.xpath(".//a[@class='bs-Header_RemoveAllLink']")) && loco.driver.findElement(By.xpath(".//a[@class='bs-Header_RemoveAllLink']")).isDisplayed() ){
					loco.getWebElement(By.xpath(".//a[@class='bs-Header_RemoveAllLink']")).click();
				}
			}
			
		}
	}

	
	public synchronized void teste(Locomotive loco, String jogador, String jogadorAdversario, String pontoJogador, String pontoJogadorAdversario){
		synchronized (this) {
			Runnable runnable = () -> {
					loco.driver.findElement(By.xpath(String.format(Bot.XPATH_GAME_BY_PLAYER,jogador))).click();
			        String name = Thread.currentThread().getName();
			        System.out.println("Foo " + name);
			        System.out.println(jogador + " - "+jogadorAdversario+" pontos1  "+pontoJogador+" pontos2 "+pontoJogadorAdversario);
			        System.out.println("\n");
			        //TimeUnit.SECONDS.sleep(1);
			        //System.out.println("Bar " + name);
			        try {
						Thread.sleep(3000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
				Thread t = new Thread(runnable);
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	

}

