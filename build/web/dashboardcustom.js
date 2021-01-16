        $('#btarih').datepicker({autoclose: true });
        $('#starih').datepicker({autoclose: true});
$('#fiyat').formatter({'pattern': '{{99999}}','persistent': true});
$('#yetiskin').formatter({'pattern': '{{9}}','persistent': true});
$('#cocuk').formatter({'pattern': '{{9}}','persistent': true});
function msg_ok(obj){
        jQuery.toast({
            heading: obj.title,
            text: obj.mesaj,
            position: 'bottom-right',
            loaderBg: '#ff6849',
            icon: 'success',
            hideAfter: 2500,
            stack: 6
        });
    }
function msg_no(obj){
        swal({
            title: obj.title,
            text: obj.mesaj,
            timer: 2000,
            type: "warning",
            showConfirmButton: false
        });
    }
    
    
   
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
                        jQuery.toast({
                            heading: data.title,
                            text: data.mesaj,
                            position: 'bottom-right',
                            loaderBg: '#ff6849',
                            icon: 'success',
                            hideAfter: 2500,
                            stack: 6
                    });
                        jQuery("#Tablo1").DataTable().ajax.reload();
                        document.getElementById("kayitclose").click();
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

    function kayitFunctionMusteri() {
        if ($("#kayitFormMusteri").validationEngine('validate'))
        {
            var gonderilenform = $("#kayitFormMusteri").serialize();
            console.log(gonderilenform);
            $.ajax({
                type: "POST",
                url: "/OtelRezervasyon/musteriListesi",
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
                        jQuery("#Tablo2").DataTable().ajax.reload();
                        document.getElementById("kayitclose1").click();
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


