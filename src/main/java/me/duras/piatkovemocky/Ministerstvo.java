package me.duras.piatkovemocky;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Ministerstvo
 */
public class Ministerstvo {

    private static final String apiUrl = "https://my-json-server.typicode.com/durasj/piatkove-mocky/vazen";

    Ministerstvo() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private static final long serialVersionUID = 1L;
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public int nahrajVazna(Vazen vazen) throws UnirestException {
        HttpResponse<Vazen> response = Unirest.post(Ministerstvo.apiUrl)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(vazen)
            .asObject(Vazen.class);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Vazen nemohol byt nahrany na API ministerstva.");
        }

        Vazen receivedVazen = response.getBody();

        return receivedVazen.getId();
    }
}
