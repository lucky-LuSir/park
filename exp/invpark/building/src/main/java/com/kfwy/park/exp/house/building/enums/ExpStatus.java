package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Component
public class ExpStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ExpStatus.class.getName(),"ExpStatus", "拓展项目状态"));
        list.add(new Dictionary(ExpStatus.class.getName(), "1", "跟进中", "ExpStatus"));
        list.add(new Dictionary(ExpStatus.class.getName(), "2", "谈判中", "ExpStatus"));
        list.add(new Dictionary(ExpStatus.class.getName(), "3", "已签约", "ExpStatus"));
        list.add(new Dictionary(ExpStatus.class.getName(), "4", "不合适", "ExpStatus"));

        return list;
    }
}
