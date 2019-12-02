package com.diamssword.pengolin.events.atcommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ATHelp implements IATCommand{

	@Override
	public void trigger(MessageReceivedEvent event, String after) {
		if(after.trim().equalsIgnoreCase("help"))
		{
			String hlp = "**Help for StoryBot:** \n"+
					"";
			event.getChannel().sendMessage(hlp).queue();
		}

	}

}
