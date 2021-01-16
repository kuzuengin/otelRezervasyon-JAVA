<%-- 
    Document   : odaDurumListesi
    Created on : 04.Oca.2021, 13:03:36
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
         
         <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Oda Durum Listesi</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
				  <table id="Tablo1" class="table table-bordered table-striped display">
					<thead>
						<tr>
							<th style="width:50px;">S.No</th>
							<th>Oda Durum Bilgisi</th>
                                                        <th style="width:50px;">Düzelt</th>
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
                <h5 class="modal-title" id="modal-title">Oda Durum Düzeltme Formu</h5>
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
                                        <label for="example-text-input" class="col-sm-4 col-form-label">Aciklama : </label>
                                        <div class="col-sm-8">
                                            <input class="validate[required] form-control" type="text" value="" id="aciklama" name="aciklama" data-errormessage="Açıklama Bilgisini Giriniz..." onkeypress="no_enter(event)"autofocus >
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
               "url": "/OtelRezervasyon/odaDurum",
               "type": "POST",
               "data": { "action":"select" }
           },
            "columns": [
                { "data": "SN" },
                { "data": "aciklama" },
                { "data": "Duzelt" },
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ["-1"]},
                {
                    "targets": [0,2],
                    "className": "text-center"
                }
            ]
        } );
        

} );
function duzelt(id=0)
{
 $('#modal-title').text("Kullanıcı Düzeltme Formu");   
document.getElementById("action").value = "update";
         $.ajax({
               "url": "/OtelRezervasyon/odaDurum",
               "type": "POST",
               "data": { "action":"edit","id":id },
                success: function(data){
                    console.log(data);
                    document.getElementById("Id").value = data[0].Id;
                    document.getElementById("aciklama").value = data[0].aciklama;
                }               
           });            
}
 
</script>