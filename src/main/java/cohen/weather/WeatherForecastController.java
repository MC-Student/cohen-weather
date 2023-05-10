package cohen.weather;

import io.reactivex.rxjava3.schedulers.Schedulers;

import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;

public class WeatherForecastController
{
    private ForecastView forecastView;
    private WeatherService weatherService;

    @Inject
    public WeatherForecastController(ForecastView forecastView, WeatherService weatherService)
    {
        this.forecastView = forecastView;
        this.weatherService = weatherService;
    }

    public void updateWeather(String city)
    {
        weatherService.getFiveDayForecast(city)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(forecastView::setForecastWeather, Throwable::printStackTrace);
    }
}