package com.java.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author azapeta
 */
public class FechaUtil {

    private static final Logger logger = Logger.getLogger(FechaUtil.class.getName());
    public static final SimpleDateFormat FORMAT_YEAR = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
    private static final long MS_YEAR = 1000L * 60 * 60 * 24 * 365;
//    private static final Logger logger = Logger.getLogger(FechaUtil.class.getName());
    private static final SimpleDateFormat formatDiversos = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public static XMLGregorianCalendar convertirFecha(String fechaString) throws ParseException, DatatypeConfigurationException, java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = sdf.parse(fechaString);
        return convertirFecha(fechaDate);
    }

    public static XMLGregorianCalendar convertirFecha(Date fecha) throws DatatypeConfigurationException {
        if (fecha != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(fecha);
            XMLGregorianCalendar retorno = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            return retorno;
        }
        return null;
    }

    public static Date convertirFecha(XMLGregorianCalendar fecha) {
        if (fecha != null) {
            return fecha.toGregorianCalendar().getTime();
        }
        return null;
    }

    public static int validarFechaAnual(Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return (int) ((fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24));
        }
        return 0;
    }

    public static String getFechaFormatByDate(Date dt) {
        String fecha = null;
        if (dt != null) {
            fecha = formatDiversos.format(dt);
        }
        return fecha;
    }

    public static Date getDateFormatByString(String dt) {
        Date fecha = null;
        if (dt != null && !dt.isEmpty()) {
            try {
                fecha = formatDiversos.parse(dt);
            } catch (ParseException ex) {
                logger.log(Level.INFO, "Error al formatear fecha.");
            }
        }
        return fecha;
    }

    public static Date newDateByString(String fecha) {
        Date result = null;
        if (fecha == null || fecha.isEmpty()) {
            return result;
        }
        DateFormat dateFormat = DateFormat.getDateInstance();
        try {
            result = dateFormat.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Deprecated
    public static Date getDateByString(String fecha) {
        Date date = null;
        if (fecha != null && !fecha.isEmpty()) {
            try {
                date = new Date(fecha);
            } catch (Exception ex) {
                return null;
            }
        }
        return date;
    }

    public static Short getEdadByFechaNacimiento(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return null;
        }
        Short edad;

        int res = getNumberOfYears(new Date(), fechaNacimiento);
        edad = (short) res;

        return edad;
    }

    public static int getNumberOfYears(Date fini, Date ffin) {
        if (PredicateUtil.isObjectNull(ffin, fini)) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(fini);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(ffin);

        long msDiff = c1.getTimeInMillis() - c2.getTimeInMillis();
        long result = msDiff / MS_YEAR;
        return (int) result;

    }

    public static Date format(String str) {
        if (str == null) {
            return null;
        }
        Date result = null;
        try {
            result = FORMAT_DATE.parse(str);
        } catch (ParseException ex) {
            return null;
        }
        return result;
    }

    public static Date sumOneYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (date != null) {
            cal.add(Calendar.YEAR, 1);
            //cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return cal.getTime();
    }

    public static Date sumOneMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (date != null) {
            cal.add(Calendar.MONTH, 1);
        }
        return cal.getTime();
    }
}
