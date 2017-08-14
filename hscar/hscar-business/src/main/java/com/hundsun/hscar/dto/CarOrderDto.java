package com.hundsun.hscar.dto;

/**
 * ${DESCRIPTION}
 *
 * @author shouchen<shouchen21647@hundsun.com>
 * @create 2017-08-10 19:12
 */

public class CarOrderDto extends BaseOrderDto {
    private Double depLongitude;
    private Double depLatitude;
    private Double desLongitude;
    private Double desLatitude;

    public Double getDepLongitude() {
        return depLongitude;
    }

    public void setDepLongitude(Double depLongitude) {
        this.depLongitude = depLongitude;
    }

    public Double getDepLatitude() {
        return depLatitude;
    }

    public void setDepLatitude(Double depLatitude) {
        this.depLatitude = depLatitude;
    }

    public Double getDesLongitude() {
        return desLongitude;
    }

    public void setDesLongitude(Double desLongitude) {
        this.desLongitude = desLongitude;
    }

    public Double getDesLatitude() {
        return desLatitude;
    }

    public void setDesLatitude(Double desLatitude) {
        this.desLatitude = desLatitude;
    }
}
