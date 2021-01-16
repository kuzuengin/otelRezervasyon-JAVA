<%-- 
    Document   : otel
    Created on : 09.Oca.2021, 21:10:31
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
   
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://www.cumhuriyet.edu.tr/kurumsal_logo.png">

    <title>Otel Rezervasyon</title>
    
	
	<!-- Bootstrap 4.0-->
	<link rel="stylesheet" href="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/bootstrap/dist/css/bootstrap.css">
    
	<!--amcharts -->
	<link href="http://enginkuzu.cumhuriyet.edu.tr/giris/src/css/export.css" rel="stylesheet" type="text/css" />
	
	<!-- Bootstrap-extend -->
	<link rel="stylesheet" href="http://enginkuzu.cumhuriyet.edu.tr/giris/src/css/bootstrap-extend.css">
	
	<!-- theme style -->
	<link rel="stylesheet" href="css/master_style.css">
	
	<!-- Crypto_Admin skins -->
	<link rel="stylesheet" href="http://enginkuzu.cumhuriyet.edu.tr/giris/src/css/skins/_all-skins.css">
      <!-- toast CSS -->
      <link href="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jquery-toast-plugin-master/src/jquery.toast.css" rel="stylesheet">
      <!--alerts CSS -->
      <link href="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
      <!-- bootstrap datepicker -->
      <link rel="stylesheet" href="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">

      <link rel="stylesheet" href="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jQuery-Validation-Engine-master/css/validationEngine.jquery.css" type="text/css"/>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<!-- jQuery 3 -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jquery/dist/jquery.js"></script>
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jQuery-Validation-Engine-master/js/languages/jquery.validationEngine-tr.js" type="text/javascript" charset="utf-8"></script>
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jQuery-Validation-Engine-master/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>	 
  </head>
 <% 
int ekran=0;
boolean kontrol=false;
Cookie cookies[] = request.getCookies ();
Cookie rezervasyonKod = null, rezervasyonTarih = null,rezAd=null,rezSoyad=null;
if (cookies != null){
    //out.print(cookies.length);
    for (int i = 0; i < cookies.length; i++){
        if (cookies[i].getName().equals("rezervasyonKod")){ rezervasyonKod = cookies[i]; kontrol=true; }
        if (cookies[i].getName().equals("rezervasyonTarih")){ rezervasyonTarih = cookies[i];  }
        if (cookies[i].getName().equals("rezAd")){ rezAd = cookies[i];  }
        if (cookies[i].getName().equals("rezSoyad")){ rezSoyad = cookies[i];  }
    }
}
    
if (kontrol) ekran=1;
//out.print(rezervasyonKod.getValue());
//out.print(rezervasyonTar1.getValue());
//out.print(rezervasyonTar2.getValue());
//out.print(rezAd.getValue());
//out.print(rezSoyad.getValue());
if (ekran==0){
%> 
<body class="hold-transition skin-yellow sidebar-mini">
<div class="wrapper">


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
							<th>Oda Tipi</th>
							<th>Oda Adı</th>
                                                        <th>Manzara</th>
                                                        <th>Yetişkin</th>
                                                        <th>Çocuk</th>
                                                        <th>Fiyat</th>
                                                        <th>Rezervasyon</th>
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
                                </div>
                                <div class="col-4">
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
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                    <input type="hidden" id="action" name="action"> 
                    <input type="hidden" id="Id" name="Id">
                    <input type="hidden" id="baslangicTarihi" name="baslangicTarihi"></input>
                    <input type="hidden" id="bitisTarihi" name="bitisTarihi"></input>
                    <input type="hidden" id="toplamTutar" name="toplamTutar"></input>
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

        document.getElementById("btarih").value = formatDate(new Date());
        document.getElementById("starih").value = formatDate(new Date());
        
         $('#Tablo1').DataTable( {
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.22/i18n/Turkish.json"
        },             
            "ajax": 
            {
               "url": "/OtelRezervasyon/otel",
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
                { "data": "tipadi" },
                { "data": "odaAdi" },
                { "data": "manzaraAdi" },
                { "data": "yetiskin" },
                { "data": "cocuk" },
                { "data": "fiyat",render: function(data, type) { return data+".00 TL.";} },
                { "data": "odaID",render: function(data, type) { return "<img src='fon/onayla.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+data+")\" />";} }
                
            ],
            columnDefs: [{ 'bSortable': false, 'targets': ['-1,-2,-3']},
                {
                    "targets": [0,1,2,3,4,5,6],
                    "className": "text-center",
                }
            ],
 initComplete: function() {
            this.api().columns(0).every(function() {
                var column = this;
                $(column.header()).append("<br>")
                var select = $('<select><option value=""></option></select>')
                    .appendTo($(column.header()))
                    .on('change', function() {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
                        column
                            .search(val ? '^' + val + '$' : '', true, false)
                            .draw();
                    });
                column.data().unique().sort().each(function(d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>')
                });
            });
        }
        
            
        } );
} );
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

