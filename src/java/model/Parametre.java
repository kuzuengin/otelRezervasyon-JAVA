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
public class Parametre {
    protected int Id;
    protected int kod1;
    protected int kod2;
    protected String aciklama;  
     protected String Duzelt;
     
    public Parametre() {} 
    public Parametre(int Id,int kod1,int kod2,String aciklama) {
        this.Id=Id;
        this.kod1=kod1;
        this.kod2=kod2;
        this.aciklama=aciklama;
    }
    public Parametre(int Id,int kod1,int kod2,String aciklama,String Duzelt) {
        this.Id=Id;
        this.kod1=kod1;
        this.kod2=kod2;
        this.aciklama=aciklama;
        this.Duzelt=Duzelt;
    }    
    public int getId() { return Id; }
    public void setId(int Id) { this.Id = Id;}  
    public int getkod1() { return Id; }
    public void setkod1(int kod1) { this.kod1 = kod1;}  
    public int getkod2() { return kod2; }
    public void setkod2(int kod2) { this.kod2 = kod2;}      
    public String getaciklama() { return aciklama; }
    public void setaciklama(String aciklama) { this.aciklama = aciklama;}    
}

