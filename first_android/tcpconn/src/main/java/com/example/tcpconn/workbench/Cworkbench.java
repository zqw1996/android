package com.example.tcpconn.workbench;

import java.util.UUID;

public class Cworkbench {
    static final long  SEC_OFFSET = 1577836800;            //为了让装置可以用32位保存时间，设置一个共同的偏移量

    static final String STR_STATUS_STANDBY = "空 闲";
    static final String STR_STATUS_RUNNING = "运 行";
    static final String STR_STATUS_FAULT = "故 障";
    static final String STR_STATUS_PAUSED = "暂 停";
    static final String STR_STATUS_WAITING = "等 待";
    static final String STR_STATUS_OFFLINE = "离 线";

    static User_t user = new User_t("","","","");

    static String NewId(){
        return UUID.randomUUID().toString().replace("-","");
    };

    static int GetExamTimeLen(Exam_info_t exam){
        int  time_len = exam.stop_time - exam.start_time;
        return  (time_len > 0)? (time_len+59)/60 : 0;
    };

}
