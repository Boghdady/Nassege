package com.index.nasseg;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;

import com.index.nasseg.helper.CustomTextView;

public class OurServicesActivity extends BaseActivity {

CustomTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        String htmlAsString = getString(R.string.html);
//        Spanned htmlAsSpanned = Html.fromHtml(htmlAsString); // used by TextView
//
//// set the html content on the TextView
//        textView = (CustomTextView) findViewById(R.id.txtservice);
//        textView.setText(htmlAsSpanned);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_our_services;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_our_services;
    }

}
