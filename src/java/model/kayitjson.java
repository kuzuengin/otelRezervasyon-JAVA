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
public class kayitjson {
    String kayit;
    String title;
    String mesaj;
    String kod;
public kayitjson(){}
    public kayitjson(String kayit,String title, String mesaj){
        this.kayit =kayit;
        this.title=title;
        this.mesaj=mesaj;
    }
    public kayitjson(String kayit,String title, String mesaj,String kod ){
        this.kayit =kayit;
        this.title=title;
        this.mesaj=mesaj;
        this.kod=kod;
    }    
         
}
