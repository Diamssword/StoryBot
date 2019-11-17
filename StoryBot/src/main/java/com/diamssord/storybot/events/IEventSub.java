package com.diamssord.storybot.events;

import net.dv8tion.jda.api.events.Event;

public interface IEventSub <T extends Event> {

	public boolean unsub();
	public void onEvent(T event);
	public Class<? extends T> getType();
}
