package com.taahyt.pingu.server.math;

import com.taahyt.pingu.server.World;
import lombok.Getter;
import lombok.Setter;

public record Location(World world, double x, double y, double z, float pitch, float yaw) {

    public Location {
        yaw = fixYaw(yaw);
    }

    public Location(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public Location x(double newX) {
        return update(newX, y, z, pitch, yaw);
    }

    public Location y(double newY) {
        return update(x, newY, z, pitch, yaw);
    }

    public Location z(double newZ) {
        return update(x, y, newZ, pitch, yaw);
    }

    public Location update(double newX, double newY, double newZ) {
        return update(newX, newY, newZ, pitch, yaw);
    }

    public Location update(float newPitch, float newYaw) {
        return update(x, y, z, newPitch, newYaw);
    }

    public Location update(double newX, double newY, double newZ, float newPitch, float newYaw) {
        return new Location(world, newX, newY, newZ, newPitch, newYaw);
    }

    private float fixYaw(float yaw) {
        yaw %= 360;
        if (yaw <= -180.0F) {
            yaw += 360.0F;
        } else if (yaw >= 180.0F) {
            yaw -= 360.0F;
        }
        return yaw;
    }
}
