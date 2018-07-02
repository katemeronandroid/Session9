package com.firstexample.emarkova.sesion9;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentWrite extends Fragment {
    EditText name;
    EditText text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = (EditText)view.findViewById(R.id.editNameRead);
        text = (EditText)view.findViewById(R.id.editNoteRead);
        Bundle bundle = getArguments();
        if(bundle != null) {
            Log.d("Logs", bundle.getString("name"));
            name.setText( bundle.getString("name"));
            Log.d("Logs", "bundle");
            text.setText((CharSequence) bundle.getString("text"));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
