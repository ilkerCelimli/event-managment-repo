package org.portifolyo.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.hibernate.validator.internal.util.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;

public class UpdateHelper<S,T> {


    public  T updateHelper(S source,T target) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        RecordComponent[] rc = source.getClass().getRecordComponents();
       Class targetClazz = target.getClass();
       Field[] targetFields = targetClazz.getDeclaredFields();
       for(RecordComponent s : rc){
           if(s.getAccessor().invoke(source) != null) {
               for(Field t : targetFields) {
                   if(t.getName().contains(s.getName())){
                       String fieldName = t.getName().substring(1);
                       String methodName = "set"+String.valueOf(t.getName().charAt(0)).toUpperCase()+fieldName;
                       MethodUtils.invokeMethod(target,methodName,s.getAccessor().invoke(source));
                   }
               }
           }
       }
       return target;
    }

}
