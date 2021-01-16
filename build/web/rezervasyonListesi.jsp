<%-- 
    Document   : anasayfa
    Created on : 06.Oca.2021, 21:15:12
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Main content -->
    <section class="content">
	  <div class="box bg-pale-yellow">
		<div class="box-body p-0">
			<div class="row">
				<div class="col-lg-3">
					<div>
						<input class="form-control text-dark p-30 bg-yellow" type="text" data-ref="input-search" placeholder="Arama">
					</div>
				</div>
				<div class="col-lg-9">					
                                    <ul class="list-inline mb-0 text-center" id="ustlist">
						<li data-filter="all"><a href="#" class="btn text-bold list-link hover-yellow p-15">Tüm Odalar</a></li>
					</ul>
				</div>
			</div>
		</div>
	  </div>
	  <div class="row">
		  <div class="col-lg-3 col-12">
			  <div class="box bg-pale-dark">
				<div class="box-body p-0">
				  <div class="media-list media-list-hover media-list-divided radio-group radios-filter" id="sollist">
					<div class="media">
						<div class="radio">
							<input name="group1" value="all" type="radio" class="with-gap radio-col-yellow" id="radio_0">
							<label for="radio_0" class="mb-0 font-weight-600">Tüm Odalar</label>
						</div>
					</div>
				  </div>
				</div>
            </div>			  
		  </div>
		  <div class="col-lg-9 col-12">
		  	<div class="row ico-filter" data-ref="ico-filter" id="odalistesi">
		
			</div>
		  </div> 
	  </div>
    </section>
    <!-- /.content -->
<!-- Modal -->
<div class="modal center-modal fade col-12" id="modal-bilgi" tabindex="-1" >
    <div class="modal-dialog" style="max-width: 100% !important;" >
        
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-title">Müşteri Bilgi Formu</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="kayitForm" name="kayitForm" method="post" >
                    <!-- Basic Forms -->
                    <div class="box box-solid bg-dark">
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-4">
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Adı : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="ad" name="ad" readonly >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Soyadı : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="soyad" name="soyad" readonly ></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Telefon : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="telefon" name="telefon" readonly></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri E-posta : </label>
                                        <div class="col-sm-8">
                                            <input class=" form-control" type="email" value="" id="eposta" name="eposta" readonly></div>
                                    </div>                                      
                                </div>
                                <div class="col-4">
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Adı : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="odaAdi" name="odaAdi" readonly >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Tipi : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="tipadi" name="tipadi" readonly ></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Günlük Fiyat : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="fiyat" name="fiyat" readonly></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Yetişkin + Cocuk: </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="yetiskincocuk" name="yetiskincocuk" readonly></div>
                                    </div>                                      
                                </div>
                                <div class="col-4">
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Başlangıç Tarihi : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="baslangicTarihi" name="baslangicTarihi" readonly >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Btiş Tarihi : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="bitisTarihi" name="bitisTarihi" readonly ></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Manzaras: </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="manzaraAdi" name="manzaraAdi" readonly></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Durumu: </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="email" value="" id="durumAciklama" name="durumAciklama" readonly></div>
                                    </div>              
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Toplam Tutar : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="toplamtutar" name="toplamtutar" readonly></div>
                                    </div>                                     
                                </div>                                
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                    <input type="hidden" id="action" name="action"> 
                    <input type="hidden" id="Id" name="Id">
                    <input type="hidden" id="musteriID" name="musteriID">
                    <input type="hidden" id="odaID" name="odaID">
                    <input type="hidden" id="tipi" name="tipi">
                    <input type="hidden" id="manzara" name="manzara">
                    <input type="hidden" id="durum" name="durum">
                    <input type="hidden" id="aktifPasif" name="aktifPasif">
                    <input type="hidden" id="toplamTutar" name="toplamTutar">
                        
                    
                </form>
            </div>
            <div class="modal-footer modal-footer-uniform">
                <button id="kayitclose" name="kayitclose" type="button" class="btn btn-bold btn-pure btn-secondary" data-dismiss="modal">İptal</button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal -->    
