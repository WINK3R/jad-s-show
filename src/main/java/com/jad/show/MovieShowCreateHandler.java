package com.jad.show;

import java.util.Locale;
import java.util.Map;

final class MovieShowCreateHandler extends CreateShowHandler {
    private MovieShowCreateHandler() {
        super(new TheaterShowCreateHandler(new StreetShowCreateHandler(new ConcertShowCreateHandler(null))));
    }

    static MovieShowCreateHandler getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected boolean canHandle(final String showDescription) {
        return "MOVIE".equals(this.extractType(showDescription));
    }

    @Override
    protected IShow doHandle(final String showDescription) {
        final Map<String, String> parameters = this.extractParameters(showDescription);
        final String name = this.requireParameter(parameters, "name");
        final String description = this.requireParameter(parameters, "description");
        final String director = this.requireParameter(parameters, "director");
        final String yearOfRelease = this.requireParameter(parameters, "yearOfRelease");
        final String movieTypeName = this.requireParameter(parameters, "movieType");

        final MovieType movieType;
        try {
            movieType = MovieType.valueOf(movieTypeName.toUpperCase(Locale.ROOT));
        } catch (final IllegalArgumentException exception) {
            throw new IllegalArgumentException("Unknown movie type '" + movieTypeName + "'", exception);
        }

        return new MovieShow(name, description, director, yearOfRelease, movieType);
    }

    private static final class Holder {
        private static final MovieShowCreateHandler INSTANCE = new MovieShowCreateHandler();
    }
}
