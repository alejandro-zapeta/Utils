package com.java.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author azapeta
 */
public class MapUtil {

    public static Map<String, String> findValsInMapByMatches(Map<String, String> vals, String matches) {
        if (vals == null || matches == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, String> result;
        result = new HashMap<String, String>();
        for (Map.Entry<String, String> object : vals.entrySet()) {
            String key = object.getKey();
            if (key == null) {
                continue;
            }
            if (key.matches(matches)) {
                result.put(key, object.getValue());
            }
        }
        return result;
    }
//    public static MapWrapper wrapMap(Map map) {
//        MapWrapper mapWrapper = null;
//        if (map != null && !map.isEmpty()) {
//            mapWrapper = new MapWrapper();
//            mapWrapper.setMap(new MapWrapper.Map());
//
//            for (Object key : map.keySet()) {
//                Entry entry = new Entry();
//                entry.setKey(key);
//                entry.setValue(map.get(key));
//                mapWrapper.getMap().getEntry().add(entry);
//            }
//        }
//
//
//        return mapWrapper;
//    }
}
