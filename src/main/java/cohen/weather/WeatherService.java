package cohen.weather;

import cohen.weather.json.CurrentWeather;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface WeatherService
{
    @GET("data/2.5/weather?q=chicago&appid=da9ab98f611490083a79456bd71f4370&units=imperial")
    Observable<CurrentWeather> getCurrentWeather();
}
