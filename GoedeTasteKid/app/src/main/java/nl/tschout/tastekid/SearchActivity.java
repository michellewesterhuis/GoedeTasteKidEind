package nl.tschout.tastekid;

import nl.tschout.tastekid.util.tastekidAdapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private static final String TAG = "MY_TAG";
    public String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TabLayout tabs = (TabLayout) findViewById(R.id.main_tablayout);
        final ViewPager pager = (ViewPager) findViewById(R.id.main_pager);
        pager.setAdapter(new tastekidAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);

        final TextView search = (TextView)findViewById(R.id.edit_message);
        search.clearFocus();
        //setButtons();

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
               String searchMessage = search.getText().toString();
                /*// Get the current list.
                SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                Set<String> myStrings = settings.getStringSet("myStrings", new HashSet<String>());

                // Add the new value.
                myStrings.add(searchMessage);

                // Save the list.
                editor.putStringSet("myStrings", myStrings);
                editor.commit();*/

                SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);

                // Writing data to SharedPreferences
                SharedPreferences.Editor editor = settings.edit();
                String newNumber = getNumber();

                switch(newNumber) {
                    case "one":
                        editor.putString("two", searchMessage);
                        editor.putString("value", "two");
                        break;
                    case "two":
                        editor.putString("three", searchMessage);
                        editor.putString("value", "three");
                        break;
                    case "three":
                        editor.putString("four", searchMessage);
                        editor.putString("value", "four");
                        break;
                    case "four":
                        editor.putString("five", searchMessage);
                        editor.putString("value", "five");
                        break;
                    case "five":
                        editor.putString("one", searchMessage);
                        editor.putString("value", "one");
                        break;
                }

                editor.commit();

                // Reading from SharedPreferences
                String one = settings.getString("one", "");
                String two = settings.getString("two", "");
                String three = settings.getString("three", "");
                String four = settings.getString("four", "");
                String five = settings.getString("five", "");
                String value = settings.getString("value", "");

                final Button btn_one = (Button)findViewById(R.id.button1);
                btn_one.setText(one);

                btn_one.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String buttonText = btn_one.getText().toString();

                        Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                        intent.putExtra(ListActivity.NAME_KEY, buttonText);
                        startActivity(intent);
                        overridePendingTransition(R.layout.animation, R.layout.animation2);
                    }
                });

                final Button btn_two = (Button)findViewById(R.id.button2);
                btn_two.setText(two);

                btn_two.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String buttonText = btn_two.getText().toString();

                        Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                        intent.putExtra(ListActivity.NAME_KEY, buttonText);
                        startActivity(intent);
                        overridePendingTransition(R.layout.animation, R.layout.animation2);
                    }
                });

                final Button btn_three = (Button)findViewById(R.id.button3);
                btn_three.setText(three);

                btn_three.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String buttonText = btn_three.getText().toString();

                        Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                        intent.putExtra(ListActivity.NAME_KEY, buttonText);
                        startActivity(intent);
                        overridePendingTransition(R.layout.animation, R.layout.animation2);
                    }
                });

                final Button btn_four = (Button)findViewById(R.id.button4);
                btn_four.setText(four);

                btn_four.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String buttonText = btn_four.getText().toString();

                        Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                        intent.putExtra(ListActivity.NAME_KEY, buttonText);
                        startActivity(intent);
                        overridePendingTransition(R.layout.animation, R.layout.animation2);
                    }
                });

                final Button btn_five = (Button)findViewById(R.id.button5);
                btn_five.setText(five);

                btn_five.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String buttonText = btn_five.getText().toString();

                        Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                        intent.putExtra(ListActivity.NAME_KEY, buttonText);
                        startActivity(intent);
                        overridePendingTransition(R.layout.animation, R.layout.animation2);
                    }
                });

                Log.d(TAG, value);
                Log.d(TAG, one);
                Log.d(TAG, two);
                Log.d(TAG, three);
                Log.d(TAG, four);
                Log.d(TAG, five);

                String message = search.getText().toString() + " is toegevoegd aan je library";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
                        .show();



                /*
                Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                String transitionName = getString(R.string.edit_message);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SearchActivity.this, search, transitionName);
                ActivityCompat.startActivity(SearchActivity.this, intent, options.toBundle());
                */
                Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                intent.putExtra("search",search.getText().toString());
                intent.putExtra(ListActivity.NAME_KEY, searchMessage);
                String transitionName = getString(R.string.value_transition);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SearchActivity.this, findViewById(R.id.edit_message), transitionName);
                ActivityCompat.startActivity(SearchActivity.this, intent, options.toBundle());
                //startActivity(intent);

                overridePendingTransition(R.layout.animation, R.layout.animation2);

                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100);
    }

    public String getNumber(){

        if (word == null) {
            word = "one";
        }
        SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
        String value = settings.getString("value", "");

        switch(value) {
            case "one":
                word = "two";
                break;
            case "two":
                word = "three";
                break;
            case "three":
                word = "four";
                break;
            case "four":
                word = "five";
                break;
            case "five":
                word = "one";
                break;
        }

        return word;
    }

    /*public void setButtons(){
        SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
        // Reading from SharedPreferences
        String one = settings.getString("one", "");
        String two = settings.getString("two", "");
        String three = settings.getString("three", "");
        String four = settings.getString("four", "");
        String five = settings.getString("five", "");

        Button btn_one = (Button)findViewById(R.id.button1);
        btn_one.setText(one);

        Button btn_two = (Button)findViewById(R.id.button2);
        btn_two.setText(two);

        Button btn_three = (Button)findViewById(R.id.button3);
        btn_three.setText(three);

        Button btn_four = (Button)findViewById(R.id.button4);
        btn_four.setText(four);

        Button btn_five = (Button)findViewById(R.id.button5);
        btn_five.setText(five);
    }*/
}
