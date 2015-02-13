package co.mobilemakers.wiki;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ProjectListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        if (savedInstanceState == null) {
            if(getIntent() != null) {
                ProjectListFragment projectListFragment = new ProjectListFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ProjectListFragment.CATEGORY, getIntent().getExtras().getString(ProjectListFragment.CATEGORY));
                projectListFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, projectListFragment)
                        .commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
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
