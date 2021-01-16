<%-- 
    Document   : kullaniciListesi
    Created on : 30.Ara.2020, 23:43:37
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
         
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Kullanıcı Listesi</h3>
			  
              <a href="" class="btn btn-app bg-olive" style="float:right;" data-toggle="modal" data-target="#modal-kayit" onclick="yeni()"><i class="fa fa-plus"></i> Yeni Kayıt</a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo1" class="table table-bordered table-striped display">
					<thead>
						<tr>
							<th style="width:50px;">S.No</th>
							<th>Ad Soyad</th>
							<th>Kullanıcı Adı</th>
							<th>Yetki</th>
							<th style="width:50px;">Durumu</th>
                                                        <th style="width:50px;">Düzelt</th>
                                                        <th style="width:50px;">Sil</th>
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
    <!-- /.content -->
<!-- Modal -->
<div class="modal center-modal fade col-12" id="modal-kayit" tabindex="-1" >
    <div class="modal-dialog" style="max-width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-title">Kullanıcı Formu</h5>
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
                                <div class="col-12">
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Ad Soyad : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="adSoyad" name="adSoyad" data-errormessage="Ad Soyad Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="50" autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Kullanıcı Adı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="kullanici" name="kullanici" data-errormessage="Kullanıcı Adını Giriniz..." onkeypress="no_enter(event)"  maxlength="50" >
                                        </div>
                                    </div>
                                    <div class="form-group row" id="div-sifre">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Şifre : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="sifre" name="sifre" data-errormessage="Kullanıcı Şifresini Giriniz..." onkeypress="no_enter(event)" maxlength="50" >
                                        </div>
                                    </div>                                    
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Yetki Grubu: </label>
                                        <div class="col-sm-8">
                                            <select name="yetki" id="yetki" class="validate[required] form-control" data-errormessage="Yetki Grubu Bilgisini Seçiniz...">
                                                </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Durumu : </label>
                                        <div class="col-sm-8">
                                            <select name="durum" id="durum" class="validate[required] form-control" data-errormessage="Durum Bilgisini Seçiniz...">
                                            </select>
                                        </div>
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
<script>
    $(document).ready(function(){
         $('#Tablo1').DataTable( {
            "ajax": 
            {
               "url": "/OtelRezervasyon/kullaniciListesi",
               "type": "POST",
               "data": { "action":"select" }
           },
            "columns": [
                { "data": "SN" },
                { "data": "adSoyad" },
                { "data": "kullanici" },
                { "data": "yetkiAciklama" },
                { "data": "durumAciklama" },
                { "data": "Duzelt" },
                { "data": "Sil" }
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ["-1"]},
                {
                    "targets": [0,3,4,5],
                    "className": "text-center"
                }
            ]
        } );
        

} );
function yeni()
{
 $('#div-sifre').show();
 $('#modal-title').text("Yeni Kullanıcı Ekleme Formu");  
 document.getElementById("action").value = "insert";
 document.getElementById("Id").value = "0";
document.getElementById("adSoyad").value = "";
document.getElementById("kullanici").value = "";
document.getElementById("sifre").value = "";
 $("#yetki").html('');
 $("#durum").html('');
          $.ajax({
               "url": "/OtelRezervasyon/yetki",
               "type": "POST",
               "data": { "action":"combo" },
                success: function(data){
                    $("#yetki").html(data);
                    console.log(data);
                }               
           });
         $.ajax({
               "url": "/OtelRezervasyon/parametre",
               "type": "POST",
               "data": { "action":"combo","kod":1 },
                success: function(data){
                    $("#durum").html(data);
                    console.log(data);
                }               
           });
 
}
function duzelt(id=0)
{
 $('#div-sifre').hide();
 $('#modal-title').text("Kullanıcı Düzeltme Formu");   
document.getElementById("action").value = "update";
$("#yetki").html('');
$("#durum").html('');  
         $.ajax({
               "url": "/OtelRezervasyon/yetki",
               "type": "POST",
               "data": { "action":"combo" },
                success: function(data){
                    $("#yetki").html(data);
                    console.log(data);
                }               
           });    
         $.ajax({
               "url": "/OtelRezervasyon/parametre",
               "type": "POST",
               "data": { "action":"combo","kod":1 },
                success: function(data){
                    $("#durum").html(data);
                    console.log(data);
                }               
           });        
         $.ajax({
               "url": "/OtelRezervasyon/kullaniciListesi",
               "type": "POST",
               "data": { "action":"edit","id":id },
                success: function(data){
                    console.log(data);
                    document.getElementById("Id").value = data[0].Id;
                    document.getElementById("adSoyad").value = data[0].adSoyad;
                    document.getElementById("kullanici").value = data[0].kullanici;
                    document.getElementById("yetki").value = data[0].yetki;
                    document.getElementById("durum").value = data[0].durum;

                }               
           });            
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
               "url": "/OtelRezervasyon/kullaniciListesi",
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