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
public class FbType implements DictionaryProvider {


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FbType.class.getName(),"fbType", "反馈问题类型"));
        list.add(new Dictionary(FbType.class.getName(), "1", "硬件设备相关", "fbType"));
        list.add(new Dictionary(FbType.class.getName(), "2", "软件系统相关", "fbType"));
        list.add(new Dictionary(FbType.class.getName(), "3", "服务相关", "fbType"));
        list.add(new Dictionary(FbType.class.getName(), "4", "其他", "fbType"));

        return list;
    }
}
