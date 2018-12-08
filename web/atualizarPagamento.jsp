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
                                <p class="well lead text-center">Dados do Pagamento</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Pagamento" method="post">
                                        <div class="col-sm-12 contact-form">
                                            <input type="hidden" name="idPagamento" value="${pagamento.pagamentoID}"/>
                                            
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="col-md-0 control-label"> <J1>Dentista</J1> </label>                    
                                                    <input readonly="" placeholder="Dentista"  class="form-control input-md" type="text" name="dentista" value="${pagamento.dentista.pessoa.nome}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="col-md-0 control-label"> <J1>Paciente</J1> </label>                    
                                                    <input readonly="" placeholder="Paciente"  class="form-control input-md" type="text" name="paciente" value="${pagamento.paciente.pessoa.nome}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <label class="col-md-0 control-label"> <j1>Data do Atendimento</j1> </label>
                                                    <input readonly="" placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="data" value="${pagamento.atendimento.agendamento.dtagendamento}"/>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="col-md-1 control-label"> <J1>Valor</j1> </label>
                                                    <input readonly="" maxlength="10" class="form-control input-md" onKeyPress="return(MascaraMoeda(this, '', '.', event))" type="text" name="valor" value="${pagamento.valor}"/>
                                                </div>
                                                
                                            </div>    
                                                <%
                                                    String verificaValor = (String) request.getAttribute("verificaValor");
                                                    if (verificaValor != null) {
                                                %>
                                                <div class="alert alert-danger" id="falhaLogin">
                                                    <font color="red"><j1><%=verificaValor%></j1></font>
                                                </div>
                                                <%}%>
                                                                                          
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <label class="col-md-0 control-label"> <j1>Data do Pagamento</j1> </label>
                                                    <input placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dtpagamento" value="${pagamento.dtpagamento}"/>
                                                </div>
                                                
                                                <div class="col-md-2">
                                                    <label class="col-md-0 control-label"> <J1>Valor Pago</j1> </label>
                                                    <input maxlength="10" class="form-control input-md" onKeyPress="return(MascaraMoeda(this, '', '.', event))" type="text" name="valorPago" value="${pagamento.valorpago}"/>
                                                </div>
                                            </div>                                                                                        
                                        </div>
                                                                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input class="btn btn-primary btn-group-lg" type="submit" name="btnAcionar" value="Alterar"/>
                                                <a class="btn btn-warning btn-group-lg" href="<%=request.getContextPath()%>/Pagamento?btnAcionar=listarPacientesCombobox">Listar</a>
                                            </div>
                                        </div>
                                    </form>
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

