package com.cambro.app.fragment.transport;


import android.app.Activity;
import android.media.Image;
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
public class TransportPanFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    ImageView tpp_iv_electric;
    TextView tpp_txt_electric;

    public TransportPanFragment() {
        // Required empty public constructor
    }

    public static TransportPanFragment newInstance() {
        TransportPanFragment f = new TransportPanFragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_transport_pan, container, false);
        Boolean isElectric = ((TransportActivity)getActivity()).isElectric();
        tpp_iv_electric = ((ImageView)v.findViewById(R.id.tpp_iv_electric));
        tpp_txt_electric = (TextView) v.findViewById(R.id.tpp_txt_electric);

        if (isElectric)
        {
            tpp_iv_electric.setImageDrawable(getResources().getDrawable(R.drawable.tp_electric));
            tpp_txt_electric.setText(getString(R.string.tp_txt_electric));
        }
        else
        {
            tpp_iv_electric.setImageDrawable(getResources().getDrawable(R.drawable.tp_non_electric));
            tpp_txt_electric.setText(getString(R.string.tp_txt_non_electric));
        }

        v.findViewById(R.id.tpp_ll_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TransportActivity)getActivity()).setPans(getString(R.string.tp_txt_single_pan));
                ((TransportActivity)getActivity()).setTPTitle(3);
                onButtonPressed("3", true);
            }
        });
        v.findViewById(R.id.tpp_ll_multiple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TransportActivity)getActivity()).setPans(getString(R.string.tp_txt_multiple_pan));
                ((TransportActivity)getActivity()).setTPTitle(3);
                onButtonPressed("3", true);
            }
        });
        v.findViewById(R.id.tpp_ll_ms_pan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TransportActivity)getActivity()).setPans(getString(R.string.tp_txt_ms_pan));
                ((TransportActivity)getActivity()).setTPTitle(3);
                onButtonPressed("3", true);
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
