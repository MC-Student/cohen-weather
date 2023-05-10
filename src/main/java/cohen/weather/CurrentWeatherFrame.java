package cohen.weather;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CurrentWeatherFrame extends JFrame
{
    private final ForecastView forecastView;

    private final WeatherForecastController controller;

    @Inject
    public CurrentWeatherFrame(ForecastView forecastView, WeatherForecastController controller)
    {
        this.forecastView = forecastView;
        this.controller = controller;

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

        JButton toGraph = new JButton("Graph It");
        toGraph.setSize(25, 45);

        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new BorderLayout());

        JPanel userInput = new JPanel(new BorderLayout());
        userInput.add(city, BorderLayout.CENTER);
        userInput.add(toGraph, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userInput, BorderLayout.PAGE_START);
        mainPanel.add(forecastView, BorderLayout.CENTER);

        toGraph.addActionListener(e -> controller.updateWeather(city.getText()));
        requestFocus();

        setContentPane(mainPanel);
    }
}