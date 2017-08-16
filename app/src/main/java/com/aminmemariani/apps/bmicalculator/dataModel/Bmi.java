package com.aminmemariani.apps.bmicalculator.dataModel;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

@Table
public class Bmi extends SugarRecord{
    String BMI;
    String Date;

    public Bmi() {
    }

    public Bmi(String BMI, String date) {

        this.BMI = BMI;
        Date = date;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
