package com.diamssord.storybot.events;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BaseEvents extends ListenerAdapter 
{
    public static List<IEventSub<?>> registerEvent = new ArrayList<IEventSub<?>>();
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
    	List<IEventSub<MessageReceivedEvent>> delete = new ArrayList<IEventSub<MessageReceivedEvent>>();
    	if(!event.getMember().getUser().isBot() && !event.getMember().getUser().isFake())
    	{
    		for(IEventSub<?> sub : registerEvent)
    		{
    			if(sub.getType().isAssignableFrom(MessageReceivedEvent.class))
    			{
    				@SuppressWarnings("unchecked")
					IEventSub<MessageReceivedEvent> sub1 = (IEventSub<MessageReceivedEvent>) sub;
    				if(sub1.unsub()) 
    				{
    					delete.add(sub1);
    				}
    				else
    				{
    					sub1.onEvent(event);
    				}
    			}
    		}
    		registerEvent.removeAll(delete);
    	}
        User author = event.getAuthor();
        Message message = event.getMessage();
            System.out.println(author.getAsTag() + ": " + message.getContentDisplay());
    }
    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event)
    {
    	List<IEventSub<GuildMessageReactionAddEvent>> delete = new ArrayList<IEventSub<GuildMessageReactionAddEvent>>();
    	if(!event.getUser().isBot() && !event.getUser().isFake())
    	{
    		for(IEventSub<?> sub : registerEvent)
    		{
    			if(sub.getType().isAssignableFrom(GuildMessageReactionAddEvent.class))
    			{
    				@SuppressWarnings("unchecked")
					IEventSub<GuildMessageReactionAddEvent> sub1 = (IEventSub<GuildMessageReactionAddEvent>) sub;
    				if(sub1.unsub())
    				{
    					delete.add(sub1);
    				}
    				else
    				{
    					sub1.onEvent(event);
    				}
    			}
    		}
    		registerEvent.removeAll(delete);
    	}
    }
    
}