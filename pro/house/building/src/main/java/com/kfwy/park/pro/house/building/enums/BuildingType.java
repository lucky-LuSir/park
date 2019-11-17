package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2018/6/1.
 * 建筑结构字典
 */
@Component
public class BuildingType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(BuildingType.class.getName(),"buildingType","建筑结构"));
        list.add(new Dictionary(BuildingType.class.getName(),"1","钢结构","buildingType"));
        list.add(new Dictionary(BuildingType.class.getName(),"2","砖混","buildingType"));
        list.add(new Dictionary(BuildingType.class.getName(),"3","框架","buildingType"));
        list.add(new Dictionary(BuildingType.class.getName(),"4","钢混","buildingType"));
        return list;
    }
}
