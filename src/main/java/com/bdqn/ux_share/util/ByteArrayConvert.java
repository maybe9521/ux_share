package com.bdqn.ux_share.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Arrays;

/**
 * 字节数组转化
 */
public class ByteArrayConvert {




    public static int[] convertIntToArray(String str) {
        String[] ids = str.split(",");//字符串转成字符串数组
        int[] ret = new int[ids.length];//创建一个字符串数组长度的int数组
        int i = 0;
        for(String s : ids){
            //去的掉字符串数组中的特殊字符，获取每个元素的值
            if(i == 0){
                s = s.substring(1,s.length());
            }
            if(i == ids.length-1){
                s = s.replace("\n","").trim();
                s = s.substring(0,s.length()-1);
            }
            s = s.replace("\n","").trim();
         /*if("\n".contains(s)){
            s = s.replace("\n","");
         }*/
            //将字符串数组中的元素转换成int类型，复制给int数组的元素
            ret[i] = Integer.valueOf(s);
            i++;
        }
        return ret;
    }


}
