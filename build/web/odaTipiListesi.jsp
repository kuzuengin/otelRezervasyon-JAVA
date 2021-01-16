<%-- 
    Document   : odaTipiListesi
    Created on : 04.Oca.2021, 22:35:28
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
         
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Oda Tipi Listesi</h3>
			  
              <a href="" class="btn btn-app bg-olive" style="float:right;" data-toggle="modal" data-target="#modal-kayit" onclick="yeni()"><i class="fa fa-plus"></i> Yeni Kayıt</a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo1" class="table table-bordered table-striped display">
					<thead>
						<tr>
							<th style="width:50px;">S.No</th>
							<th>Oda Tipi Adı</th>
                                                        <th>Oda Fiyatı</th>
                                                        <th>Yetişkin Sayısı</th>
                                                        <th>Çocuk Sayısı</th>
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
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Tipi Adı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="adi" name="adi" data-errormessage="Oda Tipi Ad Bilgisini Giriniz..." onkeypress="no_enter(event)"autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Oda Fiyatı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="fiyat" name="fiyat" data-errormessage="Oda Fiyat Bilgisini Giriniz..." onkeypress="no_enter(event)" >
                                        </div>
                                    </div> 
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Yetişkin Sayısı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="yetiskin" name="yetiskin" data-errormessage="Yetişkin Sayısını Giriniz..." onkeypress="no_enter(event)" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Çocuk Sayısı : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="cocuk" name="cocuk" data-errormessage="Çocuk Sayısını Giriniz..." onkeypress="no_enter(event)" >
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
               "url": "/OtelRezervasyon/odaTipi",
               "type": "POST",
               "data": { "action":"select" }
           },
            "columns": [
                { "data": "SN" },
                { "data": "adi" },
                { "data": "fiyat" },
                { "data": "yetiskin" },
                { "data": "cocuk" },
                { "data": "Duzelt" },
                { "data": "Sil" }
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ["-1"]},
                {
                    "targets": [0,3,4,5,6],
                    "className": "text-center"
                }
            ]
        } );
        

} );
function yeni()
{
  $('#modal-title').text("Yeni Oda Tipi Ekleme Formu");  
 document.getElementById("action").value = "insert";
 document.getElementById("Id").value = "0";
document.getElementById("adi").value = "";
document.getElementById("fiyat").value = "";
document.getElementById("yetiskin").value = "";
document.getElementById("cocuk").value = "";
}
function duzelt(id=0)
{
 $('#modal-title').text("Oda Manzara Düzeltme Formu");   
document.getElementById("action").value = "update";
         $.ajax({
               "url": "/OtelRezervasyon/odaTipi",
               "type": "POST",
               "data": { "action":"edit","id":id },
                success: function(data){
                     document.getElementById("Id").value = id;
                    document.getElementById("adi").value = data[0].adi;
                    document.getElementById("fiyat").value = data[0].fiyat;
                    document.getElementById("yetiskin").value = data[0].yetiskin;
                    document.getElementById("cocuk").value = data[0].cocuk;
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
               "url": "/OtelRezervasyon/odaTipi",
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