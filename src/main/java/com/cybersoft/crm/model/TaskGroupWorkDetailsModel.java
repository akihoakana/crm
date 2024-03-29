package com.cybersoft.crm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGroupWorkDetailsModel {
    private int id;
    private String nameTask;
    private String start_date;
    private String end_date;

    public int getId() {
        return id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }
}
