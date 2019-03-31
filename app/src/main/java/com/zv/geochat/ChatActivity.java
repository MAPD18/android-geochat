package com.zv.geochat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zv.geochat.service.ChatService;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private static final int REQ_LOCATION = 134;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOCATION);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_history){
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_leave){
            leaveChat();
            finish(); // return to login screen
            return true;
        } else if (id == R.id.action_map){
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_connect){
            joinChat();
            return true;
        } else if (id == R.id.action_disconnect){
            leaveChat();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void joinChat(){
        Bundle data = new Bundle();
        data.putInt(ChatService.CMD, ChatService.CMD_JOIN_CHAT);
        Intent intent = new Intent(this, ChatService.class);
        intent.putExtras(data);
        startService(intent);
    }


    private void leaveChat(){
        Bundle data = new Bundle();
        data.putInt(ChatService.CMD, ChatService.CMD_LEAVE_CHAT);
        Intent intent = new Intent(this, ChatService.class);
        intent.putExtras(data);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
