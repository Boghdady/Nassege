package com.index.nasseg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactUsActivity extends BaseActivity {




    @Override
    int getContentViewId() {
        return R.layout.activity_contact_us;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_contact_us;
    }
}
