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
    private static final String COUNTRIES_SURAME = "https://restcountries.com/v3.1/region/South%20America";
    private static final String COUNTRIES_CENAME = "https://restcountries.com/v3.1/region/Central%20America";
    private static final String COUNTRIES_NORAME = "https://restcountries.com/v3.1/region/north%20America";

    
    public String GetCountriesSurAme() throws IOException {
        URL urlSurAme = new URL(COUNTRIES_SURAME);
        HttpURLConnection connection1 = (HttpURLConnection) urlSurAme.openConnection();
        connection1.setRequestMethod("GET");
        int responseCode1 = connection1.getResponseCode();
        if (responseCode1 != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode1);
        }
        BufferedReader in1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in1.readLine()) != null) {
            response.append(inputLine);
        }
        in1.close();

        return response.toString();
    }
    public String GetCountriesCenAme() throws IOException {
        URL urlCerAme = new URL(COUNTRIES_CENAME);
        HttpURLConnection connection2 = (HttpURLConnection) urlCerAme.openConnection(); 
        connection2.setRequestMethod("GET");
        
        int responseCode2 = connection2.getResponseCode();
        if (responseCode2 != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode2);
        }
        BufferedReader in2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in2.readLine()) != null) {
            response.append(inputLine);
        }
        in2.close();
        return response.toString();
    }
    public String GetCountriesNorAme() throws IOException {
        URL urlNorAme = new URL(COUNTRIES_NORAME); 
        HttpURLConnection connection3 = (HttpURLConnection) urlNorAme.openConnection(); 
        connection3.setRequestMethod("GET");
        
        int responseCode3 = connection3.getResponseCode();
        if (responseCode3 != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode3);
        }
        BufferedReader in3 = new BufferedReader(new InputStreamReader(connection3.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in3.readLine()) != null) {
            response.append(inputLine);
        }
        in3.close();

        return response.toString();
    }
    public List<String> GetCountriesListSur(JsonNode jsonCountries){
        List<String> CountriesSURAME = new ArrayList<>();
        if (jsonCountries != null) {
    for (int quantity = 0; quantity < 195; quantity++) {
        JsonNode countryNode = jsonCountries.get(quantity);
        if (countryNode != null) {
            JsonNode nameNode = countryNode.get("name");
            if (nameNode != null) {
                JsonNode commonNode = nameNode.get("common");
                if (commonNode != null) {
                    String country = commonNode.asText();
                    CountriesSURAME.add(country);
                }
            }
        }
    }
}
            return CountriesSURAME;
    }
public List<String> GetCountriesListNor(JsonNode jsonCountries){
        List<String> CountriesSURAME = new ArrayList<>();
        if (jsonCountries != null) {
    for (int quantity = 0; quantity < 195; quantity++) {
        JsonNode countryNode = jsonCountries.get(quantity);
        if (countryNode != null) {
            JsonNode nameNode = countryNode.get("name");
            if (nameNode != null) {
                JsonNode commonNode = nameNode.get("common");
                if (commonNode != null) {
                    String country = commonNode.asText();
                    CountriesSURAME.add(country);
                }
            }
        }
    }
}
                return CountriesSURAME;
}
        public List<String> GetCountriesListCen(JsonNode jsonCountries){
        List<String> CountriesSURAME = new ArrayList<>();
        if (jsonCountries != null) {
    for (int quantity = 0; quantity < 195; quantity++) {
        JsonNode countryNode = jsonCountries.get(quantity);
        if (countryNode != null) {
            JsonNode nameNode = countryNode.get("name");
            if (nameNode != null) {
                JsonNode commonNode = nameNode.get("common");
                if (commonNode != null) {
                    String country = commonNode.asText();
                    CountriesSURAME.add(country);
                }
            }
        }
    }
}
        return CountriesSURAME;
        }
}