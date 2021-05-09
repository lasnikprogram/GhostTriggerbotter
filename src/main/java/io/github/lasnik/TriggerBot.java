package io.github.lasnik;

import io.github.lasnik.util.Configuration;
import io.github.lasnik.util.KeyBindings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;

public class TriggerBot {
    private MinecraftClient mc = MinecraftClient.getInstance();
    private KeyBindings keyBindings = GhostTriggerbotter.getInstance().keyBindings;
    private Configuration config = GhostTriggerbotter.getInstance().config;

    private boolean enabled = true;

    public void onTick() {
        if (!config.enabled || mc.crosshairTarget == null || !(mc.crosshairTarget instanceof EntityHitResult)) {
            return;
        }

        if (keyBindings.keyBindingToggle.wasPressed()) {
            enabled = !enabled;
        }

        if (keyBindings.keyBindingHold.isPressed() || enabled) {

            Entity entity = ((EntityHitResult) mc.crosshairTarget).getEntity();

            switch (config.hitDelayType) {
                case NEW:
                    attackWithNewHitDelay(entity);
                    break;
                case OLD:
                    attackWithOldHitDelay(entity);
                    break;
            }
        }
    }

    private void attackWithNewHitDelay(Entity entity) {
        if (mc.player.getAttackCooldownProgress(mc.getTickDelta()) == 1f) {
            // TODO: Switch to most damage dealing weapon
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void attackWithOldHitDelay(Entity entity) {
        mc.interactionManager.attackEntity(mc.player, entity);
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
