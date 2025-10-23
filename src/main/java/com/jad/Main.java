package com.jad;

import com.jad.customer.*;
import com.jad.show.IShow;
import com.jad.show.ShowFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UtilityClassCanBeEnum")
public final class Main {
    public static void main(final String[] args) {
        final ISpectator jad = new Spectator("Jean-Aymeric", "Diet");

        final List<IShow> shows = new ArrayList<>();
        final List<String> showDescriptions = List.of(
                "MOVIE:name=Titre du film;" +
                        "description=Description du film;" +
                        "director=Nom du réalisateur;" +
                        "yearOfRelease=2023;" +
                        "movieType=SCIENCE_FICTION",
                "THEATER:name=Titre du spectacle de théâtre;" +
                        "description=Description du spectacle de théâtre;" +
                        "director=Nom du metteur en scène;" +
                        "actors=Nom de l'acteur 1,Nom de l'acteur 2,Nom de l'acteur 3",
                "STREET_SHOW:name=Titre du spectacle de rue;" +
                        "description=Description du spectacle de rue;" +
                        "performers=Nom de l'artiste 1,Nom de l'artiste 2,Nom de l'artiste 3",
                "CONCERT:name=Titre du concert;" +
                        "description=Description du concert;" +
                        "artist=Nom de l'artiste ou du groupe"
        );

        for (final String description : showDescriptions) {
            shows.add(ShowFactory.makeShow(description));
        }

        for (final IShow show : shows) {
            jad.watch(show);
        }
    }
}
