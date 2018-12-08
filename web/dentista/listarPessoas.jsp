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
        <title>Listar Atendentes</title>
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
                            <p class="well lead">Lista de Atendentes</p>
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
                                        <a href="<%=request.getContextPath()%>/dentista/cadastrarPessoa.jsp" class="btn btn-primary pull-right h2">Novo Atendente</a>
                                    </div>
                                </div>
                                <div class="table table-bordered">
                                    <table class="table table-hover table-striped" cellspacing="1" cellpadding="1">
                                        <thead>
                                            <tr>
                                                <!--     <th>ID</th>-->
                                                <th><j1>Nome</j1></th>
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
                                            <c:forEach items="${pessoas}" var="p">
                                                <tr>
                                                <!--<td> ${p.pessoaID} </td> -->
                                                    <td><j1> ${p.nome}</j1> </td>
                                                <!--<td> ${p.rg} </td>
                                                    <td> ${p.cpf} </td>
                                                    
                                                    <td> <fmt:formatDate value="${p.dataNascimento}" pattern="dd/MM/yyyy" /> </td> -->
                                            <td> <j1>${p.telefone}</j1> </td>
                                            <td> <j1>${p.celular}</j1> </td>
                                            <td> <j1>${p.login.email}</j1> </td>
                                                           <!-- <td> ${p.endereco.logradouro} </td>
                                                            <td> ${p.endereco.numero} </td>
                                                            <td> ${p.endereco.bairro} </td>
                                                            <td> ${p.endereco.cidade} </td>
                                                            <td> ${p.endereco.uf} </td> -->
                                            <td class = "actions">
                                                <a class="btn btn-success btn-xs" href="Pessoa?btnAcionar=ListarPorID&id=${p.pessoaID}">Visualizar</a>
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
