package com.dasamarpreet.myaudioplayer;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AboutFragment extends Fragment {

    View view;
    ImageView piyusInsta, piyusLinkedin, piyusGmail, piyusGithub, piyusFb;
    ImageView amarInsta, amarLinkedin, amarGmail, amarGithub, amarFb, amarWebsite;
    ImageView aditiInsta, aditiLinkedin, aditiGmail, aditiFb;
    CardView shareButton;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        shareButton = view.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_SUBJECT, "Try the all new Rhymeful Music Player App. Download from here:");
                share.putExtra(Intent.EXTRA_TEXT, "Try the all new Rhymeful Music Player App. Download from here:" +
                        "https://drive.google.com/drive/folders/1Bf4_M0j53hs5f0E53MUhS4MtOkmRio7S?usp=sharing");

                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });

        piyusInsta = view.findViewById(R.id.piyusInsta);
        piyusLinkedin = view.findViewById(R.id.piyusLinkedin);
        piyusGmail = view.findViewById(R.id.piyusGmail);
        piyusGithub = view.findViewById(R.id.piyusGithub);
        piyusFb = view.findViewById(R.id.piyusFb);

        piyusInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.instagram.com/its_piyushulk/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        piyusLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.linkedin.com/in/piyushulk/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        piyusGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit intent to open an email sending app
                String [] addresses = {"uic.19bca1124@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Rhymeful App Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, urlText);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        piyusGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://github.com/PiyusHulk";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        piyusFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.facebook.com/itspiyushulk";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });


        amarInsta = view.findViewById(R.id.amarInsta);
        amarLinkedin = view.findViewById(R.id.amarLinkedin);
        amarGmail = view.findViewById(R.id.amarGmail);
        amarGithub = view.findViewById(R.id.amarGithub);
        amarFb = view.findViewById(R.id.amarFb);
        amarWebsite = view.findViewById(R.id.amarWebsite);

        amarInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.instagram.com/why__so__serious_._/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        amarLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.linkedin.com/in/amar-preet-das-193b17195/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        amarGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit intent to open an email sending app
                String [] addresses = {"uic.19bca1129@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Rhymeful App Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, urlText);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        amarGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://github.com/dasamarpreet";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        amarFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.facebook.com/dasamarpreet/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        amarWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.apdas.ml";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });


        aditiInsta = view.findViewById(R.id.aditiInsta);
        aditiLinkedin = view.findViewById(R.id.aditiLinkedin);
        aditiGmail = view.findViewById(R.id.aditiGmail);
        aditiFb = view.findViewById(R.id.aditiFb);

        aditiInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.instagram.com/aditi.d.sood_/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        aditiLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.linkedin.com/in/aditi-sood-57524b194/";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        aditiGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit intent to open an email sending app
                String [] addresses = {"uic.19bca1085@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Rhymeful App Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, urlText);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        aditiFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = "https://www.facebook.com/aditi.sood.589";
                StyleableToast.makeText(getActivity(), urlText, Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpage = Uri.parse(urlText);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        return view;
    }
}