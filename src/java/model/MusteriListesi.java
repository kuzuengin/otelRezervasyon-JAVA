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
public class MusteriListesi {
    protected int Id;
    protected String ad;   
    protected String soyad;
    protected String telefon;
    protected String eposta;
    private String Duzelt; 
    private String Sil;    
    private int SN;
 public MusteriListesi() {}    
 public MusteriListesi(int Id, String ad,String soyad,String telefon,String eposta){
     super();
     this.Id=Id;
     this.ad=ad;
     this.soyad=soyad;
     this.telefon=telefon;
     this.eposta=eposta;
 } 
 public MusteriListesi(int Id, String ad,String soyad,String telefon,String eposta, String Duzelt,String Sil,int SN){
     super();
     this.Id=Id;
     this.ad=ad;
     this.soyad=soyad;
     this.telefon=telefon;
     this.eposta=eposta;
     this.Duzelt=Duzelt;
     this.Sil=Sil;
     this.SN=SN;
 }  
    public int getId() { return Id; } 
    public void setId(int Id) { this.Id = Id;}    
    public String getad() { return ad; }
    public void setad(String ad) { this.ad = ad;}   
    public String getsoyad() { return soyad; }
    public void setsoyad(String soyad) { this.soyad = soyad;}   
    public String gettelefon() { return telefon; }
    public void settelefon(String telefon) { this.telefon = telefon;}   
    public String geteposta() { return eposta; }
    public void seteposta(String eposta) { this.eposta = eposta;}       
}