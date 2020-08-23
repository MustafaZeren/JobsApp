package com.mustafazeren.jobsapp;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    public Job() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Job(String JobDetail,String Date,String Time) {
        super();
        this.JobDetail=JobDetail;
        this.Time=Time;
        this.Date=Date;
    }

    private int id;

    private String JobDetail;
    public String getJobDetail(){
        return JobDetail;
    }

    private String Time;
    public String getTime(){
        return Time;
    }
    public void setTime(String time){this.Time=time;}

    private String Date;
    public String getDate(){
        return Date;
    }
    public void setDate(String date){this.Date=date;}

}
