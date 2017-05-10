package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YMalesa on 2017/05/10.
 */

public class Main {
    @SerializedName("humidity")  private String humidity;

    @SerializedName("pressure")   private String pressure;

    @SerializedName("temp_max")   private String temp_max;

    @SerializedName("sea_level")  private String sea_level;

    @SerializedName("temp_min")   private String temp_min;

    @SerializedName("temp")       private String temp;

    @SerializedName("grnd_level") private String grnd_level;

    public String getHumidity ()
    {
        return humidity;
    }

    public void setHumidity (String humidity)
    {
        this.humidity = humidity;
    }

    public String getPressure ()
    {
        return pressure;
    }

    public void setPressure (String pressure)
    {
        this.pressure = pressure;
    }

    public String getTemp_max ()
    {
        return temp_max;
    }

    public void setTemp_max (String temp_max)
    {
        this.temp_max = temp_max;
    }

    public String getSea_level ()
    {
        return sea_level;
    }

    public void setSea_level (String sea_level)
    {
        this.sea_level = sea_level;
    }

    public String getTemp_min ()
    {
        return temp_min;
    }

    public void setTemp_min (String temp_min)
    {
        this.temp_min = temp_min;
    }

    public String getTemp ()
    {
        return temp;
    }

    public void setTemp (String temp)
    {
        this.temp = temp;
    }

    public String getGrnd_level ()
    {
        return grnd_level;
    }

    public void setGrnd_level (String grnd_level)
    {
        this.grnd_level = grnd_level;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [humidity = "+humidity+", pressure = "+pressure+", temp_max = "+temp_max+", sea_level = "+sea_level+", temp_min = "+temp_min+", temp = "+temp+", grnd_level = "+grnd_level+"]";
    }
}
