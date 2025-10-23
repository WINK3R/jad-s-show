package com.jad.show;

import java.util.Map;

final class StreetShowCreateHandler extends CreateShowHandler {
    StreetShowCreateHandler(final CreateShowHandler next) {
        super(next);
    }

    @Override
    protected boolean canHandle(final String showDescription) {
        return "STREET_SHOW".equals(this.extractType(showDescription));
    }

    @Override
    protected IShow doHandle(final String showDescription) {
        final Map<String, String> parameters = this.extractParameters(showDescription);
        final String name = this.requireParameter(parameters, "name");
        final String description = this.requireParameter(parameters, "description");
        final String performersRaw = this.requireParameter(parameters, "performers");
        final String[] performers = this.splitValues(performersRaw);
        if (performers.length == 0) {
            throw new IllegalArgumentException("At least one performer is required");
        }
        return new StreetShow(name, description, performers);
    }
}
