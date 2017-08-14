package org.agile.common.utils;

import org.agile.dto.LocationDto;

public class BdMapUtils {
	
	

    private final static double Rc = 6378137;  // 赤道半径  
    private final static double Rj = 6356725;  // 极半径  
    static double DEF_PI = 3.14159265359; // PI  
    static double DEF_2PI = 6.28318530712; // 2*PI  
    static double DEF_PI180 = 0.01745329252; // PI/180.0  
    static double DEF_R = 6370693.5; // radius of earth   
    private static final Double PI = Math.PI;  
    private static final Double PK = 180 / PI;  
    // 地球半径  
    private static final double EARTH_RADIUS = 6370996.81;  
  
    // 弧度  
    private static double radian(double d) {  
        return d * Math.PI / 180.0;  
    }   
    /** 
     * @Description: 第一种方法 
     * @param lat_a 
     * @param lng_a 
     * @param lat_b 
     * @param lng_b 
     * @param @return    
     * @return double 
     * @author Ryan 
     * @date 2014-9-7 上午10:11:35 
     */  
    public static double getDistanceFromTwoPoints(double lat_a, double lng_a, double lat_b, double lng_b) {  
        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);  
        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);  
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);  
  
        double tt = Math.acos(t1 + t2 + t3);  
  
        System.out.println("两点间的距离：" + 6366000 * tt + " 米");  
        return 6366000 * tt;  
    }  
  
      
    /********************************************************************************************************/  
  
    /** 
     * @Description: 第二种方法 
     * @param lat1 
     * @param lng1 
     * @param lat2 
     * @param lng2    
     * @return void 
     * @author Ryan
     * @date 2014-9-7 上午10:11:55 
     */  
    public static void distanceOfTwoPoints(double lat1, double lng1, double lat2, double lng2) {  
        double radLat1 = radian(lat1);  
        double radLat2 = radian(lat2);  
        double a = radLat1 - radLat2;  
        double b = radian(lng1) - radian(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        s = Math.round(s * 10000) / 10000;  
        double ss = s * 1.0936132983377;  
        System.out.println("两点间的距离是：" + s + "米" + "," + (int) ss + "码");  
    }  
	
    /** 
     *  
     * @param latitue 测点的纬度 
     * @param longitude 测点的经度 
     * @param areaLatitude1 出发地纬度 
     * @param areaLatitude2 目的地纬度
     * @param areaLongitude1 出发地经度 
     * @param areaLongitude2 目的地经度
     * @author Ryan
     * @return 
     */  
    public static boolean isInArea(double latitue,double longitude,double areaLatitude1,double areaLatitude2,double areaLongitude1,double areaLongitude2){  
        if(isInRange(latitue, areaLatitude1, areaLatitude2)){//如果在纬度的范围内  
            if(areaLongitude1*areaLongitude2>0){//如果都在东半球或者都在西半球  
                if(isInRange(longitude, areaLongitude1, areaLongitude2)){  
                    return true;  
                }else {  
                    return false;  
                }  
            }else {//如果一个在东半球，一个在西半球  
                if(Math.abs(areaLongitude1)+Math.abs(areaLongitude2)<180){//如果跨越0度经线在半圆的范围内  
                    if(isInRange(longitude, areaLongitude1, areaLongitude2)){  
                        return true;  
                    }else {  
                        return false;  
                    }  
                }else{//如果跨越180度经线在半圆范围内  
                    double left = Math.max(areaLongitude1, areaLongitude2);//东半球的经度范围left-180  
                    double right = Math.min(areaLongitude1, areaLongitude2);//西半球的经度范围right-（-180）  
                    if(isInRange(longitude, left, 180)||isInRange(longitude, 0, right)){  
                        return true;  
                    }else {  
                        return false;  
                    }  
                }  
            }  
        }else{  
            return false;  
        }  
    }  
      
    public static boolean isInRange(double point, double left,double right){  
            if(point>=Math.min(left, right)&&point<=Math.max(left, right)){  
                return true;  
            }else {  
                return false;  
            }  
          
    }  
    /**
     * 根据已知位置 ，距离和角度计算出目标位置 
     * @param latitude
     * @param longitude
     * @param distance
     * @param angle
     * @return
     */
    public static LocationDto getLocation(double latitude, double longitude, double distance, double angle) {
    	
    	LocationDto dto = new LocationDto();
    	dto.setLatitude(0.0);
    	dto.setLongitude(0.0);
        double m_Latitude;  
        double m_RadLo, m_RadLa;  
        double Ec;  
        double Ed;  
  
        m_Latitude = latitude;  
        m_RadLo = longitude * PI / 180.0;  
        m_RadLa = latitude * PI / 180.0;  
        Ec = Rj + (Rc - Rj) * (90.0 - m_Latitude) / 90.0;  
        Ed = Ec * Math.cos(m_RadLa);  
  
        double dx = distance * 1000 * Math.sin(angle * PI / 180.0);  
        double dy = distance * 1000 * Math.cos(angle * PI / 180.0);  
  
        double BJD = (dx / Ed + m_RadLo) * 180.0 / PI;  
        double BWD = (dy / Ec + m_RadLa) * 180.0 / PI;  
        dto.setLatitude(BJD);
        dto.setLongitude(BWD);
        
        return dto;  
    }  

}
