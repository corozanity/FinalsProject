package com.example.finalproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageButton logo;
    TextView answers;
    long animDuration = 1000;
    MediaPlayer ball;
    private String [] sagot = {"Syempre naman, yes!", "Oo naman noh?",
            "Sure na sure, mars!", "Isang malaking super duper na yes!",
            "It's a yes for me.", "Sorry pero oo.", "Big yes yan, mamshie.",
            "Maya uli, tinatamad ako sumagot.", "IT'S A BIG NO NO.",
            "Hindi, wag mo na ulit itanong. Hindi nga ang sagot, kulet.",
            "Malay natin, malay ko sa'yo.", "Sabi nila pag pangit ang tanong, pangit din yung nagtatanong. Di ko alam sagot.",
            "No. Skrrrrrrt", "Wag mo na lang alamin, masasaktan ka lang.",
            "Kung ganyan ang mga tanong mo, magdasal ka na lang." };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_home, container, false);
        logo = (ImageButton) myview.findViewById(R.id.logo);
        logo.setOnClickListener(this);
        answers = (TextView) myview.findViewById(R.id.answer);

        return myview;
    }


    @Override
    public void onClick(View view) {
        ball = MediaPlayer.create(getContext(), R.raw.ballsound);
        ball.start();

        ObjectAnimator animY = ObjectAnimator.ofFloat(logo,"y", 300f);
        animY.setDuration(animDuration);

        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(logo, View.ALPHA, 1.0f, 0.0f);
        alphaAnim.setDuration(animDuration);

        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(logo, "rotation", 0f, 360f);
        rotateAnim.setDuration(animDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateAnim);
        animatorSet.start();

        int rand = new Random().nextInt(sagot.length);
        answers.setText(sagot[rand]);
    }
}
