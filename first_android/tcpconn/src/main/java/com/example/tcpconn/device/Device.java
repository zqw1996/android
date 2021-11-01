package com.example.tcpconn.device;



public class Device {

    public static final int kStandby = 1;           // 空闲状态
    public static final int kRunning = 2;           // 操作（考核）状态
    public static final int kPaused = 3;            // 暂停状态
    public static final int kFault = 4;             // 故障状态
    public static final int kWaiting = 5;           // 通讯正常，状态未知
    public static final int kOpening = 6;           // 通讯暂时故障，修复中
    public static final int kLinking = 7;           // 通讯暂时故障，修复中
    public static final int kOffline = 8;           // 通讯失去联系
    public static final int kIntermediary = 100;    // Temporary


    /*------------------------------------------------------------------------------*/
    /* static const members                                                         */
    /*------------------------------------------------------------------------------*/
    private final byte PRI_DEV_DATA = 0;             //命令优先级：查询设备数据
    private final byte PRI_COMMON_CMD = 1;           //命令优先级：普通命令
    private final byte PRI_START_ECE = 16;           //命令优先级：开始急救考核
    private final byte PRI_STOP_ECE = 16;            //命令优先级：结束急救考核
    private final byte PRI_START_TX = 12;            //命令优先级：开始上传数据
    private final byte PRI_STOP_TX = 12;             //命令优先级：开始上传数据
    private final byte PRI_SET_SER = 7;              //命令优先级：设置序列号
    private final byte PRI_SET_NAME = 7;             //命令优先级：设置名称
    private final byte PRI_ERASE_FLASH = 6;          //命令优先级：擦除FLASH
    private final byte PRI_DEV_CODE = 5;             //命令优先级：设备代码
    private final byte PRI_CODE_END = 4;             //命令优先级：代码传输完成

    /*------------------------------------------------------------------------------*/
    /* CDevice start                                                                */
    /*------------------------------------------------------------------------------*/


}
