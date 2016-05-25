package com.studio.nitz.pollpedia;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.studio.nitz.pollpedia.model.BollywoodModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitinpoddar on 12/23/15.
 */
public class FragmentBollywood extends Fragment {

    public FragmentBollywood(){}
    public ListView bollywoodListView;
    public ArrayList<BollywoodModel> bollywoodQuestionList;
    public BollywoodModel model;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bollywoodlist, container, false);
        bollywoodListView = (ListView) rootView.findViewById(R.id.bollywoodListView);
        requestParseData();
        bollywoodListView.setAdapter(new ListViewAdapter(getActivity(), bollywoodQuestionList));
        return rootView;
    }

    public void requestParseData(){
        bollywoodQuestionList = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionBank");
        query.orderByAscending("createdAt");
        query.whereEqualTo("categoryId", "1");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    int len = objects.size();
                    Log.d("Pollpedia", "No. of Objects: "+len);
                    for (int i = 0; i < len; i++) {
                        ParseObject questionObject = objects.get(i);
                        model = new BollywoodModel();
                        Log.d("Pollpedia", questionObject.getString("questionString"));
                        model.setQuestionString(questionObject.getString("questionString"));
                          /*  ParseFile file = questionObject.getParseFile("questionImage");
                            file.getDataInBackground(new GetDataCallback() {

                                @Override
                                public void done(byte[] data, ParseException e) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    model.setImageView(bmp);
                                }
                            }); */
                        bollywoodQuestionList.add(model);
                    }
                } else {
                    Toast.makeText(getContext(), "Unable to fetch response from server", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
