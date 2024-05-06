package com.bytemaniak.mecha;

import com.bytemaniak.mecha.test.TestEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;

public class MechaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment())
            EntityRendererRegistry.register(Mecha.TEST_ENT, TestEntityRenderer::new);
    }
}
