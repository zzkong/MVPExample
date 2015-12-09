package lico.example.utils;

import java.lang.reflect.Field;

/**
 * Created by zzk on 15/12/7.
 */
public class Utils {

    public static int getResId(String variableName, Class<?> c){
            try {
                Field field = c.getDeclaredField(variableName);
                return field.getInt(field);
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }
    }
}
