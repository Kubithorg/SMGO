package org.kubithon.smgo.proxy;

import org.kubithon.smgo.common.Smgo;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void registerItemTexture(Item item, int metadata, String name) {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(item, metadata, new ModelResourceLocation(Smgo.MODID + ":" + name, "inventory"));
    }

    @Override
    public void registerItemTexture(Item item, String name) {
        this.registerItemTexture(item, 0, name);
    }

    @Override
    public void registerBlockTexture(Block block, int metadata, String name) {
        this.registerItemTexture(Item.getItemFromBlock(block), metadata, name);
    }

    @Override
    public void registerBlockTexture(Block block, String name) {
        this.registerBlockTexture(block, 0, name);
    }
}