<script>
    var bilgiData;
    $(document).ready(function(){

                $.ajax({
               "url": "/OtelRezervasyon/odaTipi",
               "type": "POST",
               "data": { "action":"select" },
                success: function(data){
                    var d =data.data;
                    var str="<li data-filter='all'><a href='#' class='btn text-bold list-link hover-yellow p-15'>Tüm Odalar</a></li>";
                    for (var i = 0; i < d.length; i++) {
                      str += "<li data-filter='."+d[i].adi.replaceAll(" ","_").toLowerCase()+"'><a href='#' class='btn text-bold list-link hover-yellow p-15'>"+d[i].adi+"</a></li>";
                    }
                    $("#ustlist").html(str);
                }               
           });
                $.ajax({
               "url": "/OtelRezervasyon/odaDurum",
               "type": "POST",
               "data": { "action":"select" },
                success: function(data){
                    var d =data.data;
                    var str="<div class='media'><div class='radio'><input name='group1' value='all' type='radio' class='with-gap radio-col-yellow' id='radio_0'><label for='radio_0' class='mb-0 font-weight-600'>Tüm Odalar</label></div></div> ";
                    for (var i = 0; i < d.length; i++) {
                      str +="<div class='media'><div class='radio'><input name='group1' value='."+d[i].aciklama.replaceAll(" ","_").toLowerCase()+"' type='radio' class='with-gap radio-col-yellow' id='radio_0"+d[i].Id+"'><label for='radio_0"+d[i].Id+"' class='mb-0 font-weight-600'>"+d[i].aciklama+"</label></div></div> "; 
                    }
                    $("#sollist").html(str);
                    
                }               
           });     
             rezervasyonListesiOlustur();            
            
           

} );
function rezervasyonListesiOlustur()
{
$.ajax({
               "url": "/OtelRezervasyon/rezervasyonListesi",
               "type": "POST",
               "data": { "action":"select" },
                success: function(data){
                    var d =data.data;
                    var str="";
                    bilgiData=d;
                    for (var i = 0; i < d.length; i++) {
                      if (d[i].durum===1)
                      {
                      str +="<div class='col-12 col-md-6 col-lg-4 mix "+d[i].tipadi.replaceAll(" ","_").toLowerCase()+" "+d[i].durumAciklama.replaceAll(" ","_").toLowerCase()+" "+d[i].ad.replaceAll(" ","_").toLowerCase()+" "+d[i].soyad.replaceAll(" ","_").toLowerCase()+"'>";
                        }
                      else
                      {
                          str +="<div class='col-12 col-md-6 col-lg-4 mix "+d[i].tipadi.replaceAll(" ","_").toLowerCase()+" "+d[i].durumAciklama.replaceAll(" ","_").toLowerCase()+"'>";
                        } 
                      var m ="XEM";
                      switch(d[i].manzara){
                          case 1 : m="FCT"; break;
                          case 2 : m="ADA"; break;
                          case 3 : m="INCNT"; break;
                          case 4 : m="NXT"; break;
                          case 5 : m="XEM";break;
                      }  
                       
                      str +="<div class='box box-body bg-hexagons-dark pull-up'>";
                      str +="<div class='media align-items-center p-0'><div class='text-center'>  <a href='#'><i class='cc "+m+" mr-5' title='"+d[i].manzaraAdi+"'></i></a></div><div> ";
                      str +="<h3 class='no-margin text-bold'>"+d[i].odaAdi+"</h3><span>"+d[i].tipadi+"</span></div>  </div>  ";
                      str +="<div class='flexbox align-items-center mt-25'><div>  <p class='no-margin font-weight-600'><span class='text-yellow'>250TL</span></p>";
                      switch(d[i].durum){
                          //DOLU
                        case 1: 
                            str +="<p class='no-margin'>"+d[i].ad+" "+d[i].soyad+"</p></div><div class='text-right'>  <p class='no-margin font-weight-600'>";
                            str +="<span class='text-green'>"+d[i].durumAciklama+"</span></p>  <p class='no-margin'>"+d[i].baslangicTarihi+" - "+d[i].bitisTarihi+"</p></div>  </div>";
                            str +="<div class='flexbox align-items-right mt-25'>";
                            str +="<div><button type='button' title='Ayrıntı Bilgiler...' class='btn btn-block btn-info' onclick='bilgi("+i+");'><i class='glyphicon glyphicon-info-sign'></i></button></div>";
                            str +="<div><button type='button' title='Dolu-Kirli Yap' class='btn btn-block btn-warning' onclick='bilgiDuzeltDurum("+i+",4);'><i class='glyphicon glyphicon-trash'></i></button></div>";
                            str +="<div><button type='button' title='Çıkış Yap' class='btn btn-block btn-danger' onclick='bilgiDuzeltDurum("+i+",3);'><i class='glyphicon glyphicon-minus'></i></button></div>";
                            str +="</div>";
                            break;
                            //REZERVE
                        case 2: 
                            str +="<p class='no-margin'>"+d[i].ad+" "+d[i].soyad+"</p></div><div class='text-right'>  <p class='no-margin font-weight-600'>";
                            str +="<span class='text-green'>"+d[i].durumAciklama+"</span></p>  <p class='no-margin'>"+d[i].baslangicTarihi+" - "+d[i].bitisTarihi+"</p></div>  </div>";
                            str +="<div class='flexbox align-items-right mt-25'>";
                            str +="<div><button type='button' title='Ayrıntı Bilgiler...' class='btn btn-block btn-info' onclick='bilgi("+i+")'><i class='glyphicon glyphicon-info-sign'></i></button></div>";
                            str +="<div><button type='button' title='Rezervasyon İptal Yap' class='btn btn-block btn-danger' onclick='rezervasyonSil("+d[i].Id+");'><i class='glyphicon glyphicon-remove'></i></button></div>";                            
                            str +="<div><button type='button' title='Dolu Yap' class='btn btn-block btn-success' onclick='bilgiDuzeltDurum("+i+",1);'><i class='glyphicon glyphicon-ok-circle'></i></button></div>";
                            str +="</div>";
                            break;    
                            //BOŞ KİRLİ
                        case 3:
                            str +="<p class='no-margin'>"+d[i].manzaraAdi+"</p></div><div class='text-right'>  <p class='no-margin font-weight-600'>";
                            str +="<span class='text-green'>"+d[i].durumAciklama+"</span></p>  <p class='no-margin'></p></div>  </div>";
                            str +="<div class='flexbox align-items-right mt-25'>";
                            str +="<div><button type='button' title='Tadilat Başlat' class='btn btn-block btn-primary' onclick='bilgiDuzeltDurum("+i+",5);'><i class='glyphicon glyphicon-cog'></i></button></div>";
                            str +="<div><button type='button' title='Boş Temiz Yap' class='btn btn-block btn-success' onclick='odaDuzeltDurum("+i+",0);'><i class='glyphicon glyphicon-trash'></i></button></div>";
                            str +="</div>";
                            break; 
                            //DOLU KİRLİ
                        case 4:
                            str +="<p class='no-margin'>"+d[i].ad+" "+d[i].soyad+"</p></div><div class='text-right'>  <p class='no-margin font-weight-600'>";
                            str +="<span class='text-green'>"+d[i].durumAciklama+"</span></p>  <p class='no-margin'>"+d[i].baslangicTarihi+" - "+d[i].bitisTarihi+"</p></div>  </div>";
                            str +="<div class='flexbox align-items-right mt-25'>";
                            str +="<div><button type='button' title='Ayrıntı Bilgiler...' class='btn btn-block btn-info' onclick='bilgi("+i+");'><i class='glyphicon glyphicon-info-sign'></i></button></div>";                            
                            str +="<div><button type='button' title='Dolu Temiz Yap' class='btn btn-block btn-success' onclick='bilgiDuzeltDurum("+i+",1);'><i class='glyphicon glyphicon-trash'></i></button></div>";
                            str +="<div><button type='button' title='Çıkış Yap' class='btn btn-block btn-danger' onclick='bilgiDuzeltDurum("+i+",3);'><i class='glyphicon glyphicon-minus'></i></button></div>";
                            str +="</div>";
                            break;     
                            //TADİLAT
                        case 5: 
                            str +="<p class='no-margin'>"+d[i].manzaraAdi+"</p></div><div class='text-right'>  <p class='no-margin font-weight-600'>";
                            str +="<span class='text-primary'>"+d[i].durumAciklama+"</span></p>  <p class='no-margin'></p></div>  </div>";
                            str +="<div class='flexbox align-items-right mt-25'>";
                            str +="<div><button type='button' title='Tadilat Bitir' class='btn btn-block btn-success' onclick='odaDuzeltDurum("+i+",0);'><i class='glyphicon glyphicon-cog'></i></button></div>";
                            str +="</div>";
                            break;    
                        //BOŞ
                        default:
                            str +="<p class='no-margin'>"+d[i].manzaraAdi+"</p></div><div class='text-right'>  <p class='no-margin font-weight-600'>";
                            str +="<span class='text-red'>"+d[i].durumAciklama+"</span></p>  <p class='no-margin'></p></div>  </div>"; 
                            str +="<div class='flexbox align-items-right mt-25'>";
                            if (d[i].Sil==="1")  {str +="<div><button type='button' title='Tadilat Başlat' class='btn btn-block btn-primary' onclick='odaDuzeltDurum("+i+",5);'><i class='glyphicon glyphicon-cog'></i></button></div>";} else {str +="<div></div>";}
                            str +="<div><button type='button' class='btn btn-block btn-success' onclick='alert();'><i class='glyphicon glyphicon-plus'></i></button></div>";
                            str +="</div>";
                            break;
                            
                      
                      }
                      
                    str +="</div></div>";
                    }
                  $("#odalistesi").html(str);
                  OdaListesiYaz();
                }               
           });     
}

