package com.endofmaster.commons.util.validate;

/**
 * @author YQ.Huang
 */
public class InvalidParamException extends RuntimeException {

    private final String param;
    private final Object expected;
    private final Object actual;

    public InvalidParamException(String param) {
        super(String.format("参数[%s]无效", param));
        this.param = param;
        this.expected = null;
        this.actual = null;
    }

    public InvalidParamException(String param, Object expected, Object actual) {
        super(String.format("参数[%s]无效，期待是[%s]，但实际是[%s]", param, expected, actual));
        this.param = param;
        this.expected = expected;
        this.actual = actual;
    }

    public String getParam() {
        return param;
    }

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }
}
