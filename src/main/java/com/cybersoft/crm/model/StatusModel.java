package com.cybersoft.crm.model;

public class StatusModel {
    private int id;
    private String name;
    private int count;
    private int countpercent;

    public int getCountpercent() {
        return countpercent;
    }

    public void setCountpercent(int countpercent) {
        this.countpercent = countpercent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
