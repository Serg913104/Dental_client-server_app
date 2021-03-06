package com.Tickets.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Embeddable
@Table(name = "tickets_by_date")
public class Tickets {
    @Column(name = "schedule_num")
    private int schNum;
    @Id
    @Column(name = "start_time")
    @Size(min = 1, max = 8, message = "Use other format (hh-mm-ss)")
    @NotBlank(message = "Start time is required")
    private String stTime;
    @Column(name = "finish_time")
    @Size(min = 1, max = 8, message = "Use other format (hh-mm-ss)")
    @NotBlank(message = "Finish time is required")
    private String fnTime;
    @Column(name = "engaged")
    private boolean engaged = false;

    public Integer getSchNum() {
        return schNum;
    }
    public void setSchNum(Integer schNum) {
        this.schNum = schNum;
    }
    public String getStTime() {
        return stTime;
    }
    public void setStTime(String stTime) {
        this.stTime = stTime;
    }
    public String getFnTime() {
        return fnTime;
    }
    public void setFnTime(String fnTime) {this.fnTime = fnTime; }
    public boolean isEngaged() {return engaged; }
    public void setEngaged(boolean engaged) {
        this.engaged = engaged;
    }

    public Tickets(Integer schNum, String  stTime, String  fnTime, boolean engaged) {
        this.schNum = schNum;
        this.stTime = stTime;
        this.fnTime = fnTime;
        this.engaged = engaged;
    }
    public Tickets() { }
    @Override
    public String toString() {
        if(this.engaged)
            return "Tickets info {" + "Schedule #" + schNum + ", start in '" + stTime + '\'' + ", finish in '" + fnTime + '\'' + " engaged"+ '}';
        else
            return "Tickets info {" + "Schedule #" + schNum + ", start in '" + stTime + '\'' + ", finish in '" + fnTime + '\'' + " free"+ '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tickets))
            return false;
        Tickets ticket = (Tickets) o;
        return Objects.equals(getStTime(), ticket.getStTime());
    }
    @Override
    public int hashCode() {
        int result = getStTime().hashCode();
        result = 31 * result + getFnTime().hashCode();
        result = 31 * result + getSchNum().hashCode();
        return result;
    }
}

