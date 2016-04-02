/*
 * Copyright (c) 2016. Naivor.All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.naivor.app.extras.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;

/**
 * FontUtil 是一个处理字符串字体颜色，大小，间距的工具类
 */
public class FontUtil {
    /**
     * 给字体加上颜色
     *
     * @param colorCode  颜色
     * @param text  内容
     * @return
     */
    public static CharSequence addColor(String colorCode, String text) {
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new ForegroundColorSpan(Color.parseColor(colorCode)), 0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 给字体加上颜色
     *
     * @param color 颜色
     * @param text 内容
     * @return
     */
    public static CharSequence addColor(int color, String text) {
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 调整字体大小
     *
     * @param relativeSice 相对于原来字体大小的倍数
     * @param text 内容
     * @return
     */
    public static CharSequence resize(float relativeSice, String text) {
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new RelativeSizeSpan(relativeSice), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 调整字体水平间距大小
     *
     * @param relativeXSice   相对于原来字体在水平方向上大小的倍数
     * @param text 内容
     * @return
     */
    public static CharSequence scaleX(float relativeXSice, String text) {
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new ScaleXSpan(relativeXSice), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

}
