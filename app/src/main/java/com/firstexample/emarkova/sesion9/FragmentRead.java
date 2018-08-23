package com.firstexample.emarkova.sesion9;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentRead extends Fragment {
    private TextView name;
    private TextView text;
    private DataStorage storage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = (TextView)view.findViewById(R.id.editNameRead);
        text = (TextView)view.findViewById(R.id.textNoteRead);
        //((TextView)view.findViewById(R.id.textNameRead)).setText();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            name.setText(bundle.getString("name"));
            text.setText(bundle.getString("text"));
        }
        storage = new DataStorage(getContext());
        text.setTextColor(storage.getTextColor());
        text.setTextSize(storage.getTextSize());
        text.setTypeface(Typeface.create("sans-serif", storage.getTextStyle()));

    }
}
