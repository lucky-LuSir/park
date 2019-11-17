package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2018/5/31.
 * 客户联系人职务字典
 */
@Component
public class ContactJob implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ContactJob.class.getName(),"contactJob","联系人职务"));
        list.add(new Dictionary(ContactJob.class.getName(),"1","经办人","contactJob"));
        list.add(new Dictionary(ContactJob.class.getName(),"2","决策人","contactJob"));
        list.add(new Dictionary(ContactJob.class.getName(),"3","经办和决策","contactJob"));
        return list;
    }
}
