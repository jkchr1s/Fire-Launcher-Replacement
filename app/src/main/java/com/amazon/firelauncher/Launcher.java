package com.amazon.firelauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class Launcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        launchFirstLauncher();
        finish();
    }

    private void launchFirstLauncher() {
        boolean foundLauncher = false;

        PackageManager pm = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> lst = pm.queryIntentActivities(i, 0);
        if (lst.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No launchers found!", Toast.LENGTH_LONG).show();
        }
        for (ResolveInfo resolveInfo : lst) {
            if (!resolveInfo.activityInfo.packageName.equals("com.amazon.firelauncher")) {
                Log.d("Test", "New Launcher Found: " + resolveInfo.activityInfo.packageName);
                Intent intent = getPackageManager().getLaunchIntentForPackage(resolveInfo.activityInfo.packageName);
                startActivity(intent);
                foundLauncher = true;
                break;
            }
        }

        if (!foundLauncher) {
            Toast.makeText(getApplicationContext(), "No launchers found!", Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_launcher, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
