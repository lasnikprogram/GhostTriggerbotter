package io.github.lasnik;

import io.github.lasnik.util.Configuration;
import io.github.lasnik.util.KeyBindings;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class GhostTriggerbotter implements ClientModInitializer {
    public KeyBindings keyBindings = new KeyBindings();
    public Configuration config;
    public TriggerBot triggerBot;

    private static GhostTriggerbotter instance = null;

    public static GhostTriggerbotter getInstance() {
        return instance;
    }

    @Override
    public void onInitializeClient() {
        if (instance != null) {
            throw new RuntimeException("GhostTriggerbotter has already been initialized!");
        }
        instance = this;

        AutoConfig.register(Configuration.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Configuration.class).getConfig();

        try {
            config.validatePostLoad();
        } catch (ConfigData.ValidationException e) {
            e.printStackTrace();
        }

        triggerBot = new TriggerBot();
    }
}
