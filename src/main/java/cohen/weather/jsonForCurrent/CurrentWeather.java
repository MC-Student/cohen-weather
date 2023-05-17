package cohen.weather.jsonForCurrent;

import cohen.weather.jsonForAll.Clouds;
import cohen.weather.jsonForAll.Coord;
import cohen.weather.jsonForAll.Weather;
import cohen.weather.jsonForAll.Wind;

import java.util.List;

public class CurrentWeather
{
    private String base;
    private Clouds clouds;
    private Long cod;
    private Coord coord;
    private Long dt;
    private Long id;
    private Main main;
    private String name;
    private Sys sys;
    private Long timezone;
    private Long visibility;
    private List<Weather> weather;
    private Wind wind;

    public Main getMain ()
    {
        return main;
    }

    public List<Weather> getWeather()
    {
        return weather;
    }
}