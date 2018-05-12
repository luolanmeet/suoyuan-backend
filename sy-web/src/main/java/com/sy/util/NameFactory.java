package com.sy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 *
 * @author cck
 */
@Component
public class NameFactory {

    public final static ThreadLocal<DateFormat> TL_FORMAT =
    		ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public String getName() {

        StringBuffer name = new StringBuffer();
        for (String str : UUID.randomUUID().toString().split("-")) {
            name.append(str);
        }
        return name.toString();
    }

}
