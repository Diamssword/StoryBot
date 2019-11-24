package com.diamssord.storybot.events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class EmoteQuestionAction extends ActionBase implements IEventSub<GuildMessageReactionAddEvent>{

	private String messageID="";
	private ISubAction[] answers = new ISubAction[0];
	public EmoteQuestionAction()
	{
		BaseEvents.registerEvent.add(this);
	}
	public void createMsg(TextChannel channel, String message,ISubAction... actions)
	{
		this.answers = actions;
		String msg = message+ ": \n";
		for(ISubAction act :answers)
		{
			msg= msg+String.valueOf(Character.toChars(act.emote()))+" : "+act.title()+"\n";
		}
		msg = msg+"repondez en cliquant sur un des emoji en dessous de ce message.";
		this.messageID = channel.sendMessage(msg).complete().getId();
		for(ISubAction act :answers)
		{
			channel.addReactionById(this.messageID,String.valueOf(Character.toChars(act.emote()))).queue();;
		}
	}
	public EmoteQuestionAction(IActionExecutor exec)
	{
		super(exec);
		BaseEvents.registerEvent.add(this);
	}
	@Override
	public void execute() {

	}
	@Override
	public boolean unsub() {
		return this.finished();
	}
	@Override
	public void onEvent(GuildMessageReactionAddEvent event) {
		if(event.getMessageId().equals(messageID) && !this.finished())
		{
			try {
				int code =Integer.parseInt(event.getReactionEmote().getAsCodepoints().replaceFirst("U+", ""),16);
				for(ISubAction sub : answers)
				{

					if(sub.emote()==code)
					{
						sub.execute(event);
						this.finish();
					}
				}
			}catch(NumberFormatException e) {};

		}

	}
	@Override
	public Class<? extends GuildMessageReactionAddEvent> getType() {
		return GuildMessageReactionAddEvent.class;
	}
	public static interface ISubAction
	{
		public void execute(GuildMessageReactionAddEvent event);
		public String title();
		public int emote();
	}

}
