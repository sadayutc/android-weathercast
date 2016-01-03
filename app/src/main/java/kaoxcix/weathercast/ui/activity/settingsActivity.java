package kaoxcix.weathercast.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import kaoxcix.weathercast.R;

public class settingsActivity extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(R.id.settings_content, new settingsFragment()).commit();

        initSharedPreferences();
        initActionAndStatusBar();
    }

    private void initSharedPreferences() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initActionAndStatusBar() {
        int actionBarColor = sp.getInt("actionBarColor", R.color.colorPrimary);
        int statusBarColor = sp.getInt("statusBarColor", R.color.colorPrimaryDark);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(actionBarColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(statusBarColor));
        }
    }

    public static class settingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(settingsActivity.this, mainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(settingsActivity.this, mainActivity.class);
        startActivity(intent);
    }
}
