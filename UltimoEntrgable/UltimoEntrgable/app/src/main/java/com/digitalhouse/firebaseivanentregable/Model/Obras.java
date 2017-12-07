package com.digitalhouse.firebaseivanentregable.Model;

/**
 * Created by ma on 27/11/17.
 */

public class Obras {
    private String name;
    private Integer artistId;
    private String image;

    public Obras(String name, Integer artistId, String image) {
        this.name = name;
        this.artistId = artistId;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }
}
