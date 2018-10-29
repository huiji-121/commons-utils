package com.endofmaster.commons.util.validate;

/**
 * @author YQ.Huang
 */
public class MissingParamException extends RuntimeException {

    private final String param;

    public MissingParamException(String param) {
        super("缺少参数[" + param + "]");
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
