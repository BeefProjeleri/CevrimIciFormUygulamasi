package com.tasarm.proje.prjtsrm.models;

/**
 * Created by User on 17.02.2018.
 */

public class PostModel {

    String baslik;
    String tarih;
    String gonderen;
    String id;
    String like_sayi;
    String dislike;

    String icerik;
    String com_sayi;
    String durum;
    public PostModel(String baslik, String tarih, String gonderen, String id, String icerik,String com_sayi,String like_sayi,String dislike,String durum) {
        this.baslik = baslik;
        this.tarih = tarih;
        this.gonderen = gonderen;
        this.id = id;
        this.icerik = icerik;
        this.com_sayi = com_sayi;
        this.like_sayi=like_sayi;
        this.dislike=dislike;
        this.durum=durum;

    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getLike_sayi() {
        return like_sayi;
    }

    public String getDislike() {
        return dislike;
    }
    public String getCom_sayi() {
        return com_sayi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcerik() {return icerik;}

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }









}
