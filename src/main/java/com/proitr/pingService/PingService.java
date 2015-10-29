package com.proitr.pingService;

import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kondakov on 27.10.2015.
 */
public class PingService extends Thread {

    public static final Logger LOG = Logger.getLogger(PingService.class);
    private boolean start;
    private String urlStr;
    private long delay;
    private boolean isResponse = true;


    public PingService(String urlStr, long delay) {
        this.start = true;
        this.urlStr = urlStr;
        this.delay = delay;
    }

    @Override
    public void run() {
        super.run();
        ping(this.delay);
    }

    public void ping(long delay) {
        try {
            while (this.start) {
                this.isResponse = checkIfURLExists(urlStr);
                LOG.info("wait " + delay / 1000 + " seconds...");
                Thread.sleep(delay);
            }
            this.start = true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
            ExtUtils.wLog(ExtUtils.getCustomStackTrace(e), true);
        }

    }

    private static boolean checkIfURLExists(String targetUrl) {
        boolean rez = false;
        MyTimer myTimer = new MyTimer();
        try {
            LOG.info("Ping resource...");
            //HttpURLConnection.setFollowRedirects(false);
            //HttpURLConnection.setInstanceFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(targetUrl).openConnection();
            con.setRequestMethod("HEAD");

            // Set timeouts in milliseconds
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            rez = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            String result = "Response code for the URL(" + targetUrl + "):" + con.getResponseCode() + ":" + con.getResponseMessage() + " response time: " + myTimer.getElapsed() + " ms";
            //LOG.info(result);
            ExtUtils.wLog(result + "\n", true);
        } catch (Exception e) {
//            LOG.error("Error while check URL(" + targetUrl + "):" + e.getMessage());
            ExtUtils.wLog("Error while check URL(" + targetUrl + "):" + e.getMessage() + " response time: " + myTimer.getElapsed() + " ms", true);
        }
        return rez;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
