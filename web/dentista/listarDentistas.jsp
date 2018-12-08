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
        <title>Listar Dentistas</title>
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
                            <p class="well lead">Lista de Dentistas</p>
                            <div class="container">
                                <div class="row"> 
                                    <div class="col-md-6">
                                        <div class="input-group h2">
                                            <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar">
                                            <span class="input-group-btn">
                                                <button class="btn btn-primary" type="submit">
                                                    <span class="glyphicon glyphicon-search"></span>
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <a href="<%=request.getContextPath()%>/dentista/cadastrarDentista.jsp" class="btn btn-primary pull-right h2">Novo Dentista</a>
                                    </div>
                                </div>
                                <div class="table table-bordered">
                                    <table class="table table-hover table-striped" cellspacing="1" cellpadding="1">
                                        <thead>
                                            <tr>
                                                <!--     <th>ID</th>-->
                                                <th><j1>Nome</j1></th>
                                        <th><j1>CRO</j1></th>
                                        <!--     <th>RG</th>
                                             <th>CPF</th>
                                             <th>Data Nascimento</th> -->
                                        <th><j1>Telefone</j1></th>
                                        <th><j1>Celular</j1></th>
                                        <th><j1>Email</j1></th>
                                        <!--                            <th>Logradouro</th>
                                                                    <th>Número</th>
                                                                    <th>Bairro</th>
                                                                    <th>Cidade</th>
                                                                    <th>UF</th> -->
                                        <th class ="actions"><j1>Ações</j1></th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach items="${dentista}" var="d">
                                                <tr>
                                              <!--  <td> ${d.dentistaID} </td>
                                                    <td> ${d.pessoa.pessoaID} </td> -->
                                                    <td><j1> ${d.pessoa.nome} </j1></td>
                                            <td><j1> ${d.cro} </j1></td>
                                                  <!--  <td> ${d.rg} </td>
                                                        <td> ${d.cpf} </td>
                                                        
                                                        <td> <fmt:formatDate value="${d.dataNascimento}" pattern="dd/MM/yyyy" /> </td> -->
                                            <td><j1> ${d.pessoa.telefone} </j1></td>
                                            <td><j1> ${d.pessoa.celular} </j1></td>
                                            <td><j1> ${d.pessoa.login.email} </j1></td>
                                       <!-- <td> ${d.endereco.logradouro} </td>
                                            <td> ${d.endereco.numero} </td>
                                            <td> ${d.endereco.bairro} </td>
                                            <td> ${d.endereco.cidade} </td>
                                            <td> ${d.endereco.uf} </td> -->
                                            <td class = "actions">
                                                <a class="btn btn-success btn-xs" href="Dentista?btnAcionar=ListarPorID&id=${d.pessoa.pessoaID}">Visualizar</a>
                                                <a class="btn btn-info btn-xs" href="<%=request.getContextPath()%>/Indisponibilidade?btnAcionar=Cadastrar">Nova Indisponibilidade</a>
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
