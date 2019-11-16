package com.diamssord.storybot;

import javax.security.auth.login.LoginException;

import com.diamssord.storybot.events.BaseEvents;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class StoryBot {

	public static void main(String[] args) {
		try {
			String token = "NjQ0Mjc4M!TIxODAxMTg3MzI5.!Xc_3RQ.PwwtLt8Vx0HNjzwPx-9W3Oix6Oc!";
			JDA bot =new JDABuilder().setToken(token.replaceAll("!", "")).build();
			bot.addEventListener(new BaseEvents());
		} catch (LoginException e) {
			e.printStackTrace();
		}

	}

}
