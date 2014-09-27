package cz.devsmad.formulas;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.widget.SimpleAdapter;



public class SingleListItem extends ListActivity {


    private static final String LOG_TAG = "InterstitialSample";
    private InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-6821278643779249/8626160618";
        
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            // Create an ad.
            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId(AD_UNIT_ID);

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    //.addTestDevice("F8021EF0FE7424CD02398985E941E87E")
                    .build();

            // Load the interstitial ad.
            interstitialAd.loadAd(adRequest);

            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
            }


            // Set the AdListener.
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    Log.d(LOG_TAG, "onAdLoaded");


                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            //.addTestDevice("F8021EF0FE7424CD02398985E941E87E")
                            .build();

                    // Load the interstitial ad.
                    interstitialAd.loadAd(adRequest);

                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
                    }

                }

            });


            Intent i = getIntent();
            int product = i.getIntExtra("product",0);

           // Bundle bundle=getIntent().getExtras();
            //String product=bundle.getString("product");
            //int prac = product.length();
            int y;
            String[] nazvy;
            String[] vzorce;
            
            switch (product){
            case 0: 	nazvy = getResources().getStringArray(R.array.mat);
            			vzorce = getResources().getStringArray(R.array.matv);
            			y = nazvy.length;
            			funkce(nazvy,vzorce,y);
            			break;
            	
            case 1: 	nazvy = getResources().getStringArray(R.array.fyz);
						vzorce = getResources().getStringArray(R.array.fyzv);
						y = nazvy.length;
            			funkce(nazvy,vzorce,y);
						break;
            	
            case 2: 	nazvy = getResources().getStringArray(R.array.chem);
						vzorce = getResources().getStringArray(R.array.chemv);
						y = nazvy.length;
            			funkce(nazvy,vzorce,y);
						break;
                default: 	nazvy = getResources().getStringArray(R.array.chem);
                    vzorce = getResources().getStringArray(R.array.chemv);
                    y = nazvy.length;
                    funkce(nazvy,vzorce,y);
                    break;
            	
            
            }

            
        }



        public void funkce(String[] nazvy,String[] vzorce, int y) {
            
            final ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
            
            
                HashMap<String, Object> row  = new HashMap<String, Object>();

                for(int x = 0; x < y; x = x+1){
                	row  = new HashMap<String, Object>();
                    row.put("Nazev", nazvy[x]);
                    row.put("Vzorec", vzorce[x]);
                    data.add(row);
                }
             
            

            
            SimpleAdapter adapter = new SimpleAdapter(this,
                    data,
                      R.layout.single_list_item_view,
                      new String[] {"Nazev","Vzorec"},
                      new int[] {R.id.text1,R.id.text2});
           setListAdapter(adapter);


           
        }



    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view, menu);
        
        MenuItem item = menu.findItem(R.id.exit);
        item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                SingleListItem.this.AppExit();
                return true;
            }
        });
        
        MenuItem about = menu.findItem(R.id.about);
        about.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


            	 // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), OpenAbout.class);
                startActivity(i);

                return true;
            }
        });
        

        
        return true;
    } 


    public void AppExit()
    {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

       
    }
    
    
}
