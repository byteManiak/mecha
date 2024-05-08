# MECHA

![MECHA Logo](src/main/resources/assets/mecha/icon.png)

Multiple Entity Collision Hitboxes API

## Setup

Add the Modrinth Maven and the right version of the mod to your build.gradle file:

```gradle
repositories {
	maven { url 'https://api.modrinth.com/maven' }
}

dependencies {
	modImplementation "maven.modrinth:mecha-api:VERSION_GOES_HERE"
}
```

You can see all versions of the mod at https://modrinth.com/mod/mecha-api/versions#all-versions

## Usage

Implement `MultiCollidable` in your custom entity class, and override `getColliders` to add extra colliders to the entity.

## Example

The following example code adds 2 colliders to an entity, each the size of a slab:

```java
public CustomEntity extends Entity implements MultiCollidable {
    public List<VoxelShape> getColliders() {
        List<VoxelShape> colliders = new ArrayList<>();
        BlockPos pos = this.blockPos();
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        colliders.add(VoxelShapes.cuboid(x, y, z, x+1, y+0.5, z+1));
        colliders.add(VoxelShapes.cuboid(x, y+0.5, z, x+1, y+1, z+1));
        return colliders;
    }
}
```
