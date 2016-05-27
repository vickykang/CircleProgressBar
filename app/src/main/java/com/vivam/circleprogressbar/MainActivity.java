package com.vivam.circleprogressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vivam.circleprogressbar.widget.CircleProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgressBar mCircleProgressBar;
    private EditText mMaxInput;
    private EditText mProgressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircleProgressBar = (CircleProgressBar) findViewById(R.id.circle_pb);
        mMaxInput = (EditText) findViewById(R.id.et_max);
        mProgressInput = (EditText) findViewById(R.id.et_progress);
        View button = findViewById(R.id.btn_set);
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set) {
            String maxStr = mMaxInput.getText().toString();
            int max = 100;
            try {
                max = Integer.parseInt(maxStr);
            } catch (NumberFormatException e) {
            }
            mCircleProgressBar.setMax(max);

            String progressStr = mProgressInput.getText().toString();
            try {
                int progress = Integer.parseInt(progressStr);
                mCircleProgressBar.setProgress(progress);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "The progress must between 0 and " + max , Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
