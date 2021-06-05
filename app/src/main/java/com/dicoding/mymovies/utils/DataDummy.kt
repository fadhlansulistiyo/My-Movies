package com.dicoding.mymovies.utils

import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.MoviesEntity
import com.dicoding.mymovies.data.source.local.entity.TvShowEntity

object DataDummy {

    fun generateDummyMovies(): List<MoviesEntity> {

        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                1,
                "A Star is Born",
                "2018",
                "Drama, Romance, Music",
                "75",
                "2h 16m",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                R.drawable.poster_a_start_is_born
            )
        )

        movies.add(
            MoviesEntity(
                2,
                "Alita: Battle Angel",
                "2019",
                "Action, Science, Fiction, Adventure",
                "72",
                "2h 2m",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                R.drawable.poster_alita
            )
        )

        movies.add(
            MoviesEntity(
                3,
                "Aquaman",
                "2018",
                "Action, Adventure, Fantasy",
                "69",
                "2h 23m",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman
            )
        )

        movies.add(
            MoviesEntity(
                4,
                "Bohemian Rhapsody",
                "2018",
                "Music, Drama, History",
                "80",
                "2h 15m",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                R.drawable.poster_bohemian
            )
        )

        movies.add(
            MoviesEntity(
                5,
                "Cold Pursuit",
                "2019",
                "Action, Crime, Thriller",
                "57",
                "1h 59m",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                R.drawable.poster_cold_persuit
            )
        )

        movies.add(
            MoviesEntity(
                6,
                "Creed II",
                "2018",
                "Drama",
                "69",
                "2h 10m",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                R.drawable.poster_creed
            )
        )

        movies.add(
            MoviesEntity(
                7,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "2018",
                "Adventure, Fantasy, Drama",
                "69",
                "2h 14m",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                R.drawable.poster_crimes
            )
        )

        movies.add(
            MoviesEntity(
                8,
                "Glass",
                "2019",
                "Thriller, Drama, Science Fiction",
                "67",
                "2h 9m",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                R.drawable.poster_glass
            )
        )

        movies.add(
            MoviesEntity(
                9,
                "How to Train Your Dragon: The Hidden World",
                "2019",
                "Animation, Family, Adventure",
                "78",
                "1h 44m",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                R.drawable.poster_how_to_train
            )
        )

        movies.add(
            MoviesEntity(
                10,
                "Avengers: Infinity War",
                "2018",
                "Adventure, Action, Science Fiction",
                "83",
                "2h 29m",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                R.drawable.poster_infinity_war
            )
        )
        return movies
    }

    fun generateDummyTvshow(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                1,
                "Arrow",
                "2012",
                "Action, Fiction",
                "86",
                "42",
                "After mastering the skill of archery on a deserted island, multi-millionaire playboy Oliver Queen returns to his city to take on the vigilante persona of Arrow to fight crime and corruption.",
                R.drawable.poster_arrow
            )
        )

        tvShow.add(
            TvShowEntity(
                2,
                "Doom Patrol",
                "2019",
                "Comedy, Drama",
                "96",
                "49",
                "Doom Patrol is a team of traumatized and downtrodden superheroes, each of whom has suffered a horrible accident that gave them superhuman abilities but also left them scarred and disfigured. The members of the team have found their purpose through The Chief and have come together to investigate some of the world's weirdest phenomena. After The Chief mysteriously disappears, though, the reluctant heroes find themselves called to action by Cyborg, who comes to them with a mission that they cannot refuse. Doom Patrol -- part support group, part superhero team -- is a band of superpowered freaks fighting for a world that wants nothing to do with them.",
                R.drawable.poster_doom_patrol
            )
        )

        tvShow.add(
            TvShowEntity(
                3,
                "Dragon Ball",
                "1986",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "81",
                "25",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                R.drawable.poster_dragon_ball
            )
        )

        tvShow.add(
            TvShowEntity(
                4,
                "Fairy Tail",
                "2009",
                "Action & Advanture, Animation, Comedy",
                "78",
                "25",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                R.drawable.poster_fairytail
            )
        )

        tvShow.add(
            TvShowEntity(
                5,
                "Family Guy",
                "1999",
                "Animation, Comedy",
                "70",
                "22",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                R.drawable.poster_family_guy
            )
        )

        tvShow.add(
            TvShowEntity(
                6,
                "The Flash",
                "2014",
                "Drama, Sci-Fi & Fantasy",
                "77",
                "44",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                R.drawable.poster_flash
            )
        )

        tvShow.add(
            TvShowEntity(
                7,
                "Game of Thrones",
                "2011",
                "Sci-Fi & Fantasy, Drama",
                "84",
                "1h",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                R.drawable.poster_god
            )
        )

        tvShow.add(
            TvShowEntity(
                8,
                "Gotham",
                "2014",
                "Drama, Crime, Sci-Fi & Fantasy",
                "75",
                "43",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                R.drawable.poster_gotham
            )
        )

        tvShow.add(
            TvShowEntity(
                9,
                "Grey's Anatomy",
                "2005",
                "Drama",
                "82",
                "43",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                R.drawable.poster_grey_anatomy
            )
        )

        tvShow.add(
            TvShowEntity(
                10,
                "Hanna",
                "2019",
                "Action & Adventure, Drama",
                "75",
                "50",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                R.drawable.poster_hanna
            )
        )

        return tvShow
    }

    fun getDetailMovies(moviesId: Int, listMovies: ArrayList<MoviesEntity>) : MoviesEntity =
        listMovies[moviesId - 1]

    fun getDetailTvShow(tvShowId: Int, listTvShow: ArrayList<TvShowEntity>) : TvShowEntity =
        listTvShow[tvShowId - 1]
}