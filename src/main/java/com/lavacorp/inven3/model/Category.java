package com.lavacorp.inven3.model;

import com.lavacorp.inven3.model.generic.NamedEntity;
import org.jetbrains.annotations.Nullable;

public class Category extends NamedEntity {
    @Nullable
    private String description;

    public Category() {
    }

    public @Nullable String getDescription() {
        return this.description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public String toString() {
        return "Category(description=" + this.getDescription() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Category)) return false;
        final Category other = (Category) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Category;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}
