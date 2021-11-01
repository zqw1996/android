package com.example.tcpconn.workbench;

public class Sp_data_t {

    int       seq;           //序号
    int       pos;           //按压位置
    short[]   depth;      //按压深度
    short     angle;         //气道开启角度
    int       pss;           //吹气气压
    int       vol;           //吹气气量
    short     vibt;          //拍肩振动

    public Sp_data_t() {
        super();
    }

    public Sp_data_t(int seq, int pos, short[] depth, short angle, int pss, int vol, short vibt) {
        this.seq = seq;
        this.pos = pos;
        this.depth = depth;
        this.angle = angle;
        this.pss = pss;
        this.vol = vol;
        this.vibt = vibt;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public short[] getDepth() {
        return depth;
    }

    public void setDepth(short[] depth) {
        this.depth = depth;
    }

    public short getAngle() {
        return angle;
    }

    public void setAngle(short angle) {
        this.angle = angle;
    }

    public int getPss() {
        return pss;
    }

    public void setPss(int pss) {
        this.pss = pss;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public short getVibt() {
        return vibt;
    }

    public void setVibt(short vibt) {
        this.vibt = vibt;
    }
}
