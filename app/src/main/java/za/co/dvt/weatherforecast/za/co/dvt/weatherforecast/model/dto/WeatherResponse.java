package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YMalesa on 2017/05/10.
 */

public class WeatherResponse {
    @SerializedName("id") private String id;

    @SerializedName("dt") private String dt;

    @SerializedName("clouds") private Clouds clouds;

    @SerializedName("coord") private Coordinates coord;

    @SerializedName("wind") private Wind wind;

    @SerializedName("cod") private String cod;

    @SerializedName("sys") private Sys sys;

    @SerializedName("name") private String name;

    @SerializedName("base") private String base;

    @SerializedName("weather") private Weather[] weather;

    @SerializedName("main") private Main main;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Coordinates getCoord ()
    {
        return coord;
    }

    public void setCoord (Coordinates coord)
    {
        this.coord = coord;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getBase ()
    {
        return base;
    }

    public void setBase (String base)
    {
        this.base = base;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", dt = "+dt+", clouds = "+clouds+", coord = "+coord+", wind = "+wind+", cod = "+cod+", sys = "+sys+", name = "+name+", base = "+base+", weather = "+weather+", main = "+main+"]";
    }
}
