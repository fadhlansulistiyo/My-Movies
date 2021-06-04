package com.dicoding.mymovies.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.TvShowEntity
import com.dicoding.mymovies.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShow() : List<TvShowEntity> = DataDummy.generateDummyTvshow()
}