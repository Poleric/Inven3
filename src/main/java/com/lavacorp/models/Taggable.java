package com.lavacorp.models;

import java.util.List;

public interface Taggable {
    List<Tag> getTags();
    void addTag(Tag tag);
    void removeTag(Tag tag);
}
