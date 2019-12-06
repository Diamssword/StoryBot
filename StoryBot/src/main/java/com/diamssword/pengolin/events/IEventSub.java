package com.diamssword.pengolin.events;

import net.dv8tion.jda.api.events.Event;

public interface IEventSub <T extends Event> {

	public boolean unsub();
	public void onEvent(T event);
	public Class<? extends T> getType();
	default String getEventData() {return "null";};
}
