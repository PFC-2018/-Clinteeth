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
        <title>Cadastro de Disponibilidade</title>		
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
                                <p class="well lead">Dados da Disponibilidade</p>
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Disponibilidade" method="post">
                                        <div class="col-sm-12 contact-form"> 
                                            <div class="form-group">
                                                <div class="col-md-6">                
                                                    <input type="hidden" name="idagendamento" value="${disponibilidade.disponibilidadeID}"/>	
                                                    <div class="col-md-6">
                                                            <label class="col-md-1 control-label"> <J1>Dentista</J1> </label>                    
                                                            <input readonly="" placeholder="Dentista"  class="form-control input-md" type="text" name="dentista" value="${disponibilidade.dentista.pessoa.nome}"/>
                                                        </div>                                                  
                                                </div>
                                            </div>
                                            <div class="form-group">                                                      
                                                <label class="col-xs-1 control-label"> <J1>Data Disponivel</J1> </label>              
                                                <div class="col-md-3">
                                                    <input class ="form-control input-md" required type="text" name="dataindisponivel" value="${disponibilidade.dtdisponivel}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">                                                                                                                                                   
                                                <label class="col-xs-1 control-label"> <j1>Horario</j1> </label>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="SETEMEIA" checked=""/>07:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="OITO" checked=""/>08:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="OITOMEIA" checked=""/>08:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="NOVE" checked=""/>09:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="NOVEMEIA" checked=""/>09:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="DEZ" checked=""/>10:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="DEZMEIA" checked=""/>10:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="ONZE" checked=""/>11:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="ONZEMEIA" checked=""/>11:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="MEIODIA" checked=""/>12:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="MEIODIAMEIO" checked=""/>12:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="UMA" checked=""/>13:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="UMAMEIA" checked=""/>13:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="DUAS" checked=""/>14:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="DUASMEIA" checked=""/>14:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="TRES" checked=""/>15:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="TRESMEIA" checked=""/>15:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="QUATRO" checked=""/>16:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="QUATROMEIA" checked=""/>16:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="CINCO" checked=""/>17:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="CINCOMEIA" checked=""/>17:30</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="SEIS" checked=""/>18:00</j1>
                                                <j1><input class="form-check-input" type="checkbox" name="hora" value="SEISMEIA" checked=""/>18:30</j1>         
                                       
                                                </label>                                                
                                            </div>
                                            <%
                                                String dataOcupada = (String) request.getAttribute("dataOcupada");
                                                if (dataOcupada != null) {
                                            %>
                                            <div class="alert alert-danger" id="dataOcupada">
                                                <font color="red"><j1><%=dataOcupada%></j1></font>
                                            </div>
                                            <%}%>  
                                        </div>               
                                        <div class="form-group">
                                            <input class="btn btn-primary btn-xs" type="submit" name="btnAcionar" value="Cadastrar"/>
                                            <input class="btn btn-danger btn-xs" type="reset"  name="Limpar" value="Limpar">
                                            <a class="btn btn-warning btn-xs" href="<%=request.getContextPath()%>/Disponibilidade?btnAcionar=listarDentistasCombobox">Listar</a>
                                        </div>
                                </div>
                            </div>
                            </form>
                            <script>
                                    $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                            </script>
                            </body>
                            </html>

