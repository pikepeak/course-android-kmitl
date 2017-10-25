package kmitl.lab08.khunach58070011.espresso;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import kmitl.lab08.khunach58070011.espresso.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static kmitl.lab08.khunach58070011.espresso.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test()
    public void MainActivityTest(){
        AddedTest();
        AddedOnlyAgeTest();
        GoToListTest();
        AddedOnlyNameTest();
        AddedYingTest();
        AddedLadaratTest();
        AddedSomkaitTest();
        AddedPrayochTest();
        AddedPrayochandAge50Test();
    }

    @Test
    public void AddedTest(){
        added();
        ok();
    }
    @Test
    public void AddedOnlyAgeTest(){
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText.perform(replaceText("20"), closeSoftKeyboard());
        added();
        ok();
    }
    @Test
    public void GoToListTest(){
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton5.perform(click());
        pressBack();
    }
    @Test
    public void AddedOnlyNameTest(){
        clear();
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ying"), closeSoftKeyboard());
        added();
        ok();
    }
    @Test
    public void AddedYingTest(){
        setValue("Ying", "20");
        added();
        GoToListTest(0, "Ying", "20");
    }
    @Test
    public void AddedLadaratTest(){
        setValue("Ladarat", "20");
        added();
        GoToListTest(1, "Ladarat", "20");
    }
    @Test
    public void AddedSomkaitTest(){
        setValue("Somkait", "80");
        added();
        GoToListTest(2, "Somkait", "80");
    }
    @Test
    public void AddedPrayochTest(){
        setValue("Prayoch", "60");
        added();
        GoToListTest(3, "Prayoch", "60");
    }
    @Test
    public void AddedPrayochandAge50Test(){
        setValue("Prayoch", "50");
        added();
        GoToListTest(4, "Prayoch", "50");
    }

    public void clear(){
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText.perform(replaceText(""), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText(""), closeSoftKeyboard());
    }
    public void ok(){
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton2.perform(scrollTo(), click());
    }
    public void added(){
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton3.perform(click());
    }
    public void setValue(String name,String age){
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText(name), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText.perform(replaceText(age), closeSoftKeyboard());
    }
    public void GoToListTest(int pos, String name, String age){
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton5.perform(click());

        onView(withRecyclerView(R.id.list).atPositionOnView(pos, R.id.textName)).check(matches(withText(name)));
        onView(withRecyclerView(R.id.list).atPositionOnView(pos, R.id.textAge)).check(matches(withText(age)));

        pressBack();
    }





}
