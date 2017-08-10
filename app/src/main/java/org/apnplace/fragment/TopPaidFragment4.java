package org.apnplace.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apnplace.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopPaidFragment4 extends Fragment {
    private final static String STORETEXT="storetext4.txt";
    EditText txtEditor;
    Button SaveNotes;


    public TopPaidFragment4() {
        // Required empty public constructor
    }

    //This fragment is for saving the Module Notes
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_top_paid_fragment4, container, false);
        txtEditor= (EditText) rootview.findViewById(R.id.ednotes4);
        SaveNotes=(Button)rootview.findViewById(R.id.NotesButton4);
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

        return  rootview;
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
                    buf.append(str+"n");
                }
                in.close();
                txtEditor.setText(buf.toString());

            }

        }

        catch (java.io.FileNotFoundException e) {
        }

        catch (Throwable t) {
            Toast.makeText(getContext(), "Exception: "+t.toString(), Toast.LENGTH_LONG).show();

        }

    }
    }


