package me.swipez.customcommandblocks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandBlockCreator implements Listener {

    private final ItemStack baseItem;

    private List<Block> placedBlock;
    private final JavaPlugin javaPlugin;

    public CommandBlockCreator(ItemStack baseItem, JavaPlugin javaPlugin) {
        this.baseItem = baseItem;
        this.javaPlugin = javaPlugin;
        this.placedBlock = new ArrayList<>();
    }

    public abstract void powerAction(BlockRedstoneEvent event, Block block);

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getItemInHand().isSimilar(baseItem)){
            placedBlock.add(event.getBlock());
        }
    }

    @EventHandler
    public void onBlockRemove(BlockBreakEvent event){
        Block block = event.getBlock();
        if (placedBlock.contains(block)){
            event.setDropItems(false);
            block.getWorld().dropItemNaturally(block.getLocation(), baseItem);
            placedBlock.remove(block);
        }
    }

    @EventHandler
    public void onRedstonePowered(BlockPlaceEvent event){
        if (event.getBlock().getType().equals(Material.REDSTONE_BLOCK)){
            for (Block block : placedBlock){
                if (getPowerableBlocks(event.getBlock()).contains(block)){
                    BlockRedstoneEvent blockRedstoneEvent = new BlockRedstoneEvent(block, 0, 15);
                    powerAction(blockRedstoneEvent, block);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onBlockPower(BlockRedstoneEvent event){
        if (event.getNewCurrent() == 0){
            return;
        }
        for (Block block : placedBlock){
            if (getPowerableBlocks(event.getBlock()).contains(block)){
                powerAction(event, block);
                break;
            }
        }
    }

    private List<Block> getPowerableBlocks(Block block){
        List<Block> blocks = new ArrayList<>();
        blocks.add(block.getRelative(BlockFace.NORTH));
        blocks.add(block.getRelative(BlockFace.SOUTH));
        blocks.add(block.getRelative(BlockFace.EAST));
        blocks.add(block.getRelative(BlockFace.WEST));
        blocks.add(block.getRelative(BlockFace.UP));
        blocks.add(block.getRelative(BlockFace.DOWN));

        return blocks;
    }

    public CommandBlockCreator register(){
        CustomCommandBlocks.plugin.getServer().getPluginManager().registerEvents(this, javaPlugin);
        return this;
    }
}
