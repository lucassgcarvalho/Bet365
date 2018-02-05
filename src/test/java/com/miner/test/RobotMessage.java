package com.miner.test;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updateshandlers.SentCallback;

public class RobotMessage extends TelegramLongPollingBot {
    //Obter Json com chat id etc...
	//https://api.telegram.org/bot385100995:AAEB7lY9xy6VUxxY9u9Qic4S2r1-tk82sO4/getUpdates?

	private static final String USER_NAME_ROBOT_AUTO = "RobotAuto";
	private static final String TOKEN_ROBOT_AUTO = "506012133:AAH5zL7AtIJkZwx_1En-W4cIqyJOQ-RHqi4";
	private static final String CHAT_ID_ROBOT_AUTO = "463876669";
	
	private static final String USER_NAME_ROBOT_BET_HOMOLOG = "robotBetHomolog";
	private static final String TOKEN_ROBOT_BET_HOMOLOG = "385100995:AAEB7lY9xy6VUxxY9u9Qic4S2r1-tk82sO4";
	private static final String CHAT_ID_ROBOT_BET_HOMOLOG = "463876669";
	
	private boolean robotHomolog;
	private RobotMessage robotMessage;

	public boolean initRobot(boolean robotHomolog) {
		try {
			this.robotHomolog = robotHomolog;
			if(robotMessage == null) {
		        ApiContextInitializer.init();
		        TelegramBotsApi botsApi = new TelegramBotsApi();
				robotMessage = new RobotMessage();
	            botsApi.registerBot(robotMessage);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Chat Id RobotAuto
	 * @return
	 */
	public String getChatId() {
		return robotHomolog?CHAT_ID_ROBOT_BET_HOMOLOG:CHAT_ID_ROBOT_AUTO;
	}
	
	public void sendMessageRobotAuto(String message) {
		try {
			if(!initRobot(this.robotHomolog)) {
				System.err.println("Robot não está no ar.");
				return;
			}
        	SendMessage sendMessage = new SendMessage() // Create a message object object
	                .setText(message)
	                .setChatId(getChatId());
        	SentCallback<Message> callback =  new SentCallback<Message>() {
				public void onResult(BotApiMethod<Message> method, Message response) {

				}
				
				public void onException(BotApiMethod<Message> method, Exception exception) {
					System.out.println("onException");
					System.out.println("method.getMethod(): "+method.getMethod());
					System.out.println(exception.getMessage());
				}
				
				public void onError(BotApiMethod<Message> method, TelegramApiRequestException apiException) {
					System.out.println("error");
					System.out.println(apiException.getApiResponse());
				}
			};
            this.executeAsync(sendMessage, callback );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public String getBotUsername() {
        return robotHomolog?USER_NAME_ROBOT_BET_HOMOLOG:USER_NAME_ROBOT_AUTO;
    }

    public String getBotToken() {
        return robotHomolog?TOKEN_ROBOT_BET_HOMOLOG:TOKEN_ROBOT_AUTO;
    }
    
	public void onUpdateReceived(Update update) {
		 // We check if the update has a message and the message has text
	    /*if (update.hasMessage() && update.getMessage().hasText()) {
	        // Set variables
	        String message_text = update.getMessage().getText();
	        long chat_id = update.getMessage().getChatId();

	        SendMessage message = new SendMessage() // Create a message object object
	                .setChatId(chat_id)
	                .setText(message_text);
	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }*/
   }

    
}