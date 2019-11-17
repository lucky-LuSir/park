package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 库房类型枚举类
 * @Date 2018/5/29 18:14
 */
@Component
public class WareHouseType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(WareHouseType.class.getName(),"WareHouseType","库房类型"));
        list.add(new Dictionary(WareHouseType.class.getName(),"1","普通仓库厂房","WareHouseType"));
        list.add(new Dictionary(WareHouseType.class.getName(),"2","冷链仓库","WareHouseType"));
        list.add(new Dictionary(WareHouseType.class.getName(),"3","高台仓库","WareHouseType"));
        list.add(new Dictionary(WareHouseType.class.getName(),"4","危险品库","WareHouseType"));
        return list;
    }
}
