package com.kfwy.park.act.activiti.emums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2019/4/12.
 * 退租类型
 */
@Component
public class ThrowLeaseType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(ThrowLeaseType.class.getName(),"throwLeaseType","throwLeaseType"));
        list.add(new Dictionary(ThrowLeaseType.class.getName(),"1","到期退租","throwLeaseType"));
        list.add(new Dictionary(ThrowLeaseType.class.getName(),"2","违约退租","throwLeaseType"));
        list.add(new Dictionary(ThrowLeaseType.class.getName(),"3","协商退租","throwLeaseType"));
        return list;
    }
}
