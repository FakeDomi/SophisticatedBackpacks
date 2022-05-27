package net.p3pp3rf1y.sophisticatedbackpacks.mixin.create;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackInventoryHandler;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com/simibubi/create/content/contraptions/components/structureMovement/MountedStorage", remap = false)
public class MountedStorageMixin {
	@Unique
	private final static String BACKPACK_INVENTORY_HANDLER_CLASS = BackpackInventoryHandler.class.getCanonicalName();

	@Shadow
	ItemStackHandler handler;

	@Shadow
	boolean valid;

	public MountedStorageMixin(BlockEntity be) {
	}

	@Inject(method = "serialize", at = @At("HEAD"), cancellable = true)
	public void sophisticatedBackpacks_serialize(CallbackInfoReturnable<CompoundTag> cir) {
		if (this.handler instanceof BackpackInventoryHandler h) {
			CompoundTag tag = new CompoundTag();
			tag.putString("Class", h.getClass().getCanonicalName());
			tag.put("Backpack", h.getBackpackWrapper().getBackpack().serializeNBT());

			cir.setReturnValue(tag);
		}
	}

	@Inject(method = "deserialize", at = @At("HEAD"), cancellable = true)
	private static void sophisticatedBackpacks_deserialize(CompoundTag tag, CallbackInfoReturnable<MountedStorageMixin> cir) {
		if (tag != null && BACKPACK_INVENTORY_HANDLER_CLASS.equals(tag.getString("Class"))) {
			MountedStorageMixin storage = new MountedStorageMixin(null);

			storage.handler = new BackpackWrapper(ItemStack.of(tag.getCompound("Backpack"))).getInventoryHandler();
			storage.valid = true;

			cir.setReturnValue(storage);
		}
	}
}
