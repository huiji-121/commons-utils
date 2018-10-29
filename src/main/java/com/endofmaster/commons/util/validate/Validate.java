package com.endofmaster.commons.util.validate;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ZM.Wang
 */
public class Validate {

    public static <T> void notNull(T object, String message) {
        if (object == null) {
            throw new ValidateParamIsNullException(message);
        }
    }

    public static <T extends CharSequence> void notBlank(T chars, String message) {
        if (chars == null) {
            throw new ValidateParamIsNullException(message);
        } else if (StringUtils.isBlank(chars)) {
            throw new ValidateStringIsBlankException(message);
        }
    }

}
