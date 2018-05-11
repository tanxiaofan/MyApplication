package com.example.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Created by chenheming on 16/11/15. */
public class RegexUtil {

  /** 校验是否中文 */
  public static boolean IsRegex(String input) {

    //^[\u4e00-\u9fa5_a-zA-Z]+$
    //^([\u4e00-\u9fa5]|[a-z]|[A-Z]){2,7}$
    //    ("[^\u4e00-\u9fa5]"

    Pattern p = null;
    Matcher m = null;
    boolean b = false;
    p = Pattern.compile("^([\u4e00-\u9fa5]|[a-z]|[A-Z]){2,}$"); // 验证手机号
    m = p.matcher(input);
    b = m.matches();
    return b;
  }

  /** 正则校验手机号 */
  public static boolean isPhoneNumber(String mobiles) {

    Pattern p = null;
    Matcher m = null;
    boolean b = false;
    p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
    m = p.matcher(mobiles);
    b = m.matches();
    return b;
  }

  /** 正则校验  纯数字 */
  public static boolean isNumber(String mobiles) {

    Pattern p = null;
    Matcher m = null;
    boolean b = false;
    p = Pattern.compile("^[0-9]*$"); // 验证手机号
    m = p.matcher(mobiles);
    b = m.matches();
    return b;
  }
}
