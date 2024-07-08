package com.dingo.germanforkids.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.Usage.PaintPotView;
import com.dingo.germanforkids.databinding.ActivityMainDrawBinding;


public class MainDraw extends AppCompatActivity implements View.OnClickListener {
    ActivityMainDrawBinding binding;
    private int[] image;
    private ImageView imgBack;
    private ImageView imgBlackColor;
    private ImageView imgOrangeColor;
    private ImageView imgEraser;
    private ImageView imgGreenColor;
    private ImageView imgMagentaColor;
    private ImageView imgNext;
    private ImageView imgRedColor;
    private MediaPlayer mediaPlayer;
    private String name;
    private boolean noAds;
    private int[] sound;
    private PaintPotView viewDrawingPad;
    private int position = 0;
    private int adAppearNumberForEnd = 0;
    private int adAppearNumberForMiddle = 0;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityMainDrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readSharedPrefForAdsAndMoreSub();
        initComponents();
        lessonData();
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.viewDrawingPad);


    }


    private void readSharedPrefForAdsAndMoreSub() {
        noAds = getSharedPreferences("store", 0).getBoolean("removeAds", false);
    }

    private void initComponents() {
        imgBlackColor = (ImageView) findViewById(R.id.imgBlackColor);
        imgOrangeColor = (ImageView) findViewById(R.id.imgOrangeColor);
        imgRedColor = (ImageView) findViewById(R.id.imgRedColor);
        imgGreenColor = (ImageView) findViewById(R.id.imgGreenColor);
        imgMagentaColor = (ImageView) findViewById(R.id.imgMagentaColor);
        imgEraser = (ImageView) findViewById(R.id.imgEraser);
        viewDrawingPad = (PaintPotView) findViewById(R.id.viewDrawingPad);
        imgBlackColor.setOnClickListener(this);
        imgOrangeColor.setOnClickListener(this);
        imgRedColor.setOnClickListener(this);
        imgGreenColor.setOnClickListener(this);
        imgMagentaColor.setOnClickListener(this);
        imgEraser.setOnClickListener(this);
        binding.imgBacks.setOnClickListener(this);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        ImageView imageView = (ImageView) findViewById(R.id.imgBack);
        imgBack = imageView;
        imageView.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            name = intent.getExtras().getString("name");
        }
        binding.txtTittle.setText(name);
    }

    public void imgBack(View view) {
        viewDrawingPad.reset();
        int i = position - 1;
        position = i;
        viewDrawingPad.setBackgroundResource(this.image[i]);
        if (this.position == 0) {
            imgBack.setVisibility(View.INVISIBLE);
        }
        if (this.imgNext.getVisibility() == View.INVISIBLE) {
            imgNext.setVisibility(View.VISIBLE);
        }
        speak();
    }

    public void imgSpeak(View view) {
        speak();
    }

    public void imgNext(View view) {
        viewDrawingPad.reset();
        int i = position + 1;
        position = i;
        viewDrawingPad.setBackgroundResource(this.image[i]);
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.viewDrawingPad);
        if (this.position == image.length - 1) {
            imgNext.setVisibility(View.INVISIBLE);
        }
        if (this.imgBack.getVisibility() == View.INVISIBLE) {
            imgBack.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(binding.viewDrawingPad);
        }
        speak();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBlackColor:
                viewDrawingPad.setPenColor(getColor(R.color.black_color));
                imgColorSold();
                imgBlackColor.setImageResource(R.drawable.black_circle_right);
                return;
            case R.id.imgOrangeColor:
                viewDrawingPad.setPenColor(getColor(R.color.orange_color));
                imgColorSold();
                imgOrangeColor.setImageResource(R.drawable.orange_circle_right);
                return;
            case R.id.imgEraser:
                viewDrawingPad.reset();
                return;
            case R.id.imgGreenColor:
                viewDrawingPad.setPenColor(getColor(R.color.green_color));
                imgColorSold();
                imgGreenColor.setImageResource(R.drawable.green_circle_right);
                return;
            case R.id.imgMagentaColor:
                viewDrawingPad.setPenColor(getColor(R.color.green_dark_color));
                imgColorSold();
                imgMagentaColor.setImageResource(R.drawable.green_circle_dark_right);
                return;
            case R.id.imgRedColor:
                viewDrawingPad.setPenColor(getColor(R.color.red_color));
                imgColorSold();
                imgRedColor.setImageResource(R.drawable.red_circle_right);
                return;
            case R.id.imgBacks:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    private void imgColorSold() {
        imgBlackColor.setImageResource(R.drawable.black_circle);
        imgMagentaColor.setImageResource(R.drawable.green_circle_dark);
        imgRedColor.setImageResource(R.drawable.red_circle);
        imgOrangeColor.setImageResource(R.drawable.orange_circle);
        imgGreenColor.setImageResource(R.drawable.green_circle);
    }

    private void lessonData() {
        if (this.name.equalsIgnoreCase("Alphabet")) {
            sound = new int[]{R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j, R.raw.k, R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w, R.raw.x, R.raw.y, R.raw.z};
            image = new int[]{R.drawable.a_write, R.drawable.b_write, R.drawable.c_write, R.drawable.d_write, R.drawable.e_write, R.drawable.f_write, R.drawable.g_write, R.drawable.h_write, R.drawable.i_write, R.drawable.j_write, R.drawable.k_write, R.drawable.l_write, R.drawable.m_write, R.drawable.n_write, R.drawable.o_write, R.drawable.p_write, R.drawable.q_write, R.drawable.r_write, R.drawable.s_write, R.drawable.t_write, R.drawable.u_write, R.drawable.v_write, R.drawable.w_write, R.drawable.x_write, R.drawable.y_write, R.drawable.z_write};
        } else if (this.name.equalsIgnoreCase("Zahlen")) {
            sound = new int[]{R.raw.zero, R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine, R.raw.ten};
            image = new int[]{R.drawable.zero_write, R.drawable.one_write, R.drawable.two_write, R.drawable.three_write, R.drawable.four_write, R.drawable.five_write, R.drawable.six_write, R.drawable.seven_write, R.drawable.eight_write, R.drawable.nine_write, R.drawable.ten_write};
        }
        viewDrawingPad.setBackgroundResource(this.image[this.position]);
        MediaPlayer create = MediaPlayer.create(getApplicationContext(), sound[this.position]);
        mediaPlayer = create;
        create.start();
    }

    private void speak() {
        if (this.mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if (!this.mediaPlayer.isPlaying()) {
            mediaPlayer.release();
            mediaPlayer = null;
            MediaPlayer create = MediaPlayer.create(getApplicationContext(), sound[this.position]);
            mediaPlayer = create;
            create.start();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
