package ru.akostiuchek.englishwordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text
import ru.akostiuchek.englishwordsapp.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trainer = LearnWordsTrainer()
        showNextQuestion(trainer)

        with(binding) {
            btnContinue.setOnClickListener {
                layoutResult.isVisible = true
                markAnswerNeutral(layoutAnswer1, tvVariantNumber1, tvVariantValue1)
                markAnswerNeutral(layoutAnswer2, tvVariantNumber2, tvVariantValue2)
                markAnswerNeutral(layoutAnswer3, tvVariantNumber3, tvVariantValue3)
                markAnswerNeutral(layoutAnswer4, tvVariantNumber4, tvVariantValue4)
                showNextQuestion(trainer)
            }

            btnSkip.setOnClickListener {
                showNextQuestion(trainer)
            }

            ivClose.setOnClickListener {
                finish()
            }
        }

    }

    private fun showNextQuestion(trainer: LearnWordsTrainer) {
        val firstQuestion: Question? = trainer.getNextQuestion()
        with(binding) {
            layoutResult.isVisible = false
            btnSkip.isVisible = true
            if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionWord.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Complete"
            } else {
                tvQuestionWord.isVisible = true
                tvQuestionWord.text = firstQuestion.correctAnswer.original

                tvVariantValue1.text = firstQuestion.variants[0].translate
                tvVariantValue2.text = firstQuestion.variants[1].translate
                tvVariantValue3.text = firstQuestion.variants[2].translate
                tvVariantValue4.text = firstQuestion.variants[3].translate

                layoutAnswer1.setOnClickListener {
                    if (trainer.checkAnswer(0)) {
                        markAnswerCorrect(layoutAnswer1, tvVariantNumber1, tvVariantValue1)
                    } else {
                        markAnswerIncorrect(layoutAnswer1, tvVariantNumber1, tvVariantValue1)
                    }
                    layoutAnswer1.setOnClickListener(null)
                    layoutAnswer2.setOnClickListener(null)
                    layoutAnswer3.setOnClickListener(null)
                    layoutAnswer4.setOnClickListener(null)
                }

                layoutAnswer2.setOnClickListener {
                    if (trainer.checkAnswer(1)) {
                        markAnswerCorrect(layoutAnswer2, tvVariantNumber2, tvVariantValue2)
                    } else {
                        markAnswerIncorrect(layoutAnswer2, tvVariantNumber2, tvVariantValue2)
                    }
                    layoutAnswer1.setOnClickListener(null)
                    layoutAnswer2.setOnClickListener(null)
                    layoutAnswer3.setOnClickListener(null)
                    layoutAnswer4.setOnClickListener(null)
                }

                layoutAnswer3.setOnClickListener {
                    if (trainer.checkAnswer(2)) {
                        markAnswerCorrect(layoutAnswer3, tvVariantNumber3, tvVariantValue3)
                    } else {
                        markAnswerIncorrect(layoutAnswer3, tvVariantNumber3, tvVariantValue3)
                    }
                    layoutAnswer1.setOnClickListener(null)
                    layoutAnswer2.setOnClickListener(null)
                    layoutAnswer3.setOnClickListener(null)
                    layoutAnswer4.setOnClickListener(null)
                }

                layoutAnswer4.setOnClickListener {
                    if (trainer.checkAnswer(3)) {
                        markAnswerCorrect(layoutAnswer4, tvVariantNumber4, tvVariantValue4)
                    } else {
                        markAnswerIncorrect(layoutAnswer4, tvVariantNumber4, tvVariantValue4)
                    }
                    layoutAnswer1.setOnClickListener(null)
                    layoutAnswer2.setOnClickListener(null)
                    layoutAnswer3.setOnClickListener(null)
                    layoutAnswer4.setOnClickListener(null)
                }
            }
        }
    }

    private fun markAnswerNeutral(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers
        )

        tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_variants
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textVariantsColor
                )
            )
        }

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            )
        )
    }

    private fun markAnswerIncorrect(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_incorrect
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_incorrect
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.incorrectColor2
            )
        )

        showResultMessage(false)
    }

    private fun markAnswerCorrect(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_correct
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_correct
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
            this@MainActivity,
            R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctColor
            )
        )

        showResultMessage(true)
    }

    private fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val messageText: String
        val resultIconResource: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this@MainActivity, R.color.correctColor)
            resultIconResource = R.drawable.ic_correct
            messageText = resources.getString(R.string.title_correct)
        } else {
            color = ContextCompat.getColor(this@MainActivity, R.color.incorrectColor1)
            resultIconResource = R.drawable.ic_wrong
            messageText = resources.getString(R.string.title_incorrect)
        }

        with(binding) {
            binding.btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResultMessage.text = messageText
            ivResultIcon.setImageResource(resultIconResource)
        }
    }
}