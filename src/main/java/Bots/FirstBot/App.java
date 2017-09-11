package Bots.FirstBot;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Hello world!
 *
 */
public class App extends ListenerAdapter {
	
	//»
	public static final String token = 
			"MzU2Njc3MTE2OTE5Njc2OTI4.DJe8BQ.jT5PJ84GWoFJdxXU-suZdoRm3Uo";
	public final String helpString = 
			   "<!>hello,hi,hey » Bot will reply with a friendly greeting.\n"
	         + "<!>roll [integer] » Bot will return a number from 1-6 or 1-integer.\n"
	         + "<!>pal,palindrome <word> » Bot will tell you whether or not your word is a palindrome.\n"
	         + "<!>goodnight,night » Goodnight fair user.\n"
	         + "<!>thanks,thankyou » Even bots appreciate some thanks!\n"
	         + "<!>sweet » Try it!\n"
	         + "<!>logoff » Bot will signout! --UNFINISHED";
	public final String commandUnfinished = "This command is not finished as of yet, try again soon!";
	
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
    	//Initialize the bot
    	try{
    		JDA jdaBot = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
            jdaBot.setAutoReconnect(true);
            jdaBot.addEventListener(new App());
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	Message msg = event.getMessage();
    	MessageChannel msgChannel = event.getChannel(); 
    	User author = event.getAuthor();
    	
    	if(msg.getContent().charAt(0) == '!') {
    		String[] args = msg.getContent().substring(1).split(" "); 
    		//!roll 100
    		//roll is index 0, 100 is index 1 (1,2)
    		
    		if(args[0].equals("help")) {
    			msgChannel.sendMessage(helpString).complete();
    		}
    		else if(args[0].equals("hello") || args[0].equals("hi") || args[0].equals("hey")) {
        		//can also use .queue()
        		msgChannel.sendMessage("Hello, " + author.getAsMention() + "!").complete();	
        	}
    		else if(args[0].equals("roll")) {
    			int numSides = 6;
    			if(args.length > 1) {
    				numSides = Integer.valueOf(args[1]);
    			}
    			int numToSend = (int)(Math.random() * numSides + 1);
    			msgChannel.sendMessage(/*author.getAsMention() + */"You rolled a...").complete();	
    			try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msgChannel.sendMessage("...calculating...").complete();	
    			try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msgChannel.sendMessage(numToSend + "!").complete();	
    		}
    		else if(args[0].equals("pal") || args[0].equals("palindrome")) {
    			String strPal = args[1]; //second argument
    			boolean pal = true;
    			for(int i = 0; i < strPal.length(); i++) {
    				if(strPal.charAt(i) != strPal.charAt(strPal.length() - 1 - i)) {
    					pal = false;
    				}
    			}
    			final String newStrPal = strPal.substring(0,1).toUpperCase() + strPal.substring(1).toLowerCase();
    			if(pal) {
    				msgChannel.sendMessage(newStrPal + " is a palindrome!").complete();	
    			}
    			else {
    				msgChannel.sendMessage(newStrPal + " is not a palindrome!").complete();
    			}
    		}
    		else if(args[0].equals("thanks") || args[0].equals("thankyou")) {
    			msgChannel.sendMessage("You're quite welcome, " + author.getAsMention() + "! :)").complete();
    		}
    		else if(args[0].equals("goodnight") || args[0].equals("night")) {
    			msgChannel.sendMessage("Good night, " + author.getAsMention() + "! :)").complete();
    		}
    		else if(args[0].equals("sweet")) {
    			msgChannel.sendMessage("Glad you appreciate me, " + author.getAsMention() + "!").complete();
    		}
    		else if(args[0].equals("logoff")) {
    			msgChannel.sendMessage(commandUnfinished).complete();
    		}
    	}
    }
}
