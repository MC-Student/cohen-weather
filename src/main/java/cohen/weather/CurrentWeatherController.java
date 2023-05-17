package cohen.weather;

import cohen.weather.jsonForCurrent.CurrentWeather;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeatherController
{
    private WeatherService service;
    private JLabel imageLabel;
    private JLabel degreesLabel;

    @Inject
    public CurrentWeatherController(WeatherService service,
                                    @Named("imageLabel") JLabel imageLabel,
                                    @Named("degreesLabel") JLabel degreesLabel)
    {
        this.service = service;
        this.imageLabel = imageLabel;
        this.degreesLabel = degreesLabel;
    }

    public void updateWeather(String city)
    {
        service.getCurrentWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::setCurrentWeather, Throwable::printStackTrace);
    }

    public void setCurrentWeather(CurrentWeather currentWeather) throws MalformedURLException
    {
        degreesLabel.setText(String.valueOf(currentWeather.getMain().getTemp()));
        String icon = currentWeather.getWeather().get(0).getIcon();
        String url = "http://openweathermap.org/img/w/" + icon + ".png";
        imageLabel.setIcon(new ImageIcon(new URL(url)));
    }
}
