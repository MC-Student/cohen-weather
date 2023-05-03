package cohen.weather;

import cohen.weather.jsonForFiveDay.FiveDayForecast;
import cohen.weather.jsonForFiveDay.InfoForFiveDay;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ForecastView extends JComponent
{
    public FiveDayForecast fiveDayForecast;

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int height = getHeight();
        g.translate(0, height);

        int x1;
        int y1;
        int x2 = 0;
        int y2 = 0;

        int time = 0;

        if (fiveDayForecast != null)
        {
            List<InfoForFiveDay> info = fiveDayForecast.getList();

            for (InfoForFiveDay threeHours : info)
            {
                x1 = x2;
                y1 = y2;
                x2 = time;
                y2 = (int) (double) threeHours.getMain().getTemp();

                g.drawLine(x1, -y1 * 5, x2, -y2 * 5);

                time += 20;
            }
        }
    }

    public void setForecastWeather(FiveDayForecast fiveDayForecast)
    {
        this.fiveDayForecast = fiveDayForecast;
        repaint();
    }
}