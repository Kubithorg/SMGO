package org.kubithon.smgo.client.utils;

public class Maths {
    public static final float PI         = (float) Math.PI;
    public static final float DEG_TO_RAD = PI / 180.0f;
    public static final float RAD_TO_DEG = 180.0f / PI;
    public static final float TWO_PI     = 2 * PI;

    public static float toDegrees(float angle) {
        return angle * RAD_TO_DEG;
    }

    public static float toRadians(float angle) {
        return angle * DEG_TO_RAD;
    }
}
