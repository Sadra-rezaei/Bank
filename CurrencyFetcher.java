import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class CurrencyFetcher {

    public static double getUSDPrice() {
        String apiKey = "free2ndoQgj0OUbZOmSXN2m5U6XVEZM0"; // API key
        String urlString = "https://api.navasan.tech/latest/?api_key=" + apiKey;
        double value;

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject usd = jsonObject.getJSONObject("usd");
            value = usd.getDouble("value") * 10;

        } catch (Exception e) {
            e.printStackTrace();
            value = 840000; // fallback value
        }
        return value;
    }
}