function bilgi(ID,s=0)
{
    if (s===0) {$('#modal-bilgi').modal('show');}
    var obj =bilgiData[ID];
document.getElementById("Id").value = obj.Id;     
document.getElementById("musteriID").value = obj.musteriID;    
    document.getElementById("ad").value = obj.ad;
    document.getElementById("soyad").value = obj.soyad;
    document.getElementById("telefon").value = obj.telefon;
    document.getElementById("eposta").value = obj.eposta;    
document.getElementById("odaID").value = obj.odaID;    
    document.getElementById("odaAdi").value = obj.odaAdi;    
    document.getElementById("tipi").value = obj.tipi;
    document.getElementById("tipadi").value = obj.tipadi;    
    document.getElementById("fiyat").value = obj.fiyat;    
    document.getElementById("yetiskincocuk").value = obj.yetiskin+" - "+obj.cocuk;   
    document.getElementById("baslangicTarihi").value = obj.baslangicTarihi;    
    document.getElementById("bitisTarihi").value = obj.bitisTarihi; 
document.getElementById("manzara").value = obj.manzara;    
    document.getElementById("manzaraAdi").value = obj.manzaraAdi;    
document.getElementById("durum").value = obj.durum; 
    document.getElementById("durumAciklama").value = obj.durumAciklama;    
    document.getElementById("aktifPasif").value = obj.aktifPasif; 
    document.getElementById("toplamTutar").value = obj.toplamTutar; 
    document.getElementById("toplamtutar").value = obj.toplamTutar; 
    
}
function bilgiDuzeltDurum(ID,durum){
   
    console.log("bilgiDuzeltDurum");
  bilgi(ID,1);  
    var obj =bilgiData[ID];
  document.getElementById("action").value = "update";
  document.getElementById("durum").value = durum;
  if (durum===3){ document.getElementById("aktifPasif").value = 0;}
    document.getElementById("baslangicTarihi").value = obj.baslangicTarihi.substr(6, 4)+"-"+obj.baslangicTarihi.substr(3, 2)+"-"+obj.baslangicTarihi.substr(0, 2);    
    document.getElementById("bitisTarihi").value = obj.bitisTarihi.substr(6, 4)+"-"+obj.bitisTarihi.substr(3, 2)+"-"+obj.bitisTarihi.substr(0, 2);        
    kayitFunction();
    setTimeout(function(){ rezervasyonListesiOlustur(); }, 2000);
  

}

