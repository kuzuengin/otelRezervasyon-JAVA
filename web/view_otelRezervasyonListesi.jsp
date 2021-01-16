<%-- 
    Document   : v_otelRezervasyonYap
    Created on : 09.Oca.2021, 20:55:32
    Author     : engin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
  
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
         
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Otel Rezervasyon Listesi</h3>
                        <div class="form-group row">
                            <label for="example-text-input" class="col-sm-1 col-form-label">ilk Tarih:</label>
                            <div class="input-group date col-sm-3">
                                <div class="input-group-addon" style="height: 2.573rem; background-color: #ccc;">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" id="btarih" name="btarih" value="">
                            </div>
                            <label for="example-text-input" class="col-sm-2 col-form-label" style="text-align: right;">Son Tarih:</label>
                            <div class="input-group date col-sm-3">
                                <div class="input-group-addon" style="height: 2.573rem; background-color: #ccc;">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" id="starih" name="starih" value="">
                            </div>
                            <div class="btn-group"><button  class="btn btn-warning " value="listele" id="listele" name="listele" onclick="randevulist();"><span class="glyphicon glyphicon-search mr5"></span> LİSTELE  </button></div>
                            <label for="inputStandard" >Listele işlemi iki tarih arasına göre yapılmaktadır.</label>
                        </div>

            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo1" class="table table-bordered table-striped display">
					<thead>
						<tr>
                                                        <th>Oda Adı</th>
							<th>Oda Tipi</th>
                                                        <th>Manzara</th>
                                                        <th>Yetişkin</th>
                                                        <th>Çocuk</th>
                                                        <th>Fiyat</th>
                                                        <th>Durumu</th>
                                                        <th>Ad</th>
                                                        <th>Soyad</th>
                                                        <th>Başlangıç Tarihi</th>
                                                        <th>Bitiş Tarihi</th>
                                                        <th>Rezervasyon</th>
                                                        <th>Düzelt</th>
                                                        <th>Sil</th>
						</tr>
					</thead>
				  </table>
				</div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->  
			
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
</div>
    
    <!-- /.content -->
<!-- Modal -->
<div class="modal center-modal fade col-12" id="modal-kayit" tabindex="-1" >
    <div class="modal-dialog" style="max-width: 100% !important;" >
        
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-title">Rezervasyon Formu</h5>
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
                                            <input class="validate[required] form-control" type="text" value="" id="ad" name="ad" data-errormessage="Müşteri Adı Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="50" autofocus ></input></div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Soyadı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="soyad" name="soyad" data-errormessage="Müşteri Soyadı Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="50"  ></input></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Telefon : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required,custom[phone]]] form-control" type="text" value="" id="telefon" name="telefon" data-errormessage="Müşteri Telefon Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="20"  ></input></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri E-posta : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required,custom[email]] form-control" type="email" value="" id="eposta" name="eposta" data-errormessage="Müşteri E-posta Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="100"  ></input></div>
                                    </div>      
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Başlangıç Tarihi : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="baslangictarihi" name="baslangictarihi" readonly >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Btiş Tarihi : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="bitistarihi" name="bitistarihi" readonly ></div>
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
                                            <input class="form-control" type="text" value="" id="odaTipi" name="odaTipi" readonly ></div>
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

                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Gün Sayısı: </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="gunsayisi" name="gunsayisi" readonly></div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Toplam Tutar : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="toplamfiyat" name="toplamfiyat" readonly></div>
                                    </div>                                     
                                </div>
                                <div class="col-4"  id="musteriListesi">
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Müşteri Listesi</h3>
			  
              <a href="" class="btn btn-app bg-olive" style="float:right;" data-toggle="modal" data-target="#modal-kayit-musteri" onclick="yenimusteri()"><i class="fa fa-plus"></i> Yeni Kayıt</a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo2" class="table table-bordered table-striped display">
					<thead>
						<tr>
							<th>Müşteri Adı</th>
                                                        <th>Müşteri Soyadı</th>
                                                        <th>Telefon</th>
                                                        <th>E-posta</th>
                                                        <th style="width:50px;">Seç</th>
						</tr>
					</thead>
				  </table>
				</div>
            </div>
            <!-- /.box-body -->
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
                    <input type="hidden" id="musteriID" name="musteriID">
                    <input type="hidden" id="odaID" name="odaID">
                    <input type="hidden" id="Id" name="Id">
                    <input type="hidden" id="baslangicTarihi" name="baslangicTarihi"></input>
                    <input type="hidden" id="bitisTarihi" name="bitisTarihi"></input>
                    <input type="hidden" id="toplamTutar" name="toplamTutar"></input>
                    <input type="hidden" id="durum" name="durum"></input>
                    </form>
            </div>
            <div class="modal-footer modal-footer-uniform">
                <button id="kayitclose" name="kayitclose" type="button" class="btn btn-bold btn-pure btn-secondary" data-dismiss="modal">İptal</button>
                <button class="btn btn-bold btn-pure btn-success float-right" onclick="kayitFunction();">Kaydet</button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal -->  
