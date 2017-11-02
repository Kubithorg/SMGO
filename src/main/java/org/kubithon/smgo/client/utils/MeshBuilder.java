package org.kubithon.smgo.client.utils;

import org.joml.MatrixStackf;
import org.joml.Vector3f;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MeshBuilder {
    private VertexBuffer buffer;

    private MatrixStackf stack;
    private Vector3f     temp = new Vector3f();

    public MeshBuilder(VertexBuffer buffer) {
        this.buffer = buffer;
        this.stack = new MatrixStackf(8);
    }

    public void vertex(Vector3f vec) {
        this.vertex(vec.x, vec.y, vec.z);
    }

    public void vertex(float x, float y, float z) {
        this.temp.set(x, y, z);
        this.stack.transformProject(this.temp);
        this.buffer.pos(this.temp.x, this.temp.y, this.temp.z).endVertex();
    }

    public void cube(float x, float y, float z, float size) {
        this.pushMatrix();
        this.stack.translate(x, y, z);
        this.stack.scale(size);
        this.unitCube();
        this.popMatrix();
    }

    public void unitCube() {

        // Horizontal
        for (int i = 0; i < 2; i++) {
            this.vertex(0.0f, i, 0.0f);
            this.vertex(0.0f, i, 1.0f);

            this.vertex(0.0f, i, 1.0f);
            this.vertex(1.0f, i, 1.0f);

            this.vertex(1.0f, i, 1.0f);
            this.vertex(1.0f, i, 0.0f);

            this.vertex(1.0f, i, 0.0f);
            this.vertex(0.0f, i, 0.0f);
        }

        // Vertical
        this.vertex(0.0f, 0.0f, 0.0f);
        this.vertex(0.0f, 1.0f, 0.0f);

        this.vertex(1.0f, 0.0f, 1.0f);
        this.vertex(1.0f, 1.0f, 1.0f);

        this.vertex(1.0f, 0.0f, 0.0f);
        this.vertex(1.0f, 1.0f, 0.0f);

        this.vertex(0.0f, 0.0f, 1.0f);
        this.vertex(0.0f, 1.0f, 1.0f);
    }

    public MatrixStackf pushMatrix() {
        return this.stack.pushMatrix();
    }

    public MatrixStackf popMatrix() {
        return this.stack.popMatrix();
    }

    public MatrixStackf getStack() {
        return this.stack;
    }
}
