package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.exception.UnrecognizedInteractionType;
import lombok.Getter;

/**
 * InteractionType
 *
 * @author elvea
 */
@Getter
public enum InteractionType {
    /**
     *
     */
    CHOICE("choice"),
    /**
     *
     */
    SEQUENCING("sequencing"),
    /**
     *
     */
    LIKERT("likert"),
    /**
     *
     */
    MATCHING("matching"),
    /**
     *
     */
    PERFORMANCE("performance"),
    /**
     *
     */
    TRUE_FALSE("true-false"),
    /**
     *
     */
    FILL_IN("fill-in"),
    /**
     *
     */
    LONG_FILL_IN("long-fill-in"),
    /**
     *
     */
    NUMERIC("numeric"),
    /**
     *
     */
    OTHER("other");

    private final String text;

    InteractionType(final String text) {
        this.text = text;
    }

    public static InteractionType getByString(String type) throws UnrecognizedInteractionType {
        for (InteractionType it : InteractionType.values()) {
            if (type.equals(it.toString())) {
                return it;
            }
        }
        throw new UnrecognizedInteractionType(type);
    }

}
