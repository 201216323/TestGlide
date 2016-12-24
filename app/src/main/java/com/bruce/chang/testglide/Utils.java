package com.bruce.chang.testglide;

import android.content.Context;

/**
 * Created by Administrator
 * Date:2016/12/24
 * Time:20:27
 * Author:BruceChang
 * Function:
 */

public class Utils {

    //转换dip为px
    public static int dip2px(Context context, float dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dip*scale + 0.5f*(dip>=0?1:-1));
    }

    //转换px为dip
    public static int px2dp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale + 0.5f*(px>=0?1:-1));
    }
}
