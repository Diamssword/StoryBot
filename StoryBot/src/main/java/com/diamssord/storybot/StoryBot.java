package com.diamssord.storybot;

import javax.security.auth.login.LoginException;

import com.diamssord.storybot.events.BaseEvents;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class StoryBot {

	public static void main(String[] args) {
		try {
			JDA bot =new JDABuilder().setToken("NjQ0Mjc4MTIxODAxMTg3MzI5.Xcxsyw.f6JpupWn0CnJysVtz1j1nxCc1vM").build();
			bot.addEventListener(new BaseEvents());
		} catch (LoginException e) {
			e.printStackTrace();
		}

	}

}
