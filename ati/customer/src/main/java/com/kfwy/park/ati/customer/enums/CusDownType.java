package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Description 下架类型字典
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Component
public class CusDownType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CusDownType.class.getName(), "cusDownType", "下架类型"));
        list.add(new Dictionary(CusDownType.class.getName(), "1", "内部成交", "cusDownType"));
        list.add(new Dictionary(CusDownType.class.getName(), "2", "外部成交", "cusDownType"));
        list.add(new Dictionary(CusDownType.class.getName(), "3", "不租了", "cusDownType"));
        return list;
    }
}
