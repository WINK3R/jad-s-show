package com.jad.show;

import java.util.Map;

final class TheaterShowCreateHandler extends CreateShowHandler {
    TheaterShowCreateHandler(final CreateShowHandler next) {
        super(next);
    }

    @Override
    protected boolean canHandle(final String showDescription) {
        return "THEATER".equals(this.extractType(showDescription));
    }

    @Override
    protected IShow doHandle(final String showDescription) {
        final Map<String, String> parameters = this.extractParameters(showDescription);
        final String name = this.requireParameter(parameters, "name");
        final String description = this.requireParameter(parameters, "description");
        final String director = this.requireParameter(parameters, "director");
        final String actorsRaw = this.requireParameter(parameters, "actors");
        final String[] actors = this.splitValues(actorsRaw);
        if (actors.length == 0) {
            throw new IllegalArgumentException("At least one actor is required");
        }
        return new TheaterShow(name, description, director, actors);
    }
}
