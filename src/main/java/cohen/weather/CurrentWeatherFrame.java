package cohen.weather;

import cohen.weather.jsonForFiveDay.FiveDayForecast;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CurrentWeatherFrame extends JFrame
{
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    WeatherService service = retrofit.create(WeatherService.class);

    CurrentWeatherView currentWeatherView;

    public CurrentWeatherFrame()
    {
        String cityName = "New York";

        Observable<FiveDayForecast> fiveDayForecast = service.getFiveDayForecast(cityName);

        currentWeatherView = new CurrentWeatherView();

        setSize(800, 600);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField city = new JTextField();
        city.setText("New York");
        city.setSize(30,45);
        JButton toGraph = new JButton("Graph It");
        toGraph.setSize(25,45);

        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new BorderLayout());
//        graphPanel.add(currentWeatherView, BorderLayout.CENTER);

        JPanel userInput = new JPanel(new BorderLayout());
        userInput.add(city, BorderLayout.CENTER);
        userInput.add(toGraph, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userInput, BorderLayout.PAGE_START);
        mainPanel.add(currentWeatherView, BorderLayout.CENTER);

        toGraph.addActionListener(e -> graphIt(city));

        setFocusable(true);

        addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (character == KeyEvent.VK_ENTER)
                {
                    graphIt(city);
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

        setContentPane(mainPanel);
    }

    private void graphIt(JTextField city)
    {
        FiveDayForecast fiveDayForecast = service.getFiveDayForecast(city.getText()).blockingFirst();
        currentWeatherView.setForecastWeather(fiveDayForecast);
    }
}