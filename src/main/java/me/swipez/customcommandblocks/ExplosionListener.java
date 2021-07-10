package me.swipez.customcommandblocks;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ExplosionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.setResourcePack("https://cdn.discordapp.com/attachments/812394140577824808/858427986176114688/CustomCommandBlocks.zip");
    }

    @EventHandler
    public void onTNTExplode(EntityDamageByEntityEvent event){
        if (!(event.getDamager() instanceof TNTPrimed)){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event){
        if (event.getEntity().getType().equals(EntityType.PRIMED_TNT)){
            event.setCancelled(true);
            event.getEntity().getWorld().playSound(event.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 1);
            for (Block block : event.blockList()){
                if (!block.getType().toString().contains("GLAZED")){
                    for (ItemStack itemStack : block.getDrops()){
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.AIR);
                }
            }
        }
    }
}
