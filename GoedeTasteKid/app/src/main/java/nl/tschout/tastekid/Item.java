package nl.tschout.tastekid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TextView textViewName = (TextView) findViewById(R.id.nameinvoer);
        TextView textViewType = (TextView) findViewById(R.id.typeinvoer);
        TextView textViewContent = (TextView) findViewById(R.id.contentinvoer);

        String invoernaam = getIntent().getExtras().getString("nameinvoer");
        // String test = getIntent().getStringExtra("nameinvoer");
        String invoertype = getIntent().getExtras().getString("typeinvoer");

        textViewName.setText(invoernaam);
        textViewType.setText(invoertype);
        textViewContent.setText("hallo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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
