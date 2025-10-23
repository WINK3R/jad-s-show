package com.jad.customer;

import com.jad.show.*;

import java.util.Iterator;

public class Spectator implements ISpectator, IShowVisitor {
    private final String firstName;
    private final String lastName;

    public Spectator(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void watch(final IShow show) {
        if (show == null) {
            throw new IllegalArgumentException("show must not be null");
        }
        show.accept(this);
    }

    @Override
    public void visitMovie(final MovieShow show) {
        System.out.printf("J'ai assisté au film %s de %s sorti en %s%n",
                show.getName(),
                show.getDirector(),
                show.getYearOfRelease());
    }

    @Override
    public void visitTheater(final TheaterShow show) {
        System.out.printf("J'ai assisté à la pièce de théâtre %s de %s.%n",
                show.getName(),
                show.getDirector());
        printList("Il y avait : ", show.getActors().iterator());
    }

    @Override
    public void visitStreet(final StreetShow show) {
        System.out.printf("J'ai assisté au spectacle de rue %s.%n",
                show.getName());
        printList("Il y avait : ", show.getPerformers().iterator());
    }

    @Override
    public void visitConcert(final ConcertShow show) {
        System.out.printf("J'ai assisté au concert %s de %s%n",
                show.getName(),
                show.getArtist());
    }

    @Override
    public void visitDefault(final IShow show) {
        System.out.printf("J'ai assisté au spectacle %s (%s)%n",
                show.getName(),
                show.getShowType().getName());
    }

    private void printList(final String prefix, final Iterator<String> iterator) {
        System.out.print(prefix);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            System.out.print(", ");
        }
        System.out.println();
    }
}
