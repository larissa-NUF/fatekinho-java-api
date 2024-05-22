

$(document).ready(function(){

    iniciar_linhas();
    $('.entrar').show();
    $('#btn_entrar').hide();
    $("#txt_vitoria").text("Ganhou!!")
    getIDinfo();

    setTimeout(function() {
        // Esconde a tela de loading
        $('#btn_entrar').css("visibility","visible");
        $('#btn_entrar').show();
        $('.entrar_container').hide();
      }, 5000);

    $('#bet_valor').text(0)
    
    var larguraTela = $(window).width();
    var alturaTela = $(window).height();
    

    console.log("Largura da tela: " + larguraTela);
    console.log("Altura da tela: " + alturaTela);

    $('#btn_entrar').click(function(){
        $('.entrar').hide();
        background_music();
    });

    $("#girar").click(function(){
        desativar_botao()
        if($('#bet_valor').text()===0){
            alert("entre uma valor de aposta!")
            ativar_botao();
        }else{
            aposta = $('#bet_valor').text()
            saldo = parseFloat($('#bet_saldo').text())
            if(aposta>saldo){
                alert("Saldo insuficiente!")
                ativar_botao();
            }else{
                $('#bet_saldo').text(saldo-aposta)
                girarTodasAsColunas();
            }
            
        }
        

    });
    $('#bet_1').click(function(){
        $("#bet_saldo").text(getCoins(usuario));
        inserir_moedas_sound();
        $('#bet_valor').text(1);
        $('.ativo').removeClass("ativo"); // Remove a classe 'ativo' de todos os elementos
        $(this).addClass("ativo"); // Adiciona a classe 'ativo' apenas ao elemento clicado
    });
    
    $('#bet_10').click(function(){
        $("#bet_saldo").text(getCoins(usuario));
        inserir_moedas_sound();
        $('#bet_valor').text(10);
        $('.ativo').removeClass("ativo");
        $(this).addClass("ativo");
    });
    
    $('#bet_100').click(function(){
        $("#bet_saldo").text(getCoins(usuario));
        inserir_moedas_sound();
        $('#bet_valor').text(100);
        $('.ativo').removeClass("ativo");
        $(this).addClass("ativo");
    });
    
    $('#bet_all').click(function(){
        $("#bet_saldo").text(getCoins(usuario));
        inserir_moedas_sound();
        $('#bet_valor').text(parseFloat($('#bet_saldo').text()));
        $('.ativo').removeClass("ativo");
        $(this).addClass("ativo");
    });
    
    $(window).on('scroll', function() {
        $('.card').each(function() {
            if (isScrolledIntoView($(this))) {
                var cartaClicada = $(this).find('img').attr('src'); // Pegue a imagem da carta que caiu
                console.log('Carta que caiu:', cartaClicada);
            }
        });
    });
});

window.onerror = function(e){
    alert(e.toString())
}

function iniciar_linhas(){
    var $coluna =  $(".column.zero"),
        linha = '',
        cardEscolhida = 0;
    for(var x = 0; x < 150; x++){
        cardEscolhida = Math.floor(Math.random() * 3);
        linha = "<div class='column0 card pos"+x+" img"+cardEscolhida+"'><img src='../imgs/"+cardEscolhida+".png'><\/div>";
        $coluna.append(linha);
    }

    var $coluna =  $(".column.um"),
        linha = '';
    for(var x = 0; x < 150; x++){
        cardEscolhida = Math.floor(Math.random() * 3);
        linha = "<div class='column1 card pos"+x+" img"+cardEscolhida+"'><img src='../imgs/"+cardEscolhida+".png'><\/div>";
        $coluna.append(linha);
    }

    var $coluna =  $(".column.dois"),
    linha = '';

    for(var x = 0; x < 150; x++){
        cardEscolhida = Math.floor(Math.random() * 3);
        linha = "<div class='column2 card pos"+x+" img"+cardEscolhida+"'><img src='../imgs/"+cardEscolhida+".png'><\/div>";
        $coluna.append(linha);
    }

    var $coluna =  $(".column.tres"),
    linha = '';
    for(var x = 0; x < 150; x++){
        cardEscolhida = Math.floor(Math.random() * 3);
        linha = "<div class='column3 card pos"+x+" img"+cardEscolhida+"'><img src='../imgs/"+cardEscolhida+".png'><\/div>";
        $coluna.append(linha);
    }

    var $coluna =  $(".column.quatro");
    linha = '';
    for(var x = 0; x < 150; x++){
        cardEscolhida = Math.floor(Math.random() * 3);
        linha = "<div class='column4 card pos"+x+" img"+cardEscolhida+"'><img src='../imgs/"+cardEscolhida+".png'><\/div>";
        $coluna.append(linha);
    }
}

