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
        <title>Agendamentos</title>
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
                            <p class="well lead text-center">Lista de Agendamentos</p>
                            <div class="container">
                                <form action="<%=request.getContextPath()%>/Agenda" method="post">
                                    <div class="row"> 
                                       
                                        <div class="col-md-6">
                                            <div class="input-group h2">                    
                                                <select name="dentista" class="form-control">
                                                    <c:forEach items="${dentista}" var="d">
                                                        <option value="${d.dentistaID.toString()}"> ${d.pessoa.nome} </option>
                                                    </c:forEach>
                                                </select>
                                                <span class="input-group-btn">
                                                    <button class="btn btn-primary" name="btnAcionar" value="ListarTudo" type="submit">
                                                        <span class="glyphicon glyphicon-search"></span>
                                                    </button>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <a href="<%=request.getContextPath()%>/Agenda?btnAcionar=Cadastrar" class="btn btn-primary pull-right h2">Novo Agendamento</a>
                                        </div>
                                    </div>
                                    <div class="table table-bordered">
                                        <table class="table table-hover table-striped" cellspacing="1" cellpadding="1">
                                            <thead>
                                                <tr>                                  
                                                    <th><j1>Nome do Paciente</j1></th>
                                                    <th><j1>Nome do Dentista</j1></th>
                                                    <th><j1>Data da Consulta</j1></th>
                                                    <th><j1>Horario</j1></th>                                      
                                                    <th><j1>Situação</j1></th>                                       
                                                    <th class ="actions"><j1>Ações</j1></th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach items="${agenda}" var="a">
                                                    <tr>                                     
                                                        <td><j1> ${a.paciente.pessoa.nome} </j1></td>
                                                        <td><j1> ${a.dentista.pessoa.nome} </j1></td>
                                                        <td><j1><fmt:formatDate value="${a.dtagendamento}" pattern="dd/MM/yyyy" />  </j1></td>
                                                        <td><j1> ${a.hragendamento} </j1></td>
                                                        <td><j1> ${a.situacao} </j1></td>
                                                        <td class = "actions">
                                                            <a class="btn btn-success btn-xs" href="<%=request.getContextPath()%>/Agenda?btnAcionar=ListarPorID&id=${a.agendamentoID}">Visualizar</a>
                                                            <a class="btn btn-info btn-xs"  href="<%=request.getContextPath()%>/Atendimento?btnAcionar=ListarPorIdAgendamento&id=${a.agendamentoID}">Efetuar Atendimento</a>                                                                                                     
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
