package com.edu.util;

import java.lang.reflect.Field;


public class ReflectUtils {

    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtils.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return result;
    }

    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    public static void setFieldValue(Object obj, String fieldName,String fieldValue) {

        Field field = ReflectUtils.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(obj, fieldValue);
            } catch (Exception e) {

            }
            field.setAccessible(false);
        }
    }

    public static <T> T findMemberByType (Object object,Class<T> type){

        if( null == type )
            return null;

        if(  type.isInstance( object )  ){
            return (T)object;
        }

        if(  object instanceof java.util.Map ){

            java.util.Map paramMap= (java.util.Map) object;
            for(Object parameter : paramMap.values() ){

                if( type.isInstance( parameter )){
                    return (T) parameter;
                }
            }
        }
        return null;
    }
}
