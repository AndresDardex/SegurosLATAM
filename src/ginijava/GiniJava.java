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
        Scanner sc = new Scanner(System.in);
        apiConnectionPerCountry apiConnection = new apiConnectionPerCountry();
        yearGini objGini = new yearGini();
        listCountries listCountries = new listCountries();
        listLATAM listLATAM = new listLATAM();
        apiConnectionPerRegion perRegion = new apiConnectionPerRegion();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Ingrese la opcion: ");
        int opcion = Integer.parseInt(sc.nextLine());
         try {
            switch (opcion){
                case 1:
                    // pais unitario
                    System.out.println("Ingrese el Pais de donde quiere sacar la informacion: ");
                    String countryName = sc.nextLine(); 
                    String country = apiConnection.GetCountry(countryName);
                    JsonNode jsonCountry = objectMapper.readTree(country);
                    String official = jsonCountry.get(0).get("name").get("official").asText();
                    String capital = jsonCountry.get(0).get("capital").get(0).asText();
                    String population = jsonCountry.get(0).get("population").asText();
                    JsonNode p = jsonCountry.get(0).get("subregion");
                    int currentYear = LocalDate.now().getYear();
                    List<Map<String, String>> yearGiniList = objGini.yearGiniList(currentYear, jsonCountry);
                    System.out.println(official);
                    System.out.println(capital);
                    System.out.println(population);
                    System.out.println(p);
                    if (!yearGiniList.isEmpty()) {
                        String year = yearGiniList.get(0).get("year");
                        String gini = yearGiniList.get(0).get("gini");
                        System.out.println("para el año: " + year + ", el Gini es de: " + gini);
                    } else {
                        System.out.println("No se encontraron valores de gini.");
                        }  
                    break;
                case 2:
                    //lista todos los paises
                    String countries = listCountries.GetCountries();
                    JsonNode jsonCountries = objectMapper.readTree(countries);
                    List<String> Countries = listCountries.GetCountriesList(jsonCountries);
                    System.out.println(Countries);
                    List<String> subRegions = listCountries.GetSubRegions(jsonCountries);
                    System.out.println(subRegions);
                    break;
                case 3:
                    //lista paises latam
                    String countriesLATAM = listLATAM.GetCountriesLATAM();
                    JsonNode jsonCountriesLATAMSur = objectMapper.readTree(countriesLATAM);
                    List<String> latinAmericanCountries = listLATAM.LATAM(jsonCountriesLATAMSur);
                    System.out.println(latinAmericanCountries);
                    break;
                case 4:
                    //lista paises por region
                    System.out.println("introduzca la sub region que le gusta: ");
                    String SubRegion = sc.nextLine();
                    String countriesSub = perRegion.GetCountries(SubRegion);
                    JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                    List<String> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                    System.out.println(subregionCountries);
                }  
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
