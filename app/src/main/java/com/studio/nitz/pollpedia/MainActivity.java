package com.studio.nitz.pollpedia;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ListView mListview;
    private String[] planets;
    public Menu menu;
    public String titleSelected;
    public ArrayAdapter<String> adapter;
    //private ParseQueryAdapter<ParseObject> mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        planets = getResources().getStringArray(R.array.planets);
        mListview = (ListView) findViewById(R.id.category);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, planets);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        menu = navigationView.getMenu();
        menu.setGroupCheckable(1,false,true);
        /*
        mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Category");
        mainAdapter.setTextKey("categoryId");
        categoryAdapter = new CategoryAdapter(this);
        */
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    int len=objects.size();
                    for (int i=0;i<len;i++){
                        ParseObject categoryObject = objects.get(i);
                        menu.add(categoryObject.getString("categoryName"));
                    }
                    mListview.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to fetch response from server", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                titleSelected = menuItem.getTitle().toString();
                selectDrawerItem(menuItem);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void selectDrawerItem(MenuItem menuItem) {
        String selection = menuItem.getTitle().toString();
        Fragment fragment=null;
        switch (selection) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case "Bollywood":
                Toast.makeText(getApplicationContext(), "Bollywood", Toast.LENGTH_SHORT).show();
                fragment = new FragmentBollywood();
                break;
            case "Entertainment":
                Toast.makeText(getApplicationContext(), "Entertainment", Toast.LENGTH_SHORT).show();
                fragment = new FragmentCricket();
                break;
            case "Politics":
                Toast.makeText(getApplicationContext(), "Politics", Toast.LENGTH_SHORT).show();
                fragment = new FragmentBollywood();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                fragment = new FragmentBollywood();
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        //Check to see which item was being clicked and perform appropriate action
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

}
