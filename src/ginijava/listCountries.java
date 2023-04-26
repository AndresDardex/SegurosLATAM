package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class listCountries {
    private static final String ALL_COUNTRIES = "https://restcountries.com/v3.1/all";
    
    public String GetCountries() throws IOException {
        URL url = new URL(ALL_COUNTRIES);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
    public  List<List<String>> GetCountriesList(JsonNode jsonCountries){
         List<List<String>> allCountries = new ArrayList<>();
        if (jsonCountries != null) {
            for (int quantity = 0; quantity < 195; quantity++) {
                JsonNode countryNode = jsonCountries.get(quantity);
                if (countryNode != null) {
                    JsonNode nameNode = countryNode.get("name");
                    if (nameNode != null) {
                        JsonNode commonNode = nameNode.get("common");
                        if (commonNode != null) {
                            String country = commonNode.asText();
                            JsonNode capitalNode = countryNode.get("capital");
                            if (capitalNode != null) {
                                String capital = capitalNode.get(0).asText();
                                String population = countryNode.get("population").asText();
                                int currentYear = LocalDate.now().getYear();
                                boolean encontrado = false;
                                for (int year = currentYear; year > 1992; year--) {
                                    String strNumber = String.valueOf(year);
                                    JsonNode giniNode = countryNode.get("gini");
                                    if (giniNode != null && giniNode.has(strNumber)) {
                                        String gini = giniNode.get(strNumber).asText();
                                        List<String> countryInfo = new ArrayList<>();
                                        countryInfo.add(country);
                                        countryInfo.add(capital);
                                        countryInfo.add(population);
                                        countryInfo.add(gini);
                                        allCountries.add(countryInfo);
                                        encontrado = true;
                                    }
                                }
                                if (encontrado == false) {
                                    List<String> countryInfo = new ArrayList<>();
                                    countryInfo.add(country);
                                    countryInfo.add(capital);
                                    countryInfo.add(population);
                                    countryInfo.add("N/A");
                                    allCountries.add( countryInfo);
                        }
                    }
                            if(capitalNode == null){
                              String population = countryNode.get("population").asText();
                                int currentYear = LocalDate.now().getYear();
                                boolean encontrado = false;
                                for (int year = currentYear; year > 1992; year--) {
                                    String strNumber = String.valueOf(year);
                                    JsonNode giniNode = countryNode.get("gini");
                                    if (giniNode != null && giniNode.has(strNumber)) {
                                        String gini = giniNode.get(strNumber).asText();
                                        List<String> countryInfo = new ArrayList<>();
                                        countryInfo.add(country);
                                        countryInfo.add("N/A");
                                        countryInfo.add(population);
                                        countryInfo.add(gini);
                                        allCountries.add(countryInfo);
                                        encontrado = true;
                                    }
                                }
                                if (encontrado == false) {
                                    List<String> countryInfo = new ArrayList<>();
                                    countryInfo.add(country);
                                    countryInfo.add("N/A");
                                    countryInfo.add(population);
                                    countryInfo.add("N/A");
                                    allCountries.add( countryInfo);
                        }  
                    }
                }
            }
        }
    }
}
        return allCountries;
    }
}
