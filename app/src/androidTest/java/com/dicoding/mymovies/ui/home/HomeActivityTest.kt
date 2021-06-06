package com.dicoding.mymovies.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.mymovies.R
import com.dicoding.mymovies.utils.DataDummy
import com.dicoding.mymovies.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvshow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // 1. title
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))

        // 2. release date
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))

        // 3. genre
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))

        // 4. rating
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))

        // 5. duration
        onView(withId(R.id.tv_durating_eps)).check(matches(isDisplayed()))

        // 6. overview
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        // 7. image
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // 1. title
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))

        // 2. release date
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))

        // 3. genre
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))

        // 4. rating
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))

        // 5. duration
        onView(withId(R.id.tv_durating_eps)).check(matches(isDisplayed()))

        // 6. overview
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        // 7. image
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }

}