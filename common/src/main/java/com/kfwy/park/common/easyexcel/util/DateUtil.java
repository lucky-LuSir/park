package com.kfwy.park.common.easyexcel.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangyuanxin on 2016/3/21.
 */
public class DateUtil {
    public static final String YYYYMMDD = "yyyy/MM/dd";

    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }
}
