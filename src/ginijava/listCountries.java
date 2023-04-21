package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class listCountries {
    private static final String COUNTRIES_SURAME = "https://restcountries.com/v3.1/all";
    
    public String GetCountries() throws IOException {
        URL url = new URL(COUNTRIES_SURAME);
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
    public List<String> GetSubRegions(JsonNode jsonCountries){
        List<String> subRegions = new ArrayList<>();
        if(jsonCountries != null){
            for(int quantity = 0; quantity < 9;quantity++){
                JsonNode SubRegionNode = jsonCountries.get(quantity);
                if (SubRegionNode != null){
                    JsonNode subRegion = SubRegionNode.get("subregion");
                    String eachSubRegion = subRegion.asText();
                    if(!eachSubRegion.isEmpty()){
                        subRegions.add(eachSubRegion);
                    }
                }
            }
        }
        return subRegions;
    }
}
