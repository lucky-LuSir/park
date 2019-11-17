package com.kfwy.park.common.dic;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 消防等级枚举类
 * @Date 2018/5/29 18:14
 */
@Component
public class FirefightingLevel implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FirefightingLevel.class.getName(),"FirefightingLevel","消防等级"));
        list.add(new Dictionary(FirefightingLevel.class.getName(),"1","甲类","FirefightingLevel"));
        list.add(new Dictionary(FirefightingLevel.class.getName(),"2","乙类","FirefightingLevel"));
        list.add(new Dictionary(FirefightingLevel.class.getName(),"3","丙一类","FirefightingLevel"));
        list.add(new Dictionary(FirefightingLevel.class.getName(),"4","丙二类","FirefightingLevel"));
        list.add(new Dictionary(FirefightingLevel.class.getName(),"5","丁类","FirefightingLevel"));
        list.add(new Dictionary(FirefightingLevel.class.getName(),"6","戊类","FirefightingLevel"));
        return list;
    }
}
