package com.proitr.pingService;

import com.mycomp.ExtUtils.FileUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by kondakov on 27.10.2015.
 */
public class ExtUtils {

    public static void wLog(String message, boolean add) {
        try {
            message = currentDate() + message;
            System.out.println(message);
            FileUtils.writeFile("./log.out", message + "\n", add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String currentDate() {
        long curTime = System.currentTimeMillis();
        SimpleDateFormat sFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return "[" + sFormat.format(curTime) + "] ";
    }

    public static String getCustomStackTrace(Throwable aThrowable) {
        //add the class name and any message passed to constructor
        final StringBuilder result = new StringBuilder( "Stack trace: " );
        result.append(aThrowable.toString());
        final String NEW_LINE = System.getProperty("line.separator");
        result.append(NEW_LINE);

        //add each element of the stack trace
        for (StackTraceElement element : aThrowable.getStackTrace() ){
            result.append( element );
            result.append( NEW_LINE );
        }

        return result.toString();
    }

}
