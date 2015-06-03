package qubiz.movinglightcontroller;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import qubiz.movinglightcontroller.adapters.MyPagerAdapter;
import qubiz.movinglightcontroller.tabs.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_conrtol);

        initializeVariables();

        /*colorPicker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
                int red = (i >> 16) & 0xff;
                int green = (i >> 8) & 0xff;
                int blue = (i) & 0xff;

                Log.e("COLOR", "(" + red + ", " + green + ", " + blue + ")");
            }
        });*/
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

    private void initializeVariables() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitleTextColor(Color.parseColor("#EFEFEF"));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        tabs.setViewPager(viewPager);


    }
}
