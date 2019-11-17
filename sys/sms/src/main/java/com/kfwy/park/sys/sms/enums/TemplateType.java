package com.kfwy.park.sys.sms.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 短信模板类型
 * @Auth luming
 * @Date 2019/7/12 9:40
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class TemplateType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(TemplateType.class.getName(), "templateType", "短信模板类型"));
        list.add(new Dictionary(TemplateType.class.getName(), "1", "验证码", "templateType"));
        list.add(new Dictionary(TemplateType.class.getName(), "2", "短信通知", "templateType"));
        list.add(new Dictionary(TemplateType.class.getName(), "3", "推广短信", "templateType"));
        return list;
    }
}