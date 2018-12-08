<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Atendimento</title>

        <script src="content/bootstrap/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                var total = 0.0;

                (function ($) {
                    remove = function (item, preco) {
                        var tr = $(item).closest('tr');
                        tr.fadeOut(400, function () {
                            tr.remove();
                        });
                        total -= preco;
                        $("#total").val(total.toFixed(2));
                        return false;
                    }
                })(jQuery);

                function somarPrecos() {
                    var somaTotal = 0.0;
                    var coluna = document.querySelectorAll('#tbody');
                    somaTotal += coluna;
                    $("#total").val(somaTotal.toFixed(2));
                }

                function calcularTotal() {
                    var coluna = document.querySelectorAll('#tbody');
                    var numColunas = coluna.length;

                    for (let i = 0; i < numColunas; i++) {
                        $("#total").val(numColunas.toFixed(2));
                    }
                }


                $("#desconto").focusout(aplicarDesconto);

                function aplicarDesconto() {
                    var desconto = $("#total").val() - $("#desconto").val();
                    $("#total").val(desconto.toFixed(2));
                }

                $("#procedimento").change(function () {
                    var idprocedimento = $("#procedimento option:selected").val();


                    $.ajax({
                        method: "GET",
                        url: "Procedimentos",
                        data: {
                            btnAcionar: 'ListarPorIDGson',
                            id: idprocedimento
                        },
                        success: function (meujson) {
                            //vai voltar um json. for js para append na tabela html
                            //alert(meujson);
                            var meuObj = $.parseJSON(meujson);


                            var tbody = $("#tbody");
                            var linha = "<tr id=\"json\">"
                                    + "<td><j1>" + meuObj.procedimentoID + ""
                                    + "<input hidden type='text' name='servicos' value='" + meuObj.procedimentoID + "'/></j1></td>"
                                    + "<td><j1>" + meuObj.descricao + "</j1></td>"
                                    + "<td><j1>" + meuObj.preco + "</j1></td>"
                                    + "<td><j1> <button type='button' onclick='remove(this," + meuObj.preco + ")'> Excluir </button></j1> </td>"
                                    + "</tr>";

                            tbody.append(linha);


                            total += meuObj.preco;
                            $("#total").val(total.toFixed(2));

                        },
                        error: function (erro) {
                            console.log(erro);
                        }

                    });

                });
            });
        </script>

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
                                <p class="well lead text-center">Dados do Atendimento</p>
                                
                                <div class="container">                                    
                                    <form class="form-horizontal" action="<%=request.getContextPath()%>/Atendimento" method="post">
                                        
                                        <div class="col-sm-12 contact-form">
                                            <input type="hidden" name="idAgendamento" value="${agenda.agendamentoID}"/>
                                            <input type="hidden" name="idDentista" value="${agenda.dentista.dentistaID}"/>
                                            <input type="hidden" name="idPaciente" value="${agenda.paciente.pacienteID}"/>										
                                            
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="col-md-0 control-label"> <J1>Dentista</J1> </label>                    
                                                    <input readonly="" placeholder="Dentista"  class="form-control input-md" type="text" name="dentista" value="${agenda.dentista.pessoa.nome}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="col-md-0 control-label"> <J1>Paciente</J1> </label>                    
                                                    <input readonly="" placeholder="Paciente"  class="form-control input-md" type="text" name="paciente" value="${agenda.paciente.pessoa.nome}"/>
                                                </div>
                                            </div>
                                                
                                            <div class="form-group">
                                                <div class="col-md-3">
                                                    <label class="col-md-0 control-label"> <j1>Data do Atendimento</j1> </label>
                                                    <input readonly="" placeholder="00/00/0000" maxlength="8" OnKeyPress="formatar('##/##/####', this)" class ="form-control input-md" required type="date" name="dataProntuario" value="${agenda.dtagendamento}"/>
                                                </div>
                                                <div class="col-md-3">
                                                    <label class="col-md-0 control-label"> <j1>Horario de Atendimento</j1> </label>
                                                    <input readonly="" placeholder="00:00"  maxlength="5" OnKeyPress="formatar('##:##', this)"  class="form-control input-md" required type="text" name="horaProntuario" value="${agenda.hragendamento}"/>
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
                                                    <label class="col-xs-0 control-label"><J1>Observações do Atendimento</j1> </label>                    
                                                    <textarea class="form-control" maxlength="8800" type="text" name="observacoes" id="observacoes"></textarea>
                                                </div>                                                
                                            </div>
                                            <div class="col-md-3">
                                                <label class="col-md-0 control-label"> <j1>Total</j1> </label>
                                                <input readonly="" maxlength="5" class="form-control input-md" onKeyPress="return(MascaraMoeda(this, '', '.', event))" type="text" name="total" id="total"/>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="col-md-0 control-label"> <j1>Desconto</j1> </label>
                                                <input maxlength="5" class="form-control input-md" onKeyPress="return(MascaraMoeda(this, '', '.', event))" type="text" name="desconto" id="desconto"/>
                                            </div>
                                        </div>  
                                                
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                        <label class="col-xs-0 control-label"><J1>Procedimentos</j1> </label>                  
                                                        <select class="form-control" id="procedimento" name="procedimento">
                                                            <c:forEach items="${procedimento}" var="p">
                                                                <option value="${p.procedimentoID.toString()}"> ${p.descricao} </option>
                                                            </c:forEach>
                                                        </select>
                                                </div>
                                            </div>
                                            
                                            <div class="table table-bordere">
                                                <table class="col-md-6" class="table table-hover table-striped " cellspacing="1" cellpadding="1" id="resultadoDoAjax">
                                                    <thead>
                                                        <tr>
                                                            <th><j1>ID</j1></th>
                                                            <th><j1>Descricao</j1></th>
                                                            <th><j1>Preco</j1></th>
                                                            <th><j1>Ação</j1></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="tbody">

                                                    </tbody>
                                                </table>
                                            </div>    
                                                
                                                
                                            <div class="form-group">
                                          
                                        </div>               
                                        <div class="form-group">
                                            <input class="btn btn-primary btn-xs" type="submit" name="btnAcionar" value="Cadastrar"/>
                                            <input class="btn btn-danger btn-xs" type="reset"  name="Limpar" value="Limpar">
                                            <a class="btn btn-warning btn-xs" href="<%=request.getContextPath()%>/Atendimento?btnAcionar=listarDentistasCombobox">Listar</a>
                                        </div>
                                </div>
                            </div>
                            </form>
                            <script>
                                $("#menu").load("<%=request.getContextPath()%>/menu.jsp");
                            </script>

                            </body>
                            </html>
