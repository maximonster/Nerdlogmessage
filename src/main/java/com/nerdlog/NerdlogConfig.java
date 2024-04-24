package com.nerdlog;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface NerdlogConfig extends Config
{
	@ConfigItem(
		keyName = "Nerdmessage",
		name = "Nerdlog message",
		description = "The message to show to the user when they login",
			position = 1

	)
	default String Nerdmessage()
	{
		return "Nerd nerd afgekeurd";
	}
	@ConfigItem(
			keyName = "Nerdiermessage",
			name = "MinutestoNerdlog",
			description = "The message to show to the user when they login",
			position = 2
	)
	default String Nerdiermessage()
	{
		return "Nerd nerd afgekeurd over [Time] minuten";
	}
}
