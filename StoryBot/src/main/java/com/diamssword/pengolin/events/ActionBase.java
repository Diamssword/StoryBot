package com.diamssword.pengolin.events;

import java.util.HashSet;
import java.util.Set;

public abstract class ActionBase implements IAction{
	private Set<IActionExecutor> executors = new HashSet<IActionExecutor>();
	private boolean finished=false;
	public ActionBase()
	{
		
	}
	public ActionBase(IActionExecutor handler)
	{
		this.registerExecutor(handler);
	}
	public void finish()
	{
		this.finished = true;
		for(IActionExecutor ex :this.executors)
		{
			ex.nextAction(this);
		}
	}
	public boolean finished()
	{
		return this.finished;
	}
	@Override
	public void registerExecutor(IActionExecutor executor) {
		executors.add(executor);
	}

}
