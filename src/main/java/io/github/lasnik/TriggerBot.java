package io.github.lasnik;

import io.github.lasnik.config.Configuration;
import io.github.lasnik.config.ConfigurationOptions;
import io.github.lasnik.util.HitDelayType;
import io.github.lasnik.util.KeyBindings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;

public class TriggerBot {
    private MinecraftClient mc = MinecraftClient.getInstance();
    private KeyBindings keyBindings = GhostTriggerbotter.getInstance().keyBindings;
    private ConfigurationOptions configurationOptions = GhostTriggerbotter.getInstance().configurationOptions;
    private int delay;

    private boolean enabled = true;

    public void onTick() {
        if (!configurationOptions.enabled || mc.crosshairTarget == null || !(mc.crosshairTarget instanceof EntityHitResult) || !mc.player.isAlive()) {
            return;
        }

        if (keyBindings.keyBindingToggle.wasPressed()) {
            enabled = !enabled;
        }

        if (keyBindings.keyBindingHold.isPressed() || enabled) {

            Entity entity = ((EntityHitResult) mc.crosshairTarget).getEntity();

            if (!entity.isAlive()) {
                return;
            }

            if (configurationOptions.hitDelayType == HitDelayType.NEW) {
                attackWithNewHitDelay(entity);
            } else {
                attackWithOldHitDelay(entity);
            }
        }
    }

    private void attackWithNewHitDelay(Entity entity) {
        if (mc.player.getAttackCooldownProgress(mc.getTickDelta()) == 1f) {
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void attackWithOldHitDelay(Entity entity) {
        delay++;
        int reqDelay = Math.round(20 / configurationOptions.clicksPerSecond);
        if (delay > reqDelay || reqDelay == 0) {
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
            delay = 0;
        }
    }
}
