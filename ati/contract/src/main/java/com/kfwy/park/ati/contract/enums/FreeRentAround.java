package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/8/1.
 */
@Component
public class FreeRentAround implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FreeRentAround.class.getName(),"FreeRentAround","免租期前后"));
        list.add(new Dictionary(FreeRentAround.class.getName(),"1","前","FreeRentAround"));
        list.add(new Dictionary(FreeRentAround.class.getName(),"2","后","FreeRentAround"));
        return list;
    }
}
