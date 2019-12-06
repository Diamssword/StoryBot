package com.diamssword.pengolin.events.atcommands;

import com.diamssword.pengolin.events.storage.Storage;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ATSetChannel implements IATCommand{
	
	@Override
	public void trigger(MessageReceivedEvent event, String after) {
		if(after.trim().toLowerCase().startsWith("set channel"))
		{
			Storage.putAndSaveData(event.getGuild().getId(), Storage.KEY_CHAN, event.getChannel().getId());
			event.getChannel().sendMessage("Channel <#"+event.getChannel().getId()+"> will be used as the main game channel").queue();
		}

	}

}
