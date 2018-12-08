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
        <title>Atualização de Atendimento</title>		
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
                                <p class="well lead text-center" >Dados do Atendimento</p>
                                <div class="container">
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Atendimento" method="post">
                                        <div class="col-sm-12 contact-form">
                                            <form action="<%=request.getContextPath()%>/Atendimento" method="post">
                                                
                                                <div class="col-sm-12 contact-form">
                                                    
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label class="col-md-1 control-label"> <J1>Dentista</J1> </label>                    
                                                            <input readonly="" placeholder="Dentista"  class="form-control input-md" type="text" name="dentista" value="${atendimento.dentista.pessoa.nome}"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label class="col-md-1 control-label"> <J1>Paciente</J1> </label>                    
                                                            <input readonly="" placeholder="Paciente"  class="form-control input-md" type="text" name="paciente" value="${atendimento.paciente.pessoa.nome}"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-md-3">
                                                            <label class="col-xs-9 control-label"> <j1>Data do Atendimento </j1> </label>
                                                            <input readonly="" placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dataAtendimento" value="${atendimento.agendamento.dtagendamento}"/>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="col-xs-1 control-label"> <j1>Horario</j1> </label>
                                                            <input readonly="" placeholder="00:00"  maxlength="5" OnKeyPress="formatar('##:##', this)"  class="form-control input-md" required type="text" name="horaAtendimento" value="${atendimento.agendamento.hragendamento}"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-md-3">
                                                            <label class="col-md-1 control-label"> <J1>Total</j1> </label>
                                                            <input readonly="" maxlength="10" class="form-control input-md" onKeyPress="return(MascaraMoeda(this, '', '.', event))" type="text" name="total" value="${atendimento.total}">
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="col-md-1 control-label"> <J1>Desconto</j1> </label>
                                                            <input readonly="" maxlength="10" class="form-control input-md" onKeyPress="return(MascaraMoeda(this, '', '.', event))" type="text" name="desconto" value="${atendimento.desconto}">
                                                        </div>
                                                    </div>  
                                                        <div class="form-group">
                                                            <label class="col-md-1 control-label"> <J1>Procedimentos</j1> </label>
                                                        </div>
                                                        
                                                        <div class="table table-bordere">
                                                            <table class="col-md-6" class="table table-hover table-striped " cellspacing="1" cellpadding="1">
                                                                <thead>
                                                                    <tr>
                                                                        <th> <j1> Descrição</jl></th>
                                                                        <th> <j1> Preço</jl></th>       
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach  var="linha" items="${procedimentos}">
                                                                        <tr>
                                                                            <td><j1> ${linha.descricao} </j1></td>
                                                                            <td><j1> ${linha.preco} </j1></td>
                                                                        </tr> 
                                                                    </c:forEach> 
                                                                </tbody>
                                                            </table>
                                                        </div>
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <a class="btn btn-warning btn-group-lg" href="<%=request.getContextPath()%>/Atendimento?btnAcionar=listarDentistasCombobox">Listar</a>
                                        </div>
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
        </div>
    </body>
</html>
    <script>
                            $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                                                            </script>
