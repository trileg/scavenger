package net.trileg.scavenger;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;


public final class CustomWebView extends WebView {
    Context context;
    GestureDetector gestureDetector;

    public CustomWebView(Context context) {
        super(context);
        this.context = context;
        gestureDetector = new GestureDetector(context, onGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return (gestureDetector.onTouchEvent(event) || super.onTouchEvent(event));
    }

    private final GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }
        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
            float deltax,deltay,velo;
            deltax = Math.abs(e1.getRawX()-e2.getRawX());
            deltay = Math.abs(e1.getRawY()-e2.getRawY());
            velo = Math.abs(velocityX);

            //velo is how fast finger moves.
            if (deltax > 200 && deltay < 90 && velo > 350) {
                if (e1.getRawX() > e2.getRawX()) {
                    if (canGoForward()){
                        Log.d("Debug", "move forward");
                        //Gesture : move forward
                        goForward();
                    }
                    else{
                        //Gesture : no more forward history
                        Log.d("Debug", "no more forward history");
                    }
                } else if(e1.getRawX() < e2.getRawX()){
                    if (canGoBack()){
                        Log.d("Debug", "go back");
                        //Gesture : go back
                        goBack();
                    }
                    else {
                        //Gesture : no more backward history
                        Log.d("Debug", "no more backward history");
                    }
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }
    };
}
