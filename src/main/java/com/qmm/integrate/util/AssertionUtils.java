package com.qmm.integrate.util;

import com.qmm.integrate.exception.CustomError;
import com.qmm.integrate.exception.CustomException;


/**
 * @author qinmengmei
 */
public class AssertionUtils {
    private AssertionUtils() {

    }

    public static <T> void isNotEmpty(T t, CustomError customError) {
        if(EmptyUtils.isEmpty(t)) {
            throw new CustomException(customError);
        }
    }

    public static void isTrue(boolean bool, CustomError customError) {
        if (!bool) {
            throw new CustomException(customError);
        }
    }

    public static void isFalse(boolean bool, CustomError customError) {
        isTrue(!bool, customError);
    }

    public static void notBlank(String s, CustomError customError) {
        isTrue(StringUtil.isNotBlank(s), customError);
    }

    public static void notNull(Object o, CustomError customError) {
        isTrue(o != null, customError);
    }

}
