package com.kfwy.park.common.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class EntityCompareUtil {


    /**
     * @Description 比较两个Object中的属性值是否都相同，相同返回true，有不同就返回false
     * @author wangchuang
     * @Date 2019/6/11 16:15
     * @Version 1.0
     * @Param [oldObject, newObject]
     * @return boolean
     **/
    public static boolean compareObject(Object oldObject, Object newObject) {

        Map<String, Map<String, Object>> resultMap = compareFields(oldObject, newObject);

        if(resultMap.size() > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个实体属性的值
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Map<String, Object>> compareFields(Object oldObject, Object newObject) {
        //返回的结果集
        Map<String, Map<String, Object>> map = null;

        try {
            /**
             * 要传入的对象是同一类型的才有可比性
             */
            if(oldObject.getClass() == newObject.getClass()) {
                map = new HashMap<String, Map<String,Object>>();

                Class clazz = oldObject.getClass();
                //获取object里面所有的属性
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();

                for (PropertyDescriptor pd : pds) {
                    //遍历获取属性名
                    String name = pd.getName();

                    //获取属性的get方法
                    Method readMethod = pd.getReadMethod();

                    //在oldObject上调用get方法等同于获得oldObject的属性值
                    Object oldValue = readMethod.invoke(oldObject);
                    //在newObject上调用get方法等同于获得newObject的属性值
                    Object newValue = readMethod.invoke(newObject);

                    //不处理集合类型
                    if(oldValue instanceof List) {
                        continue;
                    }

                    if(newValue instanceof List) {
                        continue;
                    }

                    //转换时间类型
                    if(oldValue instanceof Timestamp) {
                        oldValue = new Date(((Timestamp) oldObject).getTime());
                    }

                    if(newValue instanceof Timestamp) {
                        newValue = new Date(((Timestamp) newObject).getTime());
                    }

                    if(oldValue == null && newValue == null) {
                        continue;
                    } else if(oldValue == null && newValue != null) {
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);

                        map.put(name, valueMap);
                        continue;
                    }

                    //判断值是否相等，不相等就放在map中去
                    if(newValue instanceof BigDecimal) {
                        BigDecimal newValue1 = (BigDecimal) ((BigDecimal) newValue).setScale(2,BigDecimal.ROUND_HALF_UP);
                        newValue = newValue1;
                    }
                    if(!oldValue.equals(newValue)) {
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);

                        map.put(name, valueMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
