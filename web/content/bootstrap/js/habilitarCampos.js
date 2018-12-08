            /*Habilitar e Desabilitar campos a partir do tipo do acesso*/

 $(document).ready(function() {
  $('#tipo').change(function(){
   var tipo = $("#tipo option:selected").text();
   
    if (tipo == 'ATENDENTE') {
     $('#senha').attr('disabled', false);
     $('#cro').attr('disabled', true);
    }else if (tipo == 'DENTISTA') {
     $('#senha').attr('disabled', false);
     $('#cro').attr('disabled', false);
    }else if (tipo == 'PACIENTE') {
     $('#senha').attr('disabled', true);
     $('#cro').attr('disabled', true);
    }
    });
});
