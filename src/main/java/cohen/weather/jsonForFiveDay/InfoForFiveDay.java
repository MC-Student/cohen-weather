package cohen.weather.jsonForFiveDay;

import cohen.weather.jsonForAll.Clouds;
import cohen.weather.jsonForAll.Weather;
import cohen.weather.jsonForAll.Wind;

import java.util.List;

public class InfoForFiveDay
{
    private Clouds clouds;
    private Long dt;
    private String dt_txt;
    private Main main;
    private Double pop;
    private Rain rain;
    private Sys sys;
    private Long visibility;
    private List<Weather> weather;
    private Wind wind;

    public Main getMain()
    {
        return main;
    }
}