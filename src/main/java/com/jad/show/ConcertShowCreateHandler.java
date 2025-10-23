package com.jad.show;

import java.util.Map;

final class ConcertShowCreateHandler extends CreateShowHandler {
    ConcertShowCreateHandler(final CreateShowHandler next) {
        super(next);
    }

    @Override
    protected boolean canHandle(final String showDescription) {
        return "CONCERT".equals(this.extractType(showDescription));
    }

    @Override
    protected IShow doHandle(final String showDescription) {
        final Map<String, String> parameters = this.extractParameters(showDescription);
        final String name = this.requireParameter(parameters, "name");
        final String description = this.requireParameter(parameters, "description");
        final String artist = this.requireParameter(parameters, "artist");
        return new ConcertShow(name, description, artist);
    }
}
