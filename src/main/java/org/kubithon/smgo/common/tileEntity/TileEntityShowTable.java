package org.kubithon.smgo.common.tileEntity;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.common.Smgo;

import net.minecraft.tileentity.TileEntity;


public class TileEntityShowTable extends TileEntity {
    private Show currentShow;

    public void playerClick() {
        ShowInfos showInfos = ShowInfos.read(ShowInfos.class.getResourceAsStream("show_test.json"));
        currentShow = new Show(showInfos, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        Smgo.showsManager.startShow(currentShow);
    }

    public Show getCurrentShow() {
        return currentShow;
    }
}
