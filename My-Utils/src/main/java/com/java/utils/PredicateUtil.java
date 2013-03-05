package com.java.utils;
//
//import gt.com.gyt.quote.modelo.policy.TypePoliza;

/**
 *
 * @author azapeta
 */
public class PredicateUtil {

    public static boolean isObjectNull(Object... obj) {
        if (obj == null) {
            return true;
        }
        for (Object s : obj) {
            if (s == null) {
                return true;
            }
        }

        return false;
    }

    public static boolean isObject(Object... obj) {
        for (Object s : obj) {
            if (s instanceof Object == false) {
                return false;
            }
        }
        return true;
    }

//    public static boolean isPolicyCertificadoNull(TypePoliza policy) {
//        if (!isObjectNull(policy)) {
//            if (policy.getCertificado() != null
//                    && !policy.getCertificado().isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static boolean isObjectsEquals(Object objectFrom, Object objectTo) {
        if (isObjectNull(objectFrom, objectTo)) {
            return false;
        }
        if (!isObject(objectFrom, objectTo)) {
            return false;
        }
        if (objectFrom.equals(objectTo)) {
            return true;
        }
        return false;



    }
}
