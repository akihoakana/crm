package com.cybersoft.crm.DTO;

public class UserDetailDTO {
    private String name="";
    private String start_date="";
    private String end_date="";
    private String usersfullname ="";
    private String usersemail="";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getUsersfullname() {
        return usersfullname;
    }

    public void setUsersfullname(String usersfullname) {
        this.usersfullname = usersfullname;
    }

    public String getUsersemail() {
        return usersemail;
    }

    public void setUsersemail(String usersemail) {
        this.usersemail = usersemail;
    }

}
