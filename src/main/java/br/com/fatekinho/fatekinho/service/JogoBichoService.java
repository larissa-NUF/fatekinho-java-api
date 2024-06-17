package br.com.fatekinho.fatekinho.service;

import br.com.fatekinho.fatekinho.model.*;
import br.com.fatekinho.fatekinho.repository.ApostasJogoBichoRepository;
import br.com.fatekinho.fatekinho.repository.FatecoinsRepository;
import br.com.fatekinho.fatekinho.repository.ResultadosJogoBichoRepository;
import br.com.fatekinho.fatekinho.service.interfaces.IJogoBichoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class JogoBichoService implements IJogoBichoService {
    @Autowired
    private ApostasJogoBichoRepository _apostasJogoBichoRepository;
    @Autowired
    private FatecoinsRepository _FatecoinsRepository;
    List<Bichos> listaBichos = List.of(
            new Bichos("Avestruz", 1, List.of("1", "2", "3", "4")),
            new Bichos("Águia", 2, List.of("5", "6", "7", "8")),
            new Bichos("Burro", 3, List.of("9", "10", "11", "12")),
            new Bichos("Borboleta", 4, List.of("13", "14", "15", "16")),
            new Bichos("Cachorro", 5, List.of("17", "18", "19", "20")),
            new Bichos("Cabra", 6, List.of("21", "22", "23", "24")),
            new Bichos("Carneiro", 7, List.of("25", "26", "27", "28")),
            new Bichos("Camelo", 8, List.of("29", "30", "31", "32")),
            new Bichos("Cobra", 9, List.of("33", "34", "35", "36")),
            new Bichos("Coelho", 10, List.of("37", "38", "39", "40")),
            new Bichos("Cavalo", 11, List.of("41", "42", "43", "44")),
            new Bichos("Elefante", 12, List.of("45", "46", "47", "48")),
            new Bichos("Galo", 13, List.of("49", "50", "51", "52")),
            new Bichos("Gato", 14, List.of("53", "54", "55", "56")),
            new Bichos("Jacaré", 15, List.of("57", "58", "59", "60")),
            new Bichos("Leão", 16, List.of("61", "62", "63", "64")),
            new Bichos("Macaco", 17, List.of("65", "66", "67", "68")),
            new Bichos("Porco", 18, List.of("69", "70", "71", "72")),
            new Bichos("Pavão", 19, List.of("73", "74", "75", "76")),
            new Bichos("Peru", 20, List.of("77", "78", "79", "80")),
            new Bichos("Touro", 21, List.of("81", "82", "83", "84")),
            new Bichos("Tigre", 22, List.of("85", "86", "87", "88")),
            new Bichos("Urso", 23, List.of("89", "90", "91", "92")),
            new Bichos("Veado", 24, List.of("93", "94", "95", "96")),
            new Bichos("Vaca", 25, List.of("97", "98", "99", "00"))
    );
    List<PagamentoJogoBicho> listaPagamentos = List.of(
            new PagamentoJogoBicho(1, 0, new double[]{3}),
            new PagamentoJogoBicho(13, 0, new double[]{1,12}),
            new PagamentoJogoBicho(14, 0, new double[]{0.75,3,42}),
            new PagamentoJogoBicho(15, 0, new double[]{0.2,1.5,22,500}),
            new PagamentoJogoBicho(16, 0, new double[]{0.2,1,8,150,17000}),
            new PagamentoJogoBicho(2, 0, new double[]{1000}),
            new PagamentoJogoBicho(3, 0, new double[]{120}),
            new PagamentoJogoBicho(4, 0, new double[]{12}),
            new PagamentoJogoBicho(5, 0, new double[]{6}),
            new PagamentoJogoBicho(7, 0, new double[]{41.67}),
            new PagamentoJogoBicho(8, 0, new double[]{20}),
            new PagamentoJogoBicho(9, 0, new double[]{30})
            );

    Map<Integer, Integer> modalidades = new HashMap<Integer, Integer>() {{
        put(1, 10);
        put(2, 4);
        put(3, 3);
        put(4, 2);
        put(7, 4);
        put(8, 3);
        put(9, 2);
        put(13, 2);
        put(14, 3);
        put(15, 4);
        put(16, 5);
    }};

    @Autowired
    private ResultadosJogoBichoRepository _resultadosJogoBichoRepository;
    public ResultadosJogoBicho GerarResultado() {
        List<String> resultados = new ArrayList<>();
        Random gerador = new Random();
        for (int i = 0; i < 5; i++) {
            String numeroSorteado = "";
            for (int y = 0; y < 2; y++) {
                int dezena = gerador.nextInt(99);
                String numeroFormatado = Integer.toString(dezena);
                if (numeroFormatado.length() < 2) numeroFormatado = "0" + numeroFormatado;
                numeroSorteado += numeroFormatado;
            }
            resultados.add(numeroSorteado);
        }
        String resultadoFinal = String.join(";", resultados);
        ResultadosJogoBicho resultadoSalvar = new ResultadosJogoBicho();
        resultadoSalvar.setId(0);
        resultadoSalvar.setJogo(resultadoFinal);
        resultadoSalvar.setDataCadastro(new Date());
        _resultadosJogoBichoRepository.save(resultadoSalvar);

        List<ApostasJogoBicho> apostas = _apostasJogoBichoRepository.getAberto();
        for (ApostasJogoBicho aposta : apostas) {
            double valorGanho = ConferirResultados (
                    resultados,
                    List.of(aposta.getJogo().split(";")),
                    aposta.getModalidade(),
                    modalidades.get(aposta.getModalidade()),
                    aposta.getValor()
                    );
            aposta.setFinalizada(true);
            aposta.setGanho(valorGanho);
            aposta.setIdSorteio(resultadoSalvar.getId());
            _apostasJogoBichoRepository.save(aposta);

            Fatecoins saldo1 = _FatecoinsRepository.findByIdCliente(aposta.getIdCliente()).get();
            saldo1.setQtd(saldo1.getQtd() + (int)valorGanho);
            _FatecoinsRepository.save(saldo1);
        }
        return  resultadoSalvar;
    }

    public double ConferirResultados (List<String> resultados, List<String> jogo, int modalidadeId, int tipo, double valorAposado) {
        int acertos = 0;
        switch(modalidadeId) {
            case 2:
            case 3:
            case 4:
            case 5:
                acertos = ValidarMilharCentezaDezenaUnidade(resultados, jogo, tipo);
                break;
            case 7:
            case 8:
            case 9:
                acertos = ValidarInvertido(resultados, jogo, tipo);
                break;
            case 1:
            case 13:
            case 14:
            case 15:
            case 16:
                acertos = ValidarApostaGrupo(resultados, jogo);
                break;
            default:
                acertos = 0;
                break;
        }
        return CalcularValorGanho(modalidadeId, acertos, valorAposado);
    }
    int ValidarApostaGrupo (List<String> resultados, List<String> jogo) {
        int acertos = 0;
        for (String jog : jogo) {
            boolean apareceu = false;
            Bichos numBichos = listaBichos.get(Integer.parseInt(jog)- 1);
            for (String resul : resultados) {
                String[] dezena =resul.split("(?<=\\G.{2})");
                if(numBichos.getDezenas().contains(dezena[1])) apareceu =  true;
                //System.out.println(dezena[1]);
                //numBichos.getDezenas().forEach(System.out::println);
                //System.out.println("=======");
            }
            if (apareceu) acertos++;

        }
        return acertos;
    }

    int ValidarMilharCentezaDezenaUnidade(List<String> resultados, List<String> jogo, int itensMaximo){
        int acertos = 0;
        for (String resul : resultados) {
            for (String jog : jogo) {
                String numero = resul.substring(4 - itensMaximo);
                //System.out.println(numero);
                if(numero.equals(jog)) acertos ++;
            }
        }
        return acertos;
    }
    int ValidarInvertido (List<String> resultados, List<String> jogo, int itensMaximo) {
        int acertos = 0;
        for (String resul : resultados) {
            String resultadoLimpo = resul.substring(4 - itensMaximo);
            if (containsAllWords(resultadoLimpo, jogo)) acertos++;
        }
        return acertos;
    }

    double CalcularValorGanho(int modalidadeId, int acertos, double valorAposado){
        double valorGanho = 0;
        Optional<PagamentoJogoBicho> pagamento = listaPagamentos.stream().filter(c -> c.getModalidadeId() == modalidadeId).findAny();
        System.out.println(acertos);
        if (pagamento.get().getUnitario().length == 1 && modalidadeId != 1 && acertos > 0){
            valorGanho = valorAposado * pagamento.get().getUnitario()[0];
        }else if (modalidadeId == 1 && acertos > 0){
            valorGanho = valorAposado * pagamento.get().getUnitario()[0] * acertos;
        }else if (acertos > 0){
            valorGanho = valorAposado * pagamento.get().getUnitario()[acertos-1];
        }
        return valorGanho;
    }

    public static boolean containsAllWords(String word, List<String> keywords) {
        for (String k : keywords)
            if (!word.contains(k)) return false;
        return true;
    }

}

