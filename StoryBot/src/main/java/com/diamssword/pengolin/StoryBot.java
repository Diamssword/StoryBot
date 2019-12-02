package com.diamssword.pengolin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import javax.security.auth.login.LoginException;

import com.diamssword.pengolin.events.BaseEvents;
import com.diamssword.pengolin.events.EmoteQuestionAction;
import com.diamssword.pengolin.events.EmoteQuestionAction.ISubAction;
import com.diamssword.pengolin.events.atcommands.ATHelp;
import com.diamssword.pengolin.events.atcommands.ATSetChannel;
import com.diamssword.pengolin.events.atcommands.AtCommandsEvent;
import com.diamssword.pengolin.events.atcommands.IATCommand;
import com.diamssword.pengolin.events.torage.Storage;
import com.diamssword.pengolin.limite.Limite;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class StoryBot {
	public static JDA bot;
	public static void main(String[] args) {
		try {
			List<String> hey = Limite.getWhiteCards();
			List<String> hey1 = Limite.getBlackCards();
			
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
						public void execute(GuildMessageReactionAddEvent event) {event.getChannel().sendMessage("yes").queue();}
						@Override
						public String title() {
							return "yes";}
						@Override
						public int emote() {
							return 0x2705;}
					};
					ISubAction sub1 =new ISubAction() {
						@Override
						public void execute(GuildMessageReactionAddEvent event) {event.getChannel().sendMessage("no").queue();}
						@Override
						public String title() {
							return "no";}
						@Override
						public int emote() {
							return 0x274C;}
					};			

					act.createMsg(chan, "hey",sub,sub1);
					AtCommandsEvent.addCommand(new IATCommand() {

						@Override
						public void trigger(MessageReceivedEvent event, String after) {
							System.out.println("cmd:"+after);

						}});
					Random r = new Random(); 
					for(int i =0;i<2;i++)
					{
						String str = hey.get(r.nextInt(hey.size()));
						chan.sendMessage(str).queue();
						chan.sendMessage(hey1.get(r.nextInt(hey1.size()))).queue();
						System.out.println(str);
					}
					AtCommandsEvent.addCommand(new ATHelp());
					AtCommandsEvent.addCommand(new ATSetChannel());
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Enter String");
					String s = br.readLine();
					System.out.print("Enter Integer:");
					try {
						int i = Integer.parseInt(br.readLine());
					} catch(NumberFormatException | IOException nfe) {
						System.err.println("Invalid Format!");
					}
		} catch (LoginException | InterruptedException | IOException e) {
			e.printStackTrace();
		}

	}

}
