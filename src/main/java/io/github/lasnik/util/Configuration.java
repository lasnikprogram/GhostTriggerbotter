package io.github.lasnik.util;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.JsonHelper;

@Config(name = "ghost-triggerbotter")
public class Configuration implements ConfigData {
    public boolean enabled = true;
    public HitDelayType hitDelayType = HitDelayType.NEW;
    public boolean invisible = false;
    public boolean customName = true;
    public int randomDelayTicksMin = 2;
    public int randomDelayTicksMax = 5;
    public int clicksPerSecond = 5;
    // flyingFilter??
    // keybind and also toggle keybind
//    public String[] entities = {EntityType.PIG.toString(), EntityType.COW.toString()}; // https://www.digminecraft.com/lists/entity_list_pc.php
    SelectionMode selectionMode = SelectionMode.WHITELIST;
}
