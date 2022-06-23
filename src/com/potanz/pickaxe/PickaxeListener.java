package com.potanz.pickaxe;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class PickaxeListener implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material type = block.getType();
        World world = player.getWorld();
        Location location = block.getLocation();
        if (player.getItemInHand().getType() == Material.STONE_PICKAXE) {
            if (type.equals(Material.STONE)) {
                world.createExplosion(block.getLocation(), 5);
            }
        }
        if (player.getItemInHand().getType() == Material.IRON_PICKAXE) {
            int bx = (int) event.getBlock().getLocation().getX();
            int by = (int) event.getBlock().getLocation().getY();
            int bz = (int) event.getBlock().getLocation().getZ();
            List<Block> blocks = new ArrayList<>();
                for (int x = bx - 1; x <= bx + 1; x++) {
                    for (int y = by - 2; y <= by + 1; y++) {
                        for (int z = bz - 1; z <= bz + 1; z++) {
                            blocks.add(new Location(world, (double) x, (double) y, (double) z).getBlock());
                        }
                    }
            }
            for (Block b : blocks) {
                b.breakNaturally();
            }
            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.EXTINGUISH, null);
        }

        if (player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
            int bx = (int) event.getBlock().getLocation().getX();
            int by = (int) event.getBlock().getLocation().getY();
            int bz = (int) event.getBlock().getLocation().getZ();
            List<Block> blocks = new ArrayList<>();
            for (int y = by - 256; y <= by; y++) {
                if(new Location(world, (double) bx, (double) y, (double) bz).getBlock() != null && new Location(world, (double) bx, (double) y, (double) bz).getBlock().getType() != Material.BEDROCK)
                    blocks.add(new Location(world, (double) bx, (double) y, (double) bz).getBlock());
            }
            for (Block b : blocks) {
                b.breakNaturally();
            }
            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.FIREWORKS_SPARK, null);
        }
    }
}
