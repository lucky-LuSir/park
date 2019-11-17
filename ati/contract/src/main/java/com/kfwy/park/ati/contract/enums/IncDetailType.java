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
public class IncDetailType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(IncDetailType.class.getName(),"IncDetailType","是否详细递增"));
        list.add(new Dictionary(IncDetailType.class.getName(),"1","是","IncDetailType"));
        list.add(new Dictionary(IncDetailType.class.getName(),"2","否","IncDetailType"));
        return list;
    }
}
