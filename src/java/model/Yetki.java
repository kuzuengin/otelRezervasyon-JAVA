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
public class Yetki {
    protected int Id;
    protected String yetki;   
    protected String Duzelt;
    
 public Yetki() {}    
 public Yetki(int Id,String yetki){
     super();
     this.Id=Id;
     this.yetki=yetki;
 }   
 public Yetki(int Id,String yetki,String Duzelt){
     super();
     this.Id=Id;
     this.yetki=yetki;
     this.Duzelt = Duzelt;
 } 
    public int getId() { return Id; }
    public void setId(int Id) { this.Id = Id;}    
    public String getyetki() { return yetki; }
    public void setyetki(String adSoyad) { this.yetki = yetki;}   
}
