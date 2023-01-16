package com.cybersoft.crm.DTO;

import com.cybersoft.crm.model.StatusGroupWorkDetailsModelModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupWorkDetailsDTO {
    private int id;
    private String fullname;
    private List<StatusGroupWorkDetailsModelModel>  statusGroupWorkDetailsModelModels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<StatusGroupWorkDetailsModelModel> getStatusGroupWorkDetailsModelModels() {
        return statusGroupWorkDetailsModelModels;
    }

    public void setStatusGroupWorkDetailsModelModels(List<StatusGroupWorkDetailsModelModel> statusGroupWorkDetailsModelModels) {
        this.statusGroupWorkDetailsModelModels = statusGroupWorkDetailsModelModels;
    }
}
