package com.training.myapplication.data

import com.training.myapplication.R
import com.training.myapplication.model.Sport

object SportsData {


    fun getSportsDate() : ArrayList<Sport>{
        return arrayListOf(
            Sport(
                id = 1,
                titleResourceId = R.string.baseball,
                subTitleResourceId = R.string.baseball_subtitle,
                imageResourceId = R.drawable.icon_baseball,
                sportsImageBanner = R.drawable.img_baseball
            ),
            Sport(
                id = 2, titleResourceId = R.string.badminton,
                subTitleResourceId = R.string.badminton_subtitle,
                imageResourceId = R.drawable.icon_badminton,
                sportsImageBanner = R.drawable.img_baseball
            ),
            Sport(
                id = 3,
                titleResourceId = R.string.basketball,
                subTitleResourceId = R.string.basketball_subtitle,
                imageResourceId = R.drawable.icon_basketball,
                sportsImageBanner = R.drawable.img_basketball
            ),
            Sport(
                id = 4,
                titleResourceId = R.string.bowling,
                subTitleResourceId = R.string.bowling_subtitle,
                imageResourceId = R.drawable.icon_bowling,
                sportsImageBanner = R.drawable.img_bowling
            ),
            Sport(
                id = 5,
                titleResourceId = R.string.cycling,
                subTitleResourceId = R.string.cycling_subtitle,
                imageResourceId = R.drawable.icon_cycling,
                sportsImageBanner = R.drawable.img_cycling
            ),
            Sport(
                id = 6,
                titleResourceId = R.string.golf,
                subTitleResourceId = R.string.golf_subtitle,
                imageResourceId = R.drawable.icon_golf,
                sportsImageBanner = R.drawable.img_golf
            ),
            Sport(
                id = 7,
                titleResourceId = R.string.running,
                subTitleResourceId = R.string.running_subtitle,
                imageResourceId = R.drawable.icon_running,
                sportsImageBanner = R.drawable.img_running
            ),
            Sport(
                id = 8,
                titleResourceId = R.string.soccer,
                subTitleResourceId = R.string.soccer_subtitle,
                imageResourceId = R.drawable.icon_soccer,
                sportsImageBanner = R.drawable.img_soccer
            ),
            Sport(
                id = 9,
                titleResourceId = R.string.swimming,
                subTitleResourceId = R.string.swimming_subtitle,
                imageResourceId = R.drawable.icon_swimming,
                sportsImageBanner = R.drawable.img_swimming
            ),
            Sport(
                id = 10,
                titleResourceId = R.string.table_tennis,
                subTitleResourceId = R.string.table_tennis_subtitle,
                imageResourceId = R.drawable.icon_tabletennis,
                sportsImageBanner = R.drawable.img_tabletennis
            ),
            Sport(
                id = 11,
                titleResourceId = R.string.tennis,
                subTitleResourceId = R.string.tennis_subtitle,
                imageResourceId = R.drawable.icon_tennis,
                sportsImageBanner = R.drawable.img_tennis
            )
        )
    }
}