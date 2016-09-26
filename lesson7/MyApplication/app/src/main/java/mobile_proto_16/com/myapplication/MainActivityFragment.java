package mobile_proto_16.com.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        return "http://finance.google.com/finance/info?client=iq&q=aapl";
    }

    private String extractPriceFromJSON(JSONArray array) {
        // YOUR CODE HERE
        return "16.41";
    }

}
