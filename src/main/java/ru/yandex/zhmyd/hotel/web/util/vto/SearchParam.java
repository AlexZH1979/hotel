package ru.yandex.zhmyd.hotel.web.util.vto;


import java.io.Serializable;

public class SearchParam implements Serializable{
    private String parameter;
    private String value;
    private Integer begin;
    private Integer count;

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public SearchParam() {
    }

    public String getParameter() {
        return parameter;
    }

    public SearchParam(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "parameter='" + parameter + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
