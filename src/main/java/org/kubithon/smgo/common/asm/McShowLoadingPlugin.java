package org.kubithon.smgo.common.asm;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class McShowLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { McShowClassTransformer.class.getName() };
    }

    @Override
    public String getModContainerClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSetupClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
