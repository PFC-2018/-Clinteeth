
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>tela de teste de redirecionamento e filtragem de usuarios</title>
    </head>
    <body>
        <a href="Dentista?btnAcionar=ListarTudo">Listar Dentistas</a>
        <a href="Pessoa?btnAcionar=ListarTudo">Listar Pessoas</a>
        <a href="Paciente?btnAcionar=ListarTudo">Listar Pacientes</a>              
        <a href="<%=request.getContextPath()%>/dentista/cadastrarDisponibilidade.jsp">Disponibilidade Dentista</a>
    </body>
</html>
