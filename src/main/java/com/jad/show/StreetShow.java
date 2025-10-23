package com.jad.show;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StreetShow extends Show {
    private final ArrayList<String> performers = new ArrayList<>();

    StreetShow(final String name, final String description, final String... performers) {
        super(name, description, ShowType.STREET_SHOW);
        this.performers.addAll(Arrays.asList(performers));
    }

    public List<String> getPerformers() {
        return Collections.unmodifiableList(this.performers);
    }

    @Override
    public void accept(final IShowVisitor visitor) {
        visitor.visitStreet(this);
    }
}
