package com.clova.issuecream.common;

public interface Constant {
    static <T> T fromCode(Class<T> constantEnum, String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        for (T constant : constantEnum.getEnumConstants()) {
            if (constant instanceof Constant) {
                if (((Constant) constant).getCode().equals(code)) {
                    return constant;
                }
            }
        }
        throw new IllegalArgumentException("No enum constant " + constantEnum.getCanonicalName() + "." + code);
    }

    String getCode();
    String getText();
}
