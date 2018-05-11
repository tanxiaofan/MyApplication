package com.example.framework.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;

/**
 * function:用户改变textview中部分字体的颜色
 *
 * @author 466
 */
public class StringFormatUtil {
  private SpannableStringBuilder spBuilder;
  private String wholeStr, highlightStr, sizeFont;
  private Context mContext;
  private int color;
  private int font;
  private int start = 0, end = 0;

  /** @param wholeStr 全部文字 */
  public StringFormatUtil(Context context, String wholeStr) {
    this.mContext = context;
    this.wholeStr = wholeStr;
    spBuilder = new SpannableStringBuilder(wholeStr);
  }

  /** @return 当true:空或者"" 当false：非空 */
  public static boolean IsNullOrWhiteSpace(EditText input) {
    String inputString = input.getText().toString();
    if (inputString != null && !inputString.trim().equals("")) {
      return false;
    }
    return true;
  }

  public StringFormatUtil setHigh(String highlightStr, int color) {
    this.highlightStr = highlightStr;
    this.color = color;

    return this;
  }

  public StringFormatUtil setSize(String sizeFont, int font) {
    this.sizeFont = sizeFont;
    this.font = font;

    return this;
  }

  public StringFormatUtil fillColor() {
    if (!TextUtils.isEmpty(wholeStr) && !TextUtils.isEmpty(highlightStr)) {
      if (wholeStr.contains(highlightStr)) {
        /*
         *  返回highlightStr字符串wholeStr字符串中第一次出现处的索引。
         */
        start = wholeStr.indexOf(highlightStr);
        end = start + highlightStr.length();
      } else {
        return null;
      }
    } else {
      return null;
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      color = mContext.getResources().getColor(color, null);
    } else {
      color = mContext.getResources().getColor(color);
    }

    CharacterStyle charaStyle = new ForegroundColorSpan(color);
    spBuilder.setSpan(charaStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return this;
  }

  public StringFormatUtil fontSize() {
    if (!TextUtils.isEmpty(wholeStr) && !TextUtils.isEmpty(sizeFont)) {
      if (wholeStr.contains(sizeFont)) {
        start = wholeStr.indexOf(sizeFont);
        end = start + sizeFont.length();
      } else {
        return null;
      }
    } else {
      return null;
    }
    spBuilder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(font)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    spBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    return this;
  }

  public SpannableStringBuilder getResult() {
    if (spBuilder != null) {
      return spBuilder;
    }
    return null;
  }
}
