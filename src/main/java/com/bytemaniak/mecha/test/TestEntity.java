package com.bytemaniak.mecha.test;

import com.bytemaniak.mecha.MultiCollidable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import java.util.List;

public class TestEntity extends Entity implements MultiCollidable {
    public TestEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public List<VoxelShape> getColliders() {
        double x = getX();
        double y = getY();
        double z = getZ();
        return List.of(
                VoxelShapes.cuboid(x, y, z, x+.5, y+.5, z+.5),
                VoxelShapes.cuboid(x-.5, y+.5, z-.5, x, y+1, z)
        );
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}
}
