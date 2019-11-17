package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* @Description 描述:联系人性别字典
* @Auth xpp
* @Date 2018/8/2 14:49
* @Version 1.0
* @Param 
* @return 
**/
@Component
public class ContactSexName implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ContactSexName.class.getName(),"contactSexName","性别"));
        list.add(new Dictionary(ContactSexName.class.getName(),"1","先生","contactSexName"));
        list.add(new Dictionary(ContactSexName.class.getName(),"2","女士","contactSexName"));
        return list;
    }
}
