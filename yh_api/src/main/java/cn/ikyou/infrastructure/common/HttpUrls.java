package cn.ikyou.infrastructure.common;

public class HttpUrls {
    /**
     *     获取桩号地址 post 参数{"formname":"ZH_matching","longitude":124.738073,"latitude":43.402643}
     * {"code":"0","message":"success","body":[{"ZH":"K2+000","GS":"G2501","distance":"0.2712","ZH_m":"2000"},
     * {"ZH":"K3+000","GS":"G2501","distance":"0.7286","ZH_m":"3000"}]}
     */
    public static final String LOCATION_URL="http://8.142.158.252:52000/WebAPI/";
    //获取天气 get 参数 city=城市名称。返回xml 取 forecast下内容
    public static final String WEATHER_URL ="http://wthrcdn.etouch.cn/WeatherApi";
}
