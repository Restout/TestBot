package BotOne;

import java.net.URL;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class OwnListener implements MessageCreateListener {

	
	public void onMessageCreate(MessageCreateEvent event) {
		
if(event.getMessageContent().equalsIgnoreCase("!magic")) {
	 URL url=event.getMessageAuthor().getAvatar().getUrl();
	event.getChannel().sendMessage(url.toString());
	
}
	}

}
