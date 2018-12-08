<%@page import="clinteeth.model.Pessoa"%>
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
        <title>Atualizar Cadastro de Paciente</title>		
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
                                <p class="well lead">Atualizar Cadastro de Paciente</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Paciente" method="post">
                                        <div class="col-sm-12 contact-form">
                                            <input type="hidden" name="idPaciente" value="${paciente.pacienteID}"/>
                                            <input type="hidden" name="idPessoa" value="${paciente.pessoa.pessoaID}"/>										
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"> <J1>Nome</J1> </label>                    
                                                <div class="col-md-9">
                                                    <input placeholder="Nome Completo"  class="form-control input-md" required type="text" name="nome" value="${paciente.pessoa.nome}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"><j1>RG</j1></label>                 
                                                <div class="col-md-2">
                                                    <input placeholder="xx.xxx.xxx-x"  maxlength="12" OnKeyPress="formatar('##.###.###-#', this)" title="Digite o RG somente com números" class="form-control input-md" required type="text" name="rg" value="${paciente.pessoa.rg}"/>
                                                </div>
                                                <label class="col-xs-1 control-label"> <j1>CPF</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xxx.xxx.xxx-xx"  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" title="Digite o CPF somente com números" class="form-control input-md" required type="text" name="cpf"value="${paciente.pessoa.cpf}"/>
                                                </div>
                                                <label class="col-xs-1 control-label"> <j1>Data Nasc.</j1> </label>
                                                <div class="col-md-3">
                                                    <input placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dataNascimento" value="${paciente.pessoa.dataNascimento}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-1 control-label"> <J1>Telefone</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xx-xxxx-xxxx" maxlength="12" OnKeyPress="formatar('##-####-####', this)" class="form-control input-md" required type="text" name="telefone" value="${paciente.pessoa.telefone}"/>
                                                </div>
                                                <label class="col-md-1 control-label"><J1>Celular</J1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xx-xxxxx-xxxx" maxlength="13" OnKeyPress="formatar('##-#####-####', this)" class="form-control input-md" required type="text" name="celular" value="${paciente.pessoa.celular}"/>
                                                </div>
                                                <label class="col-md-1 control-label"><J1>Convenio</J1> </label>
                                                <div class="col-md-3">
                                                    <input class="form-control input-md" required type="text" name="convenio" value="${paciente.convenio}"/>
                                                </div>
                                                <input hidden type="text" name="tipo" value="${paciente.pessoa.login.tipo}"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"><J1>E-mail</j1> </label>                    
                                                <div class="col-md-5">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="email" id="email" value="${paciente.pessoa.login.email}"/>
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>Senha</J1> </label>
                                                <div class="col-md-3">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="senha" id="senha" value="${paciente.pessoa.login.senha}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input hidden name="idEndereco" value="${paciente.pessoa.endereco.enderecoID}">
                                                <label class="col-xs-1 control-label"><J1>Endereco</j1> </label>
                                                <div class="col-md-5">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="logradouro" value="${paciente.pessoa.endereco.logradouro}">                            
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>Número</j1> </label>                        
                                                <div class="col-md-3">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="numero" value="${paciente.pessoa.endereco.numero}">
                                                </div>
                                            </div>                
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"><J1>Complem.</j1></label>
                                                <div class="col-md-5">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="complemento" value="${paciente.pessoa.endereco.complemento}">
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>Bairro</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="bairro" value="${paciente.pessoa.endereco.bairro}">
                                                </div>

                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-1 control-label"> <J1>Cidade</j1> </label>                    
                                                <div class="col-md-4">
                                                    <input placeholder="" class="form-control input-md" required type="text" name="cidade" value="${paciente.pessoa.endereco.cidade}">
                                                </div>                                                
                                                <label class="col-xs-1 control-label"> <J1>CEP</j1> </label>
                                                <div class="col-md-2">
                                                    <input placeholder="xxxxx-xxx" maxlength="9" OnKeyPress="formatar('#####-###', this)" class="form-control input-md" required type="text" name="cep" value="${paciente.pessoa.endereco.cep}">
                                                </div>
                                                <label class="col-xs-1 control-label"> <J1>UF</j1> </label> 
                                                <div class="col-md-1">                    
                                                    <select name="uf" class="form-control">
                                                        <option> ${paciente.pessoa.endereco.uf}</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input class="btn btn-primary btn-xs" type="submit" name="btnAcionar" value="Alterar"/>
                                                <a class="btn btn-danger btn-xs"  href="<%=request.getContextPath()%>/Paciente?btnAcionar=Excluir&id=${paciente.pessoa.getPessoaID()}">Excluir</a>
                                            </div>
                                        </div>
                                </div>
                                </form>
                                <script>
                                    $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                                </script>
                                </body>
                                </html>

