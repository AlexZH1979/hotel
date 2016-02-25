package ru.yandex.zhmyd.hotel.web.util.vto;


import java.io.Serializable;

public class ListViewPart implements Serializable{
    private String firstResult;
    private String selectCount;

    public String getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(String firstResult) {
        this.firstResult = firstResult;
    }

    public String getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(String selectCount) {
        this.selectCount = selectCount;
    }

    public Integer getFirst(){
       return Integer.parseInt(this.firstResult);
    }

    public Integer getCount(){
       return Integer.parseInt(this.selectCount);
    }

    @Override
    public String toString() {
        return "ListViewPart{" +
                "firstResult='" + firstResult + '\'' +
                ", selectCount='" + selectCount + '\'' +
                '}';
    }
}
