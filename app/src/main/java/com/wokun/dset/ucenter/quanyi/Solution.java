package com.wokun.dset.ucenter.quanyi;

/**
 * Created by Administrator on 2019\1\31 0031.
 */

public class Solution {

    private Solution(){

    }


    private static class SolutionHolder{
        private static Solution instance = new Solution();
    }

    public static Solution getInstance(){
        return SolutionHolder.instance;
    }


    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int l1 = num1.length();
        int l2 = num2.length();
        int[] res = new int[l1 + l2];
        //先计算逐位相乘的结果存到数组指定位置，不管进位
        for (int i = l2-1; i >= 0; i --) {
            for (int j = l1 - 1; j >= 0; j --) {
                int pro = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                res[i + j + 1] += pro;
            }
        }
        //在计算进位后的结果
        int add = 0;//初始化进位；
        for (int i = res.length - 1; i >= 0; i --) {
            int temp = (res[i] + add) % 10;//记录进位后的结果
            add = (res[i] + add) / 10;//计算进位
            res[i] = temp;//将进位后（正确的结果）存到数组指定位置；
        }
        //将结果转化为字符串，使用Stringbuffer；
        StringBuffer buf = new StringBuffer();
        for (int i : res) {
            buf.append(i);
        }
        //将StringBuffer转换为String，注意结果去‘0’
        String s = new String(buf);
        if (s.length() > 0 && s.charAt(0) == '0')//结果最多只会出现首位是0的情况
            s = s.substring(1);
        return s;
    }


}
