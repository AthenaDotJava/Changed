
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.changedmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.changedmod.item.WrenchItem;
import net.mcreator.changedmod.item.WhiteTailChasePart2Item;
import net.mcreator.changedmod.item.WhiteTailChasePart1Item;
import net.mcreator.changedmod.item.WhiteGooJungleItem;
import net.mcreator.changedmod.item.VentPipeItem;
import net.mcreator.changedmod.item.TheWhiteKnightItem;
import net.mcreator.changedmod.item.TheSharkItem;
import net.mcreator.changedmod.item.TheLibraryItem;
import net.mcreator.changedmod.item.TheBadEndItem;
import net.mcreator.changedmod.item.SquidDogItem;
import net.mcreator.changedmod.item.ScarletCrystalMineItem;
import net.mcreator.changedmod.item.PurosHomeItem;
import net.mcreator.changedmod.item.PuroAndTheBlackGooItem;
import net.mcreator.changedmod.item.PURODANCEItem;
import net.mcreator.changedmod.item.OutsideTheTowerItem;
import net.mcreator.changedmod.item.LionChaseItem;
import net.mcreator.changedmod.item.LaboratoryItem;
import net.mcreator.changedmod.item.KeyCardItem;
import net.mcreator.changedmod.item.GasRoomItem;
import net.mcreator.changedmod.item.FinaleItem;
import net.mcreator.changedmod.item.CrystalZoneItem;
import net.mcreator.changedmod.item.CrystalDragonItem;
import net.mcreator.changedmod.item.CruelTruthItem;
import net.mcreator.changedmod.item.CheetarChaseItem;
import net.mcreator.changedmod.item.CatChaseItem;
import net.mcreator.changedmod.item.BlackGooZoneItem;
import net.mcreator.changedmod.item.BirthdaySongItem;
import net.mcreator.changedmod.ChangedMod;

public class ChangedModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ChangedMod.MODID);
	public static final RegistryObject<Item> PURO = REGISTRY.register("puro_spawn_egg",
			() -> new ForgeSpawnEggItem(ChangedModEntities.PURO, -15329770, -3092272, new Item.Properties().tab(ChangedModTabs.TAB_CHANGED)));
	public static final RegistryObject<Item> TIGER_SHARK = REGISTRY.register("tiger_shark_spawn_egg",
			() -> new ForgeSpawnEggItem(ChangedModEntities.TIGER_SHARK, -1, -14803426, new Item.Properties().tab(ChangedModTabs.TAB_CHANGED)));
	public static final RegistryObject<Item> WHITE_GOO_BLOB = REGISTRY.register("white_goo_blob_spawn_egg",
			() -> new ForgeSpawnEggItem(ChangedModEntities.WHITE_GOO_BLOB, -1, -7434610, new Item.Properties().tab(ChangedModTabs.TAB_CHANGED)));
	public static final RegistryObject<Item> SAVE_STATION = block(ChangedModBlocks.SAVE_STATION, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> DRAIN = block(ChangedModBlocks.DRAIN, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> VENT = block(ChangedModBlocks.VENT, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> CAUTION_FLOOR = block(ChangedModBlocks.CAUTION_FLOOR, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> THE_WHITE_KNIGHT = REGISTRY.register("the_white_knight", () -> new TheWhiteKnightItem());
	public static final RegistryObject<Item> CAT_CHASE = REGISTRY.register("cat_chase", () -> new CatChaseItem());
	public static final RegistryObject<Item> BLACK_GOO_ZONE = REGISTRY.register("black_goo_zone", () -> new BlackGooZoneItem());
	public static final RegistryObject<Item> CRYSTAL_ZONE = REGISTRY.register("crystal_zone", () -> new CrystalZoneItem());
	public static final RegistryObject<Item> CRYSTAL_DRAGON = REGISTRY.register("crystal_dragon", () -> new CrystalDragonItem());
	public static final RegistryObject<Item> THE_LIBRARY = REGISTRY.register("the_library", () -> new TheLibraryItem());
	public static final RegistryObject<Item> WHITE_TAIL_CHASE_PART_1 = REGISTRY.register("white_tail_chase_part_1",
			() -> new WhiteTailChasePart1Item());
	public static final RegistryObject<Item> WHITE_TAIL_CHASE_PART_2 = REGISTRY.register("white_tail_chase_part_2",
			() -> new WhiteTailChasePart2Item());
	public static final RegistryObject<Item> PURO_AND_THE_BLACK_GOO = REGISTRY.register("puro_and_the_black_goo", () -> new PuroAndTheBlackGooItem());
	public static final RegistryObject<Item> VENT_PIPE = REGISTRY.register("vent_pipe", () -> new VentPipeItem());
	public static final RegistryObject<Item> PUROS_HOME = REGISTRY.register("puros_home", () -> new PurosHomeItem());
	public static final RegistryObject<Item> GAS_ROOM = REGISTRY.register("gas_room", () -> new GasRoomItem());
	public static final RegistryObject<Item> CHEETAR_CHASE = REGISTRY.register("cheetar_chase", () -> new CheetarChaseItem());
	public static final RegistryObject<Item> THE_SHARK = REGISTRY.register("the_shark", () -> new TheSharkItem());
	public static final RegistryObject<Item> SQUID_DOG = REGISTRY.register("squid_dog", () -> new SquidDogItem());
	public static final RegistryObject<Item> WHITE_GOO_JUNGLE = REGISTRY.register("white_goo_jungle", () -> new WhiteGooJungleItem());
	public static final RegistryObject<Item> LION_CHASE = REGISTRY.register("lion_chase", () -> new LionChaseItem());
	public static final RegistryObject<Item> LABORATORY = REGISTRY.register("laboratory", () -> new LaboratoryItem());
	public static final RegistryObject<Item> SCARLET_CRYSTAL_MINE = REGISTRY.register("scarlet_crystal_mine", () -> new ScarletCrystalMineItem());
	public static final RegistryObject<Item> THE_BAD_END = REGISTRY.register("the_bad_end", () -> new TheBadEndItem());
	public static final RegistryObject<Item> OUTSIDE_THE_TOWER = REGISTRY.register("outside_the_tower", () -> new OutsideTheTowerItem());
	public static final RegistryObject<Item> FINALE = REGISTRY.register("finale", () -> new FinaleItem());
	public static final RegistryObject<Item> CRUEL_TRUTH = REGISTRY.register("cruel_truth", () -> new CruelTruthItem());
	public static final RegistryObject<Item> BIRTHDAY_SONG = REGISTRY.register("birthday_song", () -> new BirthdaySongItem());
	public static final RegistryObject<Item> PURODANCE = REGISTRY.register("purodance", () -> new PURODANCEItem());
	public static final RegistryObject<Item> BAR = block(ChangedModBlocks.BAR, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> WRENCH = REGISTRY.register("wrench", () -> new WrenchItem());
	public static final RegistryObject<Item> CAPSULE = block(ChangedModBlocks.CAPSULE, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> TERMINAL = block(ChangedModBlocks.TERMINAL, ChangedModTabs.TAB_CHANGED);
	public static final RegistryObject<Item> KEY_CARD = REGISTRY.register("key_card", () -> new KeyCardItem());
	public static final RegistryObject<Item> PHOTO_PILE = block(ChangedModBlocks.PHOTO_PILE, ChangedModTabs.TAB_CHANGED);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
