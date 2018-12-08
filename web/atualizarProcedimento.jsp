<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/content/bootstrap/css/bootstrap.css"/>        
        <link href="<%=request.getContextPath()%>/content/bootstrap/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/content/bootstrap/css/estilo.css" type="text/css"/>
        <script src="<%=request.getContextPath()%>/content/bootstrap/js/jquery.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/content/bootstrap/js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/content/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/content/bootstrap/js/bootstrap-notify.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/content/bootstrap/js/conversaoCampos.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/content/bootstrap/js/mascaraMoeda.js" type="text/javascript"></script>
        <title>Atualização de Pagamento</title>		
    </head>
    <body>
        <div id="wrapper" class="active">  
            <div id="menu"></div><br>
            <div id="page-content-wrapper">
                <div class="panel-body">
                    <div class="form-group">
                        <div class="col-md-11 control-label">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="page-content inset">
                        <div class="row">
                            <div class="col-md-12">
                                <p class="well lead text-center">Procedimentos</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Procedimentos" method="post">
                                        <div class="col-sm-12 contact-form">
                                            <input type="hidden" name="id" value="${procedimento.procedimentoID}"/>
                                            <div class="form-group">
                                            <div class="col-md-6">
                                               <label class="col-md-0 control-label"> <J1>Descrição</J1> </label>                    
                                                    <input  placeholder="Descrição"  class="form-control input-md" type="text" name="descricao" value="${procedimento.descricao}"/>
                                            </div>  
                                            <div class="col-md-4">
                                                <label class="col-md-0 control-label"> <J1>Preço</J1> </label>                    
                                                    <input  placeholder="Preço"  class="form-control input-md" type="text" name="preco" value="${procedimento.preco}"/>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input class="btn btn-primary btn-group-lg" type="submit" name="btnAcionar" value="Alterar"/>
                                                <a class="btn btn-warning btn-group-lg" href="<%=request.getContextPath()%>/Procedimentos?btnAcionar=ListarTudo">Listar</a>
                                                <a class="btn btn-danger btn-group-lg" href="<%=request.getContextPath()%>/Procedimentos?btnAcionar=Excluir&id=${procedimento.procedimentoID}">Excluir</a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
           $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
        </script>
    </body>
</html>

                         
