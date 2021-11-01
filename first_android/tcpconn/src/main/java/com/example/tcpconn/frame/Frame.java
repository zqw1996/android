package com.example.tcpconn.frame;

import com.example.tcpconn.workbench.Exam_info_t;
import com.example.tcpconn.workbench.Sp_data_t;

import java.util.Arrays;

public class Frame {
    public static final byte ERR_CODE_NONE = 0;           //错误码：无错误

    public static final byte FRM_FLAG_CMD = (byte) 0xDD;         //帧标志：命令帧
    public static final byte FRM_FLAG_DATA = (byte) 0xDD;        //帧标志：数据帧
    public static final byte FRM_FLAG_ACK = (byte) 0xAA;         //帧标志：应答帧

    public static final short PKT_LEN_MAX = 240;           //Packet data 最大长度

    public static final byte PKT_CLS_DEV_INFO = 0x01;     //Packet Class 设备信息查询
    public static final byte PKT_CLS_DEV_DATA = 0x02;     //Packet Class 设备数据查询
    public static final byte KT_CLS_SET_TIME = 0x03;     //Packet Class 对时

    public static final byte PKT_CLS_START_ECE = 0x10;    //Packet Class 开始急救考核（Emergency Care Evaluation）
    public static final byte PKT_CLS_READ_ECE = 0x11;     //Packet Class 查询急救考核
    public static final byte PKT_CLS_STOP_ECE = 0x12;     //Packet Class 结束急救考核

    public static final byte PKT_CLS_START_TX = 0x20;     //Packet Class 开始上传数据
    public static final byte PKT_CLS_STOP_TX = 0x21;      //Packet Class 停止上传数据

    public static final byte PKT_CLS_SP_DATA = 0x40;      //Packet Class 采样数据上传

    public static final byte PKT_CLS_SET_SER = (byte) 0xA0;      //Packet Class 设置序列号
    public static final byte PKT_CLS_SET_NAME = (byte) 0xA1;     //Packet Class 设置名称

    public static final byte PKT_CLS_ERASE_FLASH = (byte) 0xB0;  //Packet Class 擦除FLASH
    public static final byte PKT_CLS_DEV_CODE = (byte) 0xB1;     //Packet Class 设备代码
    public static final byte PKT_CLS_CODE_END = (byte) 0xB2;     //Packet Class 代码传输完成

    public static final byte PKT_CLS_DEV_RESET = (byte) 0xF0;    //Packet Class 设备复位

    public static final int PKT_LEN_EXAM_INFO = 80;      //考核信息包长度
    public static final int EXAM_NAME_LEN = 20;          //考核名称长度 20 字节
    public static final int TRAINEE_NAME_LEN = 16;       //学员姓名长度 16 字节
    public static final int IDEN_CARD_NUM_LEN = 18;      //身份证号码 18 字节
    public static final int PHONE_NUM_LEN = 11;          //手机号码 11 字节

    private static final byte FRM_HDR_LEN = 8;             //帧头长度

    private static final short FRM_SYN_CODE = (short) 0xF2D7;       //帧同步码

    private static short m_nextFrmId = 0;                //发送帧ID号


    private static final int[] table_crc_hi = {
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
            0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
            0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
            0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40,
            0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1,
            0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
            0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0,
            0x80, 0x41, 0x00, 0xC1, 0x81, 0x40
    };

