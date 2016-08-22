package activity;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.County;
import model.Province;
import db.CoolWeatherDB;
import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChooseAreaActivity extends Activity {
     public static final int LEVEL_PROVINCE = 0;
     public static final int LEVEL_CITY = 1;
     public static final int LEVEL_COUNTY = 2;
     private ProgressDialog progressDialog;
     private TextView titleText;
     private TextView listView;
     private ArrayAdapter<String> adapter;
     private CoolWeatherDB coolWeatherDB;
     private List<String> dataList = new ArrayList<String>();
     /*
      *  ʡ�б�
      * */
     private List<Province> provinceList;
     /*
      * ���б�
      * */
     private List<City> cityList;
     /*
      * ���б�
      * */
     private List<County> countyList;
     
     /*
      * ѡ�е�ʡ��
      * */
     private Province selectedProvince;
     /*
      * ѡ�еĳ���
      * */
     private Province selectedCity;
     /*
      * ѡ�е��س�
      * */
     private Province selectedCounty;
     /*
      * ѡ�еļ���
      * */
     private int currentLevel;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);s
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        listView = findViewById(R.id.list_view); 
        titleText = findViewById(R.id.title_text);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        coolWeatherDB =CoolWeatherDB.getInstance(this);
        listView.setOnItemClickListener(new OnItemClickListener(){
        	public void OnItemClick(AdapterView<?> arg0,View view ,int index, long arg3){
        		if(currentLevel==LEVEL_PROVINCE){
        			selectedProvince = provinceList.get(index);
        			queryCounties();
        			
        		}
        	}
        });
        queryProvinces();
     
     }
}
