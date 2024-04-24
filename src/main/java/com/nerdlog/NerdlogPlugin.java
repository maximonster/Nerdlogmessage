package com.nerdlog;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class NerdlogPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private NerdlogConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		String chatMsg = Text.removeTags(event.getMessage()); //remove color and linebreaks
		if (chatMsg.contains("You've been playing for a while, consider taking a break from your screen.!")&& event.getType() == ChatMessageType.GAMEMESSAGE)
		{
			event.getMessageNode().setValue(config.Nerdmessage());
			client.refreshChat();
		}
		if (chatMsg.contains("You will be logged out in approximately")&& event.getType() == ChatMessageType.GAMEMESSAGE)
		{
			int minutes =  Integer.valueOf(chatMsg.replace("You will be logged out in approximately ","").replace(" minutes. Make sure you move to a safe area or log out now.",""));
			config.Nerdiermessage().replace("[Time]", Integer.toString(minutes));
			event.getMessageNode().setValue(config.Nerdiermessage());
			client.refreshChat();
		}
	}

	@Provides
	NerdlogConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NerdlogConfig.class);
	}
}
