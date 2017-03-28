package fr.ironcraft.mcshow.mod.core;

import fr.ironcraft.mcshow.mod.SmgoMod;
import net.minecraft.launchwrapper.IClassTransformer;


public class McShowClassTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("<nom obfusqué>") || name.equals("<nom normal>")) { // Par exemple ici pour l'explosion : <nom obfusqué>=agw et <nom normal>=net.minecraft.world.Explosion
            SmgoMod.logger.info("About to patch : " + name);
            return patchMinecraftClass(name, basicClass, name.equals("<nom obfusqué>"));
        }
        return basicClass;
    }

    private byte[] patchMinecraftClass(String name, byte[] basicClass, boolean equals) {
        return null;
    }
}
