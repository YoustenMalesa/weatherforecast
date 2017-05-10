package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YMalesa on 2017/05/10.
 */

public class Clouds {
    @SerializedName("all") private String all;

    public String getAll ()
    {
        return all;
    }

    public void setAll (String all)
    {
        this.all = all;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [all = "+all+"]";
    }
}
