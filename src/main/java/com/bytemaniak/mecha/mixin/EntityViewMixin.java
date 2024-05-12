package com.bytemaniak.mecha.mixin;

import com.bytemaniak.mecha.MultiCollidable;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.EntityView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(EntityView.class)
public interface EntityViewMixin {
    @Shadow List<Entity> getOtherEntities(@Nullable Entity var1, Box var2, Predicate<? super Entity> var3);

    /**
     * @author byteManiak
     * @reason Replace immutable list with mutable one,
     * fix collidesWith predicate for entities that override it, and add MultiCollidable colliders.
     */
    @Overwrite
    public default List<VoxelShape> getEntityCollisions(@Nullable Entity entity, Box box) {
        if (box.getAverageSideLength() < 1.0E-7) return List.of();

        Predicate<Entity> predicate = entity == null ? EntityPredicates.CAN_COLLIDE : EntityPredicates.EXCEPT_SPECTATOR.and(e -> e.collidesWith(entity));
        predicate = predicate.and(e -> e instanceof MultiCollidable);

        List<Entity> list = this.getOtherEntities(entity, box.expand(1.0E-7), predicate);
        if (list.isEmpty()) return List.of();

        List<VoxelShape> colliders = new ArrayList<>(list.size());
        for (Entity entity2 : list) {
            if (entity2 instanceof MultiCollidable multiCollidable) {
                List<VoxelShape> entColliders = multiCollidable.getColliders();
                if (entColliders != null && !entColliders.isEmpty())
                    colliders.addAll(entColliders);
            } else colliders.add(VoxelShapes.cuboid(entity2.getBoundingBox()));
        }

        return colliders;
    }
}
