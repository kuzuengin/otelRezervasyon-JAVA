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
public class RezervasyonListesi {
    protected int Id;
    protected int musteriID;
    protected String ad;   
    protected String soyad;
    protected String telefon;
    protected String eposta;    
    protected int odaID;
    protected String odaAdi;   
    protected int tipi; 
    protected String tipadi;   
    protected String fiyat;
    protected int yetiskin;
    protected int cocuk;
    protected int manzara;
    protected String manzaraAdi;
    protected String baslangicTarihi;
    protected String bitisTarihi;
    protected int toplamTutar;
    protected int durum;
    protected String durumAciklama;
    protected int aktifPasif;
    private String Duzelt; 
    private String Sil;    
    private int SN;
    private String md5;    
 public RezervasyonListesi() {}    
 public RezervasyonListesi(int Id, int musteriID, int odaID, String baslangicTarihi, String bitisTarihi,int toplamTutar, int durum,int aktifPasif){
     super();
     this.Id=Id;
     this.musteriID=musteriID;
     this.odaID=odaID;
     this.baslangicTarihi=baslangicTarihi;
     this.bitisTarihi=bitisTarihi;
     this.toplamTutar=toplamTutar;
     this.durum=durum;
     this.aktifPasif=aktifPasif;
 } 
 public RezervasyonListesi(int Id, int musteriID, int odaID, String baslangicTarihi, String bitisTarihi,int toplamTutar, int durum,int aktifPasif,String md5){
     super();
     this.Id=Id;
     this.musteriID=musteriID;
     this.odaID=odaID;
     this.baslangicTarihi=baslangicTarihi;
     this.bitisTarihi=bitisTarihi;
     this.toplamTutar=toplamTutar;
     this.durum=durum;
     this.aktifPasif=aktifPasif;
     this.md5=md5;
 }  
 public RezervasyonListesi(int Id, int musteriID,String ad,String soyad,String telefon,String eposta,int odaID,String odaAdi, int tipi,String tipadi, String fiyat, int yetiskin, int cocuk, int manzara, String manzaraAdi, String baslangicTarihi, String bitisTarihi,int toplamTutar,int durum, String durumAciklama,int aktifPasif, String Duzelt,String Sil,int SN){
     super();
     this.Id=Id;
     this.musteriID=musteriID;
     this.ad=ad;
     this.soyad=soyad;
     this.telefon=telefon;
     this.eposta=eposta;
     this.odaID=odaID;
     this.odaAdi=odaAdi;
     this.tipi=tipi;
     this.tipadi = tipadi;
     this.fiyat =fiyat;
     this.yetiskin=yetiskin;
     this.cocuk=cocuk;
     this.manzara=manzara;
     this.manzaraAdi=manzaraAdi;
     this.baslangicTarihi=baslangicTarihi;
     this.bitisTarihi=bitisTarihi;     
    this.toplamTutar=toplamTutar;     
     this.durum=durum;
     this.durumAciklama=durumAciklama;
     this.aktifPasif=aktifPasif;
     this.Duzelt=Duzelt;
     this.Sil=Sil;
     this.SN=SN;
 }   
 public RezervasyonListesi( int odaID,String odaAdi, int tipi,String tipadi, String fiyat, int yetiskin, int cocuk, int manzara, String manzaraAdi, int durum, String durumAciklama, String Duzelt,String Sil,int SN){
     super();
     this.odaID=odaID;
     this.odaAdi=odaAdi;
     this.tipi=tipi;
     this.tipadi = tipadi;
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
    public int getmusteriID() { return musteriID; } 
    public void setmusteriID(int musteriID) { this.musteriID = musteriID;}   
    public String getad() { return ad; }
    public void setad(String ad) { this.ad = ad;}   
    public String getsoyad() { return soyad; }
    public void setsoyad(String soyad) { this.soyad = soyad;}   
    public String gettelefon() { return telefon; }
    public void settelefon(String telefon) { this.telefon = telefon;}   
    public String geteposta() { return eposta; }
    public void seteposta(String eposta) { this.eposta = eposta;}    
    public int getodaID() { return odaID; } 
    public void setodaID(int odaID) { this.odaID = odaID;}      
    public String getodaAdi() { return odaAdi; }
    public void setodaAdi(String odaAdi) { this.odaAdi = odaAdi;}   
    public int gettipi() { return tipi; }
    public void settipi(int tipi) { this.tipi = tipi;} 
    public String gettipadi() { return tipadi; }
    public void settipadi(String tipadi) { this.tipadi = tipadi;}   
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
    public String getbaslangicTarihi() { return baslangicTarihi; }
    public void setbaslangicTarihi(String baslangicTarihi) { this.baslangicTarihi = baslangicTarihi;} 
    public String getbitisTarihi() { return bitisTarihi; }
    public void setbitisTarihi(String bitisTarihi) { this.bitisTarihi = bitisTarihi;}   
    public int gettoplamTutar(){return toplamTutar;}
    public void settoplamTutar(int toplamTutar){this.toplamTutar=toplamTutar;}
    public int getdurum() { return durum; } 
    public void setdurum(int durum) { this.durum = durum;}      
    public int getaktifPasif() { return aktifPasif; } 
    public void setaktifPasif(int aktifPasif) { this.aktifPasif = aktifPasif;}       
    public String getmd5() { return md5; }
    public void setmd5(String md5) { this.md5 = md5;}     
}