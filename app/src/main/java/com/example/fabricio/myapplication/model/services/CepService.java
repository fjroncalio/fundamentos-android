package com.example.fabricio.myapplication.model.services;

import com.example.fabricio.myapplication.model.entities.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Fabricio on 27/07/2015.
 */
public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private CepService() {
        super();
    }

    public static ClientAddress getAddressBy(String cep) {
        ClientAddress address = new ClientAddress();
        try {
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, ClientAddress.class);

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

}
