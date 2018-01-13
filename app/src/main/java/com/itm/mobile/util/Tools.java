package com.itm.mobile.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tools {

    public static String OpenImageInAssets(Context context, String imageName){
        String encodedImageBase64 = "";
        AssetManager assetManager = context.getAssets();
        InputStream fileStream = null;
        try {
            fileStream = assetManager.open(imageName);
            if(fileStream != null){

                Bitmap bitmap = BitmapFactory.decodeStream(fileStream);
                // Convert bitmap to Base64 encoded image for web
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                // to get image extension file name split the received
                int fileExtensionPosition = imageName.lastIndexOf('.');
                String fileExtension = imageName.substring(fileExtensionPosition+1);
                //                  Log.d(IConstants.TAG,"fileExtension: " + fileExtension);

                if(fileExtension.equalsIgnoreCase("png")){
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    //                      Log.d(IConstants.TAG,"fileExtension is PNG");
                }else if(fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")){
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    //                      Log.d(TAG,"fileExtension is JPG");
                }

                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String imgageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImageBase64 = "data:image/png;base64," + imgageBase64;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return encodedImageBase64="";
        }
        finally {
            //Always clear and close
            try {
                if(fileStream != null) {
                    fileStream.close();
                    fileStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//      Log.d(TAG,"encodedImageBase64: " + encodedImageBase64);
        return encodedImageBase64;
    }
}
