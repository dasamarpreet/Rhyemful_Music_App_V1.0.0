package com.dasamarpreet.myaudioplayer;

import static com.dasamarpreet.myaudioplayer.ApplicationClass.ACTION_NEXT;
import static com.dasamarpreet.myaudioplayer.ApplicationClass.ACTION_PLAY;
import static com.dasamarpreet.myaudioplayer.ApplicationClass.ACTION_PREVIOUS;
import static com.dasamarpreet.myaudioplayer.MusicService.mediaPlayerid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//
//It is used to receive the actions from the notification bar. That is, when a user uses any action on
//        the notification of the player, then this class identifies it and performs the respective operation.

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String actionName = intent.getAction();
        Intent serviceIntent = new Intent(context, MusicService.class);
        if (actionName != null){
            switch (actionName){
                case ACTION_PLAY:
                    serviceIntent.putExtra("ActionName", "playPause");
                    context.startService(serviceIntent);
                    break;
                case ACTION_NEXT:
                    serviceIntent.putExtra("ActionName", "next");
                    context.startService(serviceIntent);
                    break;
                case ACTION_PREVIOUS:
                    serviceIntent.putExtra("ActionName", "previous");
                    context.startService(serviceIntent);
                    break;
            }
        }
    }
}
