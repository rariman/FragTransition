package com.rariman.fragtrasition;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements ViewListFragment.ViewListFragmentListener, ColorFragment.ColorFragmentListener{

    public static final String COLOR_INFO = "color_info";
    public static final String VIEW_ID = "view_info";
    private int itemcolorpos;

    ViewListFragment viewListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewListFragment = new ViewListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentcontainer, viewListFragment);
        transaction.commit();
    }

    @Override
    public void onsetColorView(int color) {
        getFragmentManager().popBackStack();
        viewListFragment.updateViewColor(itemcolorpos, color);
    }

    @Override
    public void onViewSelected(int position, int color) {
        itemcolorpos = position;
        displayColorFragment(color);
    }

    public void displayColorFragment(int color){
        ColorFragment colorFragment = new ColorFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(COLOR_INFO, color);
        colorFragment.setArguments(arguments);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentcontainer, colorFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
}
