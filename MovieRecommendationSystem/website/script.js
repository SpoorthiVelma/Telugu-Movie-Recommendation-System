// ─────────────────────────────────────────────
// MOVIE DATA
// ─────────────────────────────────────────────

const movies = [

    {
        title: "RRR",
        genre: "action",
        rating: 8.7,
        year: 2022,
        poster: "../posters/RRR.jpg"
    },

    {
        title: "Salaar",
        genre: "thriller",
        rating: 6.5,
        year: 2023,
        poster: "../posters/saalar.jpg"
    },

    {
        title: "Baahubali",
        genre: "fantasy",
        rating: 8.1,
        year: 2015,
        poster: "../posters/Baahubali.jpg"
    },

    {
        title: "Baahubali 2",
        genre: "action",
        rating: 8.2,
        year: 2017,
        poster: "../posters/Baahubali 2.jpg"
    },

    {
        title: "Eega",
        genre: "fantasy",
        rating: 7.7,
        year: 2012,
        poster: "../posters/Eega.jpg"
    },

    {
        title: "Magadheera",
        genre: "fantasy",
        rating: 7.6,
        year: 2009,
        poster: "../posters/Magadheera.jpg"
    },

    {
        title: "Rangasthalam",
        genre: "drama",
        rating: 8.4,
        year: 2018,
        poster: "../posters/Rangasthalam.jpg"
    },

    {
        title: "Bommarillu",
        genre: "romance",
        rating: 8.1,
        year: 2006,
        poster: "../posters/Bommarillu.jpg"
    },

    {
        title: "OK Kanmani",
        genre: "romance",
        rating: 7.4,
        year: 2015,
        poster: "../posters/Ok kanmani.jpg"
    },

    {
        title: "Nuvvu Naaku Nachav",
        genre: "comedy",
        rating: 8.8,
        year: 2001,
        poster: "../posters/Nuvvu naaku nacchavu.jpg"
    }
];


// ─────────────────────────────────────────────
// ELEMENTS
// ─────────────────────────────────────────────

const moviesGrid =
document.getElementById("moviesGrid");

const searchInput =
document.getElementById("searchInput");

const searchBtn =
document.getElementById("searchBtn");

const recommendBtn =
document.getElementById("recommendBtn");

const genreTabs =
document.querySelectorAll(".tab");

const noResults =
document.getElementById("noResults");

const recommendBanner =
document.getElementById("recommendBanner");

const recommendText =
document.getElementById("recommendText");

const clearRecommend =
document.getElementById("clearRecommend");


// ─────────────────────────────────────────────
// DISPLAY MOVIES
// ─────────────────────────────────────────────

function displayMovies(movieList) {

    moviesGrid.innerHTML = "";

    if(movieList.length === 0) {

        noResults.classList.remove("hidden");

        return;
    }

    noResults.classList.add("hidden");

    movieList.forEach((movie) => {

        moviesGrid.innerHTML += `

        <div class="movie-card">

            <img src="${movie.poster}"
                 alt="${movie.title}"

                 onerror="
                 this.style.display='none';
                 this.nextElementSibling
                 .style.display='flex';
                 ">

            <div class="movie-card-placeholder"
                 style="display:none;">

                 🎬

            </div>

            <div class="movie-info">

                <span class="movie-badge">
                    ${movie.genre}
                </span>

                <h3>
                    ${movie.title}
                </h3>

                <div class="movie-meta">

                    <span class="movie-rating">
                        ⭐ ${movie.rating}
                    </span>

                    <span class="movie-year">
                        ${movie.year}
                    </span>

                </div>

            </div>

        </div>
        `;
    });
}


// ─────────────────────────────────────────────
// SEARCH FUNCTION
// ─────────────────────────────────────────────

function searchMovies() {

    const searchValue =
    searchInput.value
    .toLowerCase()
    .trim();

    const filteredMovies =
    movies.filter((movie) =>

        movie.title
        .toLowerCase()
        .includes(searchValue)
    );

    displayMovies(filteredMovies);
}


// ─────────────────────────────────────────────
// GENRE FILTER
// ─────────────────────────────────────────────

genreTabs.forEach((tab) => {

    tab.addEventListener("click", () => {

        document
        .querySelector(".tab.active")
        .classList
        .remove("active");

        tab.classList.add("active");

        const genre =
        tab.dataset.genre;

        if(genre === "all") {

            displayMovies(movies);
        }

        else if(genre === "top") {

            const topMovies =
            movies.filter(
            movie => movie.rating >= 8.0
            );

            displayMovies(topMovies);
        }

        else {

            const filteredMovies =
            movies.filter(
            movie =>
            movie.genre === genre
            );

            displayMovies(filteredMovies);
        }
    });
});


// ─────────────────────────────────────────────
// RECOMMENDATION
// ─────────────────────────────────────────────

recommendBtn.addEventListener(
"click",
() => {

    const searchValue =
    searchInput.value
    .toLowerCase()
    .trim();

    const selectedMovie =
    movies.find(
    movie =>
    movie.title
    .toLowerCase()
    === searchValue
    );

    if(!selectedMovie) {

        alert(
        "Movie not found!"
        );

        return;
    }

    const recommendations =
    movies.filter(

        movie =>

        movie.genre ===
        selectedMovie.genre

        &&

        movie.title !==
        selectedMovie.title
    );

    displayMovies(recommendations);

    recommendBanner
    .classList
    .remove("hidden");

    recommendText.innerText =

    `Because you liked
    "${selectedMovie.title}",
    similar ${selectedMovie.genre}
    movies are recommended.`;
});


// ─────────────────────────────────────────────
// CLEAR RECOMMENDATION
// ─────────────────────────────────────────────

clearRecommend.addEventListener(
"click",
() => {

    recommendBanner
    .classList
    .add("hidden");

    displayMovies(movies);
});


// ─────────────────────────────────────────────
// SEARCH EVENTS
// ─────────────────────────────────────────────

searchBtn.addEventListener(
"click",
searchMovies
);

searchInput.addEventListener(
"keyup",
(event) => {

    if(event.key === "Enter") {

        searchMovies();
    }

    if(searchInput.value === "") {

        displayMovies(movies);
    }
});


// ─────────────────────────────────────────────
// INITIAL LOAD
// ─────────────────────────────────────────────

displayMovies(movies);