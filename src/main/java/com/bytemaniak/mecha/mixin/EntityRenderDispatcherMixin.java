package com.bytemaniak.mecha.mixin;

import com.bytemaniak.mecha.MultiCollidable;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(method = "renderHitbox", at = @At("HEAD"))
    private static void renderJumppadColliders(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        if (entity instanceof MultiCollidable multiCollidable && multiCollidable.getColliders() != null) {
            List<VoxelShape> colliders = multiCollidable.getColliders();
            if (colliders != null && !colliders.isEmpty())
                for (VoxelShape shape : colliders)
                    WorldRenderer.drawBox(matrices, vertices, shape.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ()), 0f, 1f, 0f, 1f);
        }
    }
}