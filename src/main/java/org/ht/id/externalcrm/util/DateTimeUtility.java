package org.ht.id.externalcrm.util;

import java.util.Date;

public class DateTimeUtility {
    public static long toUnixTimestamp(Date date) {
        return date.getTime() / 1000;
    }
}
