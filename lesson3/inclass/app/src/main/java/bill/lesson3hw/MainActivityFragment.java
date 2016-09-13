package bill.lesson3hw;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button myButton = (Button) view.findViewById(R.id.button);
        final TextView text = (TextView) view.findViewById(R.id.textView);
        Button myButton2 = (Button) view.findViewById(R.id.buttontest);

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Hello again!");
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivityFragment", "Hello! I am a log");
                text.setText("Goodbye earth!");
            }
        });

        return view;
    }
}
