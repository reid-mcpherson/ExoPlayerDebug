package com.google.android.exoplayer.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.exoplayer.util.Util;

public class MainActivity extends Activity {

    @Bind(R.id.url) EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.clearButton, R.id.continueButton})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearButton:
                url.setText("");
                break;
            case R.id.continueButton:
                if (url.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please enter a url to continue", Toast.LENGTH_SHORT).show();
                } else {
                    Intent mpdIntent = new Intent(this, PlayerActivity.class)
                            .setData(Uri.parse(url.getText().toString()))
                            .putExtra(PlayerActivity.CONTENT_ID_EXTRA, "redbull")
                            .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, Util.TYPE_HLS)
                            .putExtra(PlayerActivity.PROVIDER_EXTRA, "");
                    startActivity(mpdIntent);
                }
                break;
            default:
        }

    }
}
