package com.cybersoft.crm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatusGroupWorkDetailsModelModel {
    private int id;
    private String nameStatus;
    private List<TaskGroupWorkDetailsModel> taskGroupWorkDetailsModels;
}
