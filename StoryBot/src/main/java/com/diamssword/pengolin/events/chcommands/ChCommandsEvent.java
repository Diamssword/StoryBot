package com.diamssword.pengolin.events.chcommands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diamssword.pengolin.StoryBot;
import com.diamssword.pengolin.events.BaseEvents;
import com.diamssword.pengolin.events.ChannelMessageEvent;
import com.diamssword.pengolin.events.IEventSub;
import com.diamssword.pengolin.events.atcommands.ATSetChannel;
import com.diamssword.pengolin.events.storage.Storage;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message.MentionType;

public class ChCommandsEvent implements IEventSub<ChannelMessageEvent>{

	private static List<IChCommand> cmds = new ArrayList<IChCommand>();
	private String channelID;
	private static Map<String,ChCommandsEvent> instances = new HashMap<String,ChCommandsEvent>();
	public ChCommandsEvent(String channelID)
	{
		BaseEvents.registerEvent.add(this);
	}
	public static void addCommand(IChCommand command)
	{
		cmds.add(command);
	}
	public static ChCommandsEvent getInstance(Guild guild)
	{
		ChCommandsEvent r =instances.get(guild.getId());

		if(r==null ||Storage.getData(guild.getId()).get(Storage.KEY_CHAN_REFRESH).equalsIgnoreCase("true"))
		{
			Storage.getData(guild.getId()).put(Storage.KEY_CHAN_REFRESH, "false");
			String id = Storage.getData(guild.getId()).get(Storage.KEY_CHAN);
			if(id != null && id.length() >10)
				r = new ChCommandsEvent(id);
			instances.put(guild.getId(),r);
		}
		return r;
	}
	@Override
	public boolean unsub() {
		return false;
	}
	@Override
	public void onEvent(ChannelMessageEvent event) {
		for(IChCommand com : cmds)
		{
			for(String key : com.key())
			{
			if(event.getMessage().getContentRaw().trim().startsWith(key))
			{
				com.trigger(event, event.getMessage().getContentRaw().replaceFirst(key, "").split(" "));
			}
			}
		}
	}
	@Override
	public Class<? extends ChannelMessageEvent> getType() {
		return ChannelMessageEvent.class;}
	public String getEventData() 
	{
		return this.channelID;
	}
}
