package com.turtlediary.www.utilities;

import android.app.Activity;
import android.media.MediaPlayer;

import com.turtlediary.www.R;

/**
 * Created by pratibha on 17/11/17.
 */

public class SoundEffect {

    private static final SoundEffect INSTANCE = new SoundEffect();
    public static final int SOUND_1 = 1;
    public static SoundEffect getInstance() {
        return INSTANCE;
    }

    /* private SoundPool soundPool;
     private HashMap<Integer, Integer> soundPoolMap;
     int priority = 1;
     int no_loop = 0;
     private int volume;
     float normal_playback_rate = 1f;

     private Context context;

     public void init(Context context) {
         this.context = context;
         soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
         soundPoolMap = new HashMap<Integer, Integer>();
         soundPoolMap.put(SOUND_1, soundPool.load(context, R.raw.beep, 1));
         AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
         volume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
     }
 */
    public void playSound(Activity activity,boolean isHomeAndBackClicked) {
        if (Preferences.getEffectSoundEnability(activity)) {
            final MediaPlayer player;
            if(isHomeAndBackClicked)
            player = MediaPlayer.create(activity, R.raw.app_music_home);
            else
                player = MediaPlayer.create(activity, R.raw.app_music_click);

            player.setLooping(false); // Set looping
            player.setVolume(100, 100);
            player.start();

            player.setOnCompletionListener(null);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    player.release();
                }
            });

        }

    }
}
