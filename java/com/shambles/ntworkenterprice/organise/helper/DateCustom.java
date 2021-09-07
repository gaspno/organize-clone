package com.shambles.ntworkenterprice.organise.helper;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class DateCustom {

    public static String dataAtual(){

        long date=System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        return  simpleDateFormat.format(date).toString();

    }


}