function duzelt(id=0)
{
    $("#ad").focus();
document.getElementById("ad").value = "";
document.getElementById("soyad").value = "";
document.getElementById("telefon").value = "";
document.getElementById("eposta").value = "";    
var b=document.getElementById("btarih").value;
var s=document.getElementById("starih").value;    
document.getElementById("baslangicTarihi").value=b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2);
document.getElementById("bitisTarihi").value=s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2) ;
document.getElementById("baslangictarihi").value=b ;
document.getElementById("bitistarihi").value=s ;
document.getElementById("action").value = "insert";
document.getElementById("Id").value = id;

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
               "url": "/OtelRezervasyon/odaManzara",
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

function gunhesapla() {
var b=document.getElementById("btarih").value,
s=document.getElementById("starih").value,
baslangic  = new Date(b.substr(6, 4)+"-"+b.substr(3, 2)+"-"+b.substr(0, 2)),
     bitis = new Date(s.substr(6, 4)+"-"+s.substr(3, 2)+"-"+s.substr(0, 2)),
     fark  = new Date(bitis - baslangic),
     gun  = Math.floor(fark/1000/60/60/24);    
     return gun+1; 
} 
</script>




  <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->

<!-- ./wrapper -->
  	

	  

	<!-- popper -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/popper/dist/popper.min.js"></script>
	
	<!-- Bootstrap 4.0-->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/bootstrap/dist/js/bootstrap.js"></script>
	
	<!-- Slimscroll -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jquery-slimscroll/jquery.slimscroll.js"></script>
	
	<!-- FastClick -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/fastclick/lib/fastclick.js"></script>

    <!--amcharts charts -->
	<!--
	<script src="http://www.amcharts.com/lib/3/amcharts.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/gauge.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/serial.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/amstock.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/pie.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/plugins/animate/animate.min.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/plugins/export/export.min.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/themes/patterns.js" type="text/javascript"></script>
	<script src="http://www.amcharts.com/lib/3/themes/light.js" type="text/javascript"></script>	
	 -->
	<!-- webticker -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/Web-Ticker-master/jquery.webticker.min.js"></script>
	
	<!-- EChartJS JavaScript -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/echarts-master/dist/echarts-en.min.js"></script>
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/echarts-liquidfill-master/dist/echarts-liquidfill.min.js"></script>
	
	<!-- This is data table -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
    
    <!-- start - This is for export functionality only -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/dataTables.buttons.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/buttons.flash.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/ex-js/jszip.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/ex-js/pdfmake.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/ex-js/vfs_fonts.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/buttons.html5.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/buttons.print.min.js"></script>
    <!-- end - This is for export functionality only -->

	<!-- Crypto_Admin App -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/src/js/template.js"></script>
	
	<!-- Crypto_Admin dashboard demo (This is only for demo purposes) -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/src/js/pages/dashboard.js"></script>
	<!-- <script src="js/pages/dashboard-chart.js"></script>-->
	
	<!-- Crypto_Admin for demo purposes -->
	<script src="http://enginkuzu.cumhuriyet.edu.tr/giris/src/js/demo.js"></script>

        
    <!-- toast -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jquery-toast-plugin-master/src/jquery.toast.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/src/js/pages/toastr.js"></script>
            <!-- Sweet-Alert  -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/sweetalert/sweetalert.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/sweetalert/jquery.sweet-alert.custom.js"></script>
    <!-- Formatter -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/formatter/formatter.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/formatter/jquery.formatter.js"></script>
    <!-- bootstrap datepicker -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
    <script>
           
        $('#btarih').datepicker({autoclose: true });
        $('#starih').datepicker({autoclose: true});
   
   
    function kayitFunction() {
        var url=window.location.pathname;
        if ($("#kayitForm").validationEngine('validate'))
        {
            var gonderilenform = $("#kayitForm").serialize();
            console.log(gonderilenform);
            $.ajax({
                type: "POST",
                url: url,
                data:gonderilenform,
                success: function(data){
                    console.log(data.kayit);
                    if (data.kayit==='Evet'){
                        swal({
                            title: data.title,
                            text: data.mesaj,
                            timer: 2000,
                            type: "success",
                            showConfirmButton: false
                        });                        
                        jQuery("#Tablo1").DataTable().ajax.reload();
                        document.getElementById("kayitclose").click();
                        document.cookie = "rezervasyonKod="+data.kod+"; max-age="+60*60*24;
                        document.cookie = "rezervasyonTarih="+document.getElementById("btarih").value+"-"+document.getElementById("starih").value+"; max-age="+60*60*24;
                        document.cookie = "rezAd="+document.getElementById("ad").value+"; max-age="+60*60*24;
                        document.cookie = "rezSoyad="+document.getElementById("soyad").value+"; max-age="+60*60*24;
                  window.location = "otel";
                    }
                    else {
                        swal({
                            title: data.title,
                            text: data.mesaj,
                            timer: 2000,
                            type: "warning",
                            showConfirmButton: false
                        });
                    }
                },
                failure: function(errMsg) {
                    alert(errMsg);
                }
            });
        }

    }

    function no_enter(event) {
        event = event || window.event;
        if (event.which === 13) {
            event.preventDefault();
            return (false);
        }
    }        
    </script>


