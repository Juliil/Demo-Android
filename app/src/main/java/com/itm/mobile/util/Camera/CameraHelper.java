package com.itm.mobile.util.Camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraHelper {

    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    public static File createJpgFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    public static File getAlbumDir() {
        File albumDir = new File (
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM)
                        + "/CondoApp"
        );
        if (! albumDir.exists()){
            if (! albumDir.mkdir()){
                return null;
            };
        }

        return albumDir;
    }

    public static void galleryAddPic(Context context, String URLPath) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(URLPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    public static Bitmap setPic(View mImageView, String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        //Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        Bitmap ret = checkOrientation(BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions), mCurrentPhotoPath);
        compressMyFile(ret, mCurrentPhotoPath);

        return ret;
    }

    private static void compressMyFile(Bitmap bitmap, String mCurrentPhotoPath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mCurrentPhotoPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap checkOrientation(Bitmap bitmap, String mCurrentPhotoPath)
    {
        int rotate = 0;
        try {
            File imageFile = new File(mCurrentPhotoPath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rotate > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotate);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        return bitmap;
    }

}
