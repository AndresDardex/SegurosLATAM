package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class apiConnectionPerRegion {
        private static final String SUBREGIONS = "https://restcountries.com/v3.1/subregion/";
        //    South%20America
    
    public String GetCountries(String SubRegion) throws IOException {
        URL url = new URL(SUBREGIONS + SubRegion );
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
    public List<String> GetCountriesList(JsonNode jsonCountries){
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
