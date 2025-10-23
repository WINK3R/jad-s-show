package com.jad.show;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

abstract class CreateShowHandler {
    private final CreateShowHandler next;

    CreateShowHandler(final CreateShowHandler next) {
        this.next = next;
    }

    public final IShow handle(final String showDescription) {
        final String sanitized = sanitize(showDescription);
        return this.proceed(sanitized);
    }

    private IShow proceed(final String showDescription) {
        if (this.canHandle(showDescription)) {
            return this.doHandle(showDescription);
        }
        if (this.next != null) {
            return this.next.proceed(showDescription);
        }
        throw new IllegalArgumentException("Unsupported show description: " + showDescription);
    }

    protected abstract boolean canHandle(String showDescription);

    protected abstract IShow doHandle(String showDescription);

    protected String extractType(final String showDescription) {
        final int separatorIndex = showDescription.indexOf(':');
        if (separatorIndex < 0) {
            throw new IllegalArgumentException("Missing type separator ':' in description: " + showDescription);
        }
        final String type = showDescription.substring(0, separatorIndex).trim();
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Show type is missing in description: " + showDescription);
        }
        return type.toUpperCase(Locale.ROOT);
    }

    protected Map<String, String> extractParameters(final String showDescription) {
        final int separatorIndex = showDescription.indexOf(':');
        if (separatorIndex < 0 || separatorIndex == showDescription.length() - 1) {
            throw new IllegalArgumentException("Missing parameters in description: " + showDescription);
        }
        final String rawParameters = showDescription.substring(separatorIndex + 1);
        final Map<String, String> parameters = new LinkedHashMap<>();
        final String[] entries = rawParameters.split(";");
        for (final String entry : entries) {
            final String trimmedEntry = entry.trim();
            if (trimmedEntry.isEmpty()) {
                continue;
            }
            final String[] parts = trimmedEntry.split("=", 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid parameter chunk '" + trimmedEntry + "' in description: " + showDescription);
            }
            final String key = parts[0].trim().toLowerCase(Locale.ROOT);
            final String value = parts[1].trim();
            parameters.put(key, value);
        }
        return parameters;
    }

    protected String requireParameter(final Map<String, String> parameters, final String key) {
        final String value = parameters.get(key.toLowerCase(Locale.ROOT));
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Missing required parameter '" + key + "'");
        }
        return value;
    }

    protected String[] splitValues(final String rawValues) {
        return Arrays.stream(rawValues.split(","))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .toArray(String[]::new);
    }

    protected String sanitize(final String showDescription) {
        if (showDescription == null) {
            throw new IllegalArgumentException("showDescription must not be null");
        }
        return showDescription.trim();
    }
}
