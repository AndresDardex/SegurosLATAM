package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class listLATAM {
    private static final String COUNTRIES_LATAM = "https://restcountries.com/v3.1/region/americas";

    
    public String GetCountriesLATAM() throws IOException {
        URL urlSurAme = new URL(COUNTRIES_LATAM);
        HttpURLConnection connection1 = (HttpURLConnection) urlSurAme.openConnection();
        connection1.setRequestMethod("GET");
        int responseCode1 = connection1.getResponseCode();
        if (responseCode1 != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode1);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
 
        public List<String> LATAM(JsonNode jsonCountries){
            List<String> latinAmericanCountries = new ArrayList<>();
            for (int indice = 0; indice<56;indice++)
            if (jsonCountries.get(indice).get("languages").get("spa") != null || jsonCountries.get(indice).get("languages").get("por") != null || jsonCountries.get(indice).get("languages").get("fra") != null && jsonCountries.get(indice).get("subregion").asText().equals("Caribbean")) {
                latinAmericanCountries.add(jsonCountries.get(indice).get("name").get("common").asText());
            }
            
            return latinAmericanCountries;
        }
}