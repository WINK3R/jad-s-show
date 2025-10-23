package com.jad.show;

public abstract class Show implements IShow {
    private final String name;
    private final String description;
    private final ShowType showType;

    public Show(final String name, final String description, final ShowType showType) {
        this.name = name;
        this.description = description;
        this.showType = showType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public ShowType getShowType() {
        return this.showType;
    }

    @Override
    public void accept(final IShowVisitor visitor) {
        visitor.visitDefault(this);
    }
}
