
<div id="sidebar-wrapper">
    <ul id="sidebar_menu" class="sidebar-nav">                                                  
        <li class="sidebar-brand"><a id="menu-toggle" href="#">Menu<span id="main_icon" class="glyphicon glyphicon-th-list"></span></a></li>
    </ul>
    <ul class="sidebar-nav" id="sidebar">
        <ul class="sidebar-nav" id="sidebar">
            <li><a class="nav-link" href="#submenu1" data-toggle="collapse" data-target="#submenu1"><j1>Cadastrar</j1><span class="sub_icon glyphicon glyphicon-link"></span></a>
                <div class="collapse" id="submenu1" aria-expanded="false">
                    <ul class="flex-column pl-1 nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/atendente/cadastrarPaciente.jsp" title="Cadastrar um Paciente"><j1>Pacientes</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/dentista/cadastrarPessoa.jsp" title="Cadastrar um Atendente"><j1>Atendente</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/dentista/cadastrarDentista.jsp" title="Cadastrar um Dentista"><j1>Dentista</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/cadastrarProcedimento.jsp" title="Cadastrar Procedimento"><j1>Procedimento</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Disponibilidade?btnAcionar=Cadastrar" title="Cadastrar Disponibilidade"><j1>Disponibilidade</j1></a>
                        </li>                        
                    </ul>
                </div>
            <li><a class="nav-link" href="#submenu2" data-toggle="collapse" data-target="#submenu2"><j1>Agenda</j1><span class="sub_icon glyphicon glyphicon-link"></span></a>
                <div class="collapse" id="submenu2" aria-expanded="false">
                    <ul class="flex-column pl-1 nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/Agenda?btnAcionar=ListarTudo" title="Agendamentos"><j1>Agendamento</j1></a>
                            <!--<a class="nav-link" href="<%=request.getContextPath()%>/Disponibilidade?btnAcionar=listarDentistasCombobox" title="Datas Disponiveis"><j1>Disponibilidade</j1></a>-->
                        </li>                        
                    </ul>
                </div>
            <li><a class="nav-link" href="#submenu3" data-toggle="collapse" data-target="#submenu3"><j1>Consulta</j1><span class="sub_icon glyphicon glyphicon-link"></span></a>
                <div class="collapse" id="submenu3" aria-expanded="false">
                    <ul class="flex-column pl-1 nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/Paciente?btnAcionar=ListarTudo" title="Listar Pacientes"><j1>Pacientes</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Pessoa?btnAcionar=ListarTudo" title="Listar Atendentes"><j1>Atendente</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Dentista?btnAcionar=ListarTudo" title="Listar Dentistas"><j1>Dentista</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Disponibilidade?btnAcionar=ListarTudo" title="Listar Disponibilidade"><j1>Disponibilidade</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Atendimento?btnAcionar=listarDentistasCombobox" title="Atendimentos"><j1>Atendimento</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Pagamento?btnAcionar=listarPacientesCombobox" title="Pagamentos"><j1>Pagamento</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Procedimentos?btnAcionar=ListarTudo" title="Procedimentos"><j1>Procedimentos</j1></a>
                        </li>                        
                    </ul>
                </div>
                <li><a class="nav-link" href="#submenu4" data-toggle="collapse" data-target="#submenu4"><j1>Relatorio</j1><span class="sub_icon glyphicon glyphicon-link"></span></a>
                <div class="collapse" id="submenu4" aria-expanded="false">
                    <ul class="flex-column pl-1 nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/Pagamento?btnAcionar=RelatorioEntreDatas" title="Pagamento"><j1>Pagamento</j1></a>
                            <a class="nav-link" href="<%=request.getContextPath()%>/Agenda?btnAcionar=RelatorioAgendamento"><j1>Agendamento</j1></a> 
                        </li>                        
                    </ul>
                </div>
            <li><a class="nav-link" href="#submenu5" data-toggle="collapse" data-target="#submenu5"><j1>Sair</j1><span class="sub_icon glyphicon glyphicon-link"></span></a>
                <div class="collapse" id="submenu5" aria-expanded="false">
                    <ul class="flex-column pl-1 nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/Login?btnAcionar=Sair" title="Sair do Sistema"><j1>Logout</j1></a>
                        </li>                        
                    </ul>
                </div>
        </ul>
    </ul>
</div>

<script>
    $("#menu-toggle").click(function (e) {

        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
</script>