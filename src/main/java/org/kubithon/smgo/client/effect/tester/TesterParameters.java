package org.kubithon.smgo.client.effect.tester;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;

public class TesterParameters extends EffectParameters {

    public TesterParameters(IExpression x, IExpression y, IExpression z, int maxAge) {
        super(x, y, z, maxAge);
    }
}
