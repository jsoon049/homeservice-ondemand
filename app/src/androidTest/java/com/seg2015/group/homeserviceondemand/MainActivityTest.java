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

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;
    private EditText user;
    private Button but;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() throws Exception{

        View view = mActivity.findViewById(R.id.login_button);

        assertNotNull(view);

    }

    @Test
    @UiThreadTest
    public void TestEditTextUser() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.userName_Field));
        user = mActivity.findViewById(R.id.userName_Field);
        user.setText("user1");

        String name =  user.getText().toString();
        assertNotEquals("user", name);

    }

    @Test
    @UiThreadTest
    public void TestEditTextPass() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.password_Field));
        user = mActivity.findViewById(R.id.password_Field);
        user.setText("pass1");

        String name =  user.getText().toString();
        assertNotEquals("pass", name);


    }

    @Test
    @UiThreadTest
    public void TestEditTextUserMatch() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.userName_Field));
        user = mActivity.findViewById(R.id.userName_Field);
        user.setText("user1");

        String name =  user.getText().toString();
        assertEquals("user1", name);

    }

    @Test
    @UiThreadTest
    public void TestEditTextPassMatch() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.password_Field));
        user = mActivity.findViewById(R.id.password_Field);
        user.setText("pass1");

        String name =  user.getText().toString();
        assertEquals("pass1", name);


    }

    @Test
    @UiThreadTest
    public void ButtonTextTest(){

        but = mActivity.findViewById(R.id.register_button);
        assertEquals("New User? Register here", but.getText().toString());

    }


    @After
    public void tearDown() throws Exception {

        mActivity = null;
    }
}