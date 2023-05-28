package com.weatherdata.dataweather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class WeatherInfoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sanjeevdb";
    private static final String USERNAME = "sanjeev";
    private static final String PASSWORD = "sanjeev";

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void saveWeatherInfo(WeatherInfo weatherInfo) {
        String insertQuery = "INSERT INTO weather_info (pincode, date, weather_details) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setString(1, weatherInfo.getPincode());
            statement.setDate(2, java.sql.Date.valueOf(weatherInfo.getDate()));
            statement.setString(3, weatherInfo.getWeatherDetails());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WeatherInfo getWeatherInfo(String pincode, LocalDate date) {
        WeatherInfo weatherInfo = null;
        String selectQuery = "SELECT * FROM weather_info WHERE pincode = ? AND date = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setString(1, pincode);
            statement.setDate(2, java.sql.Date.valueOf(date));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                weatherInfo = new WeatherInfo();
                weatherInfo.setPincode(resultSet.getString("pincode"));
                weatherInfo.setDate(resultSet.getDate("date").toLocalDate());
                weatherInfo.setWeatherDetails(resultSet.getString("weather_details"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weatherInfo;
    }


}
