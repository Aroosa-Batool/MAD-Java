package com.example.hackathon;


import static android.hardware.camera2.params.RggbChannelVector.RED;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CanvasClass extends View { //variables
    int ballx, bally, ballw, ballh;
    int tabx, taby, tabw, tabh;
    int canvas_height;
    int canvas_width;

    //score
    int score =0;

    //making a brick
    int brickh, brickw, brickx, bricky;
    boolean show_brick1 = true;

    boolean gamover = false;

    // for setting the increment of height and width of the ball
    int ball_delta_x = 10;
    int ball_delta_y = 10;
    // for setting the increment of height and width of the tab
    int tab_delta_x = 0;
    int tab_delta_y = 0;
    public CanvasClass(Context context) {  //constructor
        super(context);
// Ball position and dimensions
        ballx = bally = 100;
        ballw = 100;
        ballh = 100;
        // Tab position and dimension
        tabx =100;
        taby =100;
        tabh = 50;
        tabw =400;
        gamover = false;

        //brick position and dimension
        brickx = 100;
        bricky = 600;
        brickw = 200;
        brickh = 100;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);  //overriding onDraw function

        canvas_height = canvas.getHeight();
        canvas_width = canvas.getWidth();

        //setting position of tab
        taby = canvas_height - 200;
        //tabh = canvas_height -200 +50;


        canvas.drawColor(Color.WHITE);

        Paint red = new Paint(Color.RED);



        canvas.drawCircle(ballx, bally, ballw/2, red);






    }
}
