package com.example.bhavya.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Tab1 extends Fragment {

    View newView;
    private CardView cardView;
    private TextView name;
    private TextView email_id;
    private ImageView photo;
    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        newView=inflater.inflate(R.layout.tab1,container,false);

        return newView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name=(TextView) newView.findViewById(R.id.name);
        email_id=(TextView) newView.findViewById(R.id.emai_id);
        photo=(ImageView) newView.findViewById(R.id.photo);
        cardView=(CardView) newView.findViewById(R.id.cardview1);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {

            String personName = acct.getDisplayName();

            String personEmail = acct.getEmail();

            String personId = acct.getId();

            Uri personPhoto = acct.getPhotoUrl();

            email_id.setText(personEmail);
            name.setText(personName);

        }

    /*    cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
*/

    }
}
