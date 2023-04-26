package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GiniJava {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        apiConnectionPerCountry apiConnection = new apiConnectionPerCountry();
        yearGini objGini = new yearGini();
        listCountries listCountries = new listCountries();
        listLATAM listLATAM = new listLATAM();
        apiConnectionPerRegion region = new apiConnectionPerRegion();
        apiConnectionPerSubRegion perRegion = new apiConnectionPerSubRegion();
        ObjectMapper objectMapper = new ObjectMapper();
         try {
             Object seleccion = JOptionPane.showInputDialog(
                     null,
                     "Escoja la opcion que desee: ",
                     "Opcions Selector",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     new Object[] {"Pais Individual", "Lista de TODOS los paises y sus regiones", "Lista paises LATAM","Lista de paises por region","Lista de paises por Sub region"},
                     null
             );
            switch (seleccion.toString()){
                case "Pais Individual":
                    Object countryName = JOptionPane.showInputDialog(
                            null,
                            "Ingrese el Pais de donde desea obtener la informacion: ",
                            "Options Selector",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] {"Afghanistan", "Algeria","Åland Islands", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Burkina Faso", "Burundi", "Cameroon", "Canada", "Cape Verde", "Caribbean Netherlands", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica", "Cuba", "Curaçao", "Cyprus", "Czechia", "DR Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Egypt", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Falkland Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern and Antarctic Lands", "Gabon", "Gambia", "Georgia", "Germany", "Gibraltar", "Greenland", "Grenada", "Guadeloupe", "Guatemala", "Guernsey", "Guinea", "Guyana", "Haiti", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Ireland", "Isle of Man", "Israel", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lesotho", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Nigeria", "North Korea", "North Macedonia", "Northern Mariana Islands", "Norway", "Pakistan", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Pitcairn Islands", "Poland", "Portugal", "Qatar", "Republic of the Congo", "Romania", "Russia", "Réunion", "Saint Barthélemy", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Samoa", "San Marino", "Senegal", "Seychelles", "Singapore", "Slovakia", "Slovenia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Switzerland", "Syria", "São Tomé and Príncipe", "Taiwan", "Thailand", "Timor-Leste", "Togo", "Tonga", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "United States Virgin Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"},
                            null
                    );
                    String country = apiConnection.GetCountry(countryName.toString());
                    JsonNode jsonCountry = objectMapper.readTree(country);
                    System.out.println(jsonCountry);
                    String common = jsonCountry.get(0).get("name").get("common").asText();
                    String capital = jsonCountry.get(0).get("capital").get(0).asText();
                    String population = jsonCountry.get(0).get("population").asText();
                    JsonNode subRegion = jsonCountry.get(0).get("subregion");
                    int currentYear = LocalDate.now().getYear();
                    List<Map<String, String>> yearGiniList = objGini.yearGiniList(currentYear, jsonCountry);
                    String mensaje = null;
                    if (!yearGiniList.isEmpty()) {
                        String year = yearGiniList.get(0).get("year");
                        String gini = yearGiniList.get(0).get("gini");
                        mensaje = "Pais: " + common + "\nCapital: " + capital + "\nPoblacion: " + population + "\npara el año: " + year + ", el Gini es de: " + gini + "\nContinente: " + subRegion;
                    }
                    JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE); 
                    break;

                case "Lista de TODOS los paises y sus regiones":
                    //lista todos los paises y sus regiones (dispuesto a cambios)
                    String countries = listCountries.GetCountries();
                    JsonNode jsonCountries = objectMapper.readTree(countries);
                    List<String> Countries = listCountries.GetCountriesList(jsonCountries);
                    System.out.println(Countries);
                    List<String> subRegions = listCountries.GetSubRegions(jsonCountries);
                    System.out.println(subRegions);
                    break;
                case "Lista paises LATAM":
                    //lista paises latam
                    String countriesLATAM = listLATAM.GetCountriesLATAM();
                    JsonNode jsonCountriesLATAMSur = objectMapper.readTree(countriesLATAM);
                    List<String> latinAmericanCountries = listLATAM.LATAM(jsonCountriesLATAMSur);
                    System.out.println(latinAmericanCountries);
                    System.out.println(latinAmericanCountries.size());
                    break;
                case "Lista de paises por region":
                    Object regionSelection = JOptionPane.showInputDialog(
                            null,
                            "¿De que region quieres obtener la informacion?",
                            "Options Selector",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] {"Americas","Africa","Oceania","Europe","Asia"},
                            null
                    );
                    String regionCountries = region.GetCountries(regionSelection.toString());
                    JsonNode jsonRegions = objectMapper.readTree(regionCountries);
                    List<String> countriesRegion = region.GetCountriesList(jsonRegions);
                    System.out.println(countriesRegion);
                    break;
                case "Lista de paises por Sub region":
                    Object SubRegionSelection = JOptionPane.showInputDialog(
                     null,
                     "¿De que Sub region quiere obtener la informacion?",
                     "Opcions Selector",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     new Object[] {"Caribbean", "Eastern Africa", "South America", "Southern Africa", "Western Africa", "Melanesia", "Polynesia", "Western Europe", "Southern Europe"},
                     null
                    );
                    if(SubRegionSelection.toString() == "South America"){
                        String countriesSub = perRegion.GetCountries("South%20America");
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    if(SubRegionSelection.toString() == "Eastern Africa"){
                        String countriesSub = perRegion.GetCountries("Eastern%20Africa");
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    if(SubRegionSelection.toString() == "Southern Africa"){
                        String countriesSub = perRegion.GetCountries("Southern%20Africa");
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    if(SubRegionSelection.toString() == "Western Africa"){
                        String countriesSub = perRegion.GetCountries("Western%20Africa");
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    if(SubRegionSelection.toString() == "Western Europe"){
                        String countriesSub = perRegion.GetCountries("Western%20Europe");
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    if(SubRegionSelection.toString() == "Southern Europe"){
                        String countriesSub = perRegion.GetCountries("Southern%20Europe");
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    else{
                        String countriesSub = perRegion.GetCountries(SubRegionSelection.toString());
                        JsonNode jsonSubCountries = objectMapper.readTree(countriesSub);
                        Map<String, List<String>> subregionCountries = perRegion.GetCountriesList(jsonSubCountries);
                        System.out.println(subregionCountries);
                    }
                    
                    
                    
                }  
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
