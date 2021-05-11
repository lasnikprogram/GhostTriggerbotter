package io.github.lasnik.mixin;

import io.github.lasnik.config.Configuration;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.screen.options.SkinOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.Collectors;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Final
    @Shadow
    private GameOptions settings;

    @Inject(method = "init()V", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 24 - 6, 310, 20,
                new TranslatableText("lol"), (button) -> {
            this.client.openScreen(AutoConfig.getConfigScreen(Configuration.class, this).get());
        }));
    }
}
