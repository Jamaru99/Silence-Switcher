package com.idownward.android.silentmodetoggle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    Button toggleButton;
    private AudioManager mAudioManager;
    private boolean mPhoneIsSilent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (Button) findViewById(R.id.toggleButton);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        checkIfPhoneIsSilent();

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
    }

    public void onClick(View v){
        if (mPhoneIsSilent){
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            mPhoneIsSilent = false;
        }
        else{
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            mPhoneIsSilent = true;
        }
        toggleUi();
    }

    private void checkIfPhoneIsSilent(){
        int ringerMode = mAudioManager.getRingerMode();
        if(ringerMode == AudioManager.RINGER_MODE_SILENT){
            mPhoneIsSilent = true;
        }
        else{
            mPhoneIsSilent = false;
        }
    }

    private void toggleUi(){
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
        Drawable newPhoneImage;

        if(mPhoneIsSilent){
            newPhoneImage = getResources().getDrawable(R.drawable.phone_silent);
        }
        else{
            newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
        }
        imageView.setImageDrawable(newPhoneImage);
    }

    @Override
    protected void onResume(){
        super.onResume();
        checkIfPhoneIsSilent();
        toggleUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }

        return onOptionsItemSelected(item);
    }

}
