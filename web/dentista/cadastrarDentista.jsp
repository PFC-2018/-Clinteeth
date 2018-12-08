<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="clinteeth.model.PessoasEnum"%>
<%@page import="clinteeth.model.EstadosEnum"%>
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
        <title>Cadastro de Dentista</title>		
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
                                <p class="well lead">Cadastro de Dentista</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Dentista" method="post">
                                        <div class="col-sm-12 contact-form"> 
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"> <J1>Nome</J1> </label>                    
                                                <div class="col-md-9">
                                                    <input placeholder="Nome Completo"  class="form-control input-md" required type="text" name="nome"/>
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"><j1>RG</j1></label>                 
                                                <div class="col-md-2">
                                                    <input placeholder="xx.xxx.xxx-x"  maxlength="12" OnKeyPress="formatar('##.###.###-#', this)" title="Digite o RG somente com números" class="form-control input-md" required type="text" name="rg"/>
                                                </div>
                                                <label class="col-xs-1 control-label"> <j1>CPF</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xxx.xxx.xxx-xx"  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" title="Digite o CPF somente com números" class="form-control input-md" required type="text" name="cpf"/>
                                                </div>
                                                <label class="col-xs-1 control-label"> <j1>Data Nasc.</j1> </label>
                                                <div class="col-md-3">
                                                    <input placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dataNascimento"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-1 control-label"> <J1>Telefone</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xx-xxxx-xxxx" maxlength="12" OnKeyPress="formatar('##-####-####', this)" class="form-control input-md" required type="text" name="telefone"/>
                                                </div>
                                                <label class="col-md-1 control-label"><J1>Celular</J1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xx-xxxxx-xxxx" maxlength="13" OnKeyPress="formatar('##-#####-####', this)" class="form-control input-md" required type="text" name="celular"/>
                                                </div>
                                                <input name="tipo" id="tipo" hidden value="DENTISTA">
                                                <label class="col-md-1 control-label"> <J1>CRO</J1> </label>                    
                                                <div class="col-md-3">
                                                    <input placeholder="CRO" class="form-control input-md" required type="text" name="cro"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"><J1>E-mail</j1> </label>                    
                                                <div class="col-md-5">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="email" id="email"/>
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>Senha</J1> </label>
                                                <div class="col-md-3">
                                                    <input placeholder="" class="form-control input-md" required type="password" name="senha" id="senha"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"><J1>Endereco</j1> </label>
                                                <div class="col-md-5">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="logradouro">                            
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>Número</j1> </label>                        
                                                <div class="col-md-3">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="numero">
                                                </div>
                                            </div>                
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"> <J1> Complem. </j1> </label>
                                                <div class="col-md-5">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="complemento">
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>Bairro</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="bairro">
                                                </div>

                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"> <J1>Cidade</j1> </label>                    
                                                <div class="col-md-4">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="cidade">
                                                </div>                                                
                                                <label class="col-xs-1 control-label"> <J1>CEP</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xxxxx-xxx" maxlength="9" OnKeyPress="formatar('#####-###', this)" class="form-control input-md" required type="text" name="cep">
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>UF</j1> </label> 
                                                <div class="col-md-1">                    
                                                    <select name="uf" class="form-control">
                                                        <c:forEach items="${EstadosEnum.values()}" var="uf">
                                                            <option value="${uf.toString()}"> ${uf.name()} </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input class="btn btn-primary btn-xs" type="submit" name="btnAcionar" value="Cadastrar"/>
                                                <input class="btn btn-danger btn-xs" type="reset"  name="Limpar" value="Limpar">
                                                <a class="btn btn-warning btn-xs" href="<%=request.getContextPath()%>/Dentista?btnAcionar=ListarTudo">Listar</a>
                                            </div>
                                        </div>
                                </div>
                                </form>
                                <script>
                                    $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                                </script>
                                </body>
                                </html>

