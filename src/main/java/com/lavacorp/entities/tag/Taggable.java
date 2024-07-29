package com.lavacorp.entities.tag;

import java.util.List;

public interface Taggable {
    List<Tag> getTags();
    void addTag(Tag tag);
    void removeTag(Tag tag);
}
