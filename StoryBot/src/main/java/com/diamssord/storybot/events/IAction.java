package com.diamssord.storybot.events;

public interface IAction {

	public void execute();
	public boolean finished();
	public void registerExecutor(IActionExecutor executor);
}
