package io.vepo.serialization;

import java.io.Serializable;
import java.util.Objects;

public class SubObject implements Serializable {
    private static final long serialVersionUID = -441739303682061736L;
    private String id;
    private String name;

    public SubObject() {
    }

    public SubObject(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
        SubObject other = (SubObject) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return String.format("SubObject [id=%s, name=%s]", id, name);
    }

}
