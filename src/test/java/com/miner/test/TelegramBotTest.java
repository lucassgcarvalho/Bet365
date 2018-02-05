package com.miner.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updateshandlers.SentCallback;

public class TelegramBotTest {

	/*public static void main(String[] args) {
		String string = new Date(System.currentTimeMillis()).toString();
		SimpleDateFormat dateFormat =  new  SimpleDateFormat("HH:mm:ss");
		
		System.out.println(dateFormat.format(System.currentTimeMillis()));
		
		
		BigDecimal valor = new BigDecimal("75");
		BigDecimal odds = new BigDecimal("1.3");
		boolean sair =false;
		while(sair==false) {
			BigDecimal multiply = valor.multiply(odds.setScale(2, RoundingMode.HALF_UP));
			System.out.println("multiply: "+multiply);
			multiply = multiply.add(valor);
		}
		
		
	}*/
	public static void main(String[] args) {
		// Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        RobotMessage robotMessage = new RobotMessage();
		robotMessage.initRobot(true);
		robotMessage.sendMessageRobotAuto("Robot Iniciado!");
	}
	
/*	public static void main(String[] args) {
		// Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
        	RobotMessage myAmazingBot = new RobotMessage();
        	SendMessage message = new SendMessage() // Create a message object object
	                .setText("TEste")
	                .setChatId("463876669");
        	SentCallback<Message> callback =  new SentCallback<Message>() {
				
				@Override
				public void onResult(BotApiMethod<Message> method, Message response) {
					System.out.println("onResult");
					System.out.println("method.getMethod(): "+method.getMethod());
					System.out.println("response.getText(): "+response.getText());
				}
				
				@Override
				public void onException(BotApiMethod<Message> method, Exception exception) {
					System.out.println("onException");
					System.out.println("method.getMethod(): "+method.getMethod());
				}
				
				@Override
				public void onError(BotApiMethod<Message> method, TelegramApiRequestException apiException) {
					System.out.println("error");
					System.out.println(apiException.getApiResponse());
				}
			};
            botsApi.registerBot(myAmazingBot);
            myAmazingBot.executeAsync(message, callback );
            
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}*/
/*	 @Test
	    public void TestTelegramApiMustBeInitializableForLongPolling() {
	        new TelegramBotsApi();
	    }

	    @Test
	    public void TestTelegramApiMustBeInitializableForWebhookWithoutSecureSupport() {
	        try {
	            new TelegramBotsApi("externalUrl", "internalUrl");
	        } catch (TelegramApiRequestException e) {
	            Assert.fail();
	        }
	    }

	    @Test
	    public void TestTelegramApiMustBeInitializableForWebhook() {
	        try {
	            new TelegramBotsApi("keyStore", "keyStorePassword", "externalUrl", "internalUrl");
	        } catch (TelegramApiRequestException e) {
	            Assert.fail();
	        }
	    }

	    @Test
	    public void TestTelegramApiMustBeInitializableForWebhookWithSelfSignedCertificate() {
	        try {
	            new TelegramBotsApi("keyStore", "keyStorePassword", "externalUrl", "internalUrl", "selfSignedPath");
	        } catch (TelegramApiRequestException e) {
	            Assert.fail();
	        }
	    }*/
	
}
