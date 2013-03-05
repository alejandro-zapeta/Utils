package com.java.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azapeta
 */
public class MyBeanUtil {

    private MyBeanUtil() {
    }
//
//    public static void print(Object value) {
//        System.out.println(value);
//    }
    private static final Map<String, Class> builtInMap = new HashMap<String, Class>();

    static {
        builtInMap.put("int", Integer.class);
        builtInMap.put("long", Long.class);
        builtInMap.put("double", Double.class);
        builtInMap.put("float", Float.class);
        builtInMap.put("bool", Boolean.class);
        builtInMap.put("char", Character.class);
        builtInMap.put("byte", Byte.class);
        builtInMap.put("void", Void.TYPE);
        builtInMap.put("short", Short.class);
    }

//    public static Map<String, String> convertToString(List<AseguradoMed> asegs, String[][] types) {
//        if (asegs == null || asegs.isEmpty()) {
//            return Collections.EMPTY_MAP;
//        }
//        Map<String, String> result = new LinkedHashMap<String, String>();
//        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
//        for (AseguradoMed aseg : asegs) {
//            String name = "";
//            for (int i = 0; i < types.length; i++) {
//
//                if (aseg.getTipoCliente().equals(types[i][0])) {
//                    name = types[i][1];
//                    Integer num = map.get(name);
//                    num = (num == null) ? 1 : ++num;
//                    map.put(name, num);
//                    name = name + "_" + num + "_dinamic_";
//                    result.putAll(nameOfMethodsBeansDynamic(aseg, name));
//                }
//            }
//            map = null;
//        }
//        return result;
//    }
    public static Map<String, String> convertToString(Object obj, Integer val) {
        return nameOfMethodsBeans(obj);
    }

    public static void copyObject(Object src, Object dest) {
        Map<String, String> srcmap = nameOfMethodsBeans(src);
        setting(srcmap, dest);
    }

