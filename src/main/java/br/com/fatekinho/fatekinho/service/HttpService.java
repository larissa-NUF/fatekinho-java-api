package br.com.fatekinho.fatekinho.service;

import br.com.fatekinho.fatekinho.model.response.LoteriaResultadoResponse;
import br.com.fatekinho.fatekinho.service.interfaces.IHttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpService implements IHttpService {
    HttpClient client = HttpClient.newHttpClient();
    private String url = "https://loteriascaixa-api.herokuapp.com/api";

    public List<String> ObterLoteria(){
        try {
            String resultados = Get(url + "/");
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> response = objectMapper.readValue(resultados, new TypeReference<List<String>>() {});
            return response;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public List<LoteriaResultadoResponse> ObterResultadoLoteria(String tipoLoteria){
        try {

            String resultados = Get(url + "/" + tipoLoteria);
            ObjectMapper objectMapper = new ObjectMapper();
            List<LoteriaResultadoResponse> response = objectMapper.readValue(resultados, new TypeReference<List<LoteriaResultadoResponse>>() {});
            return response;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public LoteriaResultadoResponse ObterResultadoConcurso(String tipoLoteria,String tipoConcurso){
        try {

            String resultados = Get(url + "/" + tipoLoteria + "/" + tipoConcurso);
            ObjectMapper objectMapper = new ObjectMapper();
            LoteriaResultadoResponse response = objectMapper.readValue(resultados, new TypeReference<LoteriaResultadoResponse>() {});
            return response;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private String Get (String url){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
