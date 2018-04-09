package com.umbrella.umbrella;

import android.os.Bundle;
import android.app.Fragment;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by samdoiron on 2018-03-28.
 */

public class StartFragment extends Fragment {
    StartPresenter presenter;

    @Override
    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        super.onCreate(bundle);
        FakeRegistrationInfoRepo infoRepo = new FakeRegistrationInfoRepo(new Date());
        presenter = new StartPresenter(infoRepo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedBundleInstance) {
        TextView deadlineText = new TextView(getActivity());
        deadlineText.setTextAppearance(android.R.style.TextAppearance_Material_Subhead);
        deadlineText.setTextSize(20);
        deadlineText.setPadding(16, 16, 16, 16);
        deadlineText.setText(presenter.getViewModel().deadlineMessage);
        return deadlineText;
    }
}
