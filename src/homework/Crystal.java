package homework;

import java.util.Objects;

public class Crystal {

    private CrystalColor color;

    public Crystal() {

    }

    public Crystal(CrystalColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Crystal{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crystal crystal = (Crystal) o;
        return color == crystal.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public CrystalColor getColor() {
        return color;
    }

    public void setColor(CrystalColor color) {
        this.color = color;
    }
}
