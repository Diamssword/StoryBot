package com.diamssord.storybot.events.atcommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface IATCommand {

	public void trigger(MessageReceivedEvent event, String after);
}
