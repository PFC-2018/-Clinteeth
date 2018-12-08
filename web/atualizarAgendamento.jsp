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
        <title>Atualizar Agendamento</title>		
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
                                <p class="well lead text-center">Dados do Agendamento</p>
                                <di v class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Agenda" method="post">
                                        
                                        <div class="col-sm-12 contact-form"> 
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <input type="hidden" name="idagendamento" value="${agenda.agendamentoID}"/>	
                                                        <label class="col-md-0 control-label"> <J1>Dentista</J1> </label>                    
                                                        <select name="dentista" class="form-control" >
                                                            <c:forEach items="${dentista}" var="d">
                                                                <option value="${d.dentistaID.toString()}"> ${d.pessoa.nome}  </option>
                                                            </c:forEach>
                                                        </select> 
                                                        <label class="col-md-0 control-label"> <J1>Paciente</J1> </label>                    
                                                            <input readonly="" placeholder="Paciente"  class="form-control input-md" type="text" name="paciente" value="${agenda.paciente.pessoa.nome}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">                                                  
                                                <div class="col-md-3">
                                                <label class="col-xs-0 control-label"> <j1>Data Consulta</j1> </label>
                                                    <input placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dtagendamento" value="${agenda.dtagendamento}"/>
                                                </div>
                                                <div class="col-md-3">
                                                <label class="col-xs-1 control-label"> <j1>Horario</j1> </label>
                                                    <input placeholder="00:00"  maxlength="5" OnKeyPress="formatar('##:##', this)"  class="form-control input-md" required type="text" name="hora" value="${agenda.hragendamento}"/>
                                                </div>
                                                <%
                                                    String dataIndisponivel = (String) request.getAttribute("dataIndisponivel");
                                                    if (dataIndisponivel != null) {
                                                %>
                                                <div class="alert alert-danger" id="falhaLogin">
                                                    <font color="red"><j1><%=dataIndisponivel%></j1></font>
                                                </div>
                                                <%}%>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-5">
                                                    <label class="col-md-0 control-label"> <J1>Titulo</j1> </label>
                                                    <input placeholder="Descricao da Consulta" maxlength="40" class="form-control input-md" type="text" name="titulo" value="${agenda.titulo}"/>
                                                </div>                                              
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-5">
                                                    <label class="col-xs-0 control-label"><J1>Observações do Agendamento</j1> </label>                    
                                                    <textarea class="form-control" maxlength="400" type="text" name="obsagendamento" value="${agenda.observacao}"></textarea>
                                                </div>                                                
                                            </div>                                            
                                        </div>               
                                        <div class="form-group">
                                            <input class="btn btn-primary btn-xs" type="submit" name="btnAcionar" value="Alterar"/>
                                            <a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#modal1"> Excluir </a>                                            
                                            <!-- Tela de Seleção da Operação Exclusão/Cancelamento -->
                                            <div class="modal fade" id="modal1" tabindex="-1" role="form" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static">
                                                <div class="modal-dialog modal-dialog-centered modal-lg" role="form">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body text-center">
                                                            <j1>SELECIONE A OPERAÇÃO DESEJADA</j1>                                                                        
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#modal2" > Excluir </a>
                                                            <button class="btn btn-secondary btn-xs" data-dismiss="modal"> Cancelar </button>
                                                            <!-- Tela de Confirmação Exclusão -->
                                                            <div class="modal fade" id="modal2" tabindex="-1" role="form" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="position:relative; width:auto; margin-left:0;" data-backdrop="static">
                                                                <div class="modal-dialog modal-dialog-centered modal-sm" role="form">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title text-center" id="exampleModalLongTitle ">Confirmação de Exclusão do Agendamento</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body text-center">
                                                                            <j1>Deseja realmente excluir o agendamento selecionado?</j1>                                                                        
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button class="btn btn-secondary" data-dismiss="modal"> Não </button>
                                                                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/Agenda?btnAcionar=Excluir&id=${agenda.agendamentoID}"> Sim </a>                                           
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                                <a class="btn btn-warning btn-xs" href="<%=request.getContextPath()%>/Agenda?btnAcionar=listarDentistasCombobox">Listar</a>
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
                                                            <!--<a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#modal3"> Cancelar </a>
                                                            <a class="btn btn-primary" data-toggle="modal" data-target="#modal4" href="<%=request.getContextPath()%>/Motivo?btnAcionar=ListarCombobox"> Cancelar </a>
                                                            Tela de Confirmação Cancelamento
                                                            <div class="modal fade" id="modal3" tabindex="-1" role="form" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="position:relative; width:auto; margin-left:0;" data-backdrop="static">
                                                                <div class="modal-dialog modal-dialog-centered modal-sm" role="form">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLongTitle">Confirmação de Cancelamento do Agendamento</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <j1>Deseja realmente cancelar o agendamento selecionado?</j1>                                                                        
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button class="btn btn-secondary" data-dismiss="modal"> Não </button>
                                                                            <a class="btn btn-primary" data-toggle="modal" data-target="#modal4" href="<%=request.getContextPath()%>/Motivo?btnAcionar=ListarCombobox"> Sim </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- Tela de Inserção do Motivo Cancelamento 
                                                            <div class="modal fade" id="modal4" tabindex="-1" role="form" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static">
                                                                <div class="modal-dialog modal-dialog-centered modal-sm" role="form">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLongTitle">Confirmação de Cancelamento do Agendamento</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <j1>Selecione o motivo do cancelamento deste agendamento</j1>
                                                                            <select name="motivo" class="form-control" required>
                                                                                <option value="${motivo.id.toString()}"> ${motivo.descricao} </option>
                                                                            </select>                                                                                                                                                    
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button class="btn btn-secondary" data-dismiss="modal"> Desfazer </button>
                                                                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/Agenda?btnAcionar=Cancelar&id=${agenda.agendamentoID}"> Cancelar </a>                                           
                                                                        </div>
                                                                    </div> -->



