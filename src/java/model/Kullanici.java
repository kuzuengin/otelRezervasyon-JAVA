/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author engin
 */
public class Kullanici {
    protected int Id;
    protected String adSoyad;
    protected String kullanici;
    protected String sifre;
    protected int yetki;
    protected String yetkiAciklama;
    protected int durum;
    protected String durumAciklama;
    private String Duzelt;
    private String Sil;
    private int SN;
    
    
    public Kullanici() {}
    public Kullanici(String adSoyad, int yetki ) {
        super();
        this.adSoyad = adSoyad;
        this.yetki = yetki;
    }  
    public Kullanici(int Id,String adSoyad, String kullanici, String sifre, int yetki,int durum ) {
        super();
        this.Id = Id;
        this.adSoyad = adSoyad;
        this.kullanici = kullanici;
        this.sifre = sifre;
        this.yetki = yetki;
        this.durum = durum;
    }
    public Kullanici(int Id,String adSoyad, String kullanici, int yetki,int durum ) {
        super();
        this.Id = Id;
        this.adSoyad = adSoyad;
        this.kullanici = kullanici;
        this.yetki = yetki;
        this.durum = durum;
    }    
    public Kullanici(int Id,String adSoyad, String kullanici,  int yetki, String yetkiAciklama, int durum, String durumAciklama, String Duzelt,String Sil,int SN ) {
        super();
        this.Id = Id;        
        this.adSoyad = adSoyad;
        this.kullanici = kullanici;
        this.yetki = yetki;
        this.yetkiAciklama = yetkiAciklama;
        this.durum = durum;
        this.durumAciklama = durumAciklama;
        this.Duzelt = Duzelt;
        this.Sil = Sil;
        this.SN=SN;
        
    }  
    
    public int getId() { return Id; }
    public void setId(int Id) { this.Id = Id;}    
    public String getadSoyad() { return adSoyad; }
    public void setadSoyad(String adSoyad) { this.adSoyad = adSoyad;}  
    public String getkullanici() { return kullanici; }
    public void setkullanici(String kullanici) { this.kullanici = kullanici;}  
    public String getsifre() { return sifre; }
    public void setsifre(String sifre) { this.sifre = sifre;}  
    public int getyetki() { return yetki; }
    public void setyetki(int yetki) { this.yetki = yetki;}    
    public int getdurum() { return durum; }
    public void setdurum(int durum) { this.durum = durum;}        
}
