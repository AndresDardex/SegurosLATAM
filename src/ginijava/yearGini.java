
package ginijava;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class yearGini {
        public List<Map<String, String>> yearGiniList(int currentYear, JsonNode jsonCountry) {
    List<Map<String, String>> yearGiniList = new ArrayList<>();
    boolean encontrado = false;
    for (int year = currentYear; year > 1992; year--) {
        String strNumber = String.valueOf(year);
        JsonNode giniNode = jsonCountry.get(0).get("gini");
        if (giniNode != null && giniNode.has(strNumber)) {
            String gini = giniNode.get(strNumber).asText();
            Map<String, String> yearGiniMap = new HashMap<>();
            yearGiniMap.put("year", String.valueOf(year));
            yearGiniMap.put("gini", gini);
            yearGiniList.add(yearGiniMap);
            encontrado = true;
        }
    }
    if (encontrado == false) {
        Map<String, String> yearGiniMap = new HashMap<>();
        yearGiniMap.put("year", "N/A");
        yearGiniMap.put("gini", "N/A");
        yearGiniList.add(yearGiniMap);
    }
    return yearGiniList;
}


}
