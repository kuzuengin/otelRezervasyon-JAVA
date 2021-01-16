<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="assets/vendor_components/bootstrap/dist/css/bootstrap.css">
    
	<!--amcharts -->
	<link href="css/export.css" rel="stylesheet" type="text/css" />
	
	<!-- Bootstrap-extend -->
	<link rel="stylesheet" href="css/bootstrap-extend.css">
	
	<!-- theme style -->
	<link rel="stylesheet" href="css/master_style.css">
	
	<!-- Crypto_Admin skins -->
	<link rel="stylesheet" href="css/skins/_all-skins.css">
      <!-- toast CSS -->
      <link href="assets/vendor_components/jquery-toast-plugin-master/src/jquery.toast.css" rel="stylesheet">
      <!--alerts CSS -->
      <link href="assets/vendor_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
      <!-- bootstrap datepicker -->
      <link rel="stylesheet" href="assets/vendor_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">

      <link rel="stylesheet" href="assets/vendor_components/jQuery-Validation-Engine-master/css/validationEngine.jquery.css" type="text/css"/>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<!-- jQuery 3 -->
	<script src="assets/vendor_components/jquery/dist/jquery.js"></script>
	<script src="assets/vendor_components/jQuery-Validation-Engine-master/js/languages/jquery.validationEngine-tr.js" type="text/javascript" charset="utf-8"></script>
	<script src="assets/vendor_components/jQuery-Validation-Engine-master/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>	 
  </head>

<body class="hold-transition skin-yellow sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">

		  <!-- User Account -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <%=session.getAttribute("otelUserName")%>&nbsp;<i class="fa fa-power-off"></i>
            </a>
            <ul class="dropdown-menu scale-up">
              <!-- User image -->

              <!-- Menu Body -->
              <li class="user-body">
                <div class="row no-gutters">
				<div role="separator" class="divider col-12"></div>
				  <div class="col-12 text-left">
                    <a href="kapat.jsp"><i class="fa fa-power-off"></i> Oturumu Kapat</a>
                  </div>				
                </div>
                <!-- /.row -->
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
         </ul>
      </div>
    </nav>
  </header>
  
  <!-- Left side column. contains the logo and sidebar MenÃÂ¼ler -->
  <aside class="main-sidebar">
    <!-- sidebar -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <!-- sidebar menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li>
          <a href="anasayfa">
            <i class="icon-home"></i> <span>Ana Sayfa</span>
          </a>
        </li>
<%=request.getAttribute("menuHtml")%>

      </ul>
    </section>
  </aside>

  <!-- Content Wrapper. Contains page content Orta Alan -->
  <div class="content-wrapper">
<% 

String menuDosya = '/'+(String)request.getAttribute("menuDosya"); 


%>

<jsp:include page="<%=menuDosya%>" flush="true" ></jsp:include>
  
  </div>
  <!-- /.content-wrapper -->
  
  <footer class="main-footer">
	  &copy; 2020 <a href="">İnternet Mühendisliği Dersi - Otel Rezervasyon Sistemi </a>
  </footer>


  <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->

<!-- ./wrapper -->
  	

	  

	<!-- popper -->
	<script src="assets/vendor_components/popper/dist/popper.min.js"></script>
	
	<!-- Bootstrap 4.0-->
	<script src="assets/vendor_components/bootstrap/dist/js/bootstrap.js"></script>
	
	<!-- Slimscroll -->
	<script src="assets/vendor_components/jquery-slimscroll/jquery.slimscroll.js"></script>
	
	<!-- FastClick -->
	<script src="assets/vendor_components/fastclick/lib/fastclick.js"></script>

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
	<script src="assets/vendor_components/Web-Ticker-master/jquery.webticker.min.js"></script>
	
	<!-- EChartJS JavaScript -->
	<script src="assets/vendor_components/echarts-master/dist/echarts-en.min.js"></script>
	<script src="assets/vendor_components/echarts-liquidfill-master/dist/echarts-liquidfill.min.js"></script>
	
	<!-- This is data table -->
    <script src="assets/vendor_plugins/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
    
    <!-- start - This is for export functionality only -->
    <script src="assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/dataTables.buttons.min.js"></script>
    <script src="assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/buttons.flash.min.js"></script>
    <script src="assets/vendor_plugins/DataTables-1.10.15/ex-js/jszip.min.js"></script>
    <script src="assets/vendor_plugins/DataTables-1.10.15/ex-js/pdfmake.min.js"></script>
    <script src="assets/vendor_plugins/DataTables-1.10.15/ex-js/vfs_fonts.js"></script>
    <script src="assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/buttons.html5.min.js"></script>
    <script src="assets/vendor_plugins/DataTables-1.10.15/extensions/Buttons/js/buttons.print.min.js"></script>
    <!-- end - This is for export functionality only -->
	<!-- Crypto_Admin for Data Table -->
	<script src="js/pages/data-table.js"></script>
	<!-- Crypto_Admin App -->
	<script src="js/template.js"></script>
	
	<!-- Crypto_Admin dashboard demo (This is only for demo purposes) -->
	<script src="js/pages/dashboard.js"></script>
	<!-- <script src="js/pages/dashboard-chart.js"></script>-->
	
	<!-- Crypto_Admin for demo purposes -->
	<script src="js/demo.js"></script>

        
    <!-- toast -->
    <script src="assets/vendor_components/jquery-toast-plugin-master/src/jquery.toast.js"></script>
    <script src="js/pages/toastr.js"></script>
            <!-- Sweet-Alert  -->
    <script src="assets/vendor_components/sweetalert/sweetalert.min.js"></script>
    <script src="assets/vendor_components/sweetalert/jquery.sweet-alert.custom.js"></script>
    <!-- Formatter -->
    <script src="assets/vendor_components/formatter/formatter.js"></script>
    <script src="assets/vendor_components/formatter/jquery.formatter.js"></script>
    <!-- bootstrap datepicker -->
    <script src="assets/vendor_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
    <script src="dashboardcustom.js"></script>
</body>
</html>
