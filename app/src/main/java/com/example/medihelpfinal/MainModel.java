package com.example.medihelpfinal;

public class MainModel {
    String dname;
    String dtype;
    String davdate;
    String demail;
    Boolean davailable;
    public MainModel()
    {

    }

    public MainModel(String dname, String dtype, String davdate, String demail, Boolean davailable) {
        this.dname = dname;
        this.dtype = dtype;
        this.davdate = davdate;
        this.demail = demail;
        this.davailable = davailable;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getDavdate() {
        return davdate;
    }

    public void setDavdate(String davdate) {
        this.davdate = davdate;
    }

    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }

    public Boolean getDavailable() {
        return davailable;
    }

    public void setDavailable(Boolean davailable) {
        this.davailable = davailable;
    }
}
