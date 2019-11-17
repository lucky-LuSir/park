package com.kfwy.park.common.utils;


import com.gniuu.framework.exception.BusinessException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/7/12.
 */
public class ParamUtil<T> {

    /*
     * @Description 该方法不处理boolean,由于前端传来的boolean默认为false
     * 同时不处理父类带有的字段,如BaseEntity中的createCode等
     * @Auth liuzhengyang
     * @Date 2018/7/12 18:19
     * @Version 1.0
     * @Param [t, map]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> objectToMap(T t,Map map) throws IllegalAccessException {

        if(t != null){
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f:fields){
                f.setAccessible(true);
                Object val = f.get(t);
                if(val != null && val != "" && !f.getType().toString().endsWith("boolean")){
                    map.put(f.getName(),val);
                }
            }
        }
        return map;
    }



    /*
     * @Description 新增修改参数校验
     * @Auth liuzhengyang
     * @Date 2018/7/20 13:33
     * @Version 1.0
     * @Param [t, params]
     * @return void
     */
    public void check(T t,List<String> params) throws IllegalAccessException {

        if(t != null){
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();

            //填充两个map集合
            Map<String,Object> valMap = new HashMap<>();
            Map<String,String> typeMap = new HashMap<>();
            for (Field f:fields){
                f.setAccessible(true);
                Object val = f.get(t);
                String param = f.getName();
                valMap.put(param,val);
                typeMap.put(param,f.getType().toString());
            }

            for(String param : params){
                Object val = valMap.get(param);
                String type = typeMap.get(param);
                if(type == null){
                    throw new BusinessException("设置的必填字段"+param+"不存在");
                }else if(type.endsWith("String")){
                    if(val == null || val == ""){
                        throw new BusinessException("必填String字段出错");
                    }
                }else if(type.endsWith("Integer") || type.endsWith("int")){
                    if(val == null || val == ""){
                        throw new BusinessException("必填Integer字段出错");
                    }
                }else if(type.endsWith("BigDecimal")){
                    if(val == null || val == ""){
                        throw new BusinessException("必填BigDecimal字段出错");
                    }
                }else if(type.endsWith("boolean") || type.endsWith("Boolean")){
                    if(val == null || val == ""){
                        throw new BusinessException("必填Boolean字段出错");
                    }
                }else if(type.endsWith("Date")){
                    if(val == null || val == ""){
                        throw new BusinessException("必填Date字段出错");
                    }
                }else if(type.endsWith("List")){
                    if(val == null || val == ""){
                        throw new BusinessException("必填String字段出错");
                    }
                }else if(val == null || val == ""){
                    throw new BusinessException("字段出错");
                }


            }



        }
    }

}
