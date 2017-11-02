package org.kubithon.smgo.client.effect.tester;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TesterParameters extends EffectParameters {

    public TesterParameters(IExpression x, IExpression y, IExpression z, int maxAge) {
        super(x, y, z, maxAge);
    }
}
