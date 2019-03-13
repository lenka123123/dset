package com.wokun.dset.home;

import java.io.Serializable;

/**
 * Created by Administrator on 2019\1\31 0031.
 */

public class LevelBean  implements Serializable{
    private  String id;
    private  int grade;
    private  String personal;
    private  String team;
    private  String team_amount;
    private  String invite_num;
    private  int invite_target;
    private  Float bfb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getInvite_target() {
        return invite_target;
    }

    public void setInvite_target(int invite_target) {
        this.invite_target = invite_target;
    }

    public Float getBfb() {
        return bfb;
    }

    public void setBfb(Float bfb) {
        this.bfb = bfb;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam_amount() {
        return team_amount;
    }

    public void setTeam_amount(String team_amount) {
        this.team_amount = team_amount;
    }

    public String getInvite_num() {
        return invite_num;
    }

    public void setInvite_num(String invite_num) {
        this.invite_num = invite_num;
    }


}
