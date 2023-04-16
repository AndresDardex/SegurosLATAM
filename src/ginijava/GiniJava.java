package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GiniJava {

    
    public static void main(String[] args) {
        ApiConnection apiConnection = new ApiConnection();
        yearGini objGini = new yearGini();
        ObjectMapper objectMapper = new ObjectMapper();
        
         try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el Pais de donde quiere sacar la informacion: ");
            String countryName = sc.nextLine(); 
            String country = apiConnection.GetCountry(countryName);
            JsonNode json = objectMapper.readTree(country);
            String official = json.get(0).get("name").get("official").asText();
            String capital = json.get(0).get("capital").get(0).asText();
            String population = json.get(0).get("population").asText();
            int currentYear = LocalDate.now().getYear();
            List<Map<String, String>> yearGiniList = objGini.yearGiniList(currentYear, json);
            System.out.println(country);
            System.out.println(official);
            System.out.println(capital);
            System.out.println(population);
            if (!yearGiniList.isEmpty()) {
                String year = yearGiniList.get(0).get("year");
                String gini = yearGiniList.get(0).get("gini");
                System.out.println("para el año: " + year + ", el Gini es de: " + gini);
            } else {
                System.out.println("No se encontraron valores de gini.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
