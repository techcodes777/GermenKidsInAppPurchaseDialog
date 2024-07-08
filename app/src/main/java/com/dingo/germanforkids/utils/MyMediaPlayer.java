package com.dingo.germanforkids.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.util.Log;

import java.util.Random;


public class MyMediaPlayer {
    Context context;
    AudioManager manager;
    MediaPlayer mp = null;
    int count = 0;
    private String colorSoundString;
    private int length = 0;

    public MyMediaPlayer(Context context) {
        this.context = context;
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void playRepeatSound(int i, int i2) {
        mp = MediaPlayer.create(context, i);
        if (mp != null) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    PlaybackParams playbackParams = new PlaybackParams();
                    playbackParams.setPitch(1.18f);
                    mp.setPlaybackParams(playbackParams);
                }
                mp.seekTo(length);
                mp.start();
                mp.setLooping(true);
                for (int i3 = 0; i3 < i2; i3++) {
                    if (i3 < i2) {
                        mp.setLooping(true);
                    } else if (count > i2) {
                        mp.setLooping(false);
                        mp.stop();
                    }
                }
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                    }
                });
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }

    public void playSound(int i) {
        try {
            mp = MediaPlayer.create(context, i);
            if (mp != null) {
                try {
                    if (Build.VERSION.SDK_INT >= 23) {
                        PlaybackParams playbackParams = new PlaybackParams();
                        playbackParams.setPitch(1.18f);
                        mp.setPlaybackParams(playbackParams);
                    }
                    mp.seekTo(length);
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                            length = 0;
                        }
                    });
                } catch (Exception e) {
                    Log.d("error", e.toString());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void playSoundloop(int i) {
        mp = MediaPlayer.create(context, i);
        if (mp != null) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    PlaybackParams playbackParams = new PlaybackParams();
                    playbackParams.setPitch(1.18f);
                    mp.setPlaybackParams(playbackParams);
                }
                mp.seekTo(length);
                mp.start();
                mp.setLooping(true);
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }

    public void StopMp() {
        if (mp != null) {
            try {
                mp.stop();
                mp.reset();
                mp.release();
                mp = null;
                length = 0;
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }

    public void StopMp(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                length = 0;
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }



    private String getRandomApplause() {
        int nextInt = new Random().nextInt(4) + 1;
        return nextInt != 1 ? nextInt != 2 ? nextInt != 3 ? nextInt != 4 ? nextInt != 5 ? "applause_excellent" : "applause_youdid" : "applause_terrific" : "applause_intelligent" : "applause_greatjob" : "applause_excellent";
    }

    public String getRandomSelectArtSound() {
        switch (new Random().nextInt(7) + 1) {
            case 1:
                return "art1";
            case 2:
                return "art2";
            case 3:
            default:
                return "art3";
            case 4:
                return "art4";
            case 5:
                return "art5";
            case 6:
                return "art6";
            case 7:
                return "art7";
            case 8:
                return "art8";
        }
    }

}
