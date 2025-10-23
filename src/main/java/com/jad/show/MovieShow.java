package com.jad.show;

public class MovieShow extends Show {
    private final String director;
    private final String yearOfRelease;
    private final MovieType movieType;

    MovieShow(final String name, final String description, final String director, final String yearOfRelease, final MovieType movieType) {
        super(name, description, ShowType.MOVIE);
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.movieType = movieType;
    }

    public String getDirector() {
        return this.director;
    }

    public String getYearOfRelease() {
        return this.yearOfRelease;
    }

    public MovieType getMovieType() {
        return this.movieType;
    }

    @Override
    public void accept(final IShowVisitor visitor) {
        visitor.visitMovie(this);
    }
}
