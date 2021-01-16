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
public class OdaTipi {
    protected int Id;
    protected String adi;   
    protected String fiyat; 
    protected int yetiskin; 
    protected int cocuk; 
    private String Duzelt; 
    private String Sil; 
    private int SN; 
 public OdaTipi() {}    
 public OdaTipi(int Id, String adi, String fiyat, int yetiskin, int cocuk){
     super();
     this.Id=Id;
     this.adi=adi;
     this.fiyat=fiyat;
     this.yetiskin=yetiskin;
     this.cocuk=cocuk;
 } 
 public OdaTipi(int Id, String adi, String fiyat, int yetiskin, int cocuk, String Duzelt,String Sil,int SN){
     super();
     this.Id=Id;
     this.adi=adi;
     this.fiyat=fiyat;
     this.yetiskin=yetiskin;
     this.cocuk=cocuk;
     this.Duzelt=Duzelt;
     this.Sil=Sil;
     this.SN=SN;
 }  
    public int getId() { return Id; } 
    public void setId(int Id) { this.Id = Id;}    
    public String getadi() { return adi; }
    public void setadi(String adi) { this.adi = adi;}   
    public String getfiyat() { return fiyat; }
    public void setfiyat(String fiyat) { this.fiyat = fiyat;}       
    public int getyetiskin() { return yetiskin; } 
    public void setyetiskin(int yetiskin) { this.yetiskin = yetiskin;}   
    public int getcocuk() { return cocuk; } 
    public void setcocuk(int cocuk) { this.cocuk = cocuk;}       
}

