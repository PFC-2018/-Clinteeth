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
        <title>Atualizar Motivo de Cancelamento</title>		
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
                            <div class="col-md-10">
                                <p class="well lead">Dados do Motivo do Cancelamento</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Motivo" method="post">
                                        <div class="col-sm-12 contact-form">                                                                              
                                            <div class="form-group">
                                                <input type="hidden" name="idmotivo" value="${motivo.id}"/>	
                                                <label class="col-md-1 control-label"> <J1>Descricao</j1> </label>
                                                <div class="col-md-10">
                                                    <input placeholder="Descricao do Motivo" maxlength="50" class="form-control input-md" type="text" name="descricao" value="${motivo.descricao}"/>
                                                </div>                                              
                                            </div>                                                                                        
                                        </div>               
                                        <div class="form-group">
                                            <form action="Procedimento" method="post">
                                            <input type="submit" name="btnAcionar" value="Alterar"/>
                                            <a href="/Motivo?btnAcionar=Excluir&id=${procedimento.id}">Excluir</a>
                                            <a href="</Motivo?btnAcionar=ListarTudo">Listar</a>
                                            </form>
                                        </div>
                                </div>
                            </div>
                            </form>
                            <script>
                                $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                            </script>
                            </body>
                            </html>

