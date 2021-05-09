package io.github.lasnik.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public KeyBinding keyBindingToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.ghost_triggerbotter.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.categories.ghost_triggerbotter"));

    public KeyBinding keyBindingHold = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.ghost_triggerbotter.hold",
            InputUtil.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_LEFT,
            "key.categories.ghost_triggerbotter"));
}
