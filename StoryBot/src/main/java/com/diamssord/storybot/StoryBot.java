package com.diamssord.storybot;

import javax.security.auth.login.LoginException;

import com.diamssord.storybot.events.BaseEvents;
import com.diamssord.storybot.events.EmoteQuestionAction;
import com.diamssord.storybot.events.EmoteQuestionAction.ISubAction;
import com.diamssord.storybot.events.atcommands.AtCommandsEvent;
import com.diamssord.storybot.events.torage.Storage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class StoryBot {
	public static JDA bot;
	public static void main(String[] args) {
		try {
			String token = "NjQ0Mjc4M!TIxODAxMTg3MzI5.!Xc_3RQ.PwwtLt8Vx0HNjzwPx-9W3Oix6Oc!";
			bot =new JDABuilder().setToken(token.replaceAll("!", "")).build();
			bot.addEventListener(new BaseEvents());
			bot.awaitReady();
			Storage.loadData();
			Storage.getData(bot.getGuilds().get(0).getId()).put("hey", "nope");
			Storage.saveData(bot.getGuilds().get(0).getId());
			TextChannel chan =bot.getGuilds().get(0).getTextChannels().get(0);
			EmoteQuestionAction act =new EmoteQuestionAction() ;
			ISubAction sub =new ISubAction() {
				@Override
				public void execute() {}
				@Override
				public String title() {
					return "yes";}
				@Override
				public int emote() {
					return 0x2705;}
			};
			ISubAction sub1 =new ISubAction() {
				@Override
				public void execute() {}
				@Override
				public String title() {
					return "no";}
				@Override
				public int emote() {
					return 0x274C;}
			};			
			
			act.createMsg(chan, "hey",sub,sub1);
			new AtCommandsEvent();
		} catch (LoginException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
