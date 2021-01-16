<%-- 
    Document   : musteriListesi
    Created on : 06.Oca.2021, 16:06:39
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
         
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Müşteri Listesi</h3>
			  
              <a href="" class="btn btn-app bg-olive" style="float:right;" data-toggle="modal" data-target="#modal-kayit" onclick="yeni()"><i class="fa fa-plus"></i> Yeni Kayıt</a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo1" class="table table-bordered table-striped display">
					<thead>
						<tr>
							<th style="width:50px;">S.No</th>
							<th>Müşteri Adı</th>
                                                        <th>Müşteri Soyadı</th>
                                                        <th>Telefon</th>
                                                        <th>E-posta</th>
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
               "url": "/OtelRezervasyon/musteriListesi",
               "type": "POST",
               "data": { "action":"select" }
           },
            "columns": [
                { "data": "SN" },
                { "data": "ad" },
                { "data": "soyad" },
                { "data": "telefon" },
                { "data": "eposta" },
                { "data": "Duzelt" },
                { "data": "Sil" }
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ["-1,-2"]},
                {
                    "targets": [0,5,6],
                    "className": "text-center"
                }
            ]
        } );
        

} );
function yeni()
{
  $('#modal-title').text("Yeni Müşteri Ekleme Formu");  
 document.getElementById("action").value = "insert";
 document.getElementById("Id").value = "0";
document.getElementById("ad").value = "";
document.getElementById("soyad").value = "";
document.getElementById("telefon").value = "";
document.getElementById("eposta").value = "";
}
function duzelt(id=0)
{
 $('#modal-title').text("Müşteri Düzeltme Formu");   
document.getElementById("action").value = "update";
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
                    document.getElementById("Id").value = data[0].Id;
                    document.getElementById("ad").value = data[0].ad;
                    document.getElementById("soyad").value = data[0].soyad;
                    document.getElementById("telefon").value = data[0].telefon;
                    document.getElementById("eposta").value = data[0].eposta;
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
               "url": "/OtelRezervasyon/musteriListesi",
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