    private static final int[] table_crc_lo = {
            0x00, 0xC0, 0xC1, 0x01, 0xC3, 0x03, 0x02, 0xC2, 0xC6, 0x06,
            0x07, 0xC7, 0x05, 0xC5, 0xC4, 0x04, 0xCC, 0x0C, 0x0D, 0xCD,
            0x0F, 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB, 0x0B, 0xC9, 0x09,
            0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB, 0xDA, 0x1A,
            0x1E, 0xDE, 0xDF, 0x1F, 0xDD, 0x1D, 0x1C, 0xDC, 0x14, 0xD4,
            0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13, 0xD3,
            0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3,
            0xF2, 0x32, 0x36, 0xF6, 0xF7, 0x37, 0xF5, 0x35, 0x34, 0xF4,
            0x3C, 0xFC, 0xFD, 0x3D, 0xFF, 0x3F, 0x3E, 0xFE, 0xFA, 0x3A,
            0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38, 0x28, 0xE8, 0xE9, 0x29,
            0xEB, 0x2B, 0x2A, 0xEA, 0xEE, 0x2E, 0x2F, 0xEF, 0x2D, 0xED,
            0xEC, 0x2C, 0xE4, 0x24, 0x25, 0xE5, 0x27, 0xE7, 0xE6, 0x26,
            0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0, 0xA0, 0x60,
            0x61, 0xA1, 0x63, 0xA3, 0xA2, 0x62, 0x66, 0xA6, 0xA7, 0x67,
            0xA5, 0x65, 0x64, 0xA4, 0x6C, 0xAC, 0xAD, 0x6D, 0xAF, 0x6F,
            0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8, 0x68,
            0x78, 0xB8, 0xB9, 0x79, 0xBB, 0x7B, 0x7A, 0xBA, 0xBE, 0x7E,
            0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4, 0x74, 0x75, 0xB5,
            0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71,
            0x70, 0xB0, 0x50, 0x90, 0x91, 0x51, 0x93, 0x53, 0x52, 0x92,
            0x96, 0x56, 0x57, 0x97, 0x55, 0x95, 0x94, 0x54, 0x9C, 0x5C,
            0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E, 0x5A, 0x9A, 0x9B, 0x5B,
            0x99, 0x59, 0x58, 0x98, 0x88, 0x48, 0x49, 0x89, 0x4B, 0x8B,
            0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D, 0x4D, 0x4C, 0x8C,
            0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82, 0x42,
            0x43, 0x83, 0x41, 0x81, 0x80, 0x40
    };


    /*------------------------------------------------------------------------------*/
    /* 在数据缓冲区，按通讯协议搜索帧                                                   */
    /*------------------------------------------------------------------------------*/
    public static byte[] searchFrame(byte[] buf) {
        for (int i = 0; i + FRM_HDR_LEN + 2 <= buf.length; /**/) {
            byte[] frm = Arrays.copyOfRange(buf,i,buf.length);

            if (GetSynCode(frm) != FRM_SYN_CODE || !ChkPktLen(frm)) {
                i++;
                continue;
            }

            int len = GetPktLen(frm);

            if (frm.length < FRM_HDR_LEN + len + 2)      //Not the whole Frame received
            {
                break;
            }

            if (!ChkFrmCRC(frm)) {
                i += 2;
                continue;
            }

            buf = Arrays.copyOfRange(buf,i + 10 + len,buf.length);

            return Arrays.copyOfRange(frm , 0 ,10 + len);
        }
        return null;
    }

    /*-------------------------------------------------------------------------------*/
    /* 识别帧，如果是设备信息帧，提取PakcetData                                          */
    /*-------------------------------------------------------------------------------*/
    public static boolean bIsDevInfoFrame(byte[] frm, byte[] pktData)
    {
        if( GetFrmFlag(frm)==FRM_FLAG_ACK &&
                GetPktLen(frm)==52 &&
                GetPktCls(frm)==PKT_CLS_DEV_INFO)       //设备信息答复帧
        {
            pktData = GetPktData(frm);

            return  true;
        }
        else
        {
            return  false;
        }
    }

    /*------------------------------------------------------------------------------*/
    /* 生成一个应答帧                                                                 */
    /*------------------------------------------------------------------------------*/
    public static byte[] makeAckFrm(byte[] rxFrm)
    {
        byte[] frm = new byte[FRM_HDR_LEN + 2];
        Arrays.fill(frm, (byte) 0x0);

        SetSynCode(frm, FRM_SYN_CODE);
        SetFrmID(frm, GetFrmID(rxFrm));
        SetFrmFlag(frm, FRM_FLAG_ACK);

        SetPktLen(frm, (byte) 0x0);
        SetPktCls(frm, GetPktCls(rxFrm));

        SetFrmCRC(frm);

        return  frm;
    }

    /*------------------------------------------------------------------------------*/
    /* 生成一个命令帧                                                               */
    /*------------------------------------------------------------------------------*/
    public static byte[] makeCmdFrm(byte pktCls, byte[] pktData)
    {
        byte[] frm = new byte[FRM_HDR_LEN + pktData.length + 2];
        Arrays.fill(frm ,(byte) 0x0);

        SetSynCode(frm, FRM_SYN_CODE);
        SetFrmID(frm, m_nextFrmId++);
        SetFrmFlag(frm, FRM_FLAG_CMD);

        SetPktLen(frm, (byte) pktData.length);
        SetPktCls(frm, pktCls);

        SetPktData(frm, pktData);

        SetFrmCRC(frm);

        return  frm;
    }

    /*-------------------------------------------------------------------------------*/
    /* 取数据包                                                                       */
    /*-------------------------------------------------------------------------------*/
    public static byte[] GetPktData(byte[] frm)
    {
        int pktLen = GetPktLen(frm);

        return  Arrays.copyOfRange(frm, 8 , 8 + pktLen);
    }

