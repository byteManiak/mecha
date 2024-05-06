package com.bytemaniak.mecha.test;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class TestEntityRenderer extends EntityRenderer<TestEntity> {
    public TestEntityRenderer(EntityRendererFactory.Context ctx) { super(ctx); }

    @Override
    public Identifier getTexture(TestEntity entity) { return null; }
}
