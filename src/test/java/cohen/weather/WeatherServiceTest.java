package cohen.weather;

import cohen.weather.jsonForCurrent.CurrentWeather;
import cohen.weather.jsonForFiveDay.FiveDayForecast;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest
{
    @Test
    public void getCurrentWeather()
    {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        //when
        CurrentWeather weather = service.getCurrentWeather("chicago").blockingFirst();
        //then
        assertNotNull(weather);
    }

    @Test
    public void getFiveDayForecast()
    {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        //when
        FiveDayForecast fiveDayForecast = service.getFiveDayForecast("chicago").blockingFirst();
        //then
        assertNotNull(fiveDayForecast);
    }
}