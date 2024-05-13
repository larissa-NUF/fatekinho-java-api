package br.com.fatekinho.fatekinho.service;

import br.com.fatekinho.fatekinho.service.interfaces.IHttpService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService implements IHttpService {
    HttpClient client = HttpClient.newHttpClient();

    public JSONArray ObterResultadoLotoFacil(){
        try {
            String url = "https://apiloterias.com.br/app/v2/resultado?loteria=lotofacil&token=kJdfLjd38Jai2ek&concurso=ultimos3";
            String resultados = Get(url);
            JSONArray jsonObject = new JSONArray (resultados);
            return jsonObject;
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
