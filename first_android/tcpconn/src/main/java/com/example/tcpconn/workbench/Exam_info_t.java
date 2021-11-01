package com.example.tcpconn.workbench;

public class Exam_info_t {
    public String  phone_num;
    public String  id;
    public String  name;
    public String  dev_name;
    public String  trainee_name;
    public String  iden_card_num;
    public int      start_time;
    public int      stop_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getTrainee_name() {
        return trainee_name;
    }

    public void setTrainee_name(String trainee_name) {
        this.trainee_name = trainee_name;
    }

    public String getIden_card_num() {
        return iden_card_num;
    }

    public void setIden_card_num(String iden_card_num) {
        this.iden_card_num = iden_card_num;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getStop_time() {
        return stop_time;
    }

    public void setStop_time(int stop_time) {
        this.stop_time = stop_time;
    }
}
