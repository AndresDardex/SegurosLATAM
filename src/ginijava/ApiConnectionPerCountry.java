package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class apiConnectionPerCountry {
    private static final String BASE_URL = "https://restcountries.com/v3.1/name/";
    yearGini objGini = new yearGini();
    
    public String GetCountry(String country) throws IOException {
        String error = country.replace(" ", "%20");
        URL url = new URL(BASE_URL + error);
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
    public List<List<String>> listCountry(JsonNode jsonCountries) throws MalformedURLException{
        List<List<String>> country = new ArrayList<>();
        String common = jsonCountries.get(0).get("name").get("common").asText();
        JsonNode capitalNode = jsonCountries.get(0).get("capital");
        if(capitalNode != null){
        String capital = capitalNode.get(0).asText();
        String population = jsonCountries.get(0).get("population").asText();
        JsonNode subRegion = jsonCountries.get(0).get("subregion");
        int currentYear = LocalDate.now().getYear();
        List<Map<String, String>> yearGiniList = objGini.yearGiniList(currentYear, jsonCountries);
        String mensaje = null;
        if (!yearGiniList.isEmpty()) {
            String year = yearGiniList.get(0).get("year");
            String gini = yearGiniList.get(0).get("gini");
            mensaje = "Pais: " + common + "\nCapital: " + capital + "\nPoblacion: " + population + "\npara el año: " + year + ", el Gini es de: " + gini + "\nContinente: " + subRegion;
        }
        JsonNode imagen = jsonCountries.get(0).get("flags").get("png"); 
        String urlImagen = imagen.asText();
        ImageIcon icono = new ImageIcon(new URL(urlImagen));
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE,icono); 
        }
        if(capitalNode == null){
        String population = jsonCountries.get(0).get("population").asText();
        JsonNode subRegion = jsonCountries.get(0).get("subregion");
        int currentYear = LocalDate.now().getYear();
        List<Map<String, String>> yearGiniList = objGini.yearGiniList(currentYear, jsonCountries);
        String mensaje = null;
        if (!yearGiniList.isEmpty()) {
            String year = yearGiniList.get(0).get("year");
            String gini = yearGiniList.get(0).get("gini");
            mensaje = "Pais: " + common + "\nCapital: " + "N/A" + "\nPoblacion: " + population + "\npara el año: " + year + ", el Gini es de: " + gini + "\nContinente: " + subRegion;
        }
        JsonNode imagen = jsonCountries.get(0).get("flags").get("png"); 
        String urlImagen = imagen.asText();
        ImageIcon icono = new ImageIcon(new URL(urlImagen));
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE,icono); 
        }
         return country;
    }
}