package com.startdt.modules.common.utils;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/30 上午11:00
 * @Modified By:
 */
public class RegexUtil {

    public static String wildToRegex(String wild) {
        //'\\', '$', '^', '[', ']', '(', ')', '{', '|', '+', '.'
        wild = wild.replace("\\", "\\\\");
        wild = wild.replace("$", "\\$");
        wild = wild.replace("^", "\\^");
        wild = wild.replace("[", "\\[");
        wild = wild.replace("]", "\\]");
        wild = wild.replace("(", "\\(");
        wild = wild.replace(")", "\\)");
        wild = wild.replace("|", "\\|");
        wild = wild.replace("+", "\\+");
        wild = wild.replace(".", "\\.");
        //
        wild = wild.replace("*", ".*");
        wild = wild.replace("?", ".");
        return wild;
    }
}
