package com.iweb.homework;

public class Subject {

    private String subjectId;
    private String name;
    private String createTime;
    private String description;

    public Subject() {
    }

    public Subject(String subjectId, String name, String createTime, String description) {
        this.subjectId = subjectId;
        this.name = name;
        this.createTime = createTime;
        this.description = description;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "课程信息 {" +
                "课程编号='" + subjectId + '\'' +
                ", 名称='" + name + '\'' +
                ", 创建时间='" + createTime + '\'' +
                ", 课程描述='" + description + '\'' +
                '}';
    }
}


