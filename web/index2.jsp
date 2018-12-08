<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="content/bootstrap/css/bootstrap.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="content/bootstrap/js/jquery.min.js" type="text/javascript"></script>
        <script src="content/bootstrap/js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="content/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="content/bootstrap/js/bootstrap-notify.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-default" role="navigation">
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
                                <li class="active"><a href="http://www.jquery2dotnet.com">Pagina Principal</a></li>
                                <li><a href="http://www.jquery2dotnet.com">Sobre Nós</a></li>
                                <li class="dropdown">
                                    <a href="http://www.jquery2dotnet.com" class="dropdown-toggle" data-toggle="dropdown">Menu <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="http://www.jquery2dotnet.com">Action</a></li>
                                        <li><a href="http://www.jquery2dotnet.com">Another action</a></li>
                                        <li><a href="http://www.jquery2dotnet.com">Something else here</a></li>
                                        <li class="divider"></li>
                                        <li><a href="http://www.jquery2dotnet.com">Separated link</a></li>
                                        <li class="divider"></li>
                                        <li><a href="http://www.jquery2dotnet.com">One more separated link</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Digite sua Busca">
                                </div>
                                <button type="submit" class="btn btn-default">Procurar</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="http://www.jquery2dotnet.com">Sign Up</a></li>
                                <li class="dropdown">
                                    <a href="http://www.jquery2dotnet.com" class="dropdown-toggle" data-toggle="dropdown">Login <b class="caret"></b></a>
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
                        <!-- /.navbar-collapse -->
                    </nav>
                </div>
            </div>
        </div>

    </body>
</html>