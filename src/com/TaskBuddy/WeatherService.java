package com.TaskBuddy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

public class WeatherService {

    public static void getWeather(String city) {
        try {
            String apiKey = "e358aeea311bdaaec9b0954725cad90d".trim();
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" +
                    city + "&appid=" + apiKey + "&units=metric";
            
            System.out.println("Calling URL: " + urlString);


            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject obj = new JSONObject(response.toString());

                String cityName = obj.getString("name");
                JSONObject main = obj.getJSONObject("main");
                double temp = main.getDouble("temp");
                int humidity = main.getInt("humidity");

                JSONArray weatherArray = obj.getJSONArray("weather");
                String condition = weatherArray.getJSONObject(0).getString("description");

                JSONObject wind = obj.getJSONObject("wind");
                double windSpeed = wind.getDouble("speed");

                // ✅ Get timestamp and convert to readable date-time
                long timestamp = obj.getLong("dt");
                java.time.LocalDateTime dateTime = java.time.Instant.ofEpochSecond(timestamp)
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
                
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
                String formattedDateTime = dateTime.format(formatter);           


                // Output
                System.out.println("\nWeather Report for " + cityName);
                System.out.println("Date & Time: " + formattedDateTime);
                System.out.println("Temperature: " + temp + "°C");
                System.out.println("Condition: " + condition);
                System.out.println("Humidity: " + humidity + "%");
                System.out.println("Wind Speed: " + windSpeed + " m/s");
                System.out.println("-----------------------------------");


            } else {
                System.out.println("API Error: Response code " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("Error fetching weather data:");
            e.printStackTrace();
        }
    }
}
