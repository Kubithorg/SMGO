package org.kubithon.smgo.client.effect;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.kubithon.smgo.client.effect.beam.Beam;
import org.kubithon.smgo.client.effect.beam.BeamParameters;
import org.kubithon.smgo.client.effect.beambunch.BeamBunch;
import org.kubithon.smgo.client.effect.beambunch.BeamBunchParameters;
import org.kubithon.smgo.client.effect.tester.Tester;
import org.kubithon.smgo.client.effect.tester.TesterParameters;
import org.kubithon.smgo.client.effect.torus.Torus;
import org.kubithon.smgo.client.effect.torus.TorusParameters;


/**
 * Represents a type of an {@link Effect}.
 * 
 * @author Wytrem
 *
 * @param <P> The parameters type for this effect.
 */
public class EffectType<P extends EffectParameters> {
    /**
     * A unique mapping of identifier -> effect type.
     */
    private static final Map<String, EffectType<? extends EffectParameters>> typesRegistry = new ConcurrentHashMap<String, EffectType<? extends EffectParameters>>();

    // Built-in types
    public static final EffectType<TesterParameters> TESTER = new EffectType<TesterParameters>("Tester", Tester.class, TesterParameters.class);
    public static final EffectType<BeamParameters> BEAM = new EffectType<BeamParameters>("Beam", Beam.class, BeamParameters.class);
    public static final EffectType<BeamBunchParameters> BEAM_BUNCH = new EffectType<BeamBunchParameters>("BeamBunch", BeamBunch.class, BeamBunchParameters.class);
    public static final EffectType<TorusParameters> TORUS = new EffectType<TorusParameters>("Torus", Torus.class, TorusParameters.class);

    /**
     * The unique identifier for this effect type.
     */
    private final String identifier;

    /**
     * The class of an actual {@link Effect} of this type.
     */
    private final Class<? extends Effect<P>> effectClass;

    /**
     * The wrapping class for the parameters type.
     */
    private final Class<P> parametersClass;

    /**
     * Creates a new effect type and registers it.
     * 
     * @param identifier The unique identifier for this effect type
     * @param effectClass The class of an actual {@link Effect} of this type
     * @param parametersClass The wrapping class for the parameters type
     * @throws IllegalArgumentException If the identifier is already used
     */
    public EffectType(String identifier, Class<? extends Effect<P>> effectClass, Class<P> parametersClass) {
        Objects.requireNonNull(identifier, "identifier cannot be null.");
        Objects.requireNonNull(effectClass, "effectClass cannot be null.");
        Objects.requireNonNull(parametersClass, "parametersClass cannot be null.");

        if (typesRegistry.containsKey(identifier)) {
            throw new IllegalArgumentException("Identifier '" + identifier + "' is already used.");
        }

        if (!checkEffectClass(effectClass, parametersClass)) {
            throw new IllegalArgumentException("Class '" + effectClass.getCanonicalName() + "' is not valid for parameters class '" + parametersClass.getCanonicalName() + "'.");
        }

        this.identifier = identifier;
        this.effectClass = effectClass;
        this.parametersClass = parametersClass;
        typesRegistry.put(identifier, this);
    }

    /**
     * Checks if the passed classes are valid as a pair to create effects.
     * 
     * @param effectClass The class of an actual {@link Effect}
     * @param parametersClass The class of parameters
     * @return {@code true} if the pair is valid, {@code false} otherwise
     */
    private static <T extends EffectParameters> boolean checkEffectClass(Class<? extends Effect<T>> effectClass, Class<T> parametersClass) {
        try {
            effectClass.getConstructor(parametersClass);
            return true;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
        catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return The unique identifier for this effect type
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return The class of an actual {@link Effect} of this type
     */
    public Class<? extends Effect<P>> getEffectClass() {
        return effectClass;
    }

    /**
     * @return The wrapping class for the parameters type
     */
    public Class<P> getParametersClass() {
        return parametersClass;
    }

    /**
     * Creates a new {@link Effect} instance with given parameters.
     * 
     * @param parameters The parameters to be passed to the {@link Effect}
     *        constructor
     * @return A newborn {@link Effect} of this type
     */
    public Effect<? extends P> buildEffect(EffectParameters parameters) {
        Objects.requireNonNull(parameters, "parameters cannot be null.");

        if (!parametersClass.isInstance(parameters)) {
            throw new IllegalArgumentException("Invalid parameters type '" + parameters.getClass()
                                                                                       .getCanonicalName() + "', expected instance of " + parametersClass.getCanonicalName() + ".");
        }

        try {
            return effectClass.getConstructor(parametersClass)
                              .newInstance((P) parameters);
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the effect type identified by the given {@code String}.
     * 
     * @param identifier The effect type identifier
     * @return The type identified by the given {@code String}, or null if no
     *         type if registered for this identifier
     */
    public static EffectType getTypeByIdentifier(String identifier) {
        return typesRegistry.get(identifier);
    }
}
