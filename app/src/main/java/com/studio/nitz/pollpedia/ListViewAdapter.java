package com.studio.nitz.pollpedia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.ParseImageView;
import com.studio.nitz.pollpedia.model.BollywoodModel;
import java.util.ArrayList;

/**
 * Created by nitinpoddar on 12/25/15.
 */

public class ListViewAdapter extends ArrayAdapter<BollywoodModel> {

    public ArrayList<BollywoodModel> listOfQuestion;
    private LayoutInflater mInflater;
    private BollywoodModel model;

    public ListViewAdapter(Context mContext, ArrayList<BollywoodModel> bollywoodQuestionList) {
        super(mContext, R.layout.fragment_bollywoodlist, bollywoodQuestionList);
        listOfQuestion = bollywoodQuestionList;
        Log.d("Pollpedia", "Size is : "+listOfQuestion.size());
        printQlist(listOfQuestion);
        //imageLoader = new ImageLoader(mContext);
        Log.d("Pollpedia", "Inside on Listview adapter constructor");
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public class ViewHolder {
        TextView questionString;
        ImageView questionImage;
    }
    private void printQlist(ArrayList<BollywoodModel> listOfQuestion) {
        for (int i=0; i < listOfQuestion.size(); i++){
            BollywoodModel model = listOfQuestion.get(i);
            Log.d("Pollpedia", model.getQuestionString());
        }
    }
    @Override
    public int getCount()
    {
        if (listOfQuestion != null){
            return listOfQuestion.size();
        } else {
            return 0;
        }
    }
    @Override
    public BollywoodModel getItem(int position)
    {
        if (listOfQuestion != null){
            return listOfQuestion.get(position);
        } else {
            return null;
        }
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        model = listOfQuestion.get(position);
        Log.d("Pollpedia", "Inside on Listview adapter get Item");
        if (itemView == null){
            itemView = mInflater.inflate(R.layout.itemview_questionlist, null);
        }
        TextView questionItem = (TextView) itemView.findViewById(R.id.questionString);
        ParseImageView questionFile = (ParseImageView) itemView.findViewById(R.id.questionImage);
        questionItem.setText(model.getQuestionString());
        questionFile.setImageBitmap(model.getImageView());
        //questionFile.setImageBitmap(model.getImageView());
        //imageLoader.displayImage(model.getQuestionFile(), questionFile);
        //questionFile.setImageBitmap(model.getQuestionFile());
        return itemView;
    }
}