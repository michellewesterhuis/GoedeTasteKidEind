package nl.tschout.tastekid;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static final String NAME_KEY = "name";

    private ListAdapter mAdapter;
    private EditText mTxtEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Vibrator vibe = (Vibrator) ListActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mTxtEdit = (EditText) findViewById(R.id.txtEdit);
        mTxtEdit.setText(getIntent().getStringExtra("search"));

        final RecyclerView recycler = (RecyclerView)findViewById(R.id.activity_list_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mAdapter = new ListAdapter());
        mAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ResultItem result) {
                //HIER ONTVANG JE KLIK ON ROW
                Log.d("ROW", "onItemClick");
                vibe.vibrate(500);
                Intent row_clicked = new Intent(ListActivity.this, Item.class);
                //row_clicked.putExtra(nameinvoer, 1);
                //row_clicked.putExtra(typeinvoer, 1);
                //row_clicked.putExtra(position, 1);
                startActivity(row_clicked);
            }
        });

        final String term = getIntent().getStringExtra(NAME_KEY);
        if (term == null) {
            Toast.makeText(this, "TERM IS NULL", Toast.LENGTH_LONG).show();
            finish();
        }

        final String url = "https://www.tastekid.com/api/similar?q=" +
                term + "&k=172767-Tastekid-87KV08RW";

        final Intent request = new Intent(ListActivity.this, ApiRequestService.class);
        request.putExtra(ApiRequestService.RECEIVER_KEY, mReceiver);
        request.putExtra(ApiRequestService.URL_KEY, url);
        startService(request);
    }

    private final ResultReceiver mReceiver = new ResultReceiver(new Handler()) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == ApiRequestService.SUCCESS_CODE) {
                final ArrayList<ResultItem> items = resultData
                        .getParcelableArrayList(ApiRequestService.SUCCESS_KEY);
                if (items != null) mAdapter.setItems(items);
            } else if (resultCode == ApiRequestService.ERROR_CODE) {
                Log.e("onReceiveResult", resultData.getString(ApiRequestService.ERROR_KEY));
            }
        }
    };
}