    public static Map<String, String> nameOfMethodsBeans(Object myObject) {
        if (myObject == null) {
            return Collections.EMPTY_MAP;
        }
        Class cls = myObject.getClass();
        Map<String, String> result = Collections.EMPTY_MAP;
        if (cls != null) {
            Method[] methods = getMethods(cls);
            if (methods.length > 0) {
                result = new LinkedHashMap<String, String>();
                for (int i = 0; i < methods.length; i++) {
                    String nameMethod = getNameOfGetter(methods[i]);
                    if (nameMethod != null) {
                        Object o;
                        o = invoke(methods[i], myObject, (Object[]) null);
                        if (o != null) {
                            result.put(StringUtil.toLowerCase(nameMethod, 1), String.valueOf(o));
                            //   System.out.println("putting" + String.valueOf(o));
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String findFieldByNameOfMethod(Class cls, String name) {
        Field fields[] = cls.getFields();
        if (fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                Field campo = fields[i];
                String valorCampo;
                try {
                    valorCampo = (String) campo.get(cls.newInstance());
                    if (valorCampo.equals(name)) {
                        return valorCampo;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return null;
    }

    public static Map<String, String> nameOfMethodsBeansDynamic(final Object myObject, String val) {
        if (myObject == null || val == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, String> methods = nameOfMethodsBeans(myObject);
        Map<String, String> result = Collections.EMPTY_MAP;

        if (methods != null && !methods.isEmpty()) {
            result = new LinkedHashMap<String, String>();
            for (Map.Entry<String, String> s : methods.entrySet()) {
                //    print("method dynamic:" + val + s);
                result.put(val + s.getKey(), s.getValue());
            }
        }

        methods = null;
        return result;
    }

    public static String getNameOfMethodIsGetterOrSetter(Method method) {
        if (method != null) {
            String get = getNameOfGetter(method);
            if (get != null) {
                return get;
            }
        }
        return null;
    }

    public static Method[] getMethods(Class myObject) {
        if (myObject == null) {
            return null;
        }
        return myObject.getMethods();
    }

    public static String getNameOfGetter(Method method) {
        if (isGetterMethod(method)) {
            return method.getName().replaceAll("get", "");
        }
        return null;
    }

    public static String getNameOfSetter(Method method) {
        if (isSetterMethod(method)) {
            return method.getName().replaceAll("set", "").toLowerCase();
        }
        return null;
    }

    public static boolean isGetterMethod(Method method) {

        if (method != null
                && method.getName() != null
                && method.getName().matches("get([A-Z0-9]|[^Class\\w]).+")
                && method.getParameterTypes().length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isSetterMethod(Method method) {
        if (method != null
                && method.getName() != null
                && method.getName().matches("set[A-Z0-9][^Class\\w]+")
                && method.getParameterTypes().length > 0) {
            return true;
        }
        return false;
    }
//////////////////SETTTTTTTTTTTTERRRR
    // value: ???
    // key: dinamic_conyuge_1_nombre

    public static void setBeans(Object myObject) {
        if (myObject == null) {
            return;
        }
        Class cls = myObject.getClass();
        if (cls != null) {
            Method[] methods = getMethods(cls);
            for (int i = 0; i < methods.length; i++) {
                String nameSetMethod = getNameOfSetter(methods[i]);
                if (nameSetMethod != null) {
                    try {
                        methods[i].invoke(myObject, methods);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public static Method findMethod(String name, Object myObject) {
        if (myObject == null || name == null || name.isEmpty()) {
            return null;
        }

        Method[] methods = getMethods(myObject.getClass());
        for (int i = 0; i < methods.length; i++) {
            if (name.equals(methods[i].getName())) {
                return methods[i];
            }
        }
        return null;
    }

    public static void setObject(String id, String value, Object obj) {
        invokeMethodByType("set", id, value, obj);
    }

    public void getObject(String id, Object obj) {
        invokeMethodByType("get", id, null, obj);
    }

    public static void invokeMethodByType(String type, String id, String value, Object obj) {
        id = String.format("%s%s", type, StringUtil.toUpperCase(id, 1).trim());
        Method m = findMethod(id, obj);
        if (m != null) {
            invoke(m, obj, value);
        }
    }

    private static Object invoke(Method method, Object myObject, Object... objs) {
        Object o = null;
        try {

            o = method.invoke(myObject, isInvokableMethod(method, objs));
        } catch (IllegalAccessException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    private void getting(Object obj) {
    }

    public static Object[] isInvokableMethod(Method method, Object[] objs) {
        if (objs == null) {
            return null;
        }
        Object[] objetos = new Object[objs.length];
        if (method != null) {
//            int m = method.getModifiers();
            method.setAccessible(true);
//            if (Modifier.isStatic(m)) {
//                return false;
//            }
//            if (!method.isVarArgs()) {
//                return true;
//            }
            //   print(method.getName());
            //   TypeVariable[] parameters = method.getTypeParameters();
            Class<?>[] parameterss = method.getParameterTypes();
            // print(method.getParameterTypes());
            if (parameterss.length > 0) {
                int i = 0;
                for (Class param : parameterss) {
                    objetos[i] = setObject(param, objs[i]);
                }
            }
        }
        return objetos;
    }

    public static Object setObject(Class cl, Object obj) {
        Object object = null;
        if (cl == null) {
            return null;
        }
        if (cl.isPrimitive()) {

            cl = builtInMap.get(cl.getSimpleName());
        }
        if (cl == null) {
            return null;
        }
        try {

            Constructor<?> ctor = cl.getConstructor(String.class);
            String str = (String) obj;
            object = ctor.newInstance(new Object[]{str.trim()});
            return object;
        } catch (InstantiationException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
//            Logger.getLogger(MyBeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        } finally {
            // object.
        }

        return obj;
    }

    public static void setting(Map<String, String> map, Object obj) {
        if (map == null || map.isEmpty() || obj == null) {
            return;
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null) {
                setObject(entry.getKey(), entry.getValue(), obj);
            }
        }

    }
    ///MAPS

    public static Map<String, Map<String, String>> splitDinamics(Map<String, String> map) {
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<String, String>(map);
        return recursive(m, null, new HashMap<String, Map<String, String>>(), null);
    }

    public static Map<String, Map<String, String>> recursive(Map<String, String> inicial, Map<String, Map<String, String>> rfinal, Map<String, Map<String, String>> temp, String kTemp) {
        if (inicial == null || inicial.size() <= 0) {//axioma No1
            return Collections.EMPTY_MAP;
        }
        for (Map.Entry<String, String> entryInicial : inicial.entrySet()) {
            if (entryInicial != null) {
                if (entryInicial.getKey() != null
                        && entryInicial.getKey().contains("_dinamic_")) {
                    String key = entryInicial.getKey().replaceFirst("_dinamic_.+", "");
                    String kKey = entryInicial.getKey().replaceFirst(".+dinamic_", "");

                    if (kTemp == null) {
                        kTemp = new String(key);
                        temp = new HashMap<String, Map<String, String>>();
                        Map<String, String> listTemp = new HashMap<String, String>();
                        listTemp.put(kKey, entryInicial.getValue());
                        temp.put(key, listTemp);
                        inicial.remove(entryInicial.getKey());//pop
                    } else {

                        if (kTemp.equals(key)) {
                            Map<String, String> listTemp = temp.get(kTemp);
                            listTemp.put(kKey, entryInicial.getValue());
                            temp.put(kTemp, listTemp);
                            inicial.remove(entryInicial.getKey());//pop
                        }
                    }
                } else {
                    inicial.remove(entryInicial.getKey());//pop
                }
            } else {
                inicial.remove(entryInicial); // pop
            }
        }
        //addAll rFinal
        if (rfinal == null) {
            rfinal = new HashMap<String, Map<String, String>>();
        }
        rfinal.putAll(temp);
        //setNull again.
        kTemp = null;
        temp = null;


        if (inicial.size() <= 0) {//axioma No.2
            return rfinal;
        } else {
            return recursive(inicial, rfinal, temp, kTemp);
        }
    }
}
