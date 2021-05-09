package io.github.lasnik.mixin;

import io.github.lasnik.GhostTriggerbotter;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Inject(method = "tickEntities()V", at = @At("HEAD"))
    public void tickEntities(CallbackInfo ci) {
        GhostTriggerbotter.getInstance().triggerBot.onTick();
    }
}
