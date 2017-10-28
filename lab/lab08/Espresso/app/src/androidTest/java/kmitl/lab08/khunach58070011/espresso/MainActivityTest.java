package kmitl.lab08.khunach58070011.espresso;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getArguments;
import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
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
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;

import static kmitl.lab08.khunach58070011.espresso.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private String[] name = {"Ying", "Ladarat", "Somkait", "Prayoch", "Prayoch"};
    private String[] age = {"20", "20", "80", "60", "50"};
    private boolean testAll = false;
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MainActivityTest() throws Throwable{
        testAll = true;
        AddedTest();
        AddedOnlyAgeTest();
        GoToListTest();
        AddedOnlyNameTest();
        AddedYingTest();
        AddedLadaratTest();
        AddedSomkaitTest();
        AddedPrayochTest();
        AddedPrayochandAge50Test();
        testAll = false;
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
    public void AddedYingTest() throws Throwable{
        setValue(name[0], age[0]);
        added();
        GoToListTest(0, name[0], age[0]);
    }
    @Test
    public void AddedLadaratTest() throws Throwable{
        setData(1);
        setValue(name[1], age[1]);
        added();
        GoToListTest(1, name[1], age[1]);
    }
    @Test
    public void AddedSomkaitTest() throws Throwable{
        setData(2);
        setValue(name[2], age[2]);
        added();
        GoToListTest(2, name[2], age[2]);
    }
    @Test
    public void AddedPrayochTest() throws Throwable{
        setData(3);
        setValue(name[3], age[3]);
        added();
        GoToListTest(3, name[3], age[3]);
    }
    @Test
    public void AddedPrayochandAge50Test() throws Throwable{
        setData(4);
        setValue(name[4], age[4]);
        added();
        GoToListTest(4, name[4], age[4]);
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
    public void setData(final int pos) throws Throwable{
        mActivityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (testAll == true){
                    return;
                }
                for (int i = 1; i <= pos; i++){
                    mActivityTestRule.getActivity().setUserInfo(name[i - 1], age[i - 1]);
                }
            }
        });
    }
    @Before
     public void start() {
        Context context = InstrumentationRegistry.getTargetContext();
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }

        mActivityTestRule.launchActivity(new Intent());

    }

}
