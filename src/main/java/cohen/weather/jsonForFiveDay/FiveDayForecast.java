package cohen.weather.jsonForFiveDay;

import java.util.List;

public class FiveDayForecast
{
    private City city;
    private Long cnt;
    private String cod;
    private List<InfoForFiveDay> list;
    private Long message;

    public List<InfoForFiveDay> getList()
    {
        return list;
    }
}