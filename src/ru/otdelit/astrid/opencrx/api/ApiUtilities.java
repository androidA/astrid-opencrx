package ru.otdelit.astrid.opencrx.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.todoroo.andlib.utility.AndroidUtilities;
import com.todoroo.andlib.utility.DateUtilities;

/**
 * Utilities for working with API responses and JSON objects
 *
 * @author timsu
 *
 */
public final class ApiUtilities {

    private static final SimpleDateFormat timeParser = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.US); //$NON-NLS-1$

    private static final SimpleDateFormat timeWriter = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss Z", Locale.US); //$NON-NLS-1$

    private static final SimpleDateFormat dateWriter = new SimpleDateFormat(
            "yyyy/MM/dd", Locale.US); //$NON-NLS-1$

    /**
     * Utility method to convert PDV time to unix time
     *
     * @param date
     * @param defaultValue
     * @return
     */
    public static long producteevToUnixTime(String value, long defaultValue) {
        synchronized(timeParser) {
            try {
                return DateUtilities.dateToUnixtime(timeParser.parse(value));
            } catch (ParseException e) {
                return defaultValue;
            }
        }
    }

    /**
     * Utility method to convert unix time to PDV time
     * @param time
     * @return
     */
    public static String unixTimeToProducteev(long time) {
        synchronized(timeWriter) {
            return timeWriter.format(DateUtilities.unixtimeToDate(time));
        }
    }

    /**
     * Utility method to convert unix date to PDV date
     * @param time
     * @return
     */
    public static String unixDateToProducteev(long date) {
        synchronized(dateWriter) {
            return dateWriter.format(DateUtilities.unixtimeToDate(date));
        }
    }

    /**
     * Un-escape a Producteev string
     * @param string
     * @return
     */
    public static String decode(String string) {
        return StringEscapeUtils.unescapeHtml(string);
    }

}
