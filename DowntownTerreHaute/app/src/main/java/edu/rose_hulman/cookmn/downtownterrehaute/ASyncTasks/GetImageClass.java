package edu.rose_hulman.cookmn.downtownterrehaute.ASyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Dev on 02/14/2016.
 */
public class GetImageClass extends AsyncTask<String, Void, Bitmap> {
    private ImageConsumer mImageConsumer;

    public GetImageClass(ImageConsumer imageConsumer){
        mImageConsumer=imageConsumer;
    }

    @Override
    protected Bitmap doInBackground(String... urlStrings) {
        String urlString = urlStrings[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new URL(urlString).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.d(urlString, "ERROR: " + e.toString());
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mImageConsumer.onImageLoaded(bitmap);
    }

    public interface ImageConsumer{
        public void onImageLoaded(Bitmap bitmap);
    }
}