<!-- Modal -->
<div class="modal center-modal fade col-12" id="modal-kayit-musteri" tabindex="-1" >
    <div class="modal-dialog" style="max-width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-title">Kayıt Formu</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="kayitFormMusteri" name="kayitFormMusteri" method="post" >
                    <!-- Basic Forms -->
                    <div class="box box-solid bg-dark">
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Adı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="ad" name="ad" data-errormessage="Müşteri Adı Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="50" autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Soyadı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="soyad" name="soyad" data-errormessage="Müşteri Soyadı Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="50" autofocus ></input>                                        </div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri Telefon : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required,custom[phone]]] form-control" type="text" value="" id="telefon" name="telefon" data-errormessage="Müşteri Telefon Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="20" autofocus ></input>                                        </div>
                                    </div>  
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Müşteri E-posta : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required,custom[email]] form-control" type="email" value="" id="eposta" name="eposta" data-errormessage="Müşteri E-posta Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="100" autofocus ></input>                                        </div>
                                    </div>                                      
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                    <input type="hidden" id="action" name="action" value="insert"> 
                    <input type="hidden" id="Id" name="Id"> 
                </form>
            </div>
            <div class="modal-footer modal-footer-uniform">
                <button id="kayitclose1" name="kayitclose1" type="button" class="btn btn-bold btn-pure btn-secondary" data-dismiss="modal">İptal</button>
                
                <button class="btn btn-bold btn-pure btn-success float-right" onclick="kayitFunctionMusteri();">Kaydet</button>
            </div>
        </div>
    </div>
</div>
<!-- /.modal -->  
<script>

    
    $(document).ready(function(){

        document.getElementById("btarih").value = formatDate(new Date());
        document.getElementById("starih").value = formatDate(new Date());
        
         $('#Tablo1').DataTable( {
            "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.22/i18n/Turkish.json"
        },
            "ajax": 
            {
               "url": "/OtelRezervasyon/rezervasyonIslemleri",
               "type": "POST",
               "data": function( d ) {
                   var b=document.getElementById("btarih").value;
                   var s=document.getElementById("starih").value;
                   console.log("btarih:"+document.getElementById("btarih").value);
                   console.log("starih:"+document.getElementById("starih").value);
      d.action= "select";
      d.btarih= b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2);
      d.starih= s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2);
    }
                       
                       // { "action":"select","btarih":formatDateMysql(new Date()),"starih":formatDateMysql(new Date()) }
           },
            "columns": [
                { "data": "odaAdi" },
                { "data": "tipadi" },
                { "data": "manzaraAdi" },
                { "data": "yetiskin" },
                { "data": "cocuk" },
                { "data": "fiyat",render: function(data, type) { return data+".00 TL.";} },
                { "data": "durumAciklama" },
                { "data": "ad" },
                { "data": "soyad" },
                { "data": "baslangicTarihi" },
                { "data": "bitisTarihi" },                
                { "data": "odaID",render: function(data, type) { return "<img src='fon/onayla.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"yeni("+data+")\" />";}},
                { "data": "Duzelt" },
                { "data": "Sil" }
                
                
                
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ['-1,-2,-3']},
                {
                    "targets": [0,1,2,3,4,5,6,9,10,11,12],
                    "className": "text-center",
                }
            ]
            
        } );
         $('#Tablo2').DataTable( {
            "ajax": 
            {
               "url": "/OtelRezervasyon/musteriListesi",
               "type": "POST",
               "data": { "action":"select" }
           },
            "columns": [
                { "data": "ad" },
                { "data": "soyad" },
                { "data": "telefon" },
                { "data": "eposta" },
                { "data": "Id",render: function(data, type) { return "<img src='fon/onayla.png' onclick=\"musterisec("+data+")\" />";}}, 
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ["-1,-2"]},
                {
                    "targets": [4],
                    "className": "text-center"
                }
            ]
        } );        
} );

