package CodsoftInternship;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class CurrencyConverter {

    static Map<String, String> currencySymbols = new HashMap<>();
    static {
        currencySymbols.put("USD", "$");
        currencySymbols.put("EUR", "€");
        currencySymbols.put("INR", "₹");
        currencySymbols.put("GBP", "£");
        currencySymbols.put("JPY", "¥");

    }
    

    public static double getExchangeRate(String base, String target) {
        try {
            String urlStr = "https://api.exchangerate-api.com/v4/latest/" + base;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            String result = response.toString();
            

            String search = "\"" + target + "\":";
            int idx = result.indexOf(search);
            if (idx == -1) throw new Exception("Currency not found in rates.");
            int start = idx + search.length();
            int end = result.indexOf(",", start);
            if (end == -1) end = result.indexOf("}", start);
            String rate = result.substring(start, end).trim();
            return Double.parseDouble(rate);
        } catch (Exception e) {
            System.out.println("Could not fetch exchange rate: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String[] currencyList = {"USD", "EUR", "INR", "GBP", "JPY"};
        System.out.println("Supported currencies:");
        for (int i = 0; i < currencyList.length; i++) {
            System.out.println((i+1) + ". " + currencyList[i]);
        }


        System.out.print("Select base currency (number): ");
        int baseIndex = sc.nextInt() - 1;
        while (baseIndex < 0 || baseIndex >= currencyList.length) {
            System.out.print("Invalid choice. Select again: ");
            baseIndex = sc.nextInt() - 1;
        }
        String baseCurrency = currencyList[baseIndex];

        System.out.print("Select target currency (number): ");
        int targetIndex = sc.nextInt() - 1;
        while (targetIndex < 0 || targetIndex >= currencyList.length || targetIndex == baseIndex) {
            System.out.print("Invalid choice (cannot be same as base). Select again: ");
            targetIndex = sc.nextInt() - 1;
        }
        String targetCurrency = currencyList[targetIndex];


        System.out.print("Enter amount in " + baseCurrency + ": ");
        double amount = sc.nextDouble();


        System.out.println("Fetching latest exchange rates...");
        double rate = getExchangeRate(baseCurrency, targetCurrency);
        if (rate == -1) {
            System.out.println("Could not get exchange rate. Conversion failed.");
            return;
            
        }


        double converted = amount * rate;


        String targetSymbol = currencySymbols.getOrDefault(targetCurrency, targetCurrency + " ");
        System.out.printf("Converted amount: %s%.2f (%s)%n", targetSymbol, converted, targetCurrency);
        sc.close();
    }
}
