package com.example.market.utils;

import java.lang.reflect.Field;

public class ClassMapper {
    public static <T, U> U mapClass(T source, Class<U> targetClass) throws IllegalAccessException, InstantiationException {
        U target = targetClass.newInstance();
        
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();
        
        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            for (Field targetField : targetFields) {
                targetField.setAccessible(true);
                
                if (sourceField.getName().equals(targetField.getName()) && sourceField.getType() == targetField.getType()) {
                    targetField.set(target, sourceField.get(source));
                    break;
                }
            }
        }
        
        return target;
    }
}
