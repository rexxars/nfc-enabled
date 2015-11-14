package com.rexxars.nfcenabled;

import android.app.Activity;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Method;

public class CheckActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        boolean supportsNFC = checkNfcSupport();

        TextView statusView = (TextView) findViewById(R.id.status_text);
        if (supportsNFC) {
            statusView.setText("NFC is: supported.");
            statusView.setTextColor(0xFF27AE60);
        } else {
            statusView.setText("NFC is: unsupported.");
            statusView.setTextColor(0xFFE74C3C);
        }
    }

    protected boolean checkNfcSupport() {
        try  {
            Method hasSystemFeature = PackageManager.class.getMethod("hasSystemFeature", new Class[] { String.class });
            Boolean hasNfc = (Boolean) hasSystemFeature.invoke(getPackageManager(), new Object[] { "android.hardware.nfc" });
            return hasNfc.booleanValue();
        } catch (Exception localException)  {
            return false;
        }
    }
}
