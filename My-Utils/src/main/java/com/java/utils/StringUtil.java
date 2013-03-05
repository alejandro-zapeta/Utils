package com.java.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author azapeta
 */
public class StringUtil {

    public static int myIndexOf(String str, char delimiter, int pos) {
        char[] cr = str.toCharArray();
        int cont = 1;
        int pos2 = 0;
        for (char c : cr) {
            if (c == delimiter) {
                if (pos == cont) {
                    return pos2;
                }
                cont++;
            }
            pos2++;
        }
        return -1;
    }

    private static String findField(char typeCharacter, String arreglo, int posicion) {
        if (arreglo == null) {
            return null;
        }
        String result = null;
        String var = String.valueOf(typeCharacter);
        String[] split = arreglo.split(var);
        //posicion 6 y el largo es menor a 6
        if (split.length > posicion) {
            result = split[posicion];
        }
        return result;
    }

    public static String findLastFieldInStringByTypeCharacter(char typeCharacter, String arreglo) {
        String var = String.valueOf(typeCharacter);
        String[] split = arreglo.split(var);
        return findField(typeCharacter, arreglo, split.length - 1);
    }
    //SoC

    public static String findFieldInStringByTypeCharacterAndPosition(char typeCharacter, String arreglo, int posicion) {
        return findField(typeCharacter, arreglo, posicion);
    }

    public static String validateString(String value) {
        if (value != null) {
            return value;
        }
        return "";
    }

    public static String validateString(Object value) {
        if (value != null) {
            return String.valueOf(value);
        }
        return "";
    }

    public static String valueOf(String value) {
        if (value != null) {
            return value;
        }
        return null;
    }

    public static String valueOfEmpty(String value) {
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return null;
    }

    public static String split(char split, List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        String result = "";
        for (String str : list) {
            result += str + split;
        }
        //quito la ultima coma(split).
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public static String convertString(Object value) {
        if (value != null) {
            return String.valueOf(value);
        }
        return "";
    }

    public static String removeNull(Object ob) {
        if (ob != null) {
            String sr = String.valueOf(ob);
            sr = sr.replaceAll("null", "");
            return sr;
        }
        return null;
    }

    public static String toLowerCase(String str, int pos) {
        if (str == null || pos <= 0) {
            return null;
        }
        if (pos > str.length()) {
            pos = str.length();
        }
        String result = null;

        char buf[] = new char[pos];
        for (int i = 0; i < pos; i++) {
            char ch = str.charAt(i);
            buf[i] = Character.toLowerCase(ch);
        }
        result = replaceString(str.toCharArray(), buf);


        return result;
    }

    public static String toUpperCase(String str, int pos) {
        if (str == null || str.isEmpty() || pos <= 0) {
            return null;
        }
        String result = null;

        char buf[] = new char[pos];
        for (int i = 0; i < pos; i++) {
            char ch = str.charAt(i);
            buf[i] = Character.toUpperCase(ch);
        }
        result = replaceString(str.toCharArray(), buf);


        return result;
    }

    public static String replaceString(char oldChar[], char newChar[]) {

        if (oldChar == newChar) {
            return new String(oldChar);
        }

        int len = oldChar.length;
        int newlen = newChar.length;
        int i = 0;
        char buf[] = new char[len];

        while (i < len) {
            buf[i] = i < newlen ? newChar[i] : oldChar[i];
            i++;
        }

        return new String(buf);
    }

    public static String replaceValueOfManyValues(String value, String toReplace, String... valuesToReplaces) {
        String result = null;
        if (value != null
                && !value.isEmpty()
                && valuesToReplaces != null
                && valuesToReplaces.length > 0) {
            result = value;
            for (String v : valuesToReplaces) {
                if (v != null) {
                    result = result.replace(v, toReplace);
                }
            }
        }
        return result;
    }

    public static String getFirstResult(String value, String pattern) {
        if (value == null || pattern == null) {
            return null;
        }
        String result = value;


        Pattern p = Pattern.compile(pattern);
        if (p != null) {
            Matcher m = p.matcher(result);
            if (m.find()) {
                int posi = m.start(0);
                int posf = m.end(0);
                result = value.substring(posi, posf);
            }

        }
        return result;
    }

    public static String getValueOf(Object o) {
        if (o != null) {
            String re = String.valueOf(o);
            if (!re.isEmpty()) {
                return re;
            }
        }
        return null;
    }

    public static List<String> valueOf(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof List) {
            return (List<String>) o;
        }
        return null;
    }

    public static String decode(String str) {
        if (str == null) {
            return null;
        }
        String result = str;
        result = result.replace('à', 'a');
        result = result.replace('é', 'e');
        result = result.replace('í', 'i');
        result = result.replace('ó', 'o');
        result = result.replace('ú', 'u');
        result = result.replace('Á', 'A');
        result = result.replace('É', 'E');
        result = result.replace('Ï', 'I');
        result = result.replace('Ó', 'O');
        result = result.replace('Ú', 'U');
        return result;
    }
}
