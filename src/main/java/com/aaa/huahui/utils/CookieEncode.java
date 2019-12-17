package com.aaa.huahui.utils;

public class CookieEncode {

    static char secret = '~';

    public static String encryptAndDencrypt(String value)
    {
        byte[] bt=value.getBytes(); //将需要加密的内容转换为字节数组
        for(int i=0;i<bt.length;i++)
        {
            bt[i]=(byte)(bt[i]^(int)secret); //通过异或运算进行加密
        }
        return new String(bt,0,bt.length);
    }


}