    /*-------------------------------------------------------------------------------*/
    /* 置数据包                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static void SetPktData(byte[] frm, byte[] pkt)
    {
        System.arraycopy(pkt,0,frm,FRM_HDR_LEN,GetPktLen(frm));
    }

    /*-------------------------------------------------------------------------------*/
    /* 取帧ID                                                                        */
    /*-------------------------------------------------------------------------------*/
    public static short GetFrmID(byte[] frm)
    {
        return (short) ((short)(frm[2] << 8) | frm[3]);
    }

    /*-------------------------------------------------------------------------------*/
    /* 置帧ID                                                                        */
    /*-------------------------------------------------------------------------------*/
    public static void SetFrmID(byte[] frm, short frmID)
    {
        frm[2] = (byte) (frmID >> 8);
        frm[3] = (byte) (frmID & 0x0FF);
    }

    /*-------------------------------------------------------------------------------*/
    /* 取包类型                                                                       */
    /*-------------------------------------------------------------------------------*/
    public static byte GetPktCls(byte[] frm)
    {
        return  frm[7];
    }

    /*-------------------------------------------------------------------------------*/
    /* 置包类型                                                                       */
    /*-------------------------------------------------------------------------------*/
    public static void SetPktCls(byte[] frm, byte pktCls)
    {
        frm[7] = pktCls;
    }


    /*-------------------------------------------------------------------------------*/
    /* 取同步码                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static short GetSynCode(byte[] frm) {
        byte h = frm[0];
        byte l = frm[1];

        return (short) ((short) (h << 8) | l);
    }

    /*-------------------------------------------------------------------------------*/
    /* 置同步码                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static void SetSynCode(byte[] frm, short synCode) {
        frm[0] = (byte) (synCode >> 8);
        frm[1] = (byte) (synCode & 0xFF);
    }

    /*-------------------------------------------------------------------------------*/
    /* 取包长度                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static int GetPktLen(byte[] frm) {
        return frm[5] & 0x0FF;
    }

    /*-------------------------------------------------------------------------------*/
    /* 置包长度                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static void SetPktLen(byte[] frm, byte frmLen) {

        frm[5] = frmLen;
        frm[6] = (byte) (0xFF - frmLen);
    }

    /*-------------------------------------------------------------------------------*/
    /* 检查包长度                                                                    */
    /*-------------------------------------------------------------------------------*/
    public static boolean ChkPktLen(byte[] frm) {
        return (frm[5] + frm[6] == 0xFF);
    }

    /*-------------------------------------------------------------------------------*/
    /* 取帧标志                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static byte GetFrmFlag(byte[] frm)
    {
        return  frm[4];
    }

    /*-------------------------------------------------------------------------------*/
    /* 置帧标志                                                                      */
    /*-------------------------------------------------------------------------------*/
    public static void SetFrmFlag(byte[] frm, byte frmFlag)
    {
        frm[4] = frmFlag;
    }

    /*-------------------------------------------------------------------------------*/
    /* 取帧校验                                                                       */
    /*-------------------------------------------------------------------------------*/
    public static short GetFrmCRC(byte[] frm) {
        int pktLen = GetPktLen(frm);
        return (short) ((short) (frm[pktLen + 8] << 8) | frm[pktLen + 9]);
    }

    /*-------------------------------------------------------------------------------*/
    /* 置帧校验                                                                       */
    /*-------------------------------------------------------------------------------*/
    public static void SetFrmCRC(byte[] frm) {
        int pktLen = GetPktLen(frm);
        short crc =  crc16(frm, pktLen + 8);
        frm[pktLen + 8] = (byte) (crc >> 8);
        frm[pktLen + 9] = (byte) (crc & 0xFF);
    }

    /*-------------------------------------------------------------------------------*/
    /* 帧校验                                                                         */
    /*-------------------------------------------------------------------------------*/
    public static boolean ChkFrmCRC(byte[] frm) {
        int pktLen = GetPktLen(frm);
        short crc = crc16(frm, pktLen + 8);
        short frm_crc = GetFrmCRC(frm);
        return crc == frm_crc;
    }

