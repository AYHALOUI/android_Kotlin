package com.affirmation.unscramblegame

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _score = 0
    val score : Int
        get() = _score

    private var _currentWordCount = 0
    val currentWordsCount : Int
        get() = _currentWordCount

    private var _currentScambledWord = "test"
    val currentScambledWord : String
        get() = _currentScambledWord


    private var wordList : MutableList<String> = mutableListOf()
    private lateinit var currentWord : String


    init {
        Log.d("GameFragment", "GameViewModel Created")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment",  "GameViewModel destroyed!")
    }

    private fun getNextWord()
    {
        currentWord = allWordsList().random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)){
            tempWord.shuffle()
        }

        if (wordList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScambledWord = String(tempWord)
            ++_currentWordCount;
            wordList.add(currentWord)
        }
    }

    fun reinitializeData()
    {
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }

    private fun increaseScore()
    {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord : String) : Boolean{
        if(playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }

    fun nextWord() : Boolean{
        return if (_currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        }else false
    }









    companion object{

        const val MAX_NO_OF_WORDS = 10
        const val SCORE_INCREASE = 20

        fun allWordsList() : List<String> = listOf(
            "animal",
            "auto",
            "anecdote",
            "alphabet",
            "all",
            "awesome",
            "arise",
            "balloon",
            "basket",
            "bench",
            "best",
            "birthday",
            "book",
            "briefcase",
            "camera",
            "camping",
            "candle",
            "cat",
            "cauliflower",
            "chat",
            "children",
            "class",
            "classic",
            "classroom",
            "coffee",
            "colorful",
            "cookie",
            "creative",
            "cruise",
            "dance",
            "daytime",
            "dinosaur",
            "doorknob",
            "dine",
            "dream",
            "dusk",
            "eating",
            "elephant",
            "emerald",
            "eerie",
            "electric",
            "finish",
            "flowers",
            "follow",
            "fox",
            "frame",
            "free",
            "frequent",
            "funnel",
            "green",
            "guitar",
            "grocery",
            "glass",
            "great",
            "giggle",
            "haircut",
            "half",
            "homemade",
            "happen",
            "honey",
            "hurry",
            "hundred",
            "ice",
            "igloo",
            "invest",
            "invite",
            "icon",
            "introduce",
            "joke",
            "jovial",
            "journal",
            "jump",
            "join",
            "kangaroo",
            "keyboard",
            "kitchen",
            "koala",
            "kind",
            "kaleidoscope",
            "landscape",
            "late",
            "laugh",
            "learning",
            "lemon",
            "letter",
            "lily",
            "magazine",
            "marine",
            "marshmallow",
            "maze",
            "meditate",
            "melody",
            "minute",
            "monument",
            "moon",
            "motorcycle",
            "mountain",
            "music",
            "north",
            "nose",
            "night",
            "name",
            "never",
            "negotiate",
            "number",
            "opposite",
            "octopus",
            "oak",
            "order",
            "open",
            "polar",
            "pack",
            "painting",
            "person",
            "picnic",
            "pillow",
            "pizza",
            "podcast",
            "presentation",
            "puppy",
            "puzzle",
            "recipe",
            "release",
            "restaurant",
            "revolve",
            "rewind",
            "room",
            "run",
            "secret",
            "seed",
            "ship",
            "shirt",
            "should",
            "small",
            "spaceship",
            "stargazing",
            "skill",
            "street",
            "style",
            "sunrise",
            "taxi",
            "tidy",
            "timer",
            "together",
            "tooth",
            "tourist",
            "travel",
            "truck",
            "under",
            "useful",
            "unicorn",
            "unique",
            "uplift",
            "uniform",
            "vase",
            "violin",
            "visitor",
            "vision",
            "volume",
            "view",
            "walrus",
            "wander",
            "world",
            "winter",
            "well",
            "whirlwind",
            "x-ray",
            "xylophone",
            "yoga",
            "yogurt",
            "yoyo",
            "you",
            "year",
            "yummy",
            "zebra",
            "zigzag",
            "zoology",
            "zone",
            "zeal")
    }
}