package com.example.asus.project3;

import java.io.Serializable;

/*  存储相应的数据 */
public class Info implements Serializable {
    private String name;
    private String money;
    private String info1;
    private String info2;
    private String background;

    public Info(String name, String money, String info1, String info2, String background) {
        this.name = name;
        this.money = money;
        this.info1 = info1;
        this.info2 = info2;
        this.background = background;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }

    public String getInfo1() {
        return info1;
    }
    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }
    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        this.background = background;
    }

    public char getcycle() {
        char first = name.charAt(0);
        if (first >= 97 && first <= 122) {
            first -= 32;
        }
        return first;
    }
}