    /*------------------------------------------------------------------------------*/
    /* CFrame::crc16                                                                */
    /*------------------------------------------------------------------------------*/
    private static short crc16(final byte[] buffer, int bytes){
        final byte[] data = buffer;

        byte  crc_hi = (byte) 0xFF;                              /* high CRC byte initialized */
        byte  crc_lo = (byte) 0xFF;                              /* low CRC byte initialized */
        byte  i;                                          /* will index into CRC lookup */

        for(int k = 0; k < bytes; k++)
        {
            i = (byte) ((crc_hi ^ data[k]) & 0xFF);                  /* calculate the CRC  */
            crc_hi = (byte) ((crc_lo ^ table_crc_hi[i]) & 0xFF);
            crc_lo = (byte) table_crc_lo[i];
        }
        return (short) ((short) (crc_hi << 8) | (int)(crc_lo));
    }

    /*------------------------------------------------------------------------------*/
    /* 生成一个应答帧                                                                 */
    /*------------------------------------------------------------------------------*/
    public static byte[] GetByteArray(String src, int len)
    {
        byte[] array = src.getBytes();
        if(array.length < len){
            byte[] append = new byte[len];
            Arrays.fill(append, (byte) 0x0);
            System.arraycopy(array,0,append,0,array.length);
            return append;
        }
        return  Arrays.copyOfRange(array , 0 ,len);
    }

    public static byte[] GetByteArray(int src){
        byte[] array = new byte[4];
        array[0] = (byte) (src & 0xff);
        array[1] = (byte) (src >> 8 & 0xff);
        array[2] = (byte) (src >> 16 & 0xff);
        array[3] = (byte) (src >> 24 & 0xff);
        return array;
    }

    /*------------------------------------------------------------------------------*/
    /* 生成一个应答帧                                                                 */
    /*------------------------------------------------------------------------------*/
    public static byte[] makeExamInfoPkt(Exam_info_t ExamInfo)
    {

        byte[]  pktData = new byte[PKT_LEN_EXAM_INFO];
        pktData[0] = (byte) 1;//启动标志 1 字节：上位机启动

        System.arraycopy(GetByteArray(ExamInfo.name,EXAM_NAME_LEN),0,pktData,1,EXAM_NAME_LEN);               //考核名称
        System.arraycopy(GetByteArray(ExamInfo.trainee_name,TRAINEE_NAME_LEN),0,pktData,21,TRAINEE_NAME_LEN);    //学员姓名
        System.arraycopy(GetByteArray(ExamInfo.iden_card_num,IDEN_CARD_NUM_LEN),0,pktData,37,IDEN_CARD_NUM_LEN);  //身份证号码
        System.arraycopy(GetByteArray(ExamInfo.phone_num,PHONE_NUM_LEN),0,pktData,55,PHONE_NUM_LEN);          //手机号码

        System.arraycopy(GetByteArray(ExamInfo.start_time),0,pktData,66,4);

        int RsvdLen = PKT_LEN_EXAM_INFO - 1;
        RsvdLen -= EXAM_NAME_LEN;
        RsvdLen -= TRAINEE_NAME_LEN;
        RsvdLen -= IDEN_CARD_NUM_LEN;
        RsvdLen -= PHONE_NUM_LEN;
        RsvdLen -= 4;

        byte[] fill = new byte[RsvdLen];
        System.arraycopy(fill,0,pktData,70,RsvdLen);

        return  pktData;
    }

    /*------------------------------------------------------------------------------*/
    /* parseSpDataPkt，解析采样点数据包                                                */
    /*------------------------------------------------------------------------------*/
    public static boolean parseSpDataPkt(Sp_data_t spData, byte[] pktData)
    {
        if(pktData.length!=28)
        {
            return  false;
        }

        int seq = pktData[0] | pktData[1] << 8 | pktData[2] << 16 | pktData[3] << 24;
        int pos = pktData[4] | pktData[5] << 8 | pktData[6] << 16 | pktData[7] << 24;
        short[] depth = new short[4];
        depth[0] = (short) (pktData[8] | pktData[9] << 8);
        depth[1] = (short) (pktData[10] | pktData[11] << 8);
        depth[2] = (short) (pktData[12] | pktData[13] << 8);
        depth[3] = (short) (pktData[14] | pktData[15] << 8);
        short angle = (short) (pktData[16] | pktData[17] << 8);
        int pss =  pktData[18] | pktData[19] << 8 | pktData[20] << 16 | pktData[21] << 24;
        int vol =  pktData[22] | pktData[23] << 8 | pktData[24] << 16 | pktData[25] << 24;
        short vibt = (short) ( pktData[26] | pktData[27] << 8);

        spData.setSeq(seq);
        spData.setPos(pos);
        spData.setDepth(depth);
        spData.setAngle(angle);
        spData.setPss(pss);
        spData.setVol(vol);
        spData.setVibt(vibt);

        return  true;
    }


}

