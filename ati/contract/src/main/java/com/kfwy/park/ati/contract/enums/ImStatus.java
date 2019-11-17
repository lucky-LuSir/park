package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/5/29.
 */
@Component
public class ImStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ImStatus.class.getName(),"ImStatus","应付中介费状态"));
        list.add(new Dictionary(ImStatus.class.getName(),"1","待支付","ImStatus"));
        list.add(new Dictionary(ImStatus.class.getName(),"2","已支付","ImStatus"));
        return list;
    }


}
