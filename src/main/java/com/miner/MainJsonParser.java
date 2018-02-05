package com.miner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.miner.entity.Partida;

public class MainJsonParser {
	private static final String JSON_FILE_NAME = "menu.json";
	
	public static void main(String[] args) throws IOException {
		
		//((valorAposta + (valorAposta * 0,1)) * odds) - valoresArray
		
		/*RobotMessage message = new RobotMessage();
		
		message.initRobot(false);
		message.sendMessageRobotAuto("teste");*/
		BigDecimal valorAposta = new BigDecimal("0");
		BigDecimal valorOdds = new BigDecimal("1.3");
		BigDecimal valorPercentual = new BigDecimal("0");
		BigDecimal resultado = BigDecimal.ZERO;
		BigDecimal valorCalculadoAposta = BigDecimal.ZERO;
		BigDecimal valorCalculadoApostaOdds = BigDecimal.ZERO;
		boolean quit = false;
		
		Partida partida = new Partida();
		partida.setListValoresPartidasEmpatadas(new ArrayList<BigDecimal>());
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("10"));//3
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("44"));//4
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("190"));//5
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("824"));//6
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("3570"));//7
		
		//3
		/*partida.getListValoresPartidasEmpatadas().add(new BigDecimal("10"));//3
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("44"));//4
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("190"));//5
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("824"));//6
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("3570"));//7
		*/		
		
		
		//1
		/*partida.getListValoresPartidasEmpatadas().add(new BigDecimal("4.4"));//3
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("18.7"));//4
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("81.4"));//5
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("352.0"));//6
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("1525.7"));//7
		*/		
		
		//0.50
	 /*	partida.getListValoresPartidasEmpatadas().add(new BigDecimal("2.2"));//3
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("9.9"));//4
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("42.9"));//5
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("185.9"));//6
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("805.2"));//7
	 */		
		
		//0.30
		/*partida.getListValoresPartidasEmpatadas().add(new BigDecimal("1.1"));//3
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("5.5"));//4
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("23.1"));//5
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("100.1"));//6
		partida.getListValoresPartidasEmpatadas().add(new BigDecimal("434.5"));//7
		 */		
		
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("33"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("143"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("619.3"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("1"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("3.6"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("15.6"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("68.4"));
		//partida.getListValoresPartidasEmpatadas().add(new BigDecimal("296.4"));
		
		
		while(!quit){
			valorCalculadoAposta = valorAposta.add(valorAposta.multiply(valorPercentual));
			valorCalculadoApostaOdds = valorCalculadoAposta.multiply(valorOdds);
			resultado = valorCalculadoApostaOdds;
			for (BigDecimal valor : partida.getListValoresPartidasEmpatadas()) {
				resultado = resultado.subtract(valor);
			}
			//System.out.println(resultado);
			if( resultado.subtract(valorCalculadoAposta).signum() == 1 && resultado.subtract(valorCalculadoAposta).compareTo(new BigDecimal(3)) >= 0){
				quit = true;
				System.out.println("valorAposta sem 10% "+valorAposta);
			}else
				valorAposta = valorAposta.add(new BigDecimal("1"));
		}
		
		System.out.println("valorCalculadoAposta: "+valorCalculadoAposta);
		System.out.println("valorCalculadoApostaOdds: "+valorCalculadoApostaOdds);
		System.out.println("resultado " +resultado);
		System.out.println("Lucro "+resultado.subtract(valorCalculadoAposta));
		
		//String content = new String(Files.readAllBytes(new File(ClassLoader.getSystemClassLoader().getResource(JSON_FILE_NAME).getFile()).toPath()));
		//MenuRaiz data = Converter.fromJsonString(content);
		//jsonToPojoEstructure(data.getRaiz());
	}
	
	
	public static void jsonToPojoEstructure(MenuItem object) {
		//if(object instanceof SubMenu) {
			//printJson(((SubMenu)object));
			
			//if(((SubMenu)object).getSubMenu()!=null){
				//jsonToPojoEstructure(((SubMenu)object).getSubMenu());
		//	}
	//	}else {
		//	printJson(((Menu)object));
	//	}
	}
	
	public static void jsonToPojoEstructure(MenuItem[] objects) {
		for (int i = 0; i < objects.length; i++) {
			if( objects[i] instanceof SubMenu) {
				jsonToPojoEstructure(((SubMenu)objects[i]));
			}else {
				if(((Menu)objects[i]).getSubMenu()!=null) {
					jsonToPojoEstructure(objects[i]);
					jsonToPojoEstructure(((Menu)objects[i]).getSubMenu());
				}else
					jsonToPojoEstructure(objects[i]);
				System.out.println("\n");
			}
		}
	}
	
	
	public static void printJson(MenuItem menuItem){
		System.out.println("Name:"+menuItem.getName()+"  link:"+menuItem.getLink()+"  toolTip:"+menuItem.getToolTip()+"  icon:"+menuItem.getIcon());
	}
	
	
	/*public static void jsonToJava(Object object) {
		if(object instanceof SubMenu) {
			System.out.println(((SubMenu)object).getNome());
			
			if(((SubMenu)object).getSubMenu()!=null){
				jsonToJava(((SubMenu)object).getSubMenu());
			}
		}else {
			System.out.println(((Menu)object).getNome());
		}
	}
	
	public static void jsonToJava(Object[] objects) {
		for (int i = 0; i < objects.length; i++) {
			if( objects[i] instanceof SubMenu) {
				jsonToJava(((SubMenu)objects[i]));
			}else {
				if(((Menu)objects[i]).getSubMenu()!=null) {
					jsonToJava(objects[i]);
					jsonToJava(((Menu)objects[i]).getSubMenu());
				}else
					jsonToJava(objects[i]);
			}
		}
	}
	*/
}
