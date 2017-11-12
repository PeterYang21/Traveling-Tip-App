package csc214.project3.Controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;

public class SetBitmap {
    private static final String TAG = "My Tag";

    private static int SAMPLESIZE_DEFAULT = 1;

    public static Bitmap getBitmap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float mWidth = options.outWidth;
        float mHeight = options.outHeight;
        Log.d(TAG, "Source Width=" + mWidth + "," + "Source Height=" + mHeight);

        int sampleSize = SAMPLESIZE_DEFAULT;
        if (mHeight > height || mWidth > width) {
            if (mWidth > mHeight) {
                sampleSize = Math.round(mHeight / height);
            } else {
                sampleSize = Math.round(mWidth / width);
            }
        }
        BitmapFactory.Options scaledOptions = new BitmapFactory.Options();
        scaledOptions.inSampleSize = sampleSize;

        return BitmapFactory.decodeFile(path);
    }

}
