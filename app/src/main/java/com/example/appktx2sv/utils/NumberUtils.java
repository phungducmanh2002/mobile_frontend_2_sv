package com.example.appktx2sv.utils;

import java.text.DecimalFormat;

public class NumberUtils {
    public static String FormatNumber(long number){
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
    public static String GetMoney(long number) {
        DecimalFormat formatter = new DecimalFormat("###,### VNĐ");
        return formatter.format(number);
    }
}
