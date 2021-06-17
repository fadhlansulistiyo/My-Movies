package com.dicoding.mymovies.utils

import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity
import com.dicoding.mymovies.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies(): List<MoviesEntity> {
        return listOf(
            MoviesEntity(
                508442,
                "Soul",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                8.4,
                102,
                "2020-12-25",
                "Animation, Comedy, Drama, Music, Fantasy",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                false
                ),
            MoviesEntity(
                464052,
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                7.2,
                151,
                "2020-12-16",
                "Fantasy, Action, Adventure",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                false
                ),
            MoviesEntity(
                517096,
                "Cosmoball",
                "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
                5.3,
                115,
                "2020-08-27",
                "Science Fiction, Adventure",
                "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
                false
                )
        )
    }

    fun getDetailMovies() : MoviesEntity {
        return MoviesEntity(
            517096,
            "Cosmoball",
            "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
            5.3,
            115,
            "2020-08-27",
            "Science Fiction, Adventure",
            "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
            false
        )
    }

    fun generateDummyTvshow(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                1416,
                "Grey's Anatomy",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                8.2,
                43,
                "2005-03-27",
                "Drama",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                false
                ),
            TvShowEntity(
                456,
                "The Simpsons",
                "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                7.8,
                22,
                "1989-12-16",
                "Animation, Comedy, Family, Drama",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                false
                ),
            TvShowEntity(
                1399,
                "Game of Thrones",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                8.4,
                60,
                "2011-04-17",
                "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                false
                )
        )
    }

    fun getDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            77169,
            "Cobra Kai",
            "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
            8.1,
            30,
            "2018-05-02",
            "Action & Adventure, Drama",
            "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            false
            )
    }

    fun generateRemoteDummyMovies() : List<Movies> {
        return listOf(
            Movies(
                backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                genreIds =  listOf(14, 28, 12),
                id = 464052,
                originalTitle = "Wonder Woman 1984",
                overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                releaseDate = "2020-12-16",
                title = "Wonder Woman 1984",
                voteAverage = 7.2,
            ),
            Movies(
                backdropPath = "/ibwOX4xUndc6E90MYfglghWvO5Z.jpg",
                genreIds =  listOf(878, 12),
                id = 517096,
                originalTitle = "Вратарь Галактики",
                overview = "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
                posterPath = "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
                releaseDate = "2020-08-27",
                title = "Cosmoball",
                voteAverage = 5.5,
            ),
            Movies(
                backdropPath = "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                genreIds =  listOf(16, 35, 18, 10402, 14),
                id = 508442,
                originalTitle = "Soul",
                overview = "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                posterPath = "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                releaseDate = "2020-12-25",
                title = "Soul",
                voteAverage = 8.4,
            )
        )
    }

    fun generateRemoteDetailMovies() : DetailMoviesResponse {
        return DetailMoviesResponse(
            backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            genres = listOf(
                GenresItem(
                    id = 14,
                    name = "Fantasy"
                ),
                GenresItem(
                    id = 28,
                    name = "Action"
                ),
                GenresItem(
                    id = 12,
                    name = "Adventure"
                )
            ),
            id = 464052,
            overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            releaseDate = "2020-12-16",
            runtime = 151,
            title = "Wonder Woman 1984",
            voteAverage = 7.2,
        )
    }

    fun generateRemoteTvShow() : List<TvShow> {
        return listOf(
            TvShow(
                backdropPath = "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                firstAirDate = "2019-11-12",
                genreIds = listOf(10765, 10759),
                id = 82856,
                name = "The Mandalorian",
                originalLanguage = "en",
                originalName = "The Mandalorian",
                overview = "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                posterPath = "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                voteAverage = 8.5,
            ),
            TvShow(
                backdropPath = "/aq2yEMgRQBPfRkrO0Repo2qhUAT.jpg",
                firstAirDate = "2013-03-03",
                genreIds = listOf(10759, 18),
                id = 44217,
                name = "Vikings",
                originalLanguage = "en",
                originalName = "Vikings",
                overview = "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                posterPath = "/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",
                voteAverage = 8.0,
            ),
            TvShow(
                backdropPath = "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                firstAirDate = "2018-05-02",
                genreIds = listOf(10759, 18),
                id = 77169,
                name = "Cobra Kai",
                originalLanguage = "en",
                originalName = "Cobra Kai",
                overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                posterPath = "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                voteAverage = 8.1,
            )
        )
    }

    fun generateRemoteDetailTvShow() : DetailTvShowResponse {
        return DetailTvShowResponse(
            backdropPath = "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
            episodeRunTime = listOf(30),
            firstAirDate = "2018-05-02",
            genres = listOf(
                GenresItem(
                    id = 10759,
                    name = "Action & Adventure"
                ),
                GenresItem(
                    id = 18,
                    name = "Drama"
                )
            ),
            homepage = "https://www.netflix.com/title/81002370",
            id = 77169,
            lastAirDate = "2021-01-01",
            name = "Cobra Kai",
            numberOfEpisodes = 30,
            numberOfSeasons = 3,
            originalName = "Cobra Kai",
            overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            posterPath = "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
            voteAverage = 8.1,
        )
    }
}