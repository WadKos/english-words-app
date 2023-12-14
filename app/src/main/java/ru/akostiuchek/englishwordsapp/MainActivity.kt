package ru.akostiuchek.englishwordsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import ru.akostiuchek.englishwordsapp.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityLearnWordBinding
    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityLernWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //findViewById
        //ViewsBinding

//        val tvQuestionWord: TextView = findViewById(R.id.tvQuestionWord)
//        tvQuestionWord.text = "42"
//        tvQuestionWord.setTextColor(Color.BLUE)
//        tvQuestionWord.setTextColor(ContextCompat.getColor(this, R.color.textVariantsColor))

        with(binding) {
            tvQuestionWord.text = "69"
            tvQuestionWord.setTextColor(Color.BLUE)
            imageButton.isVisible = false
        }




    }
}