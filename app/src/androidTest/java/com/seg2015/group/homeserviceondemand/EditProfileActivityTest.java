package com.seg2015.group.homeserviceondemand;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditProfileActivityTest {

    @Rule
    public ActivityTestRule<EditProfileActivity> profileActivityTestRule = new ActivityTestRule<>(EditProfileActivity.class);
    private EditProfileActivity profileActivity = null; //mActivity
    private EditText phoneNumber; //user

    @Before
    public void setUp() throws Exception {
        profileActivity = profileActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void TestEditTextPhoneNumber() throws Exception{
        assertNotNull(profileActivity.findViewById(R.id.phoneEditText));
        phoneNumber = profileActivity.findViewById(R.id.phoneEditText);
        phoneNumber.setText("0123456789");

        String phone =  phoneNumber.getText().toString();
        assertNotEquals("phoneNumber", phone);

    }

    @Test
    @UiThreadTest
    public void TestEditTextUserPhoneNumber() throws Exception{
        assertNotNull(profileActivity.findViewById(R.id.phoneEditText));
        phoneNumber = profileActivity.findViewById(R.id.phoneEditText);
        phoneNumber.setText("0123456789");

        String phone =  phoneNumber.getText().toString();
        assertEquals("0123456789", phone);

    }

    @After
    public void tearDown() throws Exception {

        profileActivity = null;
    }

}
