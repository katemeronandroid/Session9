package com.firstexample.emarkova.sesion9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class RecyclerClickListiner implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private GestureDetector.OnGestureListener gestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            };
    public RecyclerClickListiner(Context context) {
        gestureDetector = new GestureDetector(context, gestureListener);
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Log.d("Lods", "listen1");
        if (gestureDetector.onTouchEvent(e)) {
            Log.d("Lods", "listen");
            View clickedChild = rv.findChildViewUnder(e.getX(), e.getY());
            if (clickedChild != null && !clickedChild.dispatchTouchEvent(e)) {
                int clickedPosition = rv.getChildPosition(clickedChild);
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    onItemClick(rv, clickedChild, clickedPosition);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
    public abstract void onItemClick(RecyclerView recyclerView, View itemView, int position);
}
