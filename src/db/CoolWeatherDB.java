package db;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.County;
import model.Province;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
     public static final String DB_NAME = "cool_weather";
     public static final int VERSION = 1;
     private static CoolWeatherDB coolWeatherDB;
     private SQLiteDatabase db;
     private CoolWeatherDB(Context context){
    	 CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
    	 db = dbHelper.getWritableDatabase();
     }
     public synchronized static CoolWeatherDB getInstance(Context context){
    	 if(coolWeatherDB== null){
    		 coolWeatherDB =new CoolWeatherDB(context);
    	 }
		return coolWeatherDB;
     }
     public void saveProvince(Province province){
    	 ContentValues values = new ContentValues();
         values.put("province_name", province.getProvinceName());
         values.put("province_code", province.getProvinceCode());
         db.insert("Province", null, values);
     
     }
     public List<Province> loadProvinces(){
    	 List<Province> list = new ArrayList<Province>();
    	 Cursor cursor = db.query("Province", null, null
    			, null, null, null, null);
		
    	 if(cursor.moveToFirst()){
    		 do{
    		 Province province = new Province();
    		 province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
    		 province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
    		 province.setId(cursor.getInt(cursor.getColumnIndex("province_code")));
    	     list.add(province);
    	 }while(cursor.moveToNext());
    	 if(cursor!=null){
    	     cursor.close();
    	 }
     }
    	 return list;
     
     }
     public void saveCity(City city){
    	 ContentValues values = new ContentValues();
         values.put("city_name", city.getCityName());
         values.put("city_code", city.getCityCode());
         values.put("province_id", city.getProvinceId());
         db.insert("City", null, values);
     }
     public List<City> loadCity(int provinceId){
    	 List<City> list = new ArrayList<City>();
    	 Cursor cursor = db.query("City", null,"province_id=?"
    			, new String[]{String.valueOf(provinceId)}, null, null, null);
		
    	 if(cursor.moveToFirst()){
    		 do{
    	     City city = new City();
    	     city.setCityName(cursor.getString(cursor.getColumnIndex("city")));
    	     city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
    	     city.setId(cursor.getInt(cursor.getColumnIndex("city_code")));
    	     list.add(city);
    	 }while(cursor.moveToNext());
    	 if(cursor!=null){
    	     cursor.close();
    	 }
     }
    	 return list;
     
     }
     public void saveCounty(County county){
    	 if(county!=null){
    	 ContentValues values = new ContentValues();
         values.put("County_name", county.getCountyName());
         values.put("County_code", county.getCountyCode());
         values.put("city_id", county.getCityId());
         db.insert("County", null, values);
    	 }
    }
     public List<County> loadProvinces(int cityId){
    	 List<County> list = new ArrayList<County>();
    	 Cursor cursor = db.query("County", null, "city_id=?"
    			,new String[]{String.valueOf(cityId)}, null, null, null);
		
    	 if(cursor.moveToFirst()){
    		 do{
    		 County county = new County();
    		 county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
    		 county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
    		 county.setId(cursor.getInt(cursor.getColumnIndex("county_code")));
    		 county.setCityId(cityId);
    	     list.add(county);
    	 }while(cursor.moveToNext());
    	 if(cursor!=null){
    	     cursor.close();
    	 }
     }
    	 return list;
     
     }
     }
