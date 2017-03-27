package com.rtao.observernews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rtao.observernews.activity.MainActivity;

public class SplashActivity extends Activity {

    private static final String START_MAIN = "start_main";
    private RelativeLayout rl_splash_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setDuration(2000);

        rl_splash_root.startAnimation(animationSet);

        animationSet.setAnimationListener(new animationListener());
    }

    class animationListener implements Animation.AnimationListener {

        /**
         * call this function when animation begins to play
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * call this function when animation finishes playing
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // go to main page
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            //Toast.makeText(SplashActivity.this, "Animation ends", Toast.LENGTH_SHORT).show();
        }

        /**
         * call this function when animation repeats playing
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
