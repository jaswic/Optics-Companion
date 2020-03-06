package com.example.opticscompanion;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;


import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.opticscompanion.CenteringFragment;
import com.example.opticscompanion.EntryFragment;
import com.example.opticscompanion.LensActivity;
import com.example.opticscompanion.ProcessActivity;
import com.example.opticscompanion.R;
import com.example.opticscompanion.ZygoActivity;

public class NavigationMenuFunctions extends FragmentActivity {

    public void onMenuItemSelected(MenuItem item, Context context){
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch (id) {
            case R.id.nav_spheres:
                intent = new Intent(context, LensActivity.class);
                break;
            case R.id.nav_centering:
                fragment = new CenteringFragment();
                break;
            case R.id.nav_zygo:
                intent = new Intent(context, ZygoActivity.class);
                break;
            case R.id.nav_process:
                intent = new Intent(context, ProcessActivity.class);
                break;

            default:
                fragment = new EntryFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return;
    }


}
