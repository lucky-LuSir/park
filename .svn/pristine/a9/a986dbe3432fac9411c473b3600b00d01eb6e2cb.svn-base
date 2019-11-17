package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 工业集中区
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class ManufacturingDistrict implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ManufacturingDistrict.class.getName(),"ManufacturingDistrict","工业集中区"));
        list.add(new Dictionary(ManufacturingDistrict.class.getName(),"1","处于工业集中区","ManufacturingDistrict"));
        list.add(new Dictionary(ManufacturingDistrict.class.getName(),"2","非工业集中区，但距离在5公里及以内","ManufacturingDistrict"));
        list.add(new Dictionary(ManufacturingDistrict.class.getName(),"3","距离工业集中区超过5.1公里","ManufacturingDistrict"));
        return list;
    }
}
