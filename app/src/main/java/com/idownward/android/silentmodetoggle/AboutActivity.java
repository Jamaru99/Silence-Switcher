package com.idownward.android.silentmodetoggle;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import java.util.regex.Pattern;

public class AboutActivity extends Activity {

    TextView lblInstagram;
    TextView lblFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        lblInstagram = (TextView) findViewById(R.id.lblInstagram);
        lblInstagram.setMovementMethod(LinkMovementMethod.getInstance());

        lblFacebook = (TextView) findViewById(R.id.lblFacebook);
        lblFacebook.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
