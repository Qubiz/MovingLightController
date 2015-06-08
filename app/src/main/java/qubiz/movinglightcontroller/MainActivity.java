package qubiz.movinglightcontroller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import qubiz.movinglightcontroller.adapters.MyPagerAdapter;
import qubiz.movinglightcontroller.tabs.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

    private ViewPager viewPager;
    private SlidingTabLayout tabs;
    private SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_control);

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
        MenuItem item = menu.findItem(R.id.menu_item_main_on_off_switch);
        item.setActionView(R.layout.toolbar_switch);
        item = menu.findItem(R.id.menu_item_empty_space);
        item.setActionView(R.layout.toolbar_empty_item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeVariables() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(viewPager);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + getTitle() + "</font>"));
    }


}
