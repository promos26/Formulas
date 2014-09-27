package cz.devsmad.formulas;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


//import com.google.ads.*;
import com.google.android.gms.ads.*;


public class AndroidListViewActivity extends ListActivity {

    private AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         
        // storing string resources into Array
        String[] vzorce = getResources().getStringArray(R.array.vzorce);
        //setContentView(R.layout.list_item);
         //Button btn = (Button) findViewById(R.id.mathe);


        
        
         
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.listView1, vzorce));
     
        ListView lv = getListView();




        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item 
              int product = position;

              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("product", product);
              startActivity(i);
             
          } 
    
        });
  
 
     
    }
    /*
    public void mfc(View v) {
        // does something very interesting
    	System.out.println(v);
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view, menu);

        
        MenuItem exit = menu.findItem(R.id.exit);
        exit.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AndroidListViewActivity.this.AppExit();
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
