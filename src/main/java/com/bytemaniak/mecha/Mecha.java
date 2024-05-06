package com.bytemaniak.mecha;

import com.bytemaniak.mecha.test.TestEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Mecha implements ModInitializer {
    public static EntityType<TestEntity> TEST_ENT;
    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            TEST_ENT = Registry.register(Registries.ENTITY_TYPE, new Identifier("mecha:test"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, TestEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build());
        }
    }
}
