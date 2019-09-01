package com.startdt.modules.common.pojo;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/29 下午8:02
 * @Modified By:
 */
public class ModulesDO {
    private String code;

    private String modluesName;

    private String parentCode;

    private String sonCode;

    private String note;

    private Integer level;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModluesName() {
        return modluesName;
    }

    public void setModluesName(String modluesName) {
        this.modluesName = modluesName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getSonCode() {
        return sonCode;
    }

    public void setSonCode(String sonCode) {
        this.sonCode = sonCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
