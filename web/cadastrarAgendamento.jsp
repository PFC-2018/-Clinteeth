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
        <title>Cadastro de Agendamento</title>		
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
                                <p class="well lead text-center">Dados do Agendamento</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Agenda" method="post">
                                        <div class="col-sm-12 contact-form">
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="col-md-0 control-label"> <J1>Dentista</J1> </label>                    
                                                    <select name="dentista" class="form-control">
                                                        <c:forEach items="${dentista}" var="d">
                                                            <option value="${d.dentistaID.toString()}"> ${d.pessoa.nome} </option>
                                                        </c:forEach>
                                                    </select>                                                     
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="col-md-0 control-label"> <J1>Paciente</J1> </label>                    
                                                    <select name="paciente" class="form-control">
                                                        <c:forEach items="${paciente}" var="p">
                                                            <option value="${p.pacienteID.toString()}"> ${p.pessoa.nome} </option>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <label class="col-xs-0 control-label"> <j1>Data Consulta</j1> </label>
                                                    <input placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dtagendamento"/>
                                                </div>
                                                <div class="col-md-3">
                                                    <label class="col-xs-0 control-label"> <j1>Horario</j1> </label>
                                                    <input placeholder="00:00"  maxlength="5" OnKeyPress="formatar('##:##', this)"  class="form-control input-md" required type="text" name="hora"/>
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
                                                    <input placeholder="Descricao da Consulta" maxlength="40" class="form-control input-md" type="text" name="titulo"/>
                                                </div>                                              
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-5">
                                                    <label class="col-xs-0 control-label"><J1>Observações do Agendamento</j1> </label>                    
                                                    <textarea class="form-control" maxlength="400" type="text" name="obsagendamento" id="obsagendamento"></textarea>
                                                </div>                                                
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input class="btn btn-primary btn-xs" type="submit" name="btnAcionar" value="Cadastrar"/>
                                            <input class="btn btn-danger btn-xs" type="reset"  name="Limpar" value="Limpar">
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

