package net.mcreator.microcosm.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class FarmHouseGuideBookProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency entity for procedure FarmHouseGuideBook!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"give @p written_book{pages:['[\"\",{\"text\":\"GETTING STARTED: \",\"bold\":true},{\"text\":\"Put down the Farmhouse, fill in the 3x3 area around it with \",\"color\":\"reset\"},{\"text\":\"Empty Plots\",\"color\":\"dark_blue\"},{\"text\":\".  Provide the Farmhouse with at least one seed or crop to harvest in the top row.  The Farmhouse will slowly turn \",\"color\":\"reset\"},{\"text\":\"Empty Plots\",\"color\":\"dark_blue\"},{\"text\":\" into \",\"color\":\"reset\"},{\"text\":\"Plowed Fields\",\"color\":\"dark_blue\"},{\"text\":\". Which will then be planted and finally harvested.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Leveling:\",\"bold\":true,\"underlined\":true},{\"text\":\" As your Farmhouse harvests crops it will gain XP. It starts at lvl 0 and can currently reach 5.\\\\nlvl 1: Increase to 5x5\\\\nlvl 2: Beekeeper\\\\nlvl 3: 7x7\\\\nlvl 4: Area Saturation\\\\nlvl 5: 9x9\",\"color\":\"reset\"},{\"text\":\" \",\"obfuscated\":true}]','[\"\",{\"text\":\"Irrigated:\",\"bold\":true,\"underlined\":true},{\"text\":\" If a Farmhouse is connected to a \",\"color\":\"reset\"},{\"text\":\"Well\",\"color\":\"dark_blue\"},{\"text\":\" via \",\"color\":\"reset\"},{\"text\":\"Paths\",\"color\":\"dark_blue\"},{\"text\":\" it will fill with water that it will use to increase growth rates slightly.\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"Fertilized:\",\"bold\":true,\"underlined\":true},{\"text\":\" If a \",\"color\":\"reset\"},{\"text\":\"Midden\",\"color\":\"dark_blue\"},{\"text\":\" is placed adjacent to the Farmhouse growth rates will be halved.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Pollinated:\",\"bold\":true,\"underlined\":true},{\"text\":\" If a \",\"color\":\"reset\"},{\"text\":\"Beehive\",\"color\":\"dark_blue\"},{\"text\":\" is placed anywhere in the Farmhouses scanned area it will provide a very small bonus to growth rates. \\\\u0020At Level 2. the Farmhouse can harvest Honey Bottles from these blocks.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Silos\",\"bold\":true,\"underlined\":true,\"color\":\"dark_blue\"},{\"text\":\":\",\"color\":\"reset\",\"bold\":true,\"underlined\":true},{\"text\":\" will take all the inventory from Farmhouses in a 15x15 area and store them in deep storage. \\\\u0020They can hold up to \",\"color\":\"reset\"},{\"text\":\"10 Million\",\"obfuscated\":true},{\"text\":\" of each crop.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Crop Rotation:\",\"bold\":true,\"underlined\":true},{\"text\":\" While what a Farmhouse harvests is random, you can control this. \\\\u0020The chance of a specific crop being harvested is based on the number of seeds/crops you provide as a percentage of the total. 5 carrots and 1 Wheat seed will give carrots around 5 times out of 6.\",\"color\":\"reset\"}]','{\"text\":\"Happy Farming!\\\\n-Shadetree\"}'],title:\"Microcosm Farming\",author:Shadetree,display:{Lore:[\"Grow ALL the things!\"]}}");
			}
		}
	}
}
