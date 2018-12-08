<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>ClinTeeth Home Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="content/bootstrap/css/style-index.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="content/bootstrap/css/bootstrap.css"/>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">
        <!--<link href="content/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="content/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="content/bootstrap/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">-->

        <script src="content/bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="content/bootstrap/js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="content/bootstrap/js/bootstrap.js" type="text/javascript"></script>
        <script src="content/bootstrap/js/bootstrap-notify.min.js" type="text/javascript"></script>
    </head>
    <body>
        <!--INICIO NAVBAR-->
        <nav class="navbar navbar-default" role="navigation" style="background-color: #6495ED;">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/ClinTeeth/index.jsp">ClinTeeth</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Pagina Principal</a></li>
                        <li><a href="#">Sobre Nós</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Sign Up</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login <b class="caret"></b></a>
                            <ul class="dropdown-menu" style="padding: 15px;min-width: 250px;">
                                <li>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <%
                                                String falhaLogin = (String) request.getAttribute("falhaLogin");
                                                if (falhaLogin != null) {
                                            %>
                                            <div class="alert alert-danger" id="falhaLogin">
                                                <font color="red"><%=falhaLogin%></font>
                                            </div>
                                            <%}%>
                                            <form class="form" role="form" method="post" action="Login" accept-charset="UTF-8" id="login-nav">                                                        
                                                <div class="form-group">
                                                    <label class="sr-only" for="email">Email address</label>
                                                    <input type="Login" class="form-control" id="email" name="email" placeholder="E-mail" required oninvalid="setCustomValidity('Por favor preencha o E-mail')" onchange="try {
                                                                setCustomValidity('')
                                                            } catch (e) {
                                                            }">
                                                </div>
                                                <div class="form-group">
                                                    <label class="sr-only" for="senha">Password</label>
                                                    <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" required oninvalid="setCustomValidity('Por favor preencha a Senha')" onchange="try {
                                                                setCustomValidity('')
                                                            } catch (e) {
                                                            }">
                                                </div>
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox"> Lembrar Senha
                                                    </label>
                                                </div>
                                                <div class="form-group">
                                                    <input type="submit" class="btn btn-success btn-block" name="btnAcionar" value="Entrar"/>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.navbar-collapse -->
            <!-- INICIO BANNER -->
            <section class='banner' style="background-image: url(bg_carousel.png)">
                <div class='container'>
                    <div class="row">
                        <div class="col-lg-12" style="color: #fff;">
                            <img src="Logo.png" class="img-responsive" style="padding: 20px 0px; margin-left: -5px;">
                            <h1 style="width: 540px;">Trazendo a inovação que o seu consultório precisa.</h1>
                            <p style="width: 540px;"></p>
                        </div>
                    </div>
                </div>
                <!--INICIO CAROUSEL-->
                <section class='carousel'>



                    <div class="row">

                        <div class="col-lg-12">

                            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active" style="background-color: #6495ED"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="1" style="background-color: #6495ED"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="2" style="background-color: #6495ED"></li>
                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                    <div class="item active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <img src='Sem-título-18.jpg' class='img-responsive' style="margin-bottom: -90px;">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <img src='Sem-título-17.jpg' class='img-responsive' style="margin-bottom: -90px;">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <img src='Sem-título-14.jpg' class='img-responsive' style="margin-bottom: -90px;">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Controls -->
                                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true" style="color: #6495ED;"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="color: #6495ED;"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </section>
                <!--FIM CAROUSEL-->
            </section>
            <!-- FIM BANNER-->
            <div class="card-group" style="background-color: #6495ED; margin-top: -40px; padding: 15px 0px" >
                <div class="container">
                    <div class="row">

                        <div class="card-body">
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                                <img src="imagens/Blood-icon.png" style="margin-left: 28px; margin-bottom: -47px; width: 5%;"><h5 class="card-title card-1 ml-5">Blood</h5>
                                <p class="card-text text-white text-justify ml-5" 
                                   style="width:170px; font-size: 12px; color: #fff;">At-Home, Easy and Pain-Free test utilizing a 
                                    combination of dried blood spot technology and serum based testing.</p>
                                <a class="card-link card-1 ml-5" href="#">Shop Tests</a>
                            </div>
                        </div>


                        <div class="card-body">
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                                <img src="imagens/DNA-icon.png" style="margin-left: 28px; margin-bottom: -47px; width: 5%;"><h5 class="card-title card-2 ml-5">DNA</h5>
                                <p class="card-text text-white text-justify ml-5" style="width:170px; font-size: 12px; color: #fff;">Our saliva based DNA test gives you a better understanding of how your body metabolizes medicine.</p>
                                <a class="card-link card-2 ml-5" href="#" >Shop Tests</a>
                            </div>
                        </div>


                        <div class="card-body">
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                                <img src="imagens/Food-icon.png" style="margin-left: 13px; margin-bottom: -47px; width: 11%;"><h5 class="card-title card-3 ml-5">Food Sensitivity</h5>
                                <p class="card-text text-white text-justify ml-5" style="width:160px; font-size: 12px; color: #fff;">Have stomach issues? This test provides insight on intolerances, or non-immune responses, to certain foods.</p>
                                <a class="card-link card-3 ml-5" href="#">Shop Tests</a>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                                <img src="imagens/Nutrition-icon.png" style="margin-left: 23px; margin-bottom: -47px; width: 7%;"><h5 class="card-title card-4 ml-5">Nutrition</h5>
                                <p class="card-text text-white text-justify ml-5" style="width:170px; font-size: 12px; color: #fff;">Enhance your helathy lifestyle through nutritional insights with our advance Nutrition Panel test.</p>
                                <a class="card-link card-4 ml-5" href="#">Shop Tests</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <!--FIM NAVBAR-->
        <!-- INICIO CONTEUDO PRINCIPAL-->


        <div class="jumbotron text-center" style="background-color: #fff; padding: 20px 0px">                        
            <h3 style="color: #e94a46; margin-bottom: 60px;">Empowering Your Personal Health And Wellness</h3>
        </div>
        <div class="container">
            <div class="row">
                <div class="card-group color-white">
                    <div class="card border-0 col-lg-3">
                        <div class="card-body">
                            <h5 class="card-title">Select A Test</h5>
                            <p class="card-text text-justify">Choose from over 14 different test options, Blood, DNA, food Sensitivity, and Nutrition.</p>
                        </div>
                    </div>
                    <div class="card border-0 col-lg-3">
                        <div class="card-body">
                            <h5 class="card-title">Collect Specimen</h5>
                            <p class="card-text text-justify">Our box will be mailed to you direct, within 48 hrs. You will find all applicable instructions in your box.</p>
                        </div>

                    </div>
                    <div class="card border-0 col-lg-2">
                        <div class="card-body">
                            <h5 class="card-title">Physician Review</h5>
                            <p class="card-text text-justify">Our team of over 1,500 physician partners review every test result for accuracy and oversight.</p>
                        </div>

                    </div>
                    <div class="card border-0 col-lg-2">
                        <div class="card-body ">
                            <h5 class="card-title">View Your Results</h5>
                            <p class="card-text text-justify">After the lab is finished running your test, we will upload those results to your member account.</p>
                        </div>

                    </div>
                    <div class="card border-0 col-lg-2">
                        <div class="card-body">
                            <h5 class="card-title">Take Control</h5>
                            <p class="card-text text-justify">We hope you are better informed and will benefit from the health data provided by our services.</p>
                        </div>
                    </div>
                </div>

                <div class="card-group color-white mt-5">
                    <div class="card border-0 text-left">
                        <div class="card-body ">
                            <div class="col-lg-6">

                                <h1 class="card-title text-left mt-5"
                                    >Accurate Results At Your Fingertips</h1>
                                <p class="card-text text-left card-text-1">All CashLabs reference labs are 
                                    appropriately licensed/ certified under the Clinical Laboratory Improvement 
                                    Amendments of 1988 (CLIA 88) and as required by certain State laboratory 
                                    licensure programs. In addition-Cashlabs Regional and Esoteric Reference 
                                    Laboratories are accredited by the College of American Pathologists (CAP).</p>
                                <p><a class="btn btn-primary btn-lg buttom_design" style="margin-top: 15px;" href="#" role="button">Shop Tests</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="card border-0 text-right">
                        <div class="col-lg-6">
                            <img class="card-img-right img-fluid" src="imagens/Celulares.png" alt="Celulares">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="card-group border-top">
            <div class="container">
                <div class="row">
                    <div class="card border-0">
                        <div class="col-lg-6">
                            <img class="card-img-right img-fluid" src="...">
                        </div>
                    </div>
                    <div class="card border-0 text-right">
                        <div class="col-lg-6">
                            <div class="card-body ">
                                <h1 class="card-title text-left">Precise Clinical-Grade Technology</h1>
                                <p class="card-text text-left">All CashLabs reference labs are appropriately licensed/ certified under the Clinical Laboratory Improvement Amendments of 1988 (CLIA 88) and as required by certain State laboratory licensure programs. In addition-Cashlabs Regional and Esoteric Reference Laboratories are accredited by the College of American Pathologists (CAP).</p>
                            </div>
                        </div>
                    </div>
                    <div class="card-group">
                        <div class="card border-0">
                            <div class="card-body">
                                <div class="col-lg-6">
                                    <h5 class="card-title card-1">Blood</h5>
                                    <p class="card-text text-white">At-Home, Easy and Pain-Free test utilizing a combination of dried blood spot technology and serum based testing.</p>
                                    <img class="card-img " src="#" alt="AdobeStock">
                                </div>
                            </div>
                        </div>
                        <div class="card border-0">
                            <div class="card-body">
                                <div class="col-lg-6">
                                    <h5 class="card-title ">Blood</h5>
                                    <p class="card-text text-white">At-Home, Easy and Pain-Free test utilizing a combination of dried blood spot technology and serum based testing.</p>
                                    <img class="card-img-right img-fluid" src="#" alt="AdobeStock">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="jumbotron junbotron_2">
            <div class="container">
                <img src="# " class="img-fluid" alt="ImgTop img-custom"> 
            </div> 
        </div>
        <!-- FIM CONTEUDO PRINCIPAL-->
        <footer>
            <div class="navbar">
                <div class="container">
                    <div class="row">

                        <ul class="nav nav-pills nav-stacked col-lg-3">
                            <div class="container">
                                <li class="nav-item">
                                    <img src="Logo_ClinTeeth.png" class="img-responsive mb-5 mt-5"  alt="Logo" style="padding: 20px 0px">
                                    <h4>More Information</h4>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Physician Oversight</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link " href="#">FAQs</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Terms Of Use</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Privacy Policy</a>
                                </li>
                            </div>
                        </ul>
                        <ul class="nav nav-pills nav-stacked col-lg-3" style="margin-top: 92px;">
                            <div class="container">
                                <li class="nav-item">
                                    <h4>About Us</h4>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link " href="#">About</a>
                                </li>
                                <li class="nav-item ">
                                    <a class="nav-link" href="#">Blog</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Become a Partner</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link " href="#">Contact Us</a>
                                </li>
                            </div>
                        </ul>
                        <ul class="nav nav-pills nav-stacked col-lg-6" style="margin-top: -30px;">
                            <div class="container">
                                <li class="nav-item" style="margin-bottom: 60px; margin-top: 132px;">
                                    <h4>Connect With Us</h4>
                                    <button class="btn btn-info" style="padding: 5px 13px;"><i class="fa fa-facebook-f"></i></button>
                                    <button class="btn btn-info" style="padding: 5px 10px;"><i class="fa fa-twitter"></i></button>
                                    <button class="btn btn-info" style="padding: 5px 8px;"><i class="fa fa-google-plus"></i></button>
                                    <button class="btn btn-info" style="padding: 5px 11px;"><i class="fa fa-instagram"></i></button>
                                    <button class="btn btn-info" style="padding: 5px 10px;"><i class="fa fa-snapchat-ghost"></i></button>
                                </li> 
                            </div>
                        </ul>
                    </div>
                </div>

            </div>
            <div class="row" style="background: #f6f7f7; padding-top: 20px;">
                <div class="container">
                    <p class="copyright text-muted" style="font-size: 12px">&copy 2018 ClinTeeth, Inc. All rights reserved.</p>
                    <p class="copyright text-muted" style="font-size: 12px">ClinTeeth, Inc. offers laboratory testing for wellness monitoring. Our tests are not intended to diagnose or treat disease, or to substitute for a physician's consultation. For regulatory reasons, our tests are not
                        available to those under the age of 18 or that reside in MD, NJ, NY, and RI.</p>
                </div>
            </div>
        </footer>



    </body>
</html>