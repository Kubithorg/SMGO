package org.kubithon.smgo.client.utils;

import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VertexBufferObject extends VertexBuffer {
    public VertexBufferObject(VertexFormat vertexFormatIn) {
        super(vertexFormatIn);
    }
}
