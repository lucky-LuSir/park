package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 税收要求
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class TaxRequest implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(TaxRequest.class.getName(),"TaxRequest","装修情况"));
        list.add(new Dictionary(TaxRequest.class.getName(),"1","无税收要求，行业无限制","TaxRequest"));
        list.add(new Dictionary(TaxRequest.class.getName(),"2","有税收要求但行业无明显限制","TaxRequest"));
        list.add(new Dictionary(TaxRequest.class.getName(),"3","有税收要求行业限制高","TaxRequest"));
        return list;
    }
}
