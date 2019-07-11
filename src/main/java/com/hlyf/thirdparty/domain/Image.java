package com.hlyf.thirdparty.domain;

/**
 * Created by Administrator on 2019-07-01.
 */
public class Image {
    private String name;
    private String base64image;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", base64image='" + base64image + '\'' +
                '}';
    }
}
