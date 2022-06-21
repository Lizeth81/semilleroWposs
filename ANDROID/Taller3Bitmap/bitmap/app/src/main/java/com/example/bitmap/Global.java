package com.example.bitmap;

import android.util.Log;

import java.util.ArrayList;

public class Global {

    public static String binarioHexadecimal(String bin){
        int a = 4;
        int b = 0, c = 0;
        String hexString = "";
        int sum = 0;

        if(bin.length() % 4 != 0){
            int temCnt = 0;
            int tempBit = bin.length() % 4;
            while (temCnt<(a-tempBit)){
                bin = "0" + bin;
                temCnt++;
            }
        }
        while(b<bin.length()){
            while(c<a){
                sum=(int)(sum + Integer.parseInt("" + bin.charAt(bin.length() - b -1)) * Math.pow(2, c));
                c++;
                b++;
            }
            if(sum < 10){
                hexString = Integer.toString(sum) + hexString;
            }else{
                hexString = (char)(sum+55) + hexString;
            }
            c=0;
            sum = 0;
        }

        return (hexString);
    }


}
