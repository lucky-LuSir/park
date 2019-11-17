package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 地坪类型枚举类
 * @Date 2018/5/29 18:14
 */
@Component
public class FloorType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FloorType.class.getName(),"FloorType","地坪类型"));
        list.add(new Dictionary(FloorType.class.getName(),"1","环氧地坪","FloorType"));
        list.add(new Dictionary(FloorType.class.getName(),"2","水泥地坪","FloorType"));
        return list;
    }
}
