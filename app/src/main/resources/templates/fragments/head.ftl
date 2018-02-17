<#import "/spring.ftl" as spring/>

<#macro common_page_head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="/bower_components/moment/min/moment-with-locales.min.js"></script>
<script type="text/javascript" src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.ru.min.js"></script>

<link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.min.css" />

<!-- Custom styles for this template -->
<link href="<@spring.url '/css/sticky-footer-navbar.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url '/css/common.css'/>" rel="stylesheet" type="text/css">

<#-- Custom JS -->
<script src="<@spring.url '/js/nav.js'/>"></script>
<script src="<@spring.url '/js/form.js'/>"></script>

<link rel="icon" href="../../favicon.ico">

<script type="application/javascript">
    $(function () {
        $(".datetimepicker").datetimepicker({locale: '${.locale}'});
        $('.input-group.datepicker').datepicker({language: "${.locale}"});
    });
</script>
</#macro>
