package com.diamssword.pengolin.events.chcommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface IChCommand {

	public void trigger(MessageReceivedEvent event, String... args);
	public String[] key();
}
