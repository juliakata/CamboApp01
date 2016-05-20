package com.cambro.app.fragment.transport;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cambro.app.PersonalizeToolActivity;
import com.cambro.app.R;
import com.cambro.app.TransportActivity;
import com.cambro.app.fragment.fitfactory.GMailSender;
import com.cambro.app.interfce.Common;
import com.cambro.app.interfce.OnFragmentInteractionListener;
import com.cambro.app.model.Transport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransportQuoteFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    TextView tpq_txt_prdcode, tpq_txt_prd_description;
    EditText tpq_edt_firstname, tpq_edt_lastname, tpq_edt_email;
    ImageView tpq_iv_prd;
    private String firstname;
    private String lastname;
    private String email;
    private Transport transport;

    public TransportQuoteFragment() {
        // Required empty public constructor
    }

    public static TransportQuoteFragment newInstance() {
        TransportQuoteFragment f = new TransportQuoteFragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_transport_quote, container, false);

        transport = ((TransportActivity) getActivity()).getTransport();
        tpq_iv_prd = (ImageView) v.findViewById(R.id.tpq_iv_prd);

        String imgName = "tp_prd_" + transport.getProductImageName().toLowerCase().trim().replace(" ", "_");
        try {
            Drawable drawable = getResources().getDrawable(getResources().getIdentifier(imgName, "drawable", getActivity().getPackageName()));
            tpq_iv_prd.setImageDrawable(drawable);
        } catch (Exception e) {
            tpq_iv_prd.setImageDrawable(getResources().getDrawable(R.drawable.trans));
        }

        tpq_txt_prdcode = (TextView) v.findViewById(R.id.tpq_txt_prdcode);
        tpq_txt_prdcode.setText(transport.getProductCode());
        tpq_txt_prd_description = (TextView) v.findViewById(R.id.tpq_txt_prd_description);
        tpq_txt_prd_description.setText(transport.getProductDescription());

        tpq_edt_firstname = (EditText) v.findViewById(R.id.tpq_edt_firstname);
        tpq_edt_lastname = (EditText) v.findViewById(R.id.tpq_edt_lastname);
        tpq_edt_email = (EditText) v.findViewById(R.id.tpq_edt_email);
        v.findViewById(R.id.tpq_txt_gp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInputItems()) return;
                new SendMailTransportProduct().execute();
            }
        });
        ((TransportActivity) getActivity()).setTitle(getString(R.string.tp_txt_gafq));
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String uri, boolean bl) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri, bl);
        }
    }

    private class SendMailTransportProduct extends AsyncTask {
        @Override

        protected Object doInBackground(Object... arg0) {

            sendEmail();
            return null;
        }
    }

    private boolean checkInputItems() {

        if (tpq_edt_firstname.getText().toString().length() == 0) {

            Toast.makeText(getActivity(), "Please input  your first name.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (tpq_edt_lastname.getText().toString().length() == 0) {

            Toast.makeText(getActivity(), "Please input  your last name.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (tpq_edt_email.getText().toString().length() == 0) {

            Toast.makeText(getActivity(), "Please input  your email address.", Toast.LENGTH_LONG).show();
            return false;
        }
        firstname = tpq_edt_firstname.getText().toString();
        lastname = tpq_edt_lastname.getText().toString();
        email = tpq_edt_email.getText().toString();
        return true;
    }

    private void sendEmail() {

    try

    {
        String body = "";
        body += "Name: " + firstname + " " + lastname + "\n";
        body += "Email: " + email + "\n";
        body += "Category: " + transport.getCategory() + "\n";
        body += "Electric: " + transport.getElectric() + "\n";
        body += "Pan: " + transport.getPan() + "\n";
        body += "Product Code: " + transport.getProductCode() + "\n";
        body += "Description: " + transport.getProductDescription() + "\n";
        body += "Deep: " + transport.getDeep() + "\n";

        File fileCapture = null;
        File f1 = new File(Environment.getExternalStorageDirectory().toString().trim());
        for (File temp : f1.listFiles()) {
            if (temp.getName().equals("cambro_trans_product.jpg")) {
                fileCapture = temp;
                break;
            }
        }
        Log.e("send mail", Long.toString(fileCapture.length()));
        GMailSender sender = new GMailSender(Common.cambroEmail, Common.cambroEmailPassword);
        sender.sendMail("Transporter Tool",
                body,
                email,
                Common.cramosEmail,
                fileCapture);

        Toast.makeText(getActivity(), "Successfully Sent.", Toast.LENGTH_LONG).show();
    }

    catch(Exception e)
    {
        Log.e("send mail", e.toString());
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
