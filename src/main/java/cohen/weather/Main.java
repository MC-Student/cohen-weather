package cohen.weather;

import cohen.weather.dagger.DaggerWeatherForecastComponent;
import cohen.weather.dagger.WeatherForecastComponent;

public class Main
{
    public static void main(String[] args)
    {
        WeatherForecastComponent component = DaggerWeatherForecastComponent
                .builder()
                .build();
        CurrentWeatherFrame frame = component.providesCurrentWeatherFrame();
        frame.setVisible(true);
    }
}