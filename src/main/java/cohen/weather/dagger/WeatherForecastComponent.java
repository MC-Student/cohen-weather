package cohen.weather.dagger;

import cohen.weather.ForecastFrame;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {WeatherServiceModule.class})

public interface WeatherForecastComponent
{
    ForecastFrame providesForecastFrame();
}