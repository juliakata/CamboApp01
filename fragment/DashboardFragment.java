package com.cambro.app.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambro.app.PersonalizeToolActivity;
import com.cambro.app.R;
import com.cambro.app.FitFactoryActivity;
import com.cambro.app.SocialActivity;
import com.cambro.app.TransportActivity;
import com.cambro.app.YouTubeActivity;
import com.cambro.app.interfce.Common;
import com.cambro.app.interfce.OnFragmentInteractionListener;
import com.cambro.app.utils.Utils;
import com.cambro.app.utils.image.ImageLoader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    TextView txtBtitle, txtBdate;
    ImageView ivBImg;
    ImageLoader imageLoader;
    public static String lastBlogLink;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment f = new DashboardFragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    private void loadSocialWebview(int param)
    {
        Intent i = new Intent(getActivity(), SocialActivity.class);
        i.putExtra("social", param);
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        txtBtitle = (TextView) v.findViewById(R.id.db_txt_btitle);
        txtBdate = (TextView) v.findViewById(R.id.db_txt_bdate);
        ivBImg = (ImageView) v.findViewById(R.id.db_iv_bImg);
        imageLoader = new ImageLoader(getActivity());

        v.findViewById(R.id.db_iv_loc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SocialActivity.class);
                i.putExtra("social", 3);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        v.findViewById(R.id.db_ll_blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastBlogLink == null) return;
                Intent i = new Intent(getActivity(), SocialActivity.class);
                i.putExtra("social", 11);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        v.findViewById(R.id.db_iv_healthcare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("5", true);
            }
        });
        v.findViewById(R.id.db_iv_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadSocialWebview(0);
                onButtonPressed("16", true);
            }
        });
        v.findViewById(R.id.db_iv_np).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSocialWebview(1);
            }
        });
        v.findViewById(R.id.db_iv_trivia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTrivia();
            }
        });
        v.findViewById(R.id.db_iv_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onButtonPressed("7", true);
                Intent i = new Intent(getActivity(), YouTubeActivity.class);
                i.putExtra("url", 0);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        v.findViewById(R.id.db_iv_social).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("8", true);
            }
        });
        v.findViewById(R.id.db_iv_camrack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("9", true);
            }
        });
        v.findViewById(R.id.db_iv_blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSocialWebview(2);
            }
        });
        v.findViewById(R.id.db_iv_fitfactory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FitFactoryActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                //onButtonPressed("16", true);
            }
        });
        v.findViewById(R.id.db_iv_persnalize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PersonalizeToolActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                //onButtonPressed("16", true);
            }
        });
        v.findViewById(R.id.db_iv_transport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TransportActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        v.findViewById(R.id.db_iv_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("10", true);
            }
        });
        new GetBlogFeed().execute();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String uri, boolean bl) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri, bl);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public class GetBlogFeed extends AsyncTask<String, Void, String> {

        NodeList nodelist;
        @Override
        protected String doInBackground(String... params) {
            String str = null;
            HttpClient httpclient = Utils.createHttpClient();
            HttpPost httppost = new HttpPost(Common.blogFeedURL);
            try {
                org.apache.http.HttpResponse response = httpclient.execute(httppost);
                final String strResponse = EntityUtils.toString(response
                        .getEntity());
                str = strResponse;
                str = str.replace("﻿", "");

            } catch (ClientProtocolException e) {
            } catch (IOException e) {
            }
            return str;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result == null) return;
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(result));

                boolean insideItem = false;
                int eventType = xpp.getEventType();
                boolean bl = true;
                while (eventType != XmlPullParser.END_DOCUMENT && bl == true) {
                    if (eventType == XmlPullParser.START_TAG) {

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem)
                                txtBtitle.setText(xpp.nextText());
                        }else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem)
                                lastBlogLink = xpp.nextText();
                        } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                            if (insideItem)
                            {
                                String[] s = xpp.nextText().split(" ");
                                String ss = s[2] + " " + s[1] + ", " + s[3];
                                txtBdate.setText(ss);
                            }
                        }else if (xpp.getName().equalsIgnoreCase("content:encoded")) {
                            if (insideItem)
                            {
                                String[] s = xpp.nextText().split("<img")[1].split("src=\"")[1].split("\"");
                                imageLoader.DisplayImage(s[0], ivBImg);
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                        bl = false;
                    }

                    eventType = xpp.next(); // move to next element
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void goTrivia()
    {
        SharedPreferences prefs = getActivity().getSharedPreferences("Cambro_Trivia", Context.MODE_PRIVATE);
        int answerNumber = prefs.getInt("answer_number", -1);
        if(answerNumber == Utils.calculateQuestionNumber())
        {
            onButtonPressed("13", true);
        }
        else
        {
            onButtonPressed("6", true);
        }
    }
}
