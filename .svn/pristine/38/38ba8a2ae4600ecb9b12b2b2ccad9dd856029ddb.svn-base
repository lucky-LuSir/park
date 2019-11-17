package com.kfwy.park.pro.feedback.enums;

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
public class FbStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FbStatus.class.getName(),"fbStatus","反馈问题状态"));
        list.add(new Dictionary(FbStatus.class.getName(),"1","待处理","fbStatus"));
        list.add(new Dictionary(FbStatus.class.getName(),"2","已受理","fbStatus"));
        list.add(new Dictionary(FbStatus.class.getName(),"3","已解决","fbStatus"));
        return list;
    }
}
