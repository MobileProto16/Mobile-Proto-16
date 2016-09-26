package mobile_proto_16.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;


import org.json.JSONException;

import java.io.Console;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.button) Button button;
    @BindView(R.id.input) EditText input;
    @BindView(R.id.price) TextView price;

    private final String TAG = this.getClass().getName();

    private Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            // YOUR CODE HERE. DO SOMETHING WHEN A RESPONSE COMES IN.
            // Hint: remove the first three characters, parse the response into a JSONArray,
            // and pass it into your extractPriceFromJSON() function.
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "A VolleyError occurred.");
            error.printStackTrace();
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        final Context c = this.getContext();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = buildSearchURL(input.getText().toString());
                // YOUR CODE HERE.
                //
                // Create a StringRequest using the URL and the listeners declared above.
                // Add the request to your RequestQueue from your MySingleton class
            }
        });

        return view;
    }

    private String buildSearchURL(String companyTicker) {
        // YOUR CODE HERE
        // USE URIBuilder
        return "";
    }

    private String extractPriceFromJSON(JSONArray array) throws JSONException {
        // Your code here. Extract the price value from the JSON array
        return "";
    }

}
