package com.halibobo.weather.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;
import com.halibobo.weather.JacksonUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * cat Weather| uniq | sort | awk '{print "private String " $1 " ;"}'
 *
 *  1 // DateTimeFormatter - SimpleDateFormat
 *  2 // Intant - Date
 *  3 // LocalDateTime - Calendar
 *  4
 *  5 // date与instant的相互转化
 *  6 Instant now = Instant.now();
 *  7 Date date = Date.from(now);
 *  8 Instant instant = date.toInstant();
 *  9 // LocalDateTime代替Calendar
 * 10 ZoneId zoneId = ZoneId.systemDefault();
 * 11 LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
 * 12 int year = localDateTime.getYear();
 * 13 int month = localDateTime.getMonthValue();
 * 14 int dayOfMonth = localDateTime.getDayOfMonth();
 * 15 int hour = localDateTime.getHour();
 * 16 int minute = localDateTime.getMinute();
 * 17 int second = localDateTime.getSecond();
 * 18 // DateTimeFormatter代替SimpleDateFormat
 * 19 DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 * 20 // Date格式化成String
 * 21 String format = dateTimeFormatter.format(localDateTime);
 * 22 LocalDateTime parse = LocalDateTime.parse(format, dateTimeFormatter);
 * 23 ZoneOffset offset = OffsetDateTime.now().getOffset();
 * 24 Instant instant2 = parse.toInstant(offset);
 * 25 // 从String获得Date
 * 26 Date from = Date.from(instant2);
 */
public class Weather implements Serializable {


    private final static DateTimeFormatter DATE_TIME_FORMATTER;
    public final transient static Weather EMTPY;

    static {
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH");
        EMTPY = new Weather();
        EMTPY.setPyName("empty");
        EMTPY.setRtm(DATE_TIME_FORMATTER.format(Instant.EPOCH.atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime()));
    }

    static final long serialVersionUID = 126789345698L;


    private String centername ;
    private String cityX ;
    private String cityY ;
    private String cityname ;
    private String fontColor ;
    private String humidity ;
    private String pyName ;
    private String state1 ;
    private String state2 ;
    private String stateDetailed ;
    private String tem1 ;
    private String tem2 ;
    private String temNow ;
    private String time ;
    private String url ;
    private String windDir ;
    private String windPower ;
    private String windState ;



    public String getRtm() {
        return rtm;
    }

    public void setRtm(String rtm) {
        this.rtm = rtm;
    }

    private String rtm;

    public Weather() {
        setRtm(DATE_TIME_FORMATTER.format(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime()));
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getStateDetailed() {
        return stateDetailed;
    }

    public void setStateDetailed(String stateDetailed) {
        this.stateDetailed = stateDetailed;
    }

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getWindState() {
        return windState;
    }

    public void setWindState(String windState) {
        this.windState = windState;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    public String getCityX() {
        return cityX;
    }

    public void setCityX(String cityX) {
        this.cityX = cityX;
    }

    public String getCityY() {
        return cityY;
    }

    public void setCityY(String cityY) {
        this.cityY = cityY;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemNow() {
        return temNow;
    }

    public void setTemNow(String temNow) {
        this.temNow = temNow;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Weather weather = (Weather) o;
        return Objects.equal(url, weather.url) &&
                Objects.equal(rtm, weather.rtm);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, rtm);
    }

    @Override
    public String toString() {
        try {
            return JacksonUtil.write2JsonStr(this);
        } catch (JsonProcessingException e) {
        }
        return "{}";
    }

    public static Weather fromString(String data) {
        try {
            return JacksonUtil.json2Object(data,Weather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return EMTPY;
    }

    public static void main(String[] args) {
        System.out.println(Weather.EMTPY);
        ThreadLocal<SimpleDateFormat> SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd:HH"));
        ExecutorService ts = Executors.newFixedThreadPool(100);
        for (; ; ) {
            ts.execute(() -> {
                try {
                   String s = SDF.get().format(Date.from(Instant.now()));
                   System.out.println(s);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }finally {
                   SDF.remove();
                }
            });

        }
    }
}
