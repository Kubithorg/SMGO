package org.kubithon.smgo.client.utils;

import org.joml.MatrixStackf;
import org.joml.Vector3f;

import net.minecraft.client.renderer.VertexBuffer;


public class MeshBuilder {
    private VertexBuffer buffer;

    private MatrixStackf stack;
    private Vector3f temp = new Vector3f();

    public MeshBuilder(VertexBuffer buffer) {
        this.buffer = buffer;
        this.stack = new MatrixStackf(8);
    }

    public void vertex(Vector3f vec) {
        vertex(vec.x, vec.y, vec.z);
    }

    public void vertex(float x, float y, float z) {
        temp.set(x, y, z);
        stack.transformProject(temp);
        buffer.pos(temp.x, temp.y, temp.z).endVertex();
    }
    
    public void cube(float x, float y, float z, float size) {
        pushMatrix();
        stack.translate(x, y, z);
        stack.scale(size);
        unitCube();
        popMatrix();
    }

    public void unitCube() {

        // Horizontal 
        for (int i = 0; i < 2; i++) {
            vertex(0.0f, i, 0.0f);
            vertex(0.0f, i, 1.0f);

            vertex(0.0f, i, 1.0f);
            vertex(1.0f, i, 1.0f);

            vertex(1.0f, i, 1.0f);
            vertex(1.0f, i, 0.0f);

            vertex(1.0f, i, 0.0f);
            vertex(0.0f, i, 0.0f);
        }

        // Vertical
        vertex(0.0f, 0.0f, 0.0f);
        vertex(0.0f, 1.0f, 0.0f);

        vertex(1.0f, 0.0f, 1.0f);
        vertex(1.0f, 1.0f, 1.0f);

        vertex(1.0f, 0.0f, 0.0f);
        vertex(1.0f, 1.0f, 0.0f);

        vertex(0.0f, 0.0f, 1.0f);
        vertex(0.0f, 1.0f, 1.0f);
    }

    public MatrixStackf pushMatrix() {
        return stack.pushMatrix();
    }

    public MatrixStackf popMatrix() {
        return stack.popMatrix();
    }

    public MatrixStackf getStack() {
        return stack;
    }
}
