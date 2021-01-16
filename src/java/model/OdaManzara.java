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
public class OdaManzara {
    
    protected int Id;
    protected String manzaraAdi;   
    private String Duzelt; 
    private String Sil; 
    private int SN;
 public OdaManzara() {}    
 public OdaManzara(int Id,String manzaraAdi){
     super();
     this.Id=Id;
     this.manzaraAdi=manzaraAdi;
 } 
 public OdaManzara(int Id,String manzaraAdi,String Duzelt,String Sil,int SN){
     super();
     this.Id=Id;
     this.manzaraAdi=manzaraAdi;
     this.Duzelt=Duzelt;
     this.Sil=Sil;
     this.SN=SN;
 }  
    public int getId() { return Id; } 
    public void setId(int Id) { this.Id = Id;}    
    public String getmanzaraAdi() { return manzaraAdi; }
    public void setmanzaraAdi(String manzaraAdi) { this.manzaraAdi = manzaraAdi;}   
}
