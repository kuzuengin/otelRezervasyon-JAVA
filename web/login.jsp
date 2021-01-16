<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Sivas Cumhuriyet Üniversitesi </title>
  	<!-- Bootstrap 4.0-->
	<link rel="stylesheet" href="assets/vendor_components/bootstrap/dist/css/bootstrap.css">

	<!-- Bootstrap extend-->
	<link rel="stylesheet" href="css/bootstrap-extend.css">
	
	<!-- Theme style -->
        <link href="css/master_style.css" rel="stylesheet" type="text/css"/>
	<!-- Crypto_Admin skins -->
	<link rel="stylesheet" href="css/_all-skins.css">
        <link rel="stylesheet" href="css/master_style.css">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><img src="http://www.cumhuriyet.edu.tr/kurumsal_logo.png" width="90"><br> <b>Sivas Cumhuriyet Üniversitesi Mühendislik Fakültesi İnternet Mühendisliği Dersi Proje</b></a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Rezervasyon Sistemi</p>

    <form  method="post" class="form-element" action="anasayfa">
      <div class="form-group has-feedback">
        <input type="text" name="kullanici" id="kullanici" class="form-control" placeholder="Kullanıcı Adı"  maxlength="50" autofocus>
        <span class="ion ion-person form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" name="sifre" id="sifre" class="form-control"  placeholder="Şifre"  maxlength="50">
        <span class="ion ion-locked form-control-feedback"></span>
      </div>
      <%=(request.getAttribute("hata")!=null?request.getAttribute("hata"):"")%>
      <div class="row">
        <div class="col-12 text-center">
          <button type="submit" value="gir" class="btn btn-info btn-block margin-top-10">GİRİŞ</button>
        </div>
        <!-- /.col -->
      </div>
    </form>


  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
