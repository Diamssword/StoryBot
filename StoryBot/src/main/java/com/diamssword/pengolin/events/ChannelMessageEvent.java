package com.diamssword.pengolin.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ChannelMessageEvent extends MessageReceivedEvent{

	public ChannelMessageEvent(MessageReceivedEvent msg) {
		super(msg.getJDA(), msg.getResponseNumber(), msg.getMessage());
	}

}
