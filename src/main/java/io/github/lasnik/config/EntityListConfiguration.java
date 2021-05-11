package io.github.lasnik.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityTypePredicate;

@Config(name = "entity_list_config")
public class EntityListConfiguration implements ConfigData {
    public boolean player = true;
    public boolean chicken = false;
}
