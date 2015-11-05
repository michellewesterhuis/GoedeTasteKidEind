package nl.tschout.tastekid;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ApiRequestService extends IntentService {
    public static final String RECEIVER_KEY = "receiver";
    public static final String URL_KEY = "url";
    public static final String SUCCESS_KEY = "success";
    public static final int SUCCESS_CODE = 0;
    public static final String ERROR_KEY = "error";
    public static final int ERROR_CODE = 1;

    public ApiRequestService() {
        super("ApiRequestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra(RECEIVER_KEY);
        final String url = intent.getStringExtra(URL_KEY);
        final Bundle result = new Bundle();

        if (receiver == null) return;

        try {
            final Request request = new Request.Builder().url(url).build();
            final Response response = new OkHttpClient().newCall(request).execute();

            final JSONObject jsonResponse = new JSONObject(response.body().string()),
                    similar = jsonResponse.getJSONObject("Similar");
            final JSONArray resultArray = similar.getJSONArray("Results");

            final ArrayList<ResultItem> results = new ArrayList<>();
            for (int i = 0; i < resultArray.length(); i++) {
                final JSONObject obj = resultArray.getJSONObject(i);
                results.add(new ResultItem(
                        obj.getString("Name"),
                        obj.getString("Type")
                ));
            }

            result.putParcelableArrayList(SUCCESS_KEY, results);
            receiver.send(SUCCESS_CODE, result);
        } catch (Exception e) {
            result.putString(ERROR_KEY, e.getMessage());
            receiver.send(ERROR_CODE, result);
            e.printStackTrace();
        }
    }
}