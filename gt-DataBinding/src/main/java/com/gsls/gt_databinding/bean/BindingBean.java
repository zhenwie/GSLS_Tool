package com.gsls.gt_databinding.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 绑定对象 类的解析
 */
public class BindingBean {

    private String packName;//包名
    private String className;//Activity 类名
    private String packClassPath;//包类相对路径
    private String layoutName;//布局名称
    private String layoutPath;//布局路径
    private String layoutAbsolutePath;//布局绝对路径
    private String javaLibraryName;//当前Java 库名
    private String bingingType;//绑定类型
    private List<XmlBean> xmlBeanList;//xml布局类型

    public BindingBean() {
        super();
    }

    public BindingBean(String packName, String className, String packClassPath, String layoutName, String layoutPath, String layoutAbsolutePath, String javaLibraryName, String bingingType,List<XmlBean> xmlBeanList) {
        this.packName = packName;
        this.className = className;
        this.packClassPath = packClassPath;
        this.layoutName = layoutName;
        this.layoutPath = layoutPath;
        this.layoutAbsolutePath = layoutAbsolutePath;
        this.javaLibraryName = javaLibraryName;
        this.bingingType = bingingType;
        this.xmlBeanList = xmlBeanList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String getLayoutPath() {
        return layoutPath;
    }

    public void setLayoutPath(String layoutPath) {
        this.layoutPath = layoutPath;
    }

    public String getLayoutAbsolutePath() {
        return layoutAbsolutePath;
    }

    public void setLayoutAbsolutePath(String layoutAbsolutePath) {
        this.layoutAbsolutePath = layoutAbsolutePath;
    }

    public String getJavaLibraryName() {
        return javaLibraryName;
    }

    public void setJavaLibraryName(String javaLibraryName) {
        this.javaLibraryName = javaLibraryName;
    }

    public String getBingingType() {
        return bingingType;
    }

    public void setBingingType(String bingingType) {
        this.bingingType = bingingType;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public List<XmlBean> getXmlBeanList() {
        return xmlBeanList;
    }

    public void setXmlBeanList(List<XmlBean> xmlBeanList) {
        this.xmlBeanList = xmlBeanList;
    }

    public void addXmlBean(XmlBean xmlBean) {
        if (xmlBeanList == null) {
            xmlBeanList = new ArrayList<>();
        }
        xmlBeanList.add(xmlBean);
    }

    public String getPackClassPath() {
        return packClassPath;
    }

    public void setPackClassPath(String packClassPath) {
        this.packClassPath = packClassPath;
    }

    @Override
    public String toString() {
        return "BindingBean{" +
                "packName='" + packName + '\'' +
                ", className='" + className + '\'' +
                ", packClassPath='" + packClassPath + '\'' +
                ", layoutName='" + layoutName + '\'' +
                ", layoutPath='" + layoutPath + '\'' +
                ", layoutAbsolutePath='" + layoutAbsolutePath + '\'' +
                ", javaLibraryName='" + javaLibraryName + '\'' +
                ", bingingType='" + bingingType + '\'' +
                ", xmlBeanList=" + xmlBeanList +
                '}';
    }
}
