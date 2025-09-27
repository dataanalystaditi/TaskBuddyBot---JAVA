package com.TaskBuddy;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


public class TaskBuddy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm TaskBuddy. How can I help you today?");
        System.out.println("Try commands like: weather, motivate, time, date, exit");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.contains("exit")) {
                System.out.println("Goodbye!");
                break;

            } else if (input.contains("weather")) {
                System.out.print("Enter city: ");
                String city = scanner.nextLine();
                WeatherService.getWeather(city);

            } else if (input.contains("motivate")) {
                String[] quotes = {
                        "Stay positive, work hard, make it happen!",
                        "Believe in yourself and all that you are.",
                        "Success is not final; failure is not fatal.",
                        "Don’t watch the clock; do what it does. Keep going.",
                        "Push yourself, because no one else is going to do it for you."
                    };
                    int index = new java.util.Random().nextInt(quotes.length);
                    System.out.println(quotes[index]);
                    
            } else if (input.contains("time")) {
                java.time.LocalTime currentTime = java.time.LocalTime.now();
                java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a");
                System.out.println("Current Time: " + currentTime.format(timeFormatter));

            } else if (input.contains("date")) {
                java.time.LocalDate currentDate = java.time.LocalDate.now();
                java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy");
                System.out.println("Today's Date: " + currentDate.format(dateFormatter));
                           
            } else {
                System.out.println("Sorry, I didn’t understand that.");
                
            }
        }

        scanner.close();
    }
}
