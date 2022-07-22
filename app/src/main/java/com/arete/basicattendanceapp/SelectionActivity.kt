package com.arete.basicattendanceapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arete.basicattendanceapp.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity() {

    private var _binding: ActivitySelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()

        // Add a callback that's called when the splash screen is animating to
        // the app content.
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 800L

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.doOnEnd { splashScreenView.remove() }

            // Run your animation.
            slideUp.start()
        }

        /*splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideBack = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.view.width.toFloat()
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = 800L
                doOnEnd { splashScreenView.remove() }
            }

            slideBack.start()
        }*/

        _binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.card.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
            /*lifecycleScope.launch {
                save("", "")
            }*/
        }

        binding.cardCmp.setOnClickListener{
            val intent = Intent(this, AddCompanyActivity::class.java)
            startActivity(intent)
        }

        binding.cardEmpList.setOnClickListener{
            val intent = Intent(this, EMPListActivity::class.java)
            startActivity(intent)
        }

        binding.card1.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
        }

        binding.card2.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
        }

        binding.card3.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "message")
            }
            startActivity(intent)
        }

    }

    private suspend fun save(key: String, value: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}