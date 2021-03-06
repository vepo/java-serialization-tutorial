package io.vepo.serialization;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ComplexObjects implements Serializable {
    private static final long serialVersionUID = -5307878707845899643L;
    private List<ComplexObject> objects;

    public ComplexObjects() {
    }

    public ComplexObjects(List<ComplexObject> objects) {
        this.objects = objects;
    }

    public List<ComplexObject> getObjects() {
        return objects;
    }

    public void setObjects(List<ComplexObject> objects) {
        this.objects = objects;
    }

    @Override
    public int hashCode() {
        return Objects.hash(objects);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComplexObjects other = (ComplexObjects) obj;
        return Objects.equals(objects, other.objects);
    }

    @Override
    public String toString() {
        return String.format("ComplexObjects [objects=%s]", objects);
    }

}
