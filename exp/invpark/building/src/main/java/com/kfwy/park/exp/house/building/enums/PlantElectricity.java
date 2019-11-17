package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 送电情况
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class PlantElectricity implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(PlantElectricity.class.getName(),"PlantElectricity","装修情况"));
        list.add(new Dictionary(PlantElectricity.class.getName(),"1","电已通","PlantElectricity"));
        list.add(new Dictionary(PlantElectricity.class.getName(),"2","电基本完成","PlantElectricity"));
        list.add(new Dictionary(PlantElectricity.class.getName(),"3","电未通","PlantElectricity"));
        return list;
    }
}