function odaDuzeltDurum(ID,durum){
    console.log("odaDuzeltDurum");
  bilgi(ID,1);  
    var obj =bilgiData[ID];
   document.getElementById("action").value = "odaupdate";
   document.getElementById("durum").value = durum;
   document.getElementById("Id").value = obj.odaID;
   kayitFunction();
   setTimeout(function(){ rezervasyonListesiOlustur(); }, 2000);  
}
function rezervasyonSil(id=0)
{
        swal({   
            title: "Kayıt Silinsin mi?",   
            text: "Seçili Rezervasyon İptal ediilsin mi?",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Evet",   
            cancelButtonText: "Hayır",   
            closeOnConfirm: false,   
            closeOnCancel: false 
        }, function(isConfirm){   
        if (isConfirm) {     
               $.ajax({
               "url": "/OtelRezervasyon/rezervasyonListesi",
               "type": "POST",
               "data": { "action":"delete","id":id },
                success: function(data){
                        if (data.kayit==='Evet'){
                            msg_ok(data);
                            rezervasyonListesiOlustur();
                            swal.close();
                        }
                        else {
                            msg_no(data);
                            swal.close()
                        }
                    }
                });               
        } else {swal.close();}    
        });
}
</script>
	<!-- mixitup -->
	<script src="assets/vendor_components/mixitup-v3/dist/mixitup.js"></script>
	        
        <!-- Crypto_Admin for demo purposes -->
        <script src="js/pages/ico-filter.js"></script>    