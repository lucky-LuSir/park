package com.kfwy.park.sys.board.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/7/23.
 */
public class ProgramType  implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ProgramType.class.getName(),"programType","小程序类型"));
        list.add(new Dictionary(ProgramType.class.getName(),"1","园区","programType"));
        list.add(new Dictionary(ProgramType.class.getName(),"2","招商","programType"));
        list.add(new Dictionary(ProgramType.class.getName(),"3","拓展","programType"));
        list.add(new Dictionary(ProgramType.class.getName(),"4","财务","programType"));
        list.add(new Dictionary(ProgramType.class.getName(),"5","其他","programType"));
        return list;
    }
}
