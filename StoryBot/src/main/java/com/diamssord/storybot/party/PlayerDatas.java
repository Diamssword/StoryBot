package com.diamssord.storybot.party;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;

public class PlayerDatas {
	public final String id;
	public boolean ready;
	public List<String> story;
	public PlayerDatas(String id)
	{
		this.id = id;
	}
	public PlayerDatas(Member m)
	{
		this.id = m.getId();
	}
}
