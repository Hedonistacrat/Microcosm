package net.mcreator.microcosm.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.microcosm.item.BucketOfThanksItem;
import net.mcreator.microcosm.MicrocosmModVariables;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;
import java.util.HashMap;

public class BenefactorsBonusProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency entity for procedure BenefactorsBonus!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack Gift = ItemStack.EMPTY;
		if ((!((entity.getCapability(MicrocosmModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MicrocosmModVariables.PlayerVariables())).MicrocosmSupporter))) {
			if (((((entity.getDisplayName().getString())).equals("Shadetree")) || ((((entity.getDisplayName().getString())).equals("Halbear_gameZ"))
					|| (((entity.getDisplayName().getString())).equals("Gabbroboccia"))))) {
				Gift = new ItemStack(BucketOfThanksItem.block);
				(Gift).getOrCreateTag().putDouble("Love", 9001);
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = (Gift);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			{
				boolean _setval = (boolean) (true);
				entity.getCapability(MicrocosmModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.MicrocosmSupporter = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
