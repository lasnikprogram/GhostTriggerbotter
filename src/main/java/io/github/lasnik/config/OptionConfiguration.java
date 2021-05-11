package io.github.lasnik.config;

import io.github.lasnik.util.SelectionMode;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.entity.EntityType;

import java.rmi.registry.Registry;

@Config(name = "option_config")
public class OptionConfiguration implements ConfigData {
    @Comment("Whether GhostTriggerbotter should be enabled or not.")
    public boolean enabled = true;

    @Comment ("Whether to use the hit delay type before or after Minecraft version 1.9.")
    public boolean newHitDelayType = true;

    @Comment ("[only before 1.9] How many clicks GhostTriggerbotter should do per second.")
    public int clicksPerSecond = 10;

    SelectionMode selectionMode = SelectionMode.WHITELIST;

    public int randomDelayTicksMin = 2;
    public int randomDelayTicksMax = 5;

    public String[] entities = {EntityType.PIG.toString(), EntityType.COW.toString()};
}
