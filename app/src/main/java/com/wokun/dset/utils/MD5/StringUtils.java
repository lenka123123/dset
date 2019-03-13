package com.wokun.dset.utils.MD5;

/**
 * Created by Administrator on 2019\1\18 0018.
 */

class StringUtils {

    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static String convertObjectToString(Object o) {
        return o != null?(o instanceof String?((String)o).toString():(o instanceof Integer?"" + ((Integer)o).intValue():(o instanceof Long?"" + ((Long)o).longValue():(o instanceof Double?"" + ((Double)o).doubleValue():(o instanceof Float?"" + ((Float)o).floatValue():(o instanceof Short?"" + ((Short)o).shortValue():(o instanceof Byte?"" + ((Byte)o).byteValue():(o instanceof Boolean?((Boolean)o).toString():(o instanceof Character?((Character)o).toString():o.toString()))))))))):"";
    }

    public static int hashCode(String value) {
        int h = 0;
        if(h == 0 && value.length() > 0) {
            char[] val = value.toCharArray();

            for(int i = 0; i < val.length; ++i) {
                h = 31 * h + val[i];
            }
        }

        return h;
    }
}
