package unsafedodo.cobblemonxptoggle;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unsafedodo.cobblemonxptoggle.command.XpToggleCommand;

public class CobblemonXpToggle implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("cobblemon-xptoggle");

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(unsafedodo.cobblemonxptoggle.command.XpToggleCommand::register);
	}
}