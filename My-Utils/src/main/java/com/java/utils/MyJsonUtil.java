package com.java.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author azapeta
 */
public class MyJsonUtil {

    public static String convertToString(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        String result = "";
        result = "{";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null) {
                result += String.format("%s : %s,", entry.getKey(), entry.getValue());
            }
        }
        // se quita la ultima coma ,
        result = result.substring(0, result.length() - 1);
        result += "}";
        return result;
    }

    public static Map convertToMap(String json) {
        if (json == null || json.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        Map<String, String> result = Collections.EMPTY_MAP;
        if (isJsonValid(json)) {
            result = new HashMap<String, String>();
            json = json.trim();
            //quito la primera posicion del json {
            json = json.substring(1);
            //quito la ultima posicion del json }
            json = json.substring(0, json.length() - 1);
            String[] objects = json.split(",");
            for (String object : objects) {
                String[] vals = object.split(":");
                result.put(vals[0], vals[1]);
            }
        }

        return result;
    }

    public static boolean isJsonValid(String json) {
        if (json == null || json.isEmpty()) {
            return false;
        }
        json = json.trim();
        if (json.matches("^\\{.+")) {
            return true;
        }
        return false;
    }
}
