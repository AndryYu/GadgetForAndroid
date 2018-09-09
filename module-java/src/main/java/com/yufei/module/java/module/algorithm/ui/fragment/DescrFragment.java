package com.yufei.module.java.module.algorithm.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yufei.module.java.R;
import com.yufei.module.java.module.algorithm.DataUtils;
import com.yufei.module.java.module.algorithm.entity.Algorithm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class DescrFragment extends BaseFragment {

    LinearLayout rootView;
    String descJson;
    JSONObject descObject;

    public static DescrFragment newInstance(String algorithm) {
        DescrFragment fragment = new DescrFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Algorithm.KEY_ALGORITHM, algorithm);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_desc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = (LinearLayout) view.findViewById(R.id.rootView);
        setCodeDesc(getArguments().getString(Algorithm.KEY_ALGORITHM));
    }

    public void setCodeDesc(final String key) {
        if (descJson == null && getActivity() != null) {
            new AsyncTask<Void, String, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    return DataUtils.readDescJson(getActivity());
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    descJson = s;
                    try {
                        descObject = new JSONObject(descJson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    addDescData(key);
                }
            }.execute();
        } else addDescData(key);
    }

    private void addDescData(String algorithmKey) {
        if (descJson == null || descObject == null || getActivity() == null) {
            return;
        }
        rootView.removeAllViews();
        try {
            JSONObject dataObject = descObject.getJSONObject(algorithmKey);

            Iterator<?> keys = dataObject.keys();

            while (keys.hasNext()) {
                View descView = LayoutInflater.from(getActivity()).inflate(R.layout.item_code_desc, rootView, false);
                TextView title = (TextView) descView.findViewById(R.id.title);
                TextView desc = (TextView) descView.findViewById(R.id.desc);
                desc.setMovementMethod(LinkMovementMethod.getInstance());

                String key = (String) keys.next();
                title.setText(key);

                if (dataObject.get(key) instanceof JSONObject) {
                    JSONObject jsonObject = dataObject.getJSONObject(key);
                    String descString = "";
                    Iterator<?> complexityKeys = jsonObject.keys();
                    while (complexityKeys.hasNext()) {
                        String complexityKey = (String) complexityKeys.next();
                        descString += " - ";
                        descString += complexityKey;
                        descString += " : ";
                        descString += jsonObject.getString(complexityKey);
                        descString += "<br>";
                    }
                    desc.setText(Html.fromHtml(descString));

                } else if (dataObject.get(key) instanceof JSONArray) {
                    JSONArray array = dataObject.getJSONArray(key);
                    String descString = "";
                    for (int i = 0; i < array.length(); i++) {
                        descString += " - ";
                        descString += array.getString(i);
                        descString += "<br>";
                    }
                    desc.setText(Html.fromHtml(descString));
                } else if (dataObject.get(key) instanceof String) {
                    desc.setText(Html.fromHtml(dataObject.getString(key)));
                }
                rootView.addView(descView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }
}
