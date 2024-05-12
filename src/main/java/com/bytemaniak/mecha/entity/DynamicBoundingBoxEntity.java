package com.bytemaniak.mecha.entity;

import com.bytemaniak.mecha.MultiCollidable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

/**
 * An entity which has a dynamic bounding box
 * based on the list returned by {@code getCollidable()}
 */
public abstract class DynamicBoundingBoxEntity extends Entity implements MultiCollidable {
    public DynamicBoundingBoxEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected Box calculateBoundingBox() {
        Box box = super.calculateBoundingBox();

        if (getColliders() != null && !getColliders().isEmpty())
            for (VoxelShape collider : getColliders())
                box = box.union(collider.getBoundingBox());

        return box;
    }

    @Override
    public void tick() {
        super.tick();

        setBoundingBox(calculateBoundingBox());
    }
}
