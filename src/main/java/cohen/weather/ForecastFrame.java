package cohen.weather;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ForecastFrame extends JFrame
{
    private final ForecastView forecastView;

    private final WeatherForecastController controller;

    private final CurrentWeatherController currentWeatherController;

    @Inject
    public ForecastFrame(ForecastView forecastView,
                         WeatherForecastController controller,
                         CurrentWeatherController currentWeatherController,
                         @Named("imageLabel") JLabel imageLabel,
                         @Named("degreesLabel") JLabel degreesLabel)
    {
        this.forecastView = forecastView;
        this.controller = controller;
        this.currentWeatherController = currentWeatherController;

        setSize(800, 600);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField city = new JTextField();
        city.setText("New York");
        city.setSize(30, 45);
        city.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (character == KeyEvent.VK_ENTER)
                {
                    controller.updateWeather(city.getText());
                    requestFocus();
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });
        city.setFocusable(true);
        city.requestFocus();

        JButton graphButton = new JButton("Graph It");
        graphButton.setSize(25, 45);

        JPanel weatherPanel = new JPanel();
        weatherPanel.add(imageLabel);
        weatherPanel.add(degreesLabel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(city, BorderLayout.CENTER);
        topPanel.add(graphButton, BorderLayout.EAST);

        JPanel weatherLabels = new JPanel();
        weatherLabels.add(imageLabel);
        weatherLabels.add(degreesLabel);
        topPanel.add(weatherLabels, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(forecastView, BorderLayout.CENTER);

        graphButton.addActionListener(e ->
        {
            controller.updateWeather(city.getText());
            currentWeatherController.updateWeather(city.getText());
        });

        controller.updateWeather(city.getText());
        currentWeatherController.updateWeather(city.getText());

        setContentPane(mainPanel);
    }
}