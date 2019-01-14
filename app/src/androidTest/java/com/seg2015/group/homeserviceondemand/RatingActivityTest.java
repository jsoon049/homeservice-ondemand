package com.seg2015.group.homeserviceondemand;

import org.junit.After;
import org.junit.Before;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SearchView;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class RatingActivityTest {

    @Rule
    public ActivityTestRule<RatingActivity> ratingActivityActivityTestRule = new ActivityTestRule<>(RatingActivity.class);
    private RatingActivity ratingActivity = null; //mActivity
    private RatingBar ratingBar;
    private EditText comment;

    @Before
    public void setUp() throws Exception {
        ratingActivity = ratingActivityActivityTestRule.getActivity();

    }

    @Test
    public void TestLaunch() throws Exception{

        View view = ratingActivity.findViewById(R.id.ratingBar);

        assertNotNull(view);

    }

    @Test
    @UiThreadTest
    public void TestRatingBar() throws Exception{
        assertNotNull(ratingActivity.findViewById(R.id.ratingBar));
        ratingBar = ratingActivity.findViewById(R.id.ratingBar);
        ratingBar.setNumStars(3);

        int test =  ratingBar.getNumStars();
        assertNotEquals(4, test);

    }

    @Test
    @UiThreadTest
    public void TestRatingBar2() throws Exception{
        assertNotNull(ratingActivity.findViewById(R.id.ratingBar));
        ratingBar = ratingActivity.findViewById(R.id.ratingBar);
        ratingBar.setNumStars(3);

        int test =  ratingBar.getNumStars();
        assertEquals(3, test);

    }

    @Test
    @UiThreadTest
    public void TestComments() throws Exception{
        assertNotNull(ratingActivity.findViewById(R.id.comments));
        comment = ratingActivity.findViewById(R.id.comments);
        comment.setText("good service");

        String test =  comment.getText().toString();
        assertNotEquals("12345", test);

    }

    @Test
    @UiThreadTest
    public void TestComments2() throws Exception{
        assertNotNull(ratingActivity.findViewById(R.id.comments));
        comment = ratingActivity.findViewById(R.id.comments);
        comment.setText("Good service");

        String test =  comment.getText().toString();
        assertEquals("Good service", test);

    }

    @After
    public void tearDown() throws Exception {

        ratingActivity=null;
    }
}