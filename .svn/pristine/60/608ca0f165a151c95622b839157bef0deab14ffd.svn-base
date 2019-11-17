package com.kfwy.park.sys.auth.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.sys.auth.enums.MenuType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidcun on 2018/5/19.
 */
public class MenuEntity extends BaseEntity {
    /**
     * 菜单编码
     */
    private String menuCode;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单元素
     */
    private String item;
    /**
     * 父级菜单编码
     */
    private String parentCode;

    /**
     * 菜单类型
     * @see MenuType
     */
    private String menuType;

    /**
     * 菜单类型名称
     */
    private String menuTypeName;
    /**
     * 菜单元素包
     */
    private String pack;
    /**
     * 菜单图标
     */
    private String iconCls;

    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 排序
     */
    private Integer sort;


    private List<MenuEntity> children;


    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuTypeName() {
        return DictionaryStorage.get(MenuType.class.getName(),this.getMenuType()).getName();
    }

    public void setMenuTypeName(String menuTypeName) {


        this.menuTypeName = menuTypeName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<MenuEntity> getChildren() {
        if(children == null){
            children = new ArrayList<MenuEntity>();
        }
        return children;
    }

    public void setChildren(List<MenuEntity> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuEntity that = (MenuEntity) o;

        return menuCode != null ? menuCode.equals(that.menuCode) : that.menuCode == null;
    }

    @Override
    public int hashCode() {
        return menuCode != null ? menuCode.hashCode() : 0;
    }
}
