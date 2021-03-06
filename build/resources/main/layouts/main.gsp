<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
    @import url("https://fonts.googleapis.com/css?family=Nunito:300,700,300italic,700italic");

            html, body {
                background-image: linear-gradient(145deg, #ffffff, #126091);
                font-weight: 200;
                height: 100vh;
                margin: 0;
                color:#444444;
                font-family: "Nunito"
            }

            h1,h2,h3,h4,h5 {
                font-weight: 300;
            }

            .full-height {
                height: 100vh;
            }

            .flex-center {
                align-items: center;
                display: flex;
                justify-content: center;
            }

            .position-ref {
                position: relative;
            }

            .top-right {
                position: absolute;
                right: 10px;
                top: 18px;
            }

            .content {
                text-align: center;
                width: 100%;
                max-width: 800px;
            }

            .links > a {
                color: #ffffff;
                padding: 0 25px;
                font-size: 13px;
                font-weight: 600;
                letter-spacing: .1rem;
                text-decoration: none;
                text-transform: uppercase;
            }

            .links > a:hover {
                color:#333333;
            }
            .card {
				background: #ffffff;
                min-height:300px;
                border:0;
			}
			.card-header {
				background: #126091;
                color:#ffffff;
                font-family: "Nunito";
                font-size: 24px;
                font-weight: 300;
                text-align: left;
			}
            table {
                border: 1px #999999 solid;
                background: #ffffff;
                color:#444444;
                
            }
            td {
                border: 1px #999999 dashed;
                padding: 3px;
                text-align:left;
            }
            .btn-primary {
                background-color:#126091;
                border:0;
                font-size: 100%;
            }
            .btn-primary:hover {
                background-color:#036;
                
            }
            .btn-secondary {
                background-color:#f60;
                border:0;
                font-size: 100%;
            }
            .btn-secondary:hover {
                background-color:#f30;
                
            }
            .btn-default {
                background-color:#555555;
                border:0;
                color:#ffffff;
                font-size: 100%;
            }
            .btn-default:hover {
                background-color:#444444;
                
            }
        </style>
    <g:layoutHead/>
</head>

<body>
<div class="flex-center position-ref full-height">
    <div class="top-right links">
        <a href="/"><i class="fa fa-money"></i> Home</a>
    </div>

    
    <div class="content">
        
        <g:layoutBody/>
    </div>
</div>
           

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
