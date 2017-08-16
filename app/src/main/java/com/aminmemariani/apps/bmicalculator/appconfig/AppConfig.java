package com.aminmemariani.apps.bmicalculator.appconfig;
import android.app.Application;
import com.orm.SugarContext;
public class AppConfig extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        SugarContext.init(this);}
    @Override
    public void onTerminate(){
        super.onTerminate();
        SugarContext.terminate();}}