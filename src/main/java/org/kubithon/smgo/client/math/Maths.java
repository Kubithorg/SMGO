package org.kubithon.smgo.client.math;

import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;

import org.joml.Quaternionf;
import org.joml.Vector3f;


public class Maths {
    
    public static final float PI = (float) Math.PI;
    public static final float TWO_PI = PI * 2.0f;
    public static final float HALF_PI = PI / 2.0f;


    /**
     * Computes the {@link Quaternionf} that represents the rotation which
     * transforms {@code antecedent} to {@code image}.
     */
    public static Quaternionf quatFromPair(Vector3f antecedent, Vector3f image) {
        return new Quaternionf().fromAxisAngleRad(antecedent.cross(image), antecedent.angle(image));
    }
    
    public static float random() {
        return (float) Math.random();
    }
    
    public static Vector3f randomizeOnUnitSphere() {
        Vector3f dest = new Vector3f();
        randomizeOnUnitSphere(dest, null);
        return dest;
    }

    public static void randomizeOnUnitSphere(Vector3f dest) {
        randomizeOnUnitSphere(null, dest);
    }

    public static void randomizeOnUnitSphere(Vector3f origin, Vector3f dest) {
        float theta = random() * TWO_PI;
        float phi = random() * PI;

        dest.x = sin(phi) * cos(theta);
        dest.y = sin(phi) * sin(theta);
        dest.z = cos(phi);

        if (origin != null) {
            dest.add(origin.x, origin.y, origin.z);
        }
    }
    
    public static void randomizeInUnitSphere(Vector3f dest) {
        randomizeOnUnitSphere(dest);
        dest.mul(random());
    }
    
    public static void randomizeOnOrthogonalCircle(Vector3f axis, Vector3f origin, Vector3f dest) {
        float angle = random() * TWO_PI;
        dest.x = cos(angle);
        dest.y = 0.0f;
        dest.z = sin(angle);
        
        quatFromPair(axis, new Vector3f(0.0f, 1.0f, 0.0f)).invert().transform(dest);
        dest.add(origin);
    }
    
    public static void randomizeQuad(Vector3f[] vertexes, Vector3f origin) {
        if (vertexes.length != 4) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < 4; i++) {
            if (vertexes[i] == null) {
                vertexes[i] = new Vector3f();
            }
        }
        
        randomizeInUnitSphere(vertexes[0]);
        randomizeOnUnitSphere(vertexes[0], vertexes[1]);
        randomizeOnOrthogonalCircle(vertexes[1].sub(vertexes[0], vertexes[3]), vertexes[0], vertexes[2]);
        
        // Fourth = First + (First - Second) + (First - Third) = Second + (Third - First)
        vertexes[1].add(vertexes[2], vertexes[3]);
        vertexes[3].sub(vertexes[0]);
        
        // Reverse the last two for opengl
        Vector3f temp = vertexes[3];
        vertexes[3] = vertexes[2];
        vertexes[2] = temp;
        
        if (origin != null) {
            for (int i = 0; i < 4; i++) {
                vertexes[i].add(origin);
            }
        }
    }
    
    public static void randomize(float range, Vector3f dest) {
        dest.x = (random() - 0.5f) * 2.0f * range;
        dest.y = (random() - 0.5f) * 2.0f * range;
        dest.z = (random() - 0.5f) * 2.0f * range;
    }
}
