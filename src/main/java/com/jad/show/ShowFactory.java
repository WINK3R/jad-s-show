package com.jad.show;

public final class ShowFactory {
    private ShowFactory() {
        // Utility class
    }

    public static IShow makeShow(final String showDescription) {
        return MovieShowCreateHandler.getInstance().handle(showDescription);
    }
}
