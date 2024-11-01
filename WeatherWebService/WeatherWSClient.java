package lab4.WeatherWebService;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;

import java.util.List;

public class WeatherWSClient {
    public static void main(String[] args) {

        WeatherWS factory = new WeatherWS();
        WeatherWSSoap weatherWebServiceSoap = factory.getWeatherWSSoap();

        //查询支持的省份
        ArrayOfString weatherInfo = weatherWebServiceSoap.getRegionProvince();
        List<String> lstWeatherInfo = weatherInfo.getString();
        System.out.println("支持的省份：");
        for (String string : lstWeatherInfo) {
            System.out.println(string);
        }
        System.out.println("-----------------------");

        //根据省份名称查询支持的城市
        ArrayOfString cityInfo = weatherWebServiceSoap.getSupportCityString("31111");
        List<String> lstCityInfo = cityInfo.getString();
        System.out.println("你要查询的省份支持的城市：");
        for (String string : lstCityInfo) {
            System.out.println(string);
        }
        //查询城市天气预报
        ArrayOfString result = weatherWebServiceSoap.getWeather("1994", null);
        List<String> list = result.getString();
        System.out.println("城市天气预报");
        for (String string : list) {
            System.out.println(string);
        }


        System.out.println("-----------------------");


    }
}