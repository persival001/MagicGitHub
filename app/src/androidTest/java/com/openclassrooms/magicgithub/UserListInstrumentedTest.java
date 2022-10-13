package com.openclassrooms.magicgithub;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.magicgithub.utils.RecyclerViewUtils.clickChildView;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.openclassrooms.magicgithub.ui.user_list.ListUserActivity;
import com.openclassrooms.magicgithub.utils.RecyclerViewUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 * Testing ListUserActivity screen.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserListInstrumentedTest {

    @Rule
    public IntentsTestRule<ListUserActivity> mActivityRule = new IntentsTestRule<>(ListUserActivity.class);

    private int currentUsersSize = -1;

    // Avant chaque test la taille de la liste de l interface utilisateur est recuperee
    @Before
    public void setup() {
        currentUsersSize = mActivityRule.getActivity().getUserRepository().getUsers().size();
    }

    // Verifie que des elements de la liste soient presents dans l interface utilisateur
    @Test
    public void checkIfRecyclerViewIsNotEmpty() {
        onView(withId(R.id.activity_list_user_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize));
    }

    // Verifie qu a chaque appui sur le bouton + la taille de la liste s incremente de 1
    @Test
    public void checkIfAddingRandomUserIsWorking() {
        onView(withId(R.id.activity_list_user_fab)).perform(click());
        onView(withId(R.id.activity_list_user_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize + 1));
    }

    // Verifie qu en appuyant sur la premiere icone poubelle un utilisateur soit supprime de la liste
    @Test
    public void checkIfRemovingUserIsWorking() {
        onView(ViewMatchers.withId(R.id.activity_list_user_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.item_list_user_delete_button)));
        onView(withId(R.id.activity_list_user_rv)).check(new RecyclerViewUtils.ItemCount(currentUsersSize - 1));
    }
}