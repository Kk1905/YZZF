package com.example.administrator.yzzf.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class CityModel extends Model{
    private String name;
    private List<DistrictModel> districts;

    public CityModel() {
        super();
    }

    public CityModel(String name, List<DistrictModel> districts) {
        super();
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public List<DistrictModel> getDistricts() {
        return districts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistricts(List<DistrictModel> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "CityModel [name=" + name + ", districts=" + districts + "]";
    }
}