function girarTodasAsColunas() {
    var audio = new Audio('../sound_effect/girando.wav');
    var $PopUp = $(".popup .popuptext")
    var $linha1 = $(".linha1")
    var $linha2 = $(".linha2")
    var $linha3 = $(".linha3")
    var $vlinha1 = $(".vlinha1")
    var $vlinha2 = $(".vlinha2")
    var $vlinha3 = $(".vlinha3")
    var $vlinha4 = $(".vlinha4")
    var $vlinha5 = $(".vlinha5")
    var $linha4_1 = $(".linha4_part1")
    var $linha4_2 = $(".linha4_part2")
    var $linha5_1 = $(".linha5_part1")
    var $linha5_2 = $(".linha5_part2")

    $linha1.css("visibility","hidden")
    $linha2.css("visibility","hidden")
    $linha3.css("visibility","hidden")
    $vlinha1.css("visibility","hidden")
    $vlinha2.css("visibility","hidden")
    $vlinha3.css("visibility","hidden")
    $vlinha4.css("visibility","hidden")
    $vlinha5.css("visibility","hidden")
    $linha4_1.css("visibility","hidden")
    $linha4_2.css("visibility","hidden")
    $linha5_1.css("visibility","hidden")
    $linha5_2.css("visibility","hidden")
    $PopUp.css("visibility","hidden")

    var colunas = $(".column");
    var delayBase = 100; // Ajuste o atraso base conforme necessário
    var delayIncremental = 200; // Ajuste o incremento do atraso conforme necessário
    var delay = 0
    var cartasCaidas = [[], [], [], [], []];
    var cardIndex = 0

    audio.play()
    colunas.each(function(index) {
        var $coluna = $(this);
        delay = delayBase + (index * delayIncremental);
        cardIndex = Math.floor(Math.random() * (140 - 70 + 1)) + 70;
        var caiu = $coluna.find('.card.pos' + (cardIndex)),
            caiu2 = $coluna.find('.card.pos' + (cardIndex+1)),
            caiu3 = $coluna.find('.card.pos' + (cardIndex+2))
    
        if (caiu.length > 0) {
            var classes1 = caiu.attr('class').split(' '),
                classes2 = caiu2.attr('class').split(' '),
                classes3 = caiu3.attr('class').split(' ');
            var terceiraClasse1 = get_card(classes1[3]),
                terceiraClasse2 = get_card(classes2[3]),
                terceiraClasse3 = get_card(classes3[3]);

            // alert("numero escolhido: "+cardIndex+"");
            girarColuna($coluna, delay, cardIndex);
            cartasCaidas[index].push(terceiraClasse1),
            cartasCaidas[index].push(terceiraClasse2),
            cartasCaidas[index].push(terceiraClasse3);
        } else {
            console.log("Elemento '.card.pos" + cardIndex + "' não encontrado.");
        }
        //alert("numero escolhido: "+cardIndex+"")
    });
    
    

    // transformar um vetor 1,5 em uma matrix 3x5 porem respeitando a order da criação dos slots

    // resultado: criar 3 vetores 1,5

    
    

    setTimeout(function(){
        console.log("linha 1: "+cartasCaidas[0])
        console.log("linha 2: "+cartasCaidas[1])
        console.log("linha 3: "+cartasCaidas[2])
        switch (true) {
            case (
                cartasCaidas[0][0] === cartasCaidas[0][1] && // ganhou com todos iguais na linha 1
                cartasCaidas[0][0] === cartasCaidas[0][2] &&
                cartasCaidas[0][0] === cartasCaidas[0][3] &&
                cartasCaidas[0][0] === cartasCaidas[0][4]
            ):
                $linha1.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[1][0] === cartasCaidas[1][1] && // ganhou com todos iguais na linha 2
                cartasCaidas[1][0] === cartasCaidas[1][2] &&
                cartasCaidas[1][0] === cartasCaidas[1][3] &&
                cartasCaidas[1][0] === cartasCaidas[1][4]
            ):
                $linha2.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[2][0] === cartasCaidas[2][1] && // ganhou com todos iguais na linha 3
                cartasCaidas[2][0] === cartasCaidas[2][2] &&
                cartasCaidas[2][0] === cartasCaidas[2][3] &&
                cartasCaidas[2][0] === cartasCaidas[2][4]
            ):
                $linha3.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[0][0] === cartasCaidas[0][1] && // ganhou com todos iguais na coluna 1
                cartasCaidas[0][0] === cartasCaidas[0][2]
            ):
                $vlinha1.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[1][0] === cartasCaidas[1][1] && // ganhou com todos iguais na coluna 2
                cartasCaidas[1][0] === cartasCaidas[1][2]
            ):
                $vlinha2.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[2][0] === cartasCaidas[2][1] && // ganhou com todos iguais na coluna 3
                cartasCaidas[2][0] === cartasCaidas[2][2]
            ):
                $vlinha3.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[3][0] === cartasCaidas[3][1] && // ganhou com todos iguais na coluna 4
                cartasCaidas[3][0] === cartasCaidas[3][2]
            ):
                $vlinha4.css("visibility", "visible");
                ganhou();
                break;

            case (
                cartasCaidas[4][0] === cartasCaidas[4][1] && // ganhou com todos iguais na coluna 5
                cartasCaidas[4][0] === cartasCaidas[4][2]
            ):
                $vlinha5.css("visibility", "visible");
                ganhou();
                break;

            default:
                break;
        }

        console.log(cartasCaidas)
        ativar_botao();
    },6900)

    

    

}

