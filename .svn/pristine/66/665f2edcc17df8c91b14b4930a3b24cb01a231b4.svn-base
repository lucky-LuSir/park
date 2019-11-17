package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 装修类型
 * @Date 2018/6/9 11:46
 */
@Component
public class DecorationType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(DecorationType.class.getName(), "DecorationType", "装修类型"));
        list.add(new Dictionary(DecorationType.class.getName(), "1", "毛坯", "DecorationType"));
        list.add(new Dictionary(DecorationType.class.getName(), "2", "简装", "DecorationType"));
        list.add(new Dictionary(DecorationType.class.getName(), "3", "精装", "DecorationType"));
        list.add(new Dictionary(DecorationType.class.getName(), "4", "豪华装", "DecorationType"));
        return list;
    }
}
