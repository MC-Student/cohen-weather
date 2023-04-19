package cohen.weather;

import cohen.weather.jsonForCurrent.CurrentWeather;
import cohen.weather.jsonForFiveDay.FiveDayForecast;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService
{
    @GET("data/2.5/weather?appid=da9ab98f611490083a79456bd71f4370&units=imperial")
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String location);

    @GET("data/2.5/forecast?appid=da9ab98f611490083a79456bd71f4370&units=imperial")
    Observable<FiveDayForecast> getFiveDayForecast(@Query("q") String cityName);
}