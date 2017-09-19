package com.example.lingxiao.dribbbleee.Model;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by lingxiao on 9/16/17.
 */

public class Shot {

    public static final String IMAGE_NORMAL = "normal";
    public static final String IMAGE_HIDPI = "hidpi";

    public String id;
    public String title;
    public String description;
    public String html_url;
    public boolean animated;

    public int likes_count;
    public int views_count;
    public int buckets_count;

    public Map<String, String> images;

    public User user;

    @Nullable
    public String getImageUrl() {
        if (images == null) {
            return null;
        } else if (animated) {
            return images.get(IMAGE_NORMAL);
        }

        return images.containsKey(IMAGE_HIDPI)
                ? images.get(IMAGE_HIDPI)
                : images.get(IMAGE_NORMAL);
    }

}