</body>
<%} // 
else
{ 
    
    %>
    <body>
    <center>

        <div class="col-md-12 col-lg-4" style="padding-top: 10%;">
          <div class="box box-default pull-up">
              <img class="card-img-top img-responsive" src="images/otel.jpg">
            <div class="box-body">            	
				<h4 class="box-title"><%="Sayın;"+rezAd.getValue()+" "+rezSoyad.getValue()+" "+rezervasyonTarih.getValue()+" Tarihleri Arası Revervasyon işleminiz Kayıt Altındadır"%></h4>
				<p class="box-text">Rezervasyonu İptal Etmek İsterseniz. Butona basmanız yeterlidir.</p>
                                <a href="#" class="btn btn-primary" onclick="kayitSil('<%=rezervasyonKod.getValue()%>');">Rezervasyon İptal</a>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->

    </center>
            <script>

function kayitSil(kod)
{
        swal({   
            title: "Rezervasyon İptal mi?",   
            text: "Rezervasyon İşlemini İptal Etmek İstediğinizden emin misiniz?",   
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
               "url": "/OtelRezervasyon/otel",
               "type": "POST",
               "data": { "action":"delete","kod":kod },
                success: function(data){
                        if (data.kayit==='Evet'){
                            swal({
                            title: data.title,
                            text: data.mesaj,
                            timer: 2000,
                            type: "success",
                            showConfirmButton: false
                        }); 
                        document.cookie = "rezervasyonKod=''; max-age=0";
                        document.cookie = "rezervasyonTarih=''; max-age=0";
                        document.cookie = "rezAd=''; max-age=0";
                        document.cookie = "rezSoyad=''; max-age=0";
                     window.location = "otel";
                            swal.close();    
                        }
                        else {
                            swal({
                            title: data.title,
                            text: data.mesaj,
                            timer: 2000,
                            type: "warning",
                            showConfirmButton: false
                        });
                            swal.close();
                        }
                    }
                });               
        }   else {swal.close();}  
        });
}                
                </script>
      </body>
    <!-- toast -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/jquery-toast-plugin-master/src/jquery.toast.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/src/js/pages/toastr.js"></script>
            <!-- Sweet-Alert  -->
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/sweetalert/sweetalert.min.js"></script>
    <script src="http://enginkuzu.cumhuriyet.edu.tr/giris/assets/vendor_components/sweetalert/jquery.sweet-alert.custom.js"></script>  
    
<%  } //else %>    
</html>
