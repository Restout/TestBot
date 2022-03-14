package BotOne;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.channels.ServerSocketChannel;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.channel.ServerVoiceChannelUpdater;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.TrackMarker;
public class Main {

	public static void main(String[] args) {
        // Insert your bot's token here
        String token = "OTAyNjc4ODI0NTQwNTY5NjAw.YXh7Kg.PY5HSLkAXFcAG8Qozndk8A9BTN0";
        EmbedBuilder emb=new EmbedBuilder()
        		.setTitle("Кто такой максим?")
        		.setDescription("```Максим в первую очередь неплохой человек, сварщик, варит пельмени, а во вторую ну чисто gay```")
        		.setColor(Color.CYAN)
        		.setFooter("https://vk.com/mchubrik")
        		.setImage(new File("C:\\Users\\artem\\OneDrive\\Рабочий стол\\maxresdefault.jpg"))
.setThumbnail(new File("C:\\Users\\artem\\OneDrive\\Рабочий стол\\maxresdefault.jpg"));
        
        MessageBuilder special=new MessageBuilder()
        		.append("Вы только посмотрите на этих")
        		.append("Лучших друзей",MessageDecoration.STRIKEOUT,MessageDecoration.UNDERLINE)
        		.append( ":watermelon:")
        .addAttachment(new File("C:\\Users\\artem\\OneDrive\\Рабочий стол\\gay.jpg"))
        .setEmbed(new EmbedBuilder()
        		.setTitle("WOW")
        		.setDescription("GayShit")
        		.setColor(Color.DARK_GRAY).setFooter("https://vk.com/zonnnnn"));
        
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        
//OwnListener first=new OwnListener();
        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event-> {
        	
            if (event.getMessageContent().equalsIgnoreCase("!honor")) {
                event.getChannel().sendMessage("No music!");
      
            }
            if (event.getMessageContent().equalsIgnoreCase("!Maks")) {
                event.getChannel().sendMessage(emb);
      
            }
            if(event.getMessageContent().equalsIgnoreCase("!mpf")) {
            	/*new MessageBuilder()
                .append("Look at these ")
                .append("awesome", MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                .append(" animal pictures! 😃")
                .appendCode("java", "System.out.println(\"Sweet!\");")
                .addAttachment(new File("C:\\\\Users\\\\artem\\\\OneDrive\\\\Рабочий стол\\\\maxresdefault.jpg"))
                .addAttachment(new File("C:\\\\Users\\\\artem\\\\OneDrive\\\\Рабочий стол\\\\maxresdefault.jpg"))
                .setEmbed(new EmbedBuilder()
                        .setTitle("WOW")
                        .setDescription("Really cool pictures!")
                        .setColor(Color.ORANGE))
                .send(event.getChannel());*/
            	
            
            	//event.getChannel().sendMessage("works");
            	special.send(event.getMessageAuthor().asUser().get());
            	special.send(event.getChannel());	
            			
        }
            
        });
        api.addMessageCreateListener(event->{
        		String content=event.getMessageContent().substring(0, 4), url=event.getMessageContent().substring(5);
        	event.getChannel().sendMessage(content);
        	  ServerVoiceChannel channel = event.getMessageAuthor().getConnectedVoiceChannel().get();
        	  AudioPlayerManager playerManager = new DefaultAudioPlayerManager();    
              playerManager.registerSourceManager(new YoutubeAudioSourceManager());
              AudioPlayer player =playerManager.createPlayer();
              AudioSource source=new LavaplayerAudioSource(api,player);
        	if(content.equalsIgnoreCase("!mus")) {
        	
        		 
     if(!channel.isConnected(902678824540569600L)){
       channel.connect().thenAccept(audioConnection->{
    	 System.out.print("CONNECTED");
    	 
         audioConnection.setAudioSource(source);
         
       }).exceptionally(e->{
     	  System.out.println( e.getLocalizedMessage());
     	  e.printStackTrace();
     	  return null;
       });
     }
         playerManager.loadItem(url	, new AudioLoadResultHandler() {
        	
			public void trackLoaded(AudioTrack track) {
				player.playTrack(track);
			}
			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
			for(AudioTrack track:playlist.getTracks()){
				player.playTrack(track);
				
			}
				
			}
			@Override
			public void noMatches() {
				 event.getChannel().sendMessage("No matches for your URL");
				
			}
			@Override
			public void loadFailed(FriendlyException exception) {
				 event.getChannel().sendMessage("Something went wrong");
				
			}
         
         });
       
      
       
     
       
        	}
        	if(event.getMessageContent().equalsIgnoreCase("!lea")) {
        		if(channel.isConnected(902678824540569600L)) {
        			event.getChannel().sendMessage("Disconnected");
        			player.stopTrack();
        			
        			
        	
        		
        			
        		}
        	}
        	
        });
        
       

       
     //  api.addListener(first);
      																							
       
            

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
	
	
}
