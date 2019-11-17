package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/5/29.
 */
@Component
public class TaxPoint  implements DictionaryProvider {


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(TaxPoint.class.getName(),"TaxPoint","发票税点"));
        list.add(new Dictionary(TaxPoint.class.getName(),"1","3%","TaxPoint"));
        list.add(new Dictionary(TaxPoint.class.getName(),"2","5%","TaxPoint"));
        list.add(new Dictionary(TaxPoint.class.getName(),"3","6%","TaxPoint"));
        list.add(new Dictionary(TaxPoint.class.getName(),"4","9%","TaxPoint"));
        list.add(new Dictionary(TaxPoint.class.getName(),"5","16%","TaxPoint"));
        return list;
    }


}
