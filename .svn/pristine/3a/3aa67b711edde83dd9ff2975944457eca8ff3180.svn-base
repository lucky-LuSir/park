package com.kfwy.park.ati.contract.enums;

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
public class CtStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CtStatus.class.getName(),"CtStatus","合同状态"));
        list.add(new Dictionary(CtStatus.class.getName(),"1","待提交","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"2","一审提交","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"3","一审通过","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"4","二审通过","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"5","驳回","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"6","撤回","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"7","已退租","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"8","已改签","CtStatus"));
        list.add(new Dictionary(CtStatus.class.getName(),"9","退租中","CtStatus"));
        return list;
    }
}
