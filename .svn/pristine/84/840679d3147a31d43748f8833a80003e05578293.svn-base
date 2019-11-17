package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Description 上下架类型字典
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Component
public class CusUpdownType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CusUpdownType.class.getName(),"cusUpdownType","上下架类型"));
        list.add(new Dictionary(CusUpdownType.class.getName(),"1","上架","cusUpdownType"));
        list.add(new Dictionary(CusUpdownType.class.getName(),"2","下架","cusUpdownType"));
        return list;
    }
}
