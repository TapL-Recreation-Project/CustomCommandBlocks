package me.swipez.customcommandblocks;

import me.swipez.customcommandblocks.utils.ItemBuilder;
import me.swipez.customcommandblocks.utils.StructureUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.loot.LootTables;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class ItemRegistry {

    private static Random random = new Random();

    private static List<Material> allWoodMaterials = new ArrayList<>();

    private static List<Material> allHoeMaterials = new ArrayList<>();

    private static List<Material> allOreMaterials = new ArrayList<>();

    private static List<EntityType> entityTypes = new ArrayList<>();

    static {
        allWoodMaterials.add(Material.ACACIA_WOOD);
        allWoodMaterials.add(Material.BIRCH_WOOD);
        allWoodMaterials.add(Material.DARK_OAK_WOOD);
        allWoodMaterials.add(Material.JUNGLE_WOOD);
        allWoodMaterials.add(Material.OAK_WOOD);
        allWoodMaterials.add(Material.SPRUCE_WOOD);
        allWoodMaterials.add(Material.STRIPPED_ACACIA_WOOD);
        allWoodMaterials.add(Material.STRIPPED_BIRCH_WOOD);
        allWoodMaterials.add(Material.STRIPPED_DARK_OAK_WOOD);
        allWoodMaterials.add(Material.STRIPPED_JUNGLE_WOOD);
        allWoodMaterials.add(Material.STRIPPED_OAK_WOOD);
        allWoodMaterials.add(Material.STRIPPED_SPRUCE_WOOD);
        allWoodMaterials.add(Material.OAK_FENCE);
        allWoodMaterials.add(Material.BIRCH_FENCE);
        allWoodMaterials.add(Material.ACACIA_STAIRS);
        allWoodMaterials.add(Material.DARK_OAK_STAIRS);
        allWoodMaterials.add(Material.BIRCH_STAIRS);
        allWoodMaterials.add(Material.JUNGLE_STAIRS);
        allWoodMaterials.add(Material.OAK_STAIRS);
        allWoodMaterials.add(Material.SPRUCE_STAIRS);
        allWoodMaterials.add(Material.OAK_DOOR);
        allWoodMaterials.add(Material.BIRCH_DOOR);
        allWoodMaterials.add(Material.ACACIA_DOOR);
        allWoodMaterials.add(Material.DARK_OAK_DOOR);
        allWoodMaterials.add(Material.BIRCH_DOOR);
        allWoodMaterials.add(Material.JUNGLE_DOOR);

        allHoeMaterials.add(Material.WOODEN_HOE);
        allHoeMaterials.add(Material.STONE_HOE);
        allHoeMaterials.add(Material.IRON_HOE);
        allHoeMaterials.add(Material.GOLDEN_HOE);
        allHoeMaterials.add(Material.DIAMOND_HOE);
        allHoeMaterials.add(Material.NETHERITE_HOE);

        allOreMaterials.add(Material.COAL_ORE);
        allOreMaterials.add(Material.GOLD_ORE);
        allOreMaterials.add(Material.IRON_ORE);
        allOreMaterials.add(Material.LAPIS_ORE);
        allOreMaterials.add(Material.REDSTONE_ORE);
        allOreMaterials.add(Material.DIAMOND_ORE);
        allOreMaterials.add(Material.EMERALD_ORE);

        entityTypes = Arrays.asList(EntityType.values());
    }

    private static ItemStack WOODEN_COMMAND_BLOCK_ITEM = ItemBuilder.of(Material.BROWN_GLAZED_TERRACOTTA)
            .name(ChatColor.GOLD+"Wooden Command Block")
            .lore(ChatColor.GRAY+"It's a wood apocalypse!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack PICKAXE_COMMAND_BLOCK = ItemBuilder.of(Material.PURPLE_GLAZED_TERRACOTTA)
            .name(ChatColor.DARK_PURPLE+"Pickaxe Command Block")
            .lore(ChatColor.GRAY+"Goodbye chunk!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack POTATO_COMMAND_BLOCK = ItemBuilder.of(Material.YELLOW_GLAZED_TERRACOTTA)
            .name(ChatColor.YELLOW+"Potato Command Block")
            .lore(ChatColor.GRAY+"Potatoesssss")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack HOE_COMMAND_BLOCK = ItemBuilder.of(Material.CYAN_GLAZED_TERRACOTTA)
            .name(ChatColor.AQUA+"Hoe Command Block")
            .lore(ChatColor.GRAY+"Farms... farms everywhere")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack ORE_COMMAND_BLOCK = ItemBuilder.of(Material.BLUE_GLAZED_TERRACOTTA)
            .name(ChatColor.GOLD+"Ore Command Block")
            .lore(ChatColor.GRAY+"Turn anything into an ore!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack MOB_COMMAND_BLOCK = ItemBuilder.of(Material.RED_GLAZED_TERRACOTTA)
            .name(ChatColor.GREEN+"Mob Command Block")
            .lore(ChatColor.GRAY+"They're everywhere!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack ELYTRA_COMMAND_BLOCK = ItemBuilder.of(Material.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .name(ChatColor.DARK_PURPLE+"Elytra Command Block")
            .lore(ChatColor.GRAY+"Fly through the sky!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack EXPLOSIVE_COMMAND_BLOCK = ItemBuilder.of(Material.BLACK_GLAZED_TERRACOTTA)
            .name(ChatColor.RED+"Explosive Command Block")
            .lore(ChatColor.GRAY+"Kaboom!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack NETHERITE_COMMAND_BLOCK = ItemBuilder.of(Material.GREEN_GLAZED_TERRACOTTA)
            .name(ChatColor.DARK_PURPLE+"Netherite Command Block")
            .lore(ChatColor.GRAY+"Turns anything into netherite!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    private static ItemStack COMMAND_COMMAND_BLOCK = ItemBuilder.of(Material.LIGHT_GRAY_GLAZED_TERRACOTTA)
            .name(ChatColor.RED+"Command Command Block")
            .lore(ChatColor.GRAY+"The power of all command blocks...")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static CommandBlockCreator WOOD_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(WOODEN_COMMAND_BLOCK_ITEM, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (Block allBlocks : replaceNearbyBlocks(block, 20)){
                if (allBlocks.getType().toString().contains("LOG")){
                    allBlocks.setType(Material.AIR);
                }
            }
            int loopCount = random.nextInt(30)+30;
            for (int i = 0; i < loopCount; i++){
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(allWoodMaterials.get(random.nextInt(allWoodMaterials.size())), 64));
            }
        }
    }.register();

    public static CommandBlockCreator ORE_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(ORE_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (Block allBlocks : replaceNearbyBlocks(block, 10)){
                if (!allBlocks.getType().isAir()){
                    allBlocks.setType(allOreMaterials.get(random.nextInt(allOreMaterials.size())));
                }
            }
        }
    }.register();

    public static CommandBlockCreator COMMAND_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(COMMAND_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            WOOD_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            ORE_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            NETHERITE_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            EXPLOSIVE_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            ELYTRA_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            POTATO_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            MOB_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            HOE_COMMAND_BLOCK_BLOCK.powerAction(event, block);
            PICKAXE_COMMAND_BLOCK_BLOCK.powerAction(event, block);
        }
    }.register();

    public static CommandBlockCreator NETHERITE_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(NETHERITE_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (Block allBlocks : replaceNearbyBlocks(block, 10)){
                if (!allBlocks.getType().isAir()){
                    allBlocks.setType(Material.NETHERITE_BLOCK);
                }
            }
        }
    }.register();

    public static CommandBlockCreator EXPLOSIVE_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(EXPLOSIVE_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            block.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, block.getLocation(), 6);
            for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 7, 7, 7)){
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    player.playSound(player.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
                }
            }
            for (int i = 0; i < 10; i++){
                int randomX = random.nextInt(3);
                int randomZ = random.nextInt(3);
                if (random.nextBoolean()){
                    randomX *= -1;
                }
                if (random.nextBoolean()){
                    randomZ *= -1;
                }
                Location origin = block.getLocation();
                block.getWorld().spawnEntity(origin.clone().add(randomX, 30, randomZ), EntityType.PRIMED_TNT);
                block.getWorld().dropItemNaturally(origin.clone().add(randomX, 30, randomZ), new ItemStack(Material.TNT, 64));
            }
        }
    }.register();

    public static CommandBlockCreator ELYTRA_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(ELYTRA_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (int i = 0; i < 30; i++){
                int randomX = random.nextInt(10);
                int randomZ = random.nextInt(10);
                if (random.nextBoolean()){
                    randomX *= -1;
                }
                if (random.nextBoolean()){
                    randomZ *= -1;
                }
                Location origin = block.getLocation();
                block.getWorld().spawnEntity(origin.clone().add(randomX, 30, randomZ), EntityType.PHANTOM);
            }
            for (int i = 0; i < 50; i++){
                int randomX = random.nextInt(5);
                int randomZ = random.nextInt(5);
                if (random.nextBoolean()){
                    randomX *= -1;
                }
                if (random.nextBoolean()){
                    randomZ *= -1;
                }
                Location origin = block.getLocation();
                ItemStack itemStack = null;
                if (random.nextBoolean()){
                    itemStack = new ItemStack(Material.ELYTRA, 1);
                }
                else {
                    itemStack = new ItemStack(Material.FIREWORK_ROCKET, 64);
                }
                block.getWorld().dropItemNaturally(origin.clone().add(randomX, 30, randomZ), itemStack);
            }
        }
    }.register();

    public static CommandBlockCreator POTATO_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(POTATO_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 7, 7, 7)){
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
                }
            }
            int randomX = random.nextInt(10);
            int randomY = random.nextInt(5);
            int randomZ = random.nextInt(10);
            if (random.nextBoolean()){
                randomX *= -1;
            }
            if (random.nextBoolean()){
                randomZ *= -1;
            }
            Location origin = block.getLocation();
            StructureUtil.placeWithDissolutionNEW("potato", origin.clone().add(randomX,randomY,randomZ), CustomCommandBlocks.plugin, LootTables.BASTION_HOGLIN_STABLE.getLootTable(), null);
        }
    }.register();

    public static CommandBlockCreator MOB_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(MOB_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 7, 7, 7)){
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);

                }
            }
            for (int i = 0; i < 40; i++){
                int randomX = random.nextInt(15);
                int randomZ = random.nextInt(15);
                if (random.nextBoolean()){
                    randomX *= -1;
                }
                if (random.nextBoolean()){
                    randomZ *= -1;
                }
                Location origin = block.getLocation();
                Location actualLocation = origin.clone().add(randomX, 0 , randomZ);
                actualLocation.setY(actualLocation.getWorld().getHighestBlockAt(randomX, randomZ).getY()+4);

                EntityType entityType = entityTypes.get(random.nextInt(entityTypes.size()));
                if (entityType.isAlive() && !entityType.equals(EntityType.PLAYER) && !entityType.equals(EntityType.ENDER_DRAGON) && !entityType.equals(EntityType.GHAST) && !entityType.equals(EntityType.WITHER)){
                    actualLocation.getWorld().spawnEntity(actualLocation, entityType);
                }
            }
        }
    }.register();

    public static CommandBlockCreator HOE_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(HOE_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 7, 7, 7)){
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    player.playSound(player.getLocation(), Sound.ITEM_HOE_TILL, 1, 1);
                }
            }
            for (int i = 0; i < 3; i++){
                int randomX = random.nextInt(10)+5;
                int randomY = random.nextInt(3);
                int randomZ = random.nextInt(10)+5;
                if (random.nextBoolean()){
                    randomX *= -1;
                }
                if (random.nextBoolean()){
                    randomZ *= -1;
                }
                Location origin = block.getLocation();
                StructureUtil.placeWithDissolutionNEW("farm", origin.clone().add(randomX,randomY,randomZ), CustomCommandBlocks.plugin, LootTables.BASTION_HOGLIN_STABLE.getLootTable(), null);
            }
            for (int i = 0; i < 25; i++){
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(allHoeMaterials.get(random.nextInt(allHoeMaterials.size())), 64));
            }
        }
    }.register();

    public static CommandBlockCreator PICKAXE_COMMAND_BLOCK_BLOCK = new CommandBlockCreator(PICKAXE_COMMAND_BLOCK, CustomCommandBlocks.plugin) {
        @Override
        public void powerAction(BlockRedstoneEvent event, Block block) {
            Chunk chunk = block.getChunk();
            Collection<ItemStack> ItemStacksInRow = new ArrayList<>();
            int loopCount = 0;
            int airComboCount = 0;
            HashMap<Integer, Integer> xAndZPos = new HashMap<>();
            for (int x = 0; x < 16; x++){
                for (int z = 0; z < 16; z++){
                    for (int y = 0; y < 255; y++){
                        Block chunkBlock = chunk.getBlock(x,y,z);
                        if (!chunkBlock.getType().isAir() && !chunkBlock.getType().equals(Material.BEDROCK)){
                            airComboCount = 0;
                            loopCount++;
                            ItemStacksInRow.addAll(chunkBlock.getDrops());
                            BukkitTask task = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (!chunkBlock.getType().equals(Material.CHEST)){
                                        chunkBlock.setType(Material.AIR);
                                    }
                                }
                            }.runTaskLater(CustomCommandBlocks.plugin, (long) (0.01*loopCount));
                        }
                        else {
                            airComboCount++;
                            if (airComboCount == 30){
                                Location location = chunk.getBlock(x,0,z).getLocation();
                                Block checkBlock = location.getBlock();
                                for (int i = 0; i < 10; i++){
                                    if (checkBlock.getType().equals(Material.BEDROCK) || checkBlock.getRelative(BlockFace.UP).getType().equals(Material.BEDROCK)){
                                        checkBlock = location.clone().add(0,i,0).getBlock();
                                    }
                                    else {
                                        if (xAndZPos.get(checkBlock.getX()) != null){
                                            if (xAndZPos.get(checkBlock.getX()) == checkBlock.getZ()) {
                                                break;
                                            }
                                            else {
                                                checkBlock.setType(Material.CHEST);
                                                Chest chest = (Chest) checkBlock.getState();
                                                Material material = null;
                                                for (int k = 0; k < 27; k++){
                                                    if (!chest.getWorld().getName().contains("_nether")) {
                                                        int randomIndex = random.nextInt(11);
                                                        switch (randomIndex) {
                                                            case 0:
                                                                material = Material.COBBLESTONE;
                                                                break;
                                                            case 1:
                                                                material = Material.COAL;
                                                                break;
                                                            case 2:
                                                                material = Material.EMERALD;
                                                                break;
                                                            case 3:
                                                                material = Material.ANDESITE;
                                                                break;
                                                            case 4:
                                                                material = Material.DIAMOND;
                                                                break;
                                                            case 5:
                                                                material = Material.REDSTONE;
                                                                break;
                                                            case 6:
                                                                material = Material.REDSTONE;
                                                                break;
                                                            case 7:
                                                                material = Material.IRON_ORE;
                                                                break;
                                                            case 8:
                                                                material = Material.LAPIS_LAZULI;
                                                                break;
                                                            case 9:
                                                                material = Material.DIRT;
                                                                break;
                                                            case 10:
                                                                material = Material.GOLD_ORE;
                                                                break;
                                                        }
                                                    }
                                                    else {
                                                        int randomIndex = random.nextInt(6);
                                                        switch (randomIndex) {
                                                            case 0:
                                                                material = Material.GRAVEL;
                                                                break;
                                                            case 1:
                                                                material = Material.ANCIENT_DEBRIS;
                                                                break;
                                                            case 2:
                                                                material = Material.GOLD_NUGGET;
                                                                break;
                                                            case 3:
                                                                material = Material.MAGMA_BLOCK;
                                                                break;
                                                            case 4:
                                                                material = Material.QUARTZ;
                                                                break;
                                                            case 5:
                                                                material = Material.SOUL_SOIL;
                                                                break;
                                                        }
                                                    }
                                                    chest.getInventory().addItem(new ItemStack(material, 64));
                                                }
                                                ItemStacksInRow.clear();
                                                airComboCount = 0;
                                                xAndZPos.put(chest.getX(), chest.getZ());
                                            }
                                        }
                                        else {
                                            checkBlock.setType(Material.CHEST);
                                            Chest chest = (Chest) checkBlock.getState();
                                            Material material = null;
                                            for (int k = 0; k < 27; k++){
                                                if (!chest.getWorld().getName().contains("_nether")) {
                                                    int randomIndex = random.nextInt(11);
                                                    switch (randomIndex) {
                                                        case 0:
                                                            material = Material.COBBLESTONE;
                                                            break;
                                                        case 1:
                                                            material = Material.COAL;
                                                            break;
                                                        case 2:
                                                            material = Material.EMERALD;
                                                            break;
                                                        case 3:
                                                            material = Material.ANDESITE;
                                                            break;
                                                        case 4:
                                                            material = Material.DIAMOND;
                                                            break;
                                                        case 5:
                                                            material = Material.REDSTONE;
                                                            break;
                                                        case 6:
                                                            material = Material.REDSTONE;
                                                            break;
                                                        case 7:
                                                            material = Material.IRON_ORE;
                                                            break;
                                                        case 8:
                                                            material = Material.LAPIS_LAZULI;
                                                            break;
                                                        case 9:
                                                            material = Material.DIRT;
                                                            break;
                                                        case 10:
                                                            material = Material.GOLD_ORE;
                                                            break;
                                                    }
                                                }
                                                else {
                                                    int randomIndex = random.nextInt(6);
                                                    switch (randomIndex) {
                                                        case 0:
                                                            material = Material.GRAVEL;
                                                            break;
                                                        case 1:
                                                            material = Material.ANCIENT_DEBRIS;
                                                            break;
                                                        case 2:
                                                            material = Material.GOLD_NUGGET;
                                                            break;
                                                        case 3:
                                                            material = Material.MAGMA_BLOCK;
                                                            break;
                                                        case 4:
                                                            material = Material.QUARTZ;
                                                            break;
                                                        case 5:
                                                            material = Material.SOUL_SOIL;
                                                            break;
                                                    }
                                                }
                                                chest.getInventory().addItem(new ItemStack(material, 64));
                                            }
                                            ItemStacksInRow.clear();
                                            airComboCount = 0;
                                            xAndZPos.put(chest.getX(), chest.getZ());
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }.register();

    public static void initRecipes(){
        registerGenericSurround("wood_command_block", new RecipeChoice.MaterialChoice(Material.OAK_WOOD,
                Material.BIRCH_WOOD, Material.ACACIA_WOOD, Material.JUNGLE_WOOD, Material.SPRUCE_WOOD, Material.DARK_OAK_WOOD), WOODEN_COMMAND_BLOCK_ITEM);
        registerGenericSurround("pickaxe_command_block", new RecipeChoice.MaterialChoice(Material.WOODEN_PICKAXE,
                Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE
        ), PICKAXE_COMMAND_BLOCK);
        registerGenericSurround("potato_command_block", new RecipeChoice.MaterialChoice(Material.POTATO), POTATO_COMMAND_BLOCK);
        registerGenericSurround("ore_command_block", new RecipeChoice.MaterialChoice(Material.DIAMOND), ORE_COMMAND_BLOCK);
        registerGenericSurround("mob_command_block", new RecipeChoice.MaterialChoice(Material.ROTTEN_FLESH), MOB_COMMAND_BLOCK);
        registerGenericSurround("elytra_command_block", new RecipeChoice.MaterialChoice(Material.FEATHER), ELYTRA_COMMAND_BLOCK);
        registerGenericSurround("explosive_command_block", new RecipeChoice.MaterialChoice(Material.TNT), EXPLOSIVE_COMMAND_BLOCK);
        registerGenericSurround("netherite_command_block", new RecipeChoice.MaterialChoice(Material.NETHERITE_SCRAP), NETHERITE_COMMAND_BLOCK);
        registerFullFill("custom_redstone_recipe", new RecipeChoice.MaterialChoice(Material.PORKCHOP, Material.BREAD, Material.APPLE, Material.MUSHROOM_STEW, Material.COOKED_PORKCHOP, Material.GOLDEN_APPLE, Material.ENCHANTED_GOLDEN_APPLE, Material.COD,
                Material.SALMON, Material.TROPICAL_FISH, Material.PUFFERFISH, Material.COOKED_COD, Material.COOKED_SALMON, Material.CAKE, Material.COOKIE, Material.MELON_SLICE, Material.DRIED_KELP, Material.BEEF, Material.COOKED_BEEF, Material.CHICKEN, Material.COOKED_CHICKEN,
                Material.ROTTEN_FLESH, Material.SPIDER_EYE, Material.CARROT, Material.POTATO, Material.BAKED_POTATO, Material.POISONOUS_POTATO, Material.PUMPKIN_PIE, Material.RABBIT, Material.COOKED_RABBIT, Material.RABBIT_STEW, Material.MUTTON, Material.COOKED_MUTTON,
                Material.BEETROOT, Material.BEETROOT_SOUP, Material.SWEET_BERRIES), new ItemStack(Material.REDSTONE, 64));
        registerXShape("command_command_block", new RecipeChoice.MaterialChoice(Material.BROWN_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA), COMMAND_COMMAND_BLOCK);
        registerGenericSurround("hoe_command_block", new RecipeChoice.MaterialChoice(Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE), HOE_COMMAND_BLOCK);
    }

    private static void registerXShape(String key, RecipeChoice centerItem, ItemStack resultItem){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(CustomCommandBlocks.plugin, key), resultItem)
                .shape("MRM", "RMR", "MRM")
                .setIngredient('R', Material.REDSTONE_BLOCK)
                .setIngredient('M', centerItem);
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerGenericSurround(String key, RecipeChoice centerItem, ItemStack resultItem){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(CustomCommandBlocks.plugin, key), resultItem)
                .shape(" R ", "RMR", " R ")
                .setIngredient('R', Material.REDSTONE_BLOCK)
                .setIngredient('M', centerItem);
        Bukkit.addRecipe(shapedRecipe);
    }

    private static void registerFullFill(String key, RecipeChoice centerItem, ItemStack resultItem){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(CustomCommandBlocks.plugin, key), resultItem)
                .shape("MMM", "MMM", "MMM")
                .setIngredient('M', centerItem);
        Bukkit.addRecipe(shapedRecipe);
    }

    private static List<Block> replaceNearbyBlocks(Block block, int range) {
        List<Block> blocks = new ArrayList<>();
        int firstx = block.getLocation().getBlockX() - range;
        int firsty = block.getLocation().getBlockY() - range;
        int firstz = block.getLocation().getBlockZ() - range;

        int secondx = block.getLocation().getBlockX() + range;
        int secondy = block.getLocation().getBlockY() + range;
        int secondz = block.getLocation().getBlockZ() + range;

        for (int x = firstx; x < secondx; x++) {
            for (int y = firsty; y < secondy; y++) {
                for (int z = firstz; z < secondz; z++) {
                    Block checkedBlock = block.getWorld().getBlockAt(x,y,z);
                    if (!checkedBlock.getType().toString().toLowerCase().contains("glazed")){
                        blocks.add(checkedBlock);
                    }
                }
            }
        }

        return blocks;
    }
}
