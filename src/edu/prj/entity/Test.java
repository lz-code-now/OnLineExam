package edu.prj.entity;

import java.util.Date;

public class Test {
    private Date greateOn = new Date(System.currentTimeMillis());

    public Date getGreateOn() {
        return greateOn;
    }

    public void setGreateOn(Date greateOn) {
        this.greateOn = greateOn;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setGreateOn(new Date());
        System.out.println( test.greateOn);
    }
}
