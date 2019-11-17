package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luming
 * @Descirption 建筑状态字典
 * @Date 2018/5/29 18:14
 */
@Component
public class BuildingStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(BuildingStatus.class.getName(),"buildingStatus","建筑状态"));
        list.add(new Dictionary(BuildingStatus.class.getName(),"1","热租中","buildingStatus"));
        list.add(new Dictionary(BuildingStatus.class.getName(),"2","已租完","buildingStatus"));
        return list;
    }
}
