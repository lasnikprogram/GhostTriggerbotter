package io.github.lasnik;

import io.github.lasnik.config.Configuration;
import io.github.lasnik.config.ConfigurationOptions;
import io.github.lasnik.util.KeyBindings;
import net.fabricmc.api.ClientModInitializer;

public class GhostTriggerbotter implements ClientModInitializer {
    public KeyBindings keyBindings = new KeyBindings();
    public ConfigurationOptions configurationOptions;
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

        configurationOptions = new ConfigurationOptions();
        triggerBot = new TriggerBot();
    }
}
