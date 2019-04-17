package com.perfree.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验工具
 */
public class RegexUtill {
    /**
     * 验证是否是URL
     * @param url
     * @return boolean
     */
    public static boolean verifyUrl(String url){
        String regEx ="[a-zA-z]+://[^\\s]*";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
