package com.startdt.modules.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/30 下午5:24
 * @Modified By:
 */
public class ModulesTree implements Serializable{
    private String code;

    private String name;

    private List<ModulesTree> sonCode;

    private String note;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModulesTree> getSonCode() {
        return sonCode;
    }

    public void setSonCode(List<ModulesTree> sonCode) {
        this.sonCode = sonCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
