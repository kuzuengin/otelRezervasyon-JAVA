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
public class OdaListesi {
    protected int Id;
    protected String odaAdi;   
    protected int tipi; 
    protected String adi;   
    protected String fiyat;
    protected int yetiskin;
    protected int cocuk;
    protected int manzara;
    protected String manzaraAdi;
    protected int durum;
    protected String durumAciklama;
    private String Duzelt; 
    private String Sil;    
    private int SN;
 public OdaListesi() {}    
 public OdaListesi(int Id, String odaAdi, int tipi, int manzara, int durum){
     super();
     this.Id=Id;
     this.odaAdi=odaAdi;
     this.tipi=tipi;
     this.manzara=manzara;
     this.durum=durum;
 } 
 public OdaListesi(int Id, String odaAdi, int tipi,String adi, String fiyat, int yetiskin, int cocuk, int manzara, String manzaraAdi, int durum, String durumAciklama, String Duzelt,String Sil,int SN){
     super();
     this.Id=Id;
     this.odaAdi=odaAdi;
     this.tipi=tipi;
     this.adi = adi;
     this.fiyat =fiyat;
     this.yetiskin=yetiskin;
     this.cocuk=cocuk;
     this.manzara=manzara;
     this.manzaraAdi=manzaraAdi;
     this.durum=durum;
     this.durumAciklama=durumAciklama;
     this.Duzelt=Duzelt;
     this.Sil=Sil;
     this.SN=SN;
 }  
    public int getId() { return Id; } 
    public void setId(int Id) { this.Id = Id;}    
    public String getodaAdi() { return odaAdi; }
    public void setodaAdi(String odaAdi) { this.odaAdi = odaAdi;}   
    public int gettipi() { return tipi; }
    public void settipi(int tipi) { this.tipi = tipi;} 
    public String getadi() { return adi; }
    public void setadi(String adi) { this.adi = adi;}   
    public String getfiyat() { return fiyat; }
    public void setfiyat(String fiyat) { this.fiyat = fiyat;}       
    public int getyetiskin() { return yetiskin; } 
    public void setyetiskin(int yetiskin) { this.yetiskin = yetiskin;}   
    public int getcocuk() { return cocuk; } 
    public void setcocuk(int cocuk) { this.cocuk = cocuk;}      
    public int getmanzara() { return manzara; } 
    public void setmanzara(int manzara) { this.manzara = manzara;}  
    public String getmanzaraAdi() { return manzaraAdi; }
    public void setmanzaraAdi(String manzaraAdi) { this.manzaraAdi = manzaraAdi;}     
    public int getdurum() { return durum; } 
    public void setdurum(int durum) { this.durum = durum;}       
}