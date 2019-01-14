package com.seg2015.group.homeserviceondemand;

import org.junit.After;
import org.junit.Before;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;


import static org.junit.Assert.*;

public class HomeOwnerActivityTest {

    @Rule
    public ActivityTestRule<HomeOwnerActivity> homeOwnerActivityActivityTestRule = new ActivityTestRule<>(HomeOwnerActivity.class);
    private HomeOwnerActivity homeOwnerActivity = null; //mActivity
    private SearchView searchBar;

    @Before
    public void setUp() throws Exception {
        homeOwnerActivity = homeOwnerActivityActivityTestRule.getActivity();

    }

    @Test
    public void TestLaunch() throws Exception{

        View view = homeOwnerActivity.findViewById(R.id.searchBar);

        assertNotNull(view);

    }

    @Test
    @UiThreadTest
    public void TestSearchView() throws Exception{
        assertNotNull(homeOwnerActivity.findViewById(R.id.searchBar));
        searchBar = homeOwnerActivity.findViewById(R.id.searchBar);
        searchBar.setQuery("12345", false);

        String test =  searchBar.getQuery().toString();
        assertNotEquals("words", test);

    }

    @Test
    @UiThreadTest
    public void TestSearchView2() throws Exception{
        assertNotNull(homeOwnerActivity.findViewById(R.id.searchBar));
        searchBar = homeOwnerActivity.findViewById(R.id.searchBar);
        searchBar.setQuery("12345", false);

        String test =  searchBar.getQuery().toString();
        assertEquals("12345", test);

    }

    @Test
    @UiThreadTest
    public void TestSearchView3() throws Exception{
        assertNotNull(homeOwnerActivity.findViewById(R.id.comments));
        searchBar = homeOwnerActivity.findViewById(R.id.searchBar);
        searchBar.setQuery("words", false);

        String test =  searchBar.getQuery().toString();
        assertNotEquals("12345", test);

    }

    @Test
    @UiThreadTest
    public void TestSearchView4() throws Exception{
        assertNotNull(homeOwnerActivity.findViewById(R.id.searchBar));
        searchBar = homeOwnerActivity.findViewById(R.id.searchBar);
        searchBar.setQuery("words", false);

        String test =  searchBar.getQuery().toString();
        assertEquals("words", test);

    }

    @After
    public void tearDown() throws Exception {
        homeOwnerActivity=null;
    }
}