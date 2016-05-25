package com.studio.nitz.pollpedia.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.parse.ParseImageView;

/**
 * Created by nitinpoddar on 12/24/15.
 */
public class BollywoodModel {

    private Bitmap imageBitmap;
    private String questionString;

    public Bitmap getImageView() {
        return imageBitmap;
    }

    public String getQuestionString() {
        return questionString;
    }
    public void setQuestionString(String questionString)
    {
        this.questionString = questionString;
    }

    public void setImageView(Bitmap bitmap) {
        imageBitmap = bitmap;
    }

}
