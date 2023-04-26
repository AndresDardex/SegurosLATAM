package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
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
 
        public List<List<String>> LATAM(JsonNode jsonCountries){
            List<List<String>> latinAmericanCountries = new ArrayList<>();
            for (int quantity = 0; quantity < 56; quantity++) {
                JsonNode countryNode = jsonCountries.get(quantity);
                if(countryNode == null){
                    break;
                }
                if(countryNode != null){
                    if (countryNode.get("languages").get("spa") != null || countryNode.get("languages").get("por") != null || countryNode.get("languages").get("fra") != null && countryNode.get("subregion").asText().equals("Caribbean")) {
                        JsonNode nameNode = countryNode.get("name");
                        if (nameNode != null) {
                            JsonNode commonNode = nameNode.get("common");
                            if (commonNode != null) {
                                String country = commonNode.asText();
                                String capital = countryNode.get("capital").get(0).asText();
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
                                        latinAmericanCountries.add(countryInfo);
                                        encontrado = true;
                                    }
                                }
                                if (encontrado == false) {
                                    List<String> countryInfo = new ArrayList<>();
                                    countryInfo.add(country);
                                    countryInfo.add(capital);
                                    countryInfo.add(population);
                                    countryInfo.add("N/A");
                                    latinAmericanCountries.add( countryInfo);
                                }
                            }
                        }
                    }
                }
            }

                return latinAmericanCountries;
            }


    }