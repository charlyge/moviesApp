import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.charlyge.moviesapp.R
import com.charlyge.moviesapp.activities.MainActivity
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testRecyclerViewItemClick() {

        val firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())
        val recyclerView = onView(withId(R.id.movieRecylerView))
        Thread.sleep(4000)
        // Perform a click on the first item in the RecyclerView
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Check that the correct item is displayed in the next activity
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
    }
}