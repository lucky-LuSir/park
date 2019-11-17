package com.kfwy.park.common.dic;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 人物性别
 * @Auth davidCun
 * @Date 2018/6/12 15:01
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class SexType implements DictionaryProvider {


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> dic = new ArrayList<Dictionary>();

        dic.add(new Dictionary(SexType.class.getName(),"sexType","性别"));
        dic.add(new Dictionary(SexType.class.getName(),"1","先生","sexType"));
        dic.add(new Dictionary(SexType.class.getName(),"2","女士","sexType"));

        return dic;
    }
}
