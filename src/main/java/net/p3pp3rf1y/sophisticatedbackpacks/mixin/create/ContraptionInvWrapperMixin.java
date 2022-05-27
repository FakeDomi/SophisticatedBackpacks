package net.p3pp3rf1y.sophisticatedbackpacks.mixin.create;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackInventoryHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(targets = "com/simibubi/create/content/contraptions/components/structureMovement/Contraption$ContraptionInvWrapper", remap = false)
public class ContraptionInvWrapperMixin {
	@Inject(method = "isSlotExternal", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	public void sophisticatedBackpacks_isSlotExternal(int slot, CallbackInfoReturnable<Boolean> cir, IItemHandlerModifiable handler) {
		if (handler instanceof BackpackInventoryHandler) {
			cir.setReturnValue(true);
		}
	}
}
