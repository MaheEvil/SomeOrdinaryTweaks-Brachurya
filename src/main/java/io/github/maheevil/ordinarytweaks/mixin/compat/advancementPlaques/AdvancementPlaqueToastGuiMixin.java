package io.github.maheevil.ordinarytweaks.mixin.compat.advancementPlaques;

//import com.anthonyhilyard.advancementplaques.AdvancementPlaquesToastGui;
import io.github.maheevil.ordinarytweaks.SomeOrdinaryTweaksMod;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.gui.components.toasts.AdvancementToast;
import net.minecraft.client.gui.components.toasts.Toast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = {@Condition("advancementplaques")})
@Pseudo
@Mixin(targets = "com.anthonyhilyard.advancementplaques.AdvancementPlaquesToastGui")
public class AdvancementPlaqueToastGuiMixin {
    @Inject(
            // This will not work on dev environment I am pretty sure, ony in prod
            method = "Lcom/anthonyhilyard/advancementplaques/AdvancementPlaquesToastGui;method_1999(Lnet/minecraft/class_368;)V",//method = "addToast",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    public void inJectAtHeadAddToast(Toast toast, CallbackInfo ci){
        if(toast instanceof AdvancementToast && SomeOrdinaryTweaksMod.config.hideAdvancementToasts)
            ci.cancel();
    }
}
