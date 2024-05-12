package com.bytemaniak.mecha;

import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An Entity must implement this interface in order to have multiple collision boxes
 */
public interface MultiCollidable {
    /**
     *
     * @return list of colliders contained by the entity, in world space coordinates
     */
    @Nullable
    List<VoxelShape> getColliders();
}
