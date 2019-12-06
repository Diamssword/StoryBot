package com.diamssword.pengolin.events.atcommands;

import java.util.ArrayList;
import java.util.List;

import com.diamssword.pengolin.StoryBot;
import com.diamssword.pengolin.events.BaseEvents;
import com.diamssword.pengolin.events.IEventSub;

import net.dv8tion.jda.api.entities.Message.MentionType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AtCommandsEvent implements IEventSub<MessageReceivedEvent>{

	private List<IATCommand> cmds = new ArrayList<IATCommand>();
	public static AtCommandsEvent instance = new AtCommandsEvent();
	public AtCommandsEvent()
	{
		BaseEvents.registerEvent.add(this);
	}
	public static void addCommand(IATCommand command)
	{
		instance.cmds.add(command);
	}
	@Override
	public boolean unsub() {
		return false;
	}
	@Override
	public void onEvent(MessageReceivedEvent event) {
		if(event.getMessage().isMentioned(StoryBot.bot.getSelfUser(), MentionType.USER))
		{
			String trimmed=event.getMessage().getContentRaw().trim();
			if(trimmed.startsWith("<@"))
			{
				int po =trimmed.indexOf('>');
				if(po >-1)
				{
					String id =trimmed.substring(trimmed.indexOf("<@")+2,po);
					if(id.equals(StoryBot.bot.getSelfUser().getId()))
					{
						String after= trimmed.substring(po+1);
						for(IATCommand cmd : cmds)
						{
							cmd.trigger(event, after);
						}
					}
				}
			}
		}
	}
	@Override
	public Class<? extends MessageReceivedEvent> getType() {
		return MessageReceivedEvent.class;}
}
