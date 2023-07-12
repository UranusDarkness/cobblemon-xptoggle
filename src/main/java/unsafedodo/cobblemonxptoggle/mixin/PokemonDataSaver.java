package unsafedodo.cobblemonxptoggle.mixin;

import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unsafedodo.cobblemonxptoggle.util.IPokemonDataSaver;

@Mixin(Pokemon.class)
public class PokemonDataSaver implements IPokemonDataSaver {
    private NbtCompound persistentData;
    @Override
    public NbtCompound getPersistentData() {
        if(this.persistentData == null)
            this.persistentData = new NbtCompound();

        return persistentData;
    }

    @Inject(method = "saveToNBT", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info){
        if(persistentData != null)
            nbt.put("xptoggle.xplock", persistentData);
    }

    @Inject(method = "loadFromNBT", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfoReturnable<Pokemon> cir){
        if(nbt.contains("xptoggle.xplock", 1)){
            persistentData = nbt.getCompound("xptoggle.xplock");
        }
    }
}