function girarColuna($coluna, delay, cardIndex) {
    var card_altura = $coluna.find('.card').outerHeight();
    var card_position = cardIndex * card_altura;
    var currentPosition = $coluna.scrollTop();
    var distancia_ate_card = (card_position - currentPosition);

    var object = {
		x: Math.floor(Math.random() * 50) / 100,
        y: Math.floor(Math.random() * 20) / 100
	};
    try {
        $coluna.css({
            'transition-timing-function': 'cubic-bezier(0,' + object.x + ',' + object.y + ',1)',
            'transition-duration': '0s', // Define a duração da transição como 0 para reiniciar
            'transform': 'translate3d(0px, 0px, 0px)' // Retorna à posição inicial
        });

        // Forçar um novo cálculo de layout para garantir que a reinicialização ocorra
        $coluna[0].offsetHeight; // Isso força o navegador a recalcular o layout

        // Agora, aplique a animação com o atraso especificado
        setTimeout(function() {
            $coluna.css({
                'transition-timing-function': 'cubic-bezier(0,' + object.x + ',' + object.y + ',1)',
                'transition-duration': '6s',
                'transform': 'translate3d(0px, -' + distancia_ate_card + 'px, 0px)'
            });
        }, delay);

    } catch (err) {
        alert(err.message);
    }
}

function isScrolledIntoView(elem) {
    var docViewTop = $(window).scrollTop();
    var docViewBottom = docViewTop + $(window).height();
    var elemTop = $(elem).offset().top;
    var elemBottom = elemTop + $(elem).height();
    return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
}
function get_card(card_number) {
    if(card_number==='img0'){
        return 'seven';
    }else if(card_number==='img1'){
        return  'bonus';
    }else if(card_number==='img2'){
        return  'cherry';
    }else if(card_number==='img3'){
        return  'lemon';
    }else if(card_number==='img4'){
        return  'diamond';
    }
}

function ativar_botao(){
    $("#girar").prop('disabled', false);
    $("#bet_1").prop('disabled', false);
    $("#bet_10").prop('disabled', false);
    $("#bet_100").prop('disabled', false);
    $("#bet_all").prop('disabled', false);
}

function desativar_botao(){
    $("#girar").prop('disabled', true);
    $("#bet_1").prop('disabled', true);
    $("#bet_10").prop('disabled', true);
    $("#bet_100").prop('disabled', true);
    $("#bet_all").prop('disabled', true);
}

function inserir_moedas_sound(){
    var audio = new Audio('../sound_effect/insert_coin.wav');
    audio.play();
}

function ganhou(){
    var $PopUp = $(".popup .popuptext");
    var audio = new Audio('../sound_effect/ganhou.mp3');
    $("#txt_vitoria").val("Ganhou!!")
    $PopUp.css("visibility","visible");
    audio.play();
}

function background_music(){
    var audio = $("#audio-loop")[0]; // [0] para acessar o elemento DOM diretamente
    audio.play();
}

function getIDinfo(){
    const usuario = localStorage.getItem("iduser");
    console.log("usuario: "+usuario)
    $("#bet_saldo").text(getCoins(usuario));
    
}

function getCoins(user) {
    const url = `http://localhost:8080/fatecoins/get/cliente/${user}`;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro na requisição: ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        // Manipula os dados recebidos da API
        console.log('Dados da API:', data);
        // Faça algo com os dados, como atualizar o saldo na interface do usuário
        return data.qtd
    })
    .catch(error => {
        // Trata erros ocorridos durante o request
        console.error('Erro:', error);
        // Aqui você pode lidar com o erro de acordo com sua lógica de aplicativo
    });
}

function updateCoins(novoSaldo) {
    const id = localStorage.getItem("iduser");
    const url = `http://localhost:8080/fatecoins/update/${id}`;

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ qtd: novoSaldo }) // Aqui você pode ajustar o corpo da requisição conforme necessário
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro na requisição: ' + response.statusText);
        }
        console.log('Saldo atualizado com sucesso!');
        // Aqui você pode lidar com a resposta conforme necessário
    })
    .catch(error => {
        // Trata erros ocorridos durante o request
        console.error('Erro:', error);
        // Aqui você pode lidar com o erro de acordo com sua lógica de aplicativo
    });
}

// Chame a função passando o ID do cliente desejado e o novo saldo
updateCoins(idDoCliente, novoSaldo);
