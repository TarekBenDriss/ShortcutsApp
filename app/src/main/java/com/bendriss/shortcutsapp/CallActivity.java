package com.bendriss.shortcutsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class CallActivity extends AppCompatActivity {

    private TextView phoneNumber;
    private ImageView deleteCharImg;
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        phoneNumber = findViewById(R.id.number);
        deleteCharImg = findViewById(R.id.deleteChar);
    }


    public void clickNumber(View view) {
        TextView tv = (TextView) view;
        phoneNumber.setText(phoneNumber.getText().toString() + tv.getText().toString());
        deleteCharImg.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void makeACall(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CallActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
            }
            else
            {
                startActivity(callIntent);
            }
        }
        else
        {
            startActivity(callIntent);
        }

    }

    public void deleteChar(View view)
    {
        String phoneNumberText = phoneNumber.getText().toString();
        phoneNumber.setText( phoneNumber.getText().toString().substring(0, phoneNumber.getText().toString().length()-1));

        if( phoneNumber.getText().toString().length()==0)
            deleteCharImg.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
                   // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918511812660"));

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }
                }
            }
        }
    }
}
