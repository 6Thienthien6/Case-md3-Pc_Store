package com.casemd3pcstore.utils;

import java.text.DecimalFormat;

public class FormatUtils {
    public static String doubleToVND(double value) {
        String patternVND = ",###₫";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }
}
