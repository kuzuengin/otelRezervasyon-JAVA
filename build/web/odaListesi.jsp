<%-- 
    Document   : odaListesi
    Created on : 05.Oca.2021, 11:42:44
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
         
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Oda Listesi</h3>
			  
              <a href="" class="btn btn-app bg-olive" style="float:right;" data-toggle="modal" data-target="#modal-kayit" onclick="yeni()"><i class="fa fa-plus"></i> Yeni Kayıt</a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo1" class="table table-bordered table-striped display">
					<thead>
						<tr>
							<th style="width:50px;">S.No</th>
							<th>Oda Adı</th>
                                                        <th>Oda Tipi</th>
                                                        <th>Oda Fiyatı</th>
                                                        <th>Yetişkin Sayısı</th>
                                                        <th>Çocuk Sayısı</th>
                                                        <th>Manzara</th>
                                                        <th>Durumu</th>
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
                <h5 class="modal-title" id="modal-title">Kayıt Formu</h5>
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
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Adı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="odaAdi" name="odaAdi" data-errormessage="Oda Adı Bilgisini Giriniz..." onkeypress="no_enter(event)" maxlength="50" autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Tipi : </label>
                                        <div class="col-sm-8">
                                            <select name="tipi" id="tipi"  class="validate[required] form-control" data-errormessage="Oda Tipi Bilgisini Seçiniz..." onchange="odatipigetir();">
                                            </select>
                                        </div>
                                    </div>                                    
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Fiyatı : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="fiyat" name="fiyat" readonly >
                                        </div>
                                    </div> 
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Yetişkin Sayısı : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="yetiskin" name="yetiskin" readonly >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Çocuk Sayısı : </label>
                                        <div class="col-sm-8">
                                            <input class="form-control" type="text" value="" id="cocuk" name="cocuk" readonly >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Manzara : </label>
                                        <div class="col-sm-8">
                                            <select name="manzara" id="manzara" class="validate[required] form-control" data-errormessage="Manzara Bilgisini Seçiniz...">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Durum : </label>
                                        <div class="col-sm-8">
                                            <select name="durum" id="durum"  class="validate[required] form-control" data-errormessage="Oda Durum Bilgisini Seçiniz...">
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
               "url": "/OtelRezervasyon/odaListesi",
               "type": "POST",
               "data": { "action":"select" }
           },
            "columns": [
                { "data": "SN" },
                { "data": "odaAdi" },
                { "data": "adi" },
                { "data": "fiyat" },
                { "data": "yetiskin" },
                { "data": "cocuk" },
                { "data": "manzaraAdi" },
                { "data": "durumAciklama" },
                { "data": "Duzelt" },
                { "data": "Sil" }
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ["-1"]},
                {
                    "targets": [0,3,4,5,8,9],
                    "className": "text-center"
                }
            ]
        } );
        

} );
function yeni()
{
  $('#modal-title').text("Yeni Oda Ekleme Formu");  
 document.getElementById("action").value = "insert";
 document.getElementById("Id").value = "0";
document.getElementById("odaAdi").value = "";
document.getElementById("fiyat").value = "0";
document.getElementById("yetiskin").value = "0";
document.getElementById("cocuk").value = "0";
 $("#tipi").html('');
 $("#manzara").html('');
 $("#durum").html('');
          $.ajax({
               "url": "/OtelRezervasyon/odaTipi",
               "type": "POST",
               "data": { "action":"combo" },
                success: function(data){
                    $("#tipi").html(data);
                }               
           });
          $.ajax({
               "url": "/OtelRezervasyon/odaManzara",
               "type": "POST",
               "data": { "action":"combo" },
                success: function(data){
                    $("#manzara").html(data);
                }               
           });       
          $.ajax({
               "url": "/OtelRezervasyon/odaDurum",
               "type": "POST",
               "data": { "action":"combo" },
                success: function(data){
                    $("#durum").html(data);
                }               
           });
}
function duzelt(id=0)
{
 $('#modal-title').text("Oda Manzara Düzeltme Formu");   
document.getElementById("action").value = "update";
document.getElementById("odaAdi").value = "";
document.getElementById("fiyat").value = "0";
document.getElementById("yetiskin").value = "0";
document.getElementById("cocuk").value = "0";
 $("#tipi").html('');
 $("#manzara").html('');
 $("#durum").html('');
         $.ajax({
               "url": "/OtelRezervasyon/odaListesi",
               "type": "POST",
               "data": { "action":"edit","id":id },
                success: function(data){
                    document.getElementById("Id").value = data[0].Id;
                    document.getElementById("odaAdi").value = data[0].odaAdi;
                    var tipi = data[0].tipi;
                    var manzara = data[0].manzara;
                    var durum =  data[0].durum;
                        $.ajax({
                             "url": "/OtelRezervasyon/odaTipi",
                             "type": "POST",
                             "data": { "action":"combo" },
                              success: function(data){
                                  $("#tipi").html(data);
                                  document.getElementById("tipi").value = tipi;
                                  odatipigetir();
                              }               
                         });
                        $.ajax({
                             "url": "/OtelRezervasyon/odaManzara",
                             "type": "POST",
                             "data": { "action":"combo" },
                              success: function(data){
                                  $("#manzara").html(data);
                                  document.getElementById("manzara").value = manzara;
                              }               
                         });                     
                        $.ajax({
                             "url": "/OtelRezervasyon/odaDurum",
                             "type": "POST",
                             "data": { "action":"combo" },
                              success: function(data){
                                  $("#durum").html(data);
                                  document.getElementById("durum").value = durum;
                              }               
                         });
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
               "url": "/OtelRezervasyon/odaListesi",
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
function odatipigetir(){
    var tipi = $("#tipi").val();
         $.ajax({
               "url": "/OtelRezervasyon/odaTipi",
               "type": "POST",
               "data": { "action":"edit","id":tipi },
                success: function(data){
                    document.getElementById("fiyat").value = data[0].fiyat;
                    document.getElementById("yetiskin").value = data[0].yetiskin;
                    document.getElementById("cocuk").value = data[0].cocuk;
                }               
           });              
}
 
</script>
