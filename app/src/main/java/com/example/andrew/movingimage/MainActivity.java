package com.example.andrew.movingimage;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.*;
import java.util.Timer;
import java.util.TimerTask;

import android.view.WindowManager;
import android.view.Display;
import android.view.View;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int screenWidth;
    private int screenHeight;

    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowLeft;
    private ImageView arrowRight;
    private TextView score;

    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowLeftX;
    private float arrowLeftY;
    private float arrowRightX;
    private float arrowRightY;
    private float moveSpeed = 10;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrowUp = (ImageView)findViewById(R.id.arrowUp);
        arrowDown = (ImageView)findViewById(R.id.arrowDown);
        arrowLeft = (ImageView)findViewById(R.id.arrowLeft);
        arrowRight = (ImageView)findViewById(R.id.arrowRight);
        score = (TextView)findViewById(R.id.score);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        arrowUp.setX(-80.0f);
        arrowUp.setY(-80.0f);
        arrowDown.setX(-80.0f);
        arrowDown.setY(screenHeight + 80.0f);
        arrowRight.setX(screenWidth + 80.0f);
        arrowRight.setY(-80.0f);
        arrowLeft.setX(-80.0f);
        arrowLeft.setY(-80.0f);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);
    }

    public void changePos() {

        arrowUpY -= moveSpeed;
        if (arrowUp.getY() + arrowUp.getHeight() < 0) {
            arrowUpX = (float)Math.floor(Math.random() * (screenWidth - arrowUp.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);

        arrowDownY += moveSpeed;
        if(arrowDown.getY() > screenHeight) {
            arrowDownX = (float)Math.floor(Math.random() * (screenWidth - arrowDown.getWidth()));
            arrowDownY = -100.0f;
        }

        arrowDown.setX(arrowDownX);
        arrowDown.setY(arrowDownY);

        arrowRightX += moveSpeed;
        if(arrowRight.getX() > screenWidth) {
            arrowRightX = -100.0f;
            arrowRightY = (float)Math.floor(Math.random() * (screenHeight - arrowRight.getHeight()));
        }
        arrowRight.setX(arrowRightX);
        arrowRight.setY(arrowRightY);

        arrowLeftX -= moveSpeed;
        if(arrowLeft.getX() + arrowLeft.getWidth() < 0) {
            arrowLeftX = screenWidth + 100.0f;
            arrowLeftY = (float) Math.floor(Math.random() * (screenHeight - arrowLeft.getHeight()));
        }

        arrowLeft.setX(arrowLeftX);
        arrowLeft.setY(arrowLeftY);

        int value = Integer.valueOf(score.getText().toString());
        if(value + 10 != moveSpeed)
            moveSpeed += 1;
    }

    public void imageClickUp(View view) {
        /*Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

        int value = Integer.valueOf(score.getText().toString());
        value += 1;
        score.setText(String.valueOf(value));

        arrowUpX = (float)Math.floor(Math.random() * (screenWidth - arrowUp.getWidth()));
        arrowUpY = screenHeight + 100.0f;
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);
    }

    public void imageClickDown(View view) {
        int value = Integer.valueOf(score.getText().toString());
        value += 1;
        score.setText(String.valueOf(value));

        arrowDownX = (float)Math.floor(Math.random() * (screenWidth - arrowDown.getWidth()));
        arrowDownY = -100.0f;
        arrowUp.setX(arrowDownX);
        arrowUp.setY(arrowDownY);
    }

    public void imageClickLeft(View view) {
        int value = Integer.valueOf(score.getText().toString());
        value += 1;
        score.setText(String.valueOf(value));

        arrowLeftX = screenWidth + 100.0f;
        arrowLeftY = (float) Math.floor(Math.random() * (screenHeight - arrowLeft.getHeight()));
        arrowUp.setX(arrowLeftX);
        arrowUp.setY(arrowLeftY);
    }

    public void imageClickRight(View view) {
        int value = Integer.valueOf(score.getText().toString());
        value += 1;
        score.setText(String.valueOf(value));

        arrowRightX = -100.0f;
        arrowRightY = (float)Math.floor(Math.random() * (screenHeight - arrowRight.getHeight()));
        arrowUp.setX(arrowRightX);
        arrowUp.setY(arrowRightY);
    }
}
