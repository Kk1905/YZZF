package com.example.administrator.yzzf.Util;

import com.example.administrator.yzzf.Model.CityModel;
import com.example.administrator.yzzf.Model.DistrictModel;
import com.example.administrator.yzzf.Model.ProvinceModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class XmlParserHandler extends DefaultHandler {
    /*
    存储所有对象
     */
    private List<ProvinceModel> provinceList = new ArrayList<>();
    ProvinceModel provinceModel = new ProvinceModel();
    CityModel cityModel = new CityModel();
    DistrictModel districtModel = new DistrictModel();

    public XmlParserHandler() {

    }

    public List<ProvinceModel> getProvinceList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        //当读到第一个开始标签的时候会触发该方法
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //当遇到开始标记的时候调用该方法
        if (qName.equals("province")) {
            provinceModel = new ProvinceModel();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityies(new ArrayList<CityModel>());
        } else if (qName.equals("city")) {
            cityModel = new CityModel();
            cityModel.setName(attributes.getValue(0));
            cityModel.setDistricts(new ArrayList<DistrictModel>());
        } else if (qName.equals("district")) {
            districtModel = new DistrictModel();
            districtModel.setName(attributes.getValue(0));
            districtModel.setZipcode(attributes.getValue(1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //当遇到结束标记的时候调用该方法
        if (qName.equals("district")) {
            cityModel.getDistricts().add(districtModel);
        } else if (qName.equals("city")) {
            provinceModel.getCityies().add(cityModel);
        } else if (qName.equals("province")) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }
}
