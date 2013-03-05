package com.java.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author azapeta
 */
public class NumberUtil {

    public static final DecimalFormat myFormatter = new DecimalFormat("###,###.00");

    public static String formatter(double amount) {
        return myFormatter.format(amount);
    }

    public static Double valueOfDouble(String value) {
        Double result = null;
        if (value != null && !value.isEmpty()) {
            try {
                result = Double.valueOf(value);
            } catch (NumberFormatException ex) {
                return null;
            }

        }
        return result;
    }

//    public static Long valueOfLong(String value) {
//        Number n = new Long(value);
//
//    }
    public static long primitiveLong(String value) {
        if (value == null || value.isEmpty()) {
            return 0l;
        }
        long result = 0l;
        try {
            Long tr = new Long(value);
            result = tr.longValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }

    public static double divide(double dividen, double divisor) {
//        if (dividen == null || divisor == null) {
//            return 0d;
//        }
        // double divsor = divisor.doubleValue();
        if (divisor == 0d) {
            return 0d;
        }
        return (dividen / divisor);
    }

//    public static double divideList(List<? extends Number> numbers) {
//        if (numbers == null) {
//            return 0d;
//        }
//        
//        for (Number number : numbers) {
//            
//        }
//    }
    public static Integer valueOfInteger(String value) {
        Integer result = null;
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            result = Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            return null;
        }

        return result;
    }

    public static int primitiveInteger(String value) {
        Integer result = valueOfInteger(value);
        if (result != null) {
            return result;
        }
        return 0;
    }

    public static double primitiveDouble(String v) {
        Double d = valueOfDouble(v);
        if (d != null) {
            return d;
        }
        return 0d;
    }

    public static Float valueOfFloat(String value) {
        Float result = null;
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            result = Float.valueOf(value);
        } catch (NumberFormatException ex) {
            return null;
        }

        return result;
    }

    public static float primitiveFloat(String v) {
        Float f = valueOfFloat(v);
        if (f != null) {
            return f;
        }
        return 0f;
    }

    public static BigDecimal valueOfBigDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        BigDecimal result;
        try {
            result = BigDecimal.valueOf(valueOfDouble(value));
        } catch (NumberFormatException ex) {
            return null;
        }


        return result;
    }

    public static BigDecimal negativeValue(BigDecimal value) {
        if (value != null) {
            value = value.negate();
        }

        return value;
    }

    public static Short valueOfShort(String value) {
        Short result = null;
        if (value != null && !value.isEmpty()) {
            try {
                result = Short.valueOf(value);
            } catch (Exception ex) {
                return null;
            }
        }
        return result;
    }

    public static BigDecimal validateNull(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value;
    }

    public static Double validateNull(Double value) {
        if (value == null) {
            return new Double("0");
        }
        return value;
    }

    public static Float validateNull(Float value) {
        if (value == null) {
            return new Float("0");
        }
        return value;
    }

    public static BigDecimal isZero(BigDecimal value) {
        if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
            return value;

        }
        return null;
    }

    public static Double isZero(Double value) {
        if (value != null && value.compareTo(0d) > 0) {
            return value;
        }
        return null;
    }

    public static Float isZero(Float value) {
        if (value != null && value.compareTo(0f) > 0) {
            return value;
        }
        return null;
    }

    public static int convertToInteger(String sint) {
        if (sint == null) {
            return 0;
        }
        if (sint.matches("[0-9]+")) {
            return Integer.parseInt(sint);
        }
        return 0;
    }

    public static BigDecimal valueOfBigDecimal(Object value) {
        BigDecimal rs = BigDecimal.ZERO;
        if (value == null) {
            return rs;
        }
        if (value instanceof String) {
            // rs = new Double(String.valueOf(value));
            rs = new BigDecimal((String.valueOf(value)));
        } else if (value instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) value;
            rs = bd;
        } else if (value instanceof Double) {
            Double r = (Double) value;
            rs = BigDecimal.valueOf(r);
        } else if (value instanceof Float) {
            Float fl = (Float) value;
            rs = new BigDecimal(fl);
        } else if (value instanceof Short) {
            Short st = (Short) value;
            rs = new BigDecimal(st);
        }
        return rs;
    }

    public static Double valueOfDouble(Object value) {
        Double rs = Double.MIN_VALUE;
        if (value != null) {
            if (value instanceof String) {
                rs = new Double(String.valueOf(value));
            } else if (value instanceof BigDecimal) {
                BigDecimal bd = (BigDecimal) value;
                rs = validateNull(bd).doubleValue();
            } else if (value instanceof Double) {
                rs = (Double) value;
            } else if (value instanceof Float) {
                Float fl = (Float) value;
                rs = validateNull(fl).doubleValue();
            }
        }
        return rs;
    }
}