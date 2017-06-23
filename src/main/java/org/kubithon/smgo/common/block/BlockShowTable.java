package org.kubithon.smgo.common.block;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.common.Smgo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockShowTable extends Block {
    public BlockShowTable() {
        super(Material.GLASS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        ShowInfos showInfos = ShowInfos.read(new ResourceLocation(Smgo.MODID, "show/show_test.json"));
        Show currentShow = new Show(showInfos, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        Smgo.showsManager.startShow(currentShow);

        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean isTranslucent() {
        return false;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks
     * for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
