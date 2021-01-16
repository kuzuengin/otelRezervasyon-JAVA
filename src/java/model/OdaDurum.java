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
public class OdaDurum {
    protected int Id;
    protected String aciklama;   
    private int SN;
    private String Duzelt; 
    private String Sil; 
 public OdaDurum() {}    
 public OdaDurum(int Id,String aciklama){
     super();
     this.Id=Id;
     this.aciklama=aciklama;
 } 
 public OdaDurum(int Id,String aciklama,String Duzelt,String Sil,int SN){
     super();
     this.Id=Id;
     this.aciklama=aciklama;
     this.Duzelt=Duzelt;
     this.Sil=Sil;
     this.SN=SN;
 }  
    public int getId() { return Id; } 
    public void setId(int Id) { this.Id = Id;}    
    public String getaciklama() { return aciklama; }
    public void setaciklama(String aciklama) { this.aciklama = aciklama;}   
}
