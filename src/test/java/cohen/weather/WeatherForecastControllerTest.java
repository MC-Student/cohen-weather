package cohen.weather;

import cohen.weather.jsonForFiveDay.FiveDayForecast;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherForecastControllerTest
{
    static
    {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void updateWeather()
    {
        //given
        WeatherService service = mock();
        ForecastView view = mock();
        WeatherForecastController controller = new WeatherForecastController(view, service);
        FiveDayForecast fiveDayForecast = mock();
        Observable<FiveDayForecast> observableForecast = Observable.just(fiveDayForecast);
        doReturn(observableForecast).when(service).getFiveDayForecast("New York");

        //when
        controller.updateWeather("New York");

        //then
        verify(service).getFiveDayForecast("New York");
        verify(view).setForecastWeather(fiveDayForecast);
    }
}