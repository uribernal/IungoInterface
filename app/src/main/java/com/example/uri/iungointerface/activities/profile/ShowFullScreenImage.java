package com.example.uri.iungointerface.activities.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.uri.iungointerface.R;


public class ShowFullScreenImage extends AppCompatActivity {

    private ScaleGestureDetector detector;
    private ImageView image;
    private float scale = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_screen_image);
        image = (ImageView) findViewById(R.id.iv_fullscreen);
        detector = new ScaleGestureDetector(this,new ScaleListener());

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("IMAGE");
        if (b==null){
            int icon = extras.getInt("IMAGE_INSTA");
            image.setImageResource(icon);


        }else{
            Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

            image.setImageBitmap(bmp);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        //  re-route the Touch Events to the ScaleListener class
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {


        float onScaleBegin = 0;
        float onScaleEnd = 0;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            image.setScaleX(scale);
            image.setScaleY(scale);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            onScaleBegin = scale;

            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            onScaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }
}



