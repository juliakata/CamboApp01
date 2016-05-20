package com.cambro.app.fragment.transport;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambro.app.R;
import com.cambro.app.TransportActivity;
import com.cambro.app.interfce.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransportDeepFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ImageView tpd_iv_electric, tpd_iv_pans;
    private TextView tpd_txt_electric, tpd_txt_pans;

    public TransportDeepFragment() {
        // Required empty public constructor
    }

    public static TransportDeepFragment newInstance() {
        TransportDeepFragment f = new TransportDeepFragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_transport_deep, container, false);

        Boolean isElectric = ((TransportActivity)getActivity()).isElectric();
        tpd_iv_electric = ((ImageView)v.findViewById(R.id.tpd_iv_electric));
        tpd_txt_electric = (TextView) v.findViewById(R.id.tpd_txt_electric);

        if (isElectric)
        {
            tpd_iv_electric.setImageDrawable(getResources().getDrawable(R.drawable.tp_electric));
            tpd_txt_electric.setText(getString(R.string.tp_txt_electric));
        }
        else
        {
            tpd_iv_electric.setImageDrawable(getResources().getDrawable(R.drawable.tp_non_electric));
            tpd_txt_electric.setText(getString(R.string.tp_txt_non_electric));
        }

        tpd_iv_pans = ((ImageView)v.findViewById(R.id.tpd_iv_pans));
        tpd_txt_pans = (TextView) v.findViewById(R.id.tpd_txt_pans);
        String sPan = ((TransportActivity)getActivity()).getPans();
        if (sPan.equals(getString(R.string.tp_txt_single_pan)))
        {
            tpd_iv_pans.setImageDrawable(getResources().getDrawable(R.drawable.tp_single_pan));
            tpd_txt_pans.setText(getString(R.string.tp_txt_single_pan));
        }
        else if (sPan.equals(getString(R.string.tp_txt_multiple_pan)))
        {
            tpd_iv_pans.setImageDrawable(getResources().getDrawable(R.drawable.tp_multiple_pan));
            tpd_txt_pans.setText(getString(R.string.tp_txt_multiple_pan));
        }
        else if (sPan.equals(getString(R.string.tp_txt_ms_pan)))
        {
            tpd_iv_pans.setImageDrawable(getResources().getDrawable(R.drawable.tp_pan_sheet));
            tpd_txt_pans.setText(getString(R.string.tp_txt_ms_pan));
        }

        v.findViewById(R.id.tpd_ll_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TransportActivity)getActivity()).setDeep("4");
                ((TransportActivity)getActivity()).setTPTitle(4);
                onButtonPressed("4", true);

            }
        });
        v.findViewById(R.id.tpd_ll_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TransportActivity)getActivity()).setDeep("6");
                ((TransportActivity)getActivity()).setTPTitle(4);
                onButtonPressed("4", true);
            }
        });
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
}
