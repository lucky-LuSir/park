package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 装修情况
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class PlantDecoration implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(PlantDecoration.class.getName(),"PlantDecoration","装修情况"));
        list.add(new Dictionary(PlantDecoration.class.getName(),"1","厕所楼道地坪无需修整","PlantDecoration"));
        list.add(new Dictionary(PlantDecoration.class.getName(),"2","厕所楼道地坪需稍作修整","PlantDecoration"));
        list.add(new Dictionary(PlantDecoration.class.getName(),"3","毛坯需要重新修整","PlantDecoration"));
        return list;
    }
}
