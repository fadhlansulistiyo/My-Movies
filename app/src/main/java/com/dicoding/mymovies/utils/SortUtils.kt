package com.dicoding.mymovies.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val MOVIES_ENTITIES = "movies_entities"
    const val TV_SHOW_ENTITIES = "tv_show_entities"
    const val RATING_BEST = "rating_best"
    const val RATING_WORST = "rating_worst"
    const val RANDOM = "random"

    fun getSortedQuery(filter: String, tableName: String) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $tableName ")
        when (filter) {
            RATING_BEST -> simpleQuery.append("ORDER BY voteAverage DESC")
            RATING_WORST -> simpleQuery.append("ORDER BY voteAverage ASC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}