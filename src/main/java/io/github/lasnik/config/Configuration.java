package io.github.lasnik.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "ghost-triggerbotter")
public class Configuration extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("entity_configuration")
    @ConfigEntry.Gui.TransitiveObject
    public EntityListConfiguration entityListConfiguration = new EntityListConfiguration();

    @ConfigEntry.Category("option_configuration")
    @ConfigEntry.Gui.TransitiveObject
    public OptionConfiguration optionConfiguration = new OptionConfiguration();
}