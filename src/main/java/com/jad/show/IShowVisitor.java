package com.jad.show;

public interface IShowVisitor {
    void visitMovie(MovieShow show);

    void visitTheater(TheaterShow show);

    void visitStreet(StreetShow show);

    void visitConcert(ConcertShow show);

    void visitDefault(IShow show);
}
