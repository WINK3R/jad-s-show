package com.jad.show;

public interface IShow {
    String getName();
    String getDescription();
    ShowType getShowType();

    void accept(IShowVisitor visitor);
}
