package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2018/5/31.
 * 楼层需求字典
 */
@Component
public class FloorAsk implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FloorAsk.class.getName(),"floorAsk","楼层需求"));
        list.add(new Dictionary(FloorAsk.class.getName(),"1","单层","floorAsk"));
        list.add(new Dictionary(FloorAsk.class.getName(),"2","一楼","floorAsk"));
        list.add(new Dictionary(FloorAsk.class.getName(),"3","楼上","floorAsk"));
        list.add(new Dictionary(FloorAsk.class.getName(),"4","一楼或楼上","floorAsk"));
        return list;
    }
}
