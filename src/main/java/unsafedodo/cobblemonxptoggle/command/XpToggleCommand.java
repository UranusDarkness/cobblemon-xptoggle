package unsafedodo.cobblemonxptoggle.command;

import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import unsafedodo.cobblemonxptoggle.util.IPokemonDataSaver;
import java.util.Objects;

public class XpToggleCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment){
        dispatcher.register(CommandManager.literal("xptoggle").then(CommandManager.argument("slot", IntegerArgumentType.integer(1,6)).executes(XpToggleCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException{
        PlayerPartyStore party = PlayerExtensionsKt.party(Objects.requireNonNull(context.getSource().getPlayer()));
        int slot = IntegerArgumentType.getInteger(context, "slot");
        IPokemonDataSaver selectedPokemon = (IPokemonDataSaver) party.get(slot);
        if(selectedPokemon != null){
            int togglevalue = selectedPokemon.getPersistentData().getInt("xptoggle.xplock");
            if(togglevalue == 0){
                selectedPokemon.getPersistentData().putInt("xptoggle.xplock", 0);
                context.getSource().sendFeedback(Text.literal(String.format("Exp gain for %s has been disabled",
                        Objects.requireNonNull(party.get(slot)).getDisplayName().toString()).formatted(Formatting.GREEN)), false);
            }
            else{
                selectedPokemon.getPersistentData().putInt("xptoggle.xplock", 1);
                context.getSource().sendFeedback(Text.literal(String.format("Exp gain for %s has been enabled",
                        Objects.requireNonNull(party.get(slot)).getDisplayName().toString()).formatted(Formatting.GREEN)), false);
            }
            return 0;
        }
        else
            return -1;
    }
}
