<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <title>Motivos</title>
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
                </div>
                <div class="page-content inset">
                    <div class="row">
                        <div class="col-md-12">
                            <p class="well lead">Lista de Motivos</p>
                            <div class="container">
                                <form action="<%=request.getContextPath()%>/Motivo" method="post">
                                    <div class="row"> 
                                        <div class="col-md-6">                                            
                                        </div>
                                        <div class="col-md-3">
                                            <a href="<%=request.getContextPath()%>/cadastrarMotivo.jsp" class="btn btn-primary pull-right h2">Novo Motivo</a>
                                        </div>
                                    </div>
                                    <div class="table table-bordered">
                                        <table class="table table-hover table-striped" cellspacing="1" cellpadding="1">
                                            <thead>
                                                <tr>                                                
                                                    <th><j1>Motivo do Cancelamento</j1></th>                                     
                                            <th class ="actions"><j1>Ações</j1></th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach items="${motivos}" var="a">
                                                    <tr>                                              
                                                <td><j1> ${a.descricao} </j1></td>                                                
                                                <td class = "actions">
                                                    <a class="btn btn-success btn-xs" href="<%=request.getContextPath()%>/Motivo?btnAcionar=ListarPorID&id=${a.id}">Visualizar</a>
                                                </td>                        
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                            </div>
                        </div>                     
                        <script>
                            $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                        </script>
                        </body>
                        </html>
