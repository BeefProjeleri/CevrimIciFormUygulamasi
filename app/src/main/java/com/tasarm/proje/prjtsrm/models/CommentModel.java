package com.tasarm.proje.prjtsrm.models;

/**
 * Created by User on 03.03.2018.
 */

public class CommentModel {

    String gonderen;
    String icerik;
    String idd;
    String tarih;
    public CommentModel(String gonderen, String icerik, String idd, String tarih) {
        this.gonderen = gonderen;
        this.icerik = icerik;
        this.idd = idd;
        this.tarih = tarih;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
