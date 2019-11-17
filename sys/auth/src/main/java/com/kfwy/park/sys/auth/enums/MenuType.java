package com.kfwy.park.sys.auth.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidcun on 2018/5/19.
 *
 */
@Component
public class MenuType implements DictionaryProvider {


    @Override
    public List<Dictionary> produce() {

        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(MenuType.class.getName(),"menuType","菜单类型"));
        list.add(new Dictionary(MenuType.class.getName(),"1","菜单","menuType"));
        list.add(new Dictionary(MenuType.class.getName(),"2","功能","menuType"));
        return list;
    }
}
