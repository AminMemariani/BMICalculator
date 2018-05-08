package com.aminmemariani.apps.bmicalculator.dataModel

import com.orm.SugarRecord
import com.orm.dsl.Table

@Table
class Bmi : SugarRecord {
    lateinit var bmi: String
    lateinit var date: String

    constructor() {}

    constructor(BMI: String, date: String) {

        this.bmi = BMI
        this.date = date
    }
}
