package com.proitr.pingService;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by kondakov on 27.10.2015.
 */
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);

    private static final long delay1 = 2000;      //2 секунды
    private static final long delay2 = 300000;    //5 минут
    private static final long delay3 = 600000;    //10 минут
    private static final long delay4 = 60000 * 60;    //1 час

//    private static final long delay1 = 1000;      //1 секунды
//    private static final long delay2 = 3000;    //3 минут
//    private static final long delay3 = 120000;    //2 минут
//    private static final long delay4 = 240000;    //4 мин

    public static void main(String[] args) throws IOException {
        String urlStr = "http://eaist.mos.ru/AsurNsiService/CheckXMLSmev?wsdl";
        //String urlStr = args[0];
        ExtUtils.wLog("Start monitoring\n", false);
        monitoring(urlStr);


    }

    private static void monitoring(String urlStr) {
        boolean isStart = true;
        PingService pingService1 = null;
        PingService pingService2 = null;
        try {
            while (isStart) {

                ExtUtils.wLog("++++++ Monitoring service delay " + delay1 / 1000 + " seconds ++++++ \n", true);
                pingService1 = new PingService(urlStr, delay1);
                pingService1.start();
                Thread.sleep(delay3);
                pingService1.setStart(false);

                ExtUtils.wLog("++++++ Monitoring service delay " + delay2 / 1000 + " seconds ++++++ \n", true);
                pingService2 = new PingService(urlStr, delay2);
                pingService2.start();
                Thread.sleep(delay4);
                pingService2.setStart(false);

            }

        } catch (Exception e) {
            ExtUtils.wLog(ExtUtils.getCustomStackTrace(e), true);
            e.printStackTrace();
        }

    }


}
