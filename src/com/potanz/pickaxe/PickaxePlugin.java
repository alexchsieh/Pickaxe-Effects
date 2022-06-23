package com.potanz.pickaxe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PickaxePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Enabled PickaxeEffects Plugin");

        Bukkit.getPluginManager().registerEvents(new PickaxeListener(), this);
    }
    @Override
    public void onDisable() {
        System.out.println("Disabled PickaxeEffects Plugin");
    }
}
