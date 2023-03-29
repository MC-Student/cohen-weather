package cohen.weather;

import cohen.weather.json.CurrentWeather;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest
{
    @GET("data/2.5/weather?q=chicago&appid=da9ab98f611490083a79456bd71f4370&units=imperial")

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
        CurrentWeather weather = service.getCurrentWeather().blockingFirst();
        //then
        assertNotNull(weather);
    }
}