package com.example.administrator.yzzf.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class ProvinceModel extends Model {
    private String name;
    private List<CityModel> cityies;

    public ProvinceModel() {
        super();
    }

    public ProvinceModel(String name, List<CityModel> cityies) {
        super();
        this.name = name;
        this.cityies = cityies;
    }

    public String getName() {
        return name;
    }

    public List<CityModel> getCityies() {
        return cityies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityies(List<CityModel> cityies) {
        this.cityies = cityies;
    }

    @Override
    public String toString() {
        return "ProvinceModel [name=" + name + ", cities=" + cityies + "]";
    }
}
