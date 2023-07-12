package unsafedodo.cobblemonxptoggle.mixin;

import com.cobblemon.mod.common.api.pokemon.experience.ExperienceCalculator;
import com.cobblemon.mod.common.api.pokemon.experience.StandardExperienceCalculator;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unsafedodo.cobblemonxptoggle.util.IPokemonDataSaver;

@Mixin(StandardExperienceCalculator.class)
public class XpGain {

    @ModifyVariable(method = "calculate", at = @At("STORE"), remap = false)
    private int changeXpGain(int term4, BattlePokemon battlePokemon, BattlePokemon opponentPokemon, double participationMultiplier){
        Pokemon own = battlePokemon.getOriginalPokemon();
        int toggle = ((IPokemonDataSaver) own).getPersistentData().getInt("xptoggle.xplock");
        if(toggle == 0)
            term4 = 0;
        return term4;
    }

}
