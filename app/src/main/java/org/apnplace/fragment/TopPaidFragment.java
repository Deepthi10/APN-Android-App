package org.apnplace.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apnplace.R;
import org.apnplace.constants.APNSApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopPaidFragment extends Fragment {
    private  static String STORETEXT;
     EditText txtEditor;
   Button SaveNotes;
    APNSApplication apnsApplication;
    String moduleid;
    public TopPaidFragment() {
        // Required empty public constructor
    }

//This fragment is for saving the Module Notes
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview= inflater.inflate(R.layout.fragment_top_paid, container, false);
        txtEditor= (EditText) rootview.findViewById(R.id.ednotes);
        SaveNotes=(Button)rootview.findViewById(R.id.NotesButton);
        apnsApplication=(APNSApplication)getActivity().getApplicationContext();

        moduleid=apnsApplication.getModuleid();
        STORETEXT="Module"+moduleid+".txt";
        SaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //fileOutputStream=ctx.openFileOutput(filename,Context.MODE_PRIVATE);
                    OutputStreamWriter out= new OutputStreamWriter(v.getContext().openFileOutput(STORETEXT,0));
                    out.write(txtEditor.getText().toString());
                    out.close();
                    Toast.makeText(getContext(), "The contents are saved in the file.", Toast.LENGTH_LONG).show();
                }
                catch (Throwable t) {
                    Toast.makeText(getContext(), "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
        readFileInEditor();
        return rootview;
    }
    public void readFileInEditor()

    {

        try {

            InputStream in = getContext().openFileInput(STORETEXT);

            if (in != null) {

                InputStreamReader tmp=new InputStreamReader(in);

                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str+"");
                }
                in.close();
                txtEditor.setText(buf.toString());

            }

        }

        catch (java.io.FileNotFoundException e) {

// that's OK, we probably haven't created it yet

        }

        catch (Throwable t) {

            Toast.makeText(getContext(), "Exception: "+t.toString(), Toast.LENGTH_LONG).show();

        }

    }
}
