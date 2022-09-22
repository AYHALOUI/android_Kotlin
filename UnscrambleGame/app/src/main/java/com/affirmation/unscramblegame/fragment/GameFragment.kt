package com.affirmation.unscramblegame.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.affirmation.unscramblegame.GameViewModel
import com.affirmation.unscramblegame.R
import com.affirmation.unscramblegame.databinding.FragmentGameBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class GameFragment : Fragment() {

    private val viewModel : GameViewModel by viewModels()
    private var _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater)
        Log.d("GameFragment", "GameFragment created/re-created!")
        Log.d("GameFragment", "Word: ${viewModel.currentScambledWord} " +
                "Score: ${viewModel.score} WordCount: ${viewModel.currentWordsCount}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skip.setOnClickListener { onSkipWord() }
        binding.submit.setOnClickListener { onSubmitWord() }

        updateNextWordOnScreen()
        binding.score.text = getString(R.string.score, 0)
        binding.wordCount.text = getString(R.string.word_count, 0, GameViewModel.MAX_NO_OF_WORDS)
    }

    private fun updateNextWordOnScreen(){
        binding.textViewUnscrambledWord.text = viewModel.currentScambledWord
    }

    private fun exitGame(){
        activity?.finish()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    private fun onSubmitWord()
    {
        val playWord = binding.textViewUnscrambledWord.text.toString()
        if (viewModel.isUserWordCorrect(playWord)){
            setErrorTextField(false)
            if (viewModel.nextWord()){
                updateNextWordOnScreen()
            }else{
                showFinalScoreDialog()
            }
        }else{
            setErrorTextField(true)
        }
    }

    private fun getNextScrambleWord() : String{
        val tempWord = GameViewModel.allWordsList().random().toCharArray()
        tempWord.shuffle()
        return String(tempWord)
    }

    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, viewModel.score))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)){ _, _ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)){_, _ ->
                restartGame()
            }
            .show()
    }

    private fun restartGame() {
        viewModel.reinitializeData()
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun setErrorTextField(error: Boolean) {
        if (error)
        {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        }else{
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

    private fun onSkipWord(){
        if (viewModel.nextWord()){
            setErrorTextField(false)
            updateNextWordOnScreen()
        }else{
            showFinalScoreDialog()
        }
    }
}