function yenimusteri()
{
    $("#modal-kayit-musteri").show();
    
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [day, month, year].join('/');
}
function formatDateMysql(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}
function musterisec(id=0)
{
document.getElementById("ad").value = "";
document.getElementById("soyad").value = "";
document.getElementById("telefon").value = "";
document.getElementById("eposta").value = "";
         $.ajax({
               "url": "/OtelRezervasyon/musteriListesi",
               "type": "POST",
               "data": { "action":"edit","id":id },
                success: function(data){
                    console.log(data);
                    document.getElementById("musteriID").value = data[0].Id;
                    document.getElementById("ad").value = data[0].ad;
                    document.getElementById("soyad").value = data[0].soyad;
                    document.getElementById("telefon").value = data[0].telefon;
                    document.getElementById("eposta").value = data[0].eposta;
                }               
           });               
}

function yeni(id=0)
{
    $("#musteriListesi").show();
    $("#ad").focus();
var b=document.getElementById("btarih").value;
var s=document.getElementById("starih").value;    
document.getElementById("baslangicTarihi").value=b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2);
document.getElementById("bitisTarihi").value=s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2) ;
document.getElementById("baslangictarihi").value=b ;
document.getElementById("bitistarihi").value=s ;
document.getElementById("action").value = "insert";
document.getElementById("odaID").value = id;

         $.ajax({
               "url": "/OtelRezervasyon/otel",
               "type": "POST",
               "data": { "action":"edit","id":id,"btarih":b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2),"starih":s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2) },
                success: function(data){
                    document.getElementById("odaAdi").value = data[0].odaAdi;
                    document.getElementById("odaTipi").value = data[0].tipi;
                    document.getElementById("fiyat").value = data[0].fiyat+" TL";
                    document.getElementById("yetiskincocuk").value = data[0].yetiskin+" + "+data[0].cocuk;
                    document.getElementById("gunsayisi").value = data[0].Sil;
                    document.getElementById("toplamfiyat").value = (data[0].Sil * data[0].fiyat)+" TL";
                    document.getElementById("toplamTutar").value = (data[0].Sil * data[0].fiyat);
                    
document.getElementById("ad").focus();
                }               
           });            
}

function duzelt(id=0)
{
    $("#musteriListesi").hide();
    $("#ad").focus();
document.getElementById("Id").value=id ;    
var b=document.getElementById("btarih").value;
var s=document.getElementById("starih").value;    
document.getElementById("baslangicTarihi").value=b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2);
document.getElementById("bitisTarihi").value=s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2) ;
document.getElementById("baslangictarihi").value=b ;
document.getElementById("bitistarihi").value=s ;
document.getElementById("action").value = "update";


         $.ajax({
               "url": "/OtelRezervasyon/rezervasyonIslemleri",
               "type": "POST",
               "data": { "action":"edit","id":id },
                success: function(data){
                    document.getElementById("odaID").value = data[0].odaID;
                    document.getElementById("musteriID").value = data[0].musteriID;
                    document.getElementById("ad").value = data[0].ad;
                    document.getElementById("soyad").value = data[0].soyad;
                    document.getElementById("telefon").value = data[0].telefon;
                    document.getElementById("eposta").value = data[0].eposta;                    
                    document.getElementById("odaAdi").value = data[0].odaAdi;
                    document.getElementById("odaTipi").value = data[0].tipi;
                    document.getElementById("fiyat").value = data[0].fiyat+" TL";
                    document.getElementById("yetiskincocuk").value = data[0].yetiskin+" + "+data[0].cocuk;
                    document.getElementById("gunsayisi").value = data[0].SN;
                    document.getElementById("durum").value = data[0].durum;
                    document.getElementById("toplamfiyat").value = (gunhesapla()*data[0].fiyat)+" TL";
                    document.getElementById("toplamTutar").value = (gunhesapla()*data[0].fiyat);
                    
document.getElementById("ad").focus();
                }               
           });            
}

 function randevulist() {
var b=document.getElementById("btarih").value,
s=document.getElementById("starih").value;     
 if (gunhesapla()<0)
{
document.getElementById("btarih").value=s;
document.getElementById("starih").value=b;
}

            $("#Tablo1").DataTable().ajax.reload();
        }
function gunhesapla() {
var b=document.getElementById("btarih").value,
s=document.getElementById("starih").value,
baslangic  = new Date(b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2)),
     bitis = new Date(s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2)),
     fark  = new Date(bitis - baslangic),
     gun  = Math.floor(fark/1000/60/60/24);    
     return gun+1; 
}
function kayitSil(id=0)
{
        swal({   
            title: "Kayıt Silinsin mi?",   
            text: "Seçili Kayıtın Silinmesini İstediğinizden emin misiniz?",   
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
               "url": "/OtelRezervasyon/rezervasyonIslemleri",
               "type": "POST",
               "data": { "action":"delete","id":id },
                success: function(data){
                        if (data.kayit==='Evet'){
                            msg_ok(data);
                            $("#Tablo1").DataTable().ajax.reload();
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

