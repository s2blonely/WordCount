package lab4.WeatherWebService;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;

import java.util.List;

public class WeatherWSClient {
    public static void main(String[] args) {

        WeatherWS factory = new WeatherWS();
        WeatherWSSoap weatherWebServiceSoap = factory.getWeatherWSSoap();

        //��ѯ֧�ֵ�ʡ��
        ArrayOfString weatherInfo = weatherWebServiceSoap.getRegionProvince();
        List<String> lstWeatherInfo = weatherInfo.getString();
        System.out.println("֧�ֵ�ʡ�ݣ�");
        for (String string : lstWeatherInfo) {
            System.out.println(string);
        }
        System.out.println("-----------------------");

        //����ʡ�����Ʋ�ѯ֧�ֵĳ���
        ArrayOfString cityInfo = weatherWebServiceSoap.getSupportCityString("31111");
        List<String> lstCityInfo = cityInfo.getString();
        System.out.println("��Ҫ��ѯ��ʡ��֧�ֵĳ��У�");
        for (String string : lstCityInfo) {
            System.out.println(string);
        }
        //��ѯ��������Ԥ��
        ArrayOfString result = weatherWebServiceSoap.getWeather("1994", null);
        List<String> list = result.getString();
        System.out.println("��������Ԥ��");
        for (String string : list) {
            System.out.println(string);
        }


        System.out.println("-----------------------");


    }
}