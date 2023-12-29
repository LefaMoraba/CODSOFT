import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;


public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Currency Selection
        System.out.println("Enter the base currency (e.g., USD, EUR, GBP): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Enter the target currency (e.g., USD, EUR, GBP): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Fetch Real-time Exchange Rates from an API
        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate == -1) {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
            return;
        }

        // Step 3: Amount Input
        System.out.println("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ":");
        double amount = scanner.nextDouble();

        // Step 4: Currency Conversion
        double convertedAmount = amount * exchangeRate;

        // Step 5: Display Results
        System.out.println(amount + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String apiUrl = "https://v6.exchangerate-api.com/v6/d048472ea84d807a9db7fa27/latest/" + baseCurrency;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                String jsonResponse = response.toString();
                double exchangeRate = parseExchangeRateFromJSON(jsonResponse, targetCurrency);

                return exchangeRate;
            } else {
                System.out.println("Failed to fetch exchange rates. HTTP Error: " + responseCode);
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static double parseExchangeRateFromJSON(String jsonResponse, String targetCurrency) {
        double exchangeRate = -1;

        try {
            // Parse JSON and retrieve exchange rate for the target currency
            // Assuming the API returns exchange rates in the provided format

            // Parse the main JSON object
            JSONObject mainObject = new JSONObject(jsonResponse);

            // Check if the request was successful
            if (mainObject.getString("result").equals("success")) {
                // Get the conversion rates object
                JSONObject conversionRates = mainObject.getJSONObject("conversion_rates");

                // Get the exchange rate for the target currency
                exchangeRate = conversionRates.getDouble(targetCurrency);
            } else {
                System.out.println("Failed to fetch exchange rates. API response: " + jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exchangeRate;
    }

}
