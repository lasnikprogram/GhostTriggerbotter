package io.github.lasnik.config;

import io.github.lasnik.GhostTriggerbotter;
import io.github.lasnik.util.HitDelayType;
import io.github.lasnik.util.TranslationKeyHelper;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.EntityType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Configuration extends TranslationKeyHelper {
    private ConfigurationOptions options = GhostTriggerbotter.getInstance().configurationOptions;
    private ConfigBuilder configBuilder;

    public Configuration() {
        configBuilder = ConfigBuilder.create()
                .setTitle(new TranslatableText(title))
                .setSavingRunnable(() -> {
                    System.out.println("Saving");
                });

        ConfigCategory general = configBuilder.getOrCreateCategory(new TranslatableText(category + "general"));

        ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(
                new TranslatableText(entry + "enabled"), options.enabled)
                .setDefaultValue(true)
                .setTooltip(new TranslatableText(tooltip + "enabled"))
                .setSaveConsumer(updated -> options.enabled = updated)
                .build());

        general.addEntry(entryBuilder.startEnumSelector(
                new TranslatableText(entry + "hitDelayType"), HitDelayType.class, options.hitDelayType)
                .setDefaultValue(HitDelayType.NEW)
                .setTooltip(new TranslatableText(tooltip + "hitDelayType"))
                .setSaveConsumer(updated -> options.hitDelayType = updated)
                .build());

        general.addEntry(entryBuilder.startIntSlider(
                new TranslatableText(entry + "clicksPerSecond"), options.clicksPerSecond,0,20)
                .setDefaultValue(10)
                .setTooltip(new TranslatableText(tooltip + "clicksPerSecond"))
                .setSaveConsumer(updated -> options.clicksPerSecond = updated)
                .build());

        general.addEntry(entryBuilder.startIntSlider(
                new TranslatableText(entry + "randomDelayTicksMin"), options.randomDelayTicksMin, 0,20)
                .setDefaultValue(3)
                .setTooltip(new TranslatableText(tooltip + "randomDelayTicksMin"))
                .setSaveConsumer(updated -> options.randomDelayTicksMin = updated)
                .build());

        general.addEntry(entryBuilder.startIntSlider(
                new TranslatableText(entry + "randomDelayTicksMax"), options.randomDelayTicksMax, 0, 20)
                .setDefaultValue(5)
                .setTooltip(new TranslatableText(tooltip + "randomDelayTicksMax"))
                .setSaveConsumer(updated -> options.randomDelayTicksMax = updated)
                .build());


        ConfigCategory entityFilter = configBuilder.getOrCreateCategory(new TranslatableText(category + "entityFilter"));

        entityFilter.addEntry(entryBuilder.startBooleanToggle(
                new TranslatableText(entry + "attackInvisible"), options.attackInvisible)
                .setDefaultValue(false)
                // TODO
                .setTooltip(new TranslatableText("THIS SHIT MAKE IT SMOOSHY"))
                .setSaveConsumer(updated -> options.attackInvisible = updated)
                .build());

        // TODO
        for (EntityType type : Registry.ENTITY_TYPE.stream().sorted(Comparator.comparing(n -> n.toString())).collect(Collectors.toList())) {
            entityFilter.addEntry(entryBuilder.startBooleanToggle(
                    new TranslatableText(type.getTranslationKey()), false)
                    .setDefaultValue(false)
                    .build());
        }
    }

    public Screen getScreen (Screen parent) {
        return configBuilder.setParentScreen(parent).build();
    }
}