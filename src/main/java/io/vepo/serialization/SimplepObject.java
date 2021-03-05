package io.vepo.serialization;

import java.io.Serializable;
import java.util.Objects;

public class SimplepObject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7165312237915370059L;
    private String field1;
    private int field2;
    private Integer field3;
    private transient String volatileField;

    public SimplepObject() {
        System.out.println("Calling constructor");
    }

    public SimplepObject(String field1, int field2, Integer field3) {
        System.out.println("Calling constructor with fields... field1=" + field1 + " field2=" + field2 + " field3="
                + field3);
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.volatileField = this.field1 + " - " + this.field2 + " - " + this.field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField3(Integer field3) {
        this.field3 = field3;
    }

    public String getVolatileField() {
        return volatileField;
    }

    public void setVolatileField(String volatileField) {
        this.volatileField = volatileField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3, volatileField);
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
        SimplepObject other = (SimplepObject) obj;
        return Objects.equals(field1, other.field1) && field2 == other.field2 && Objects.equals(field3, other.field3)
                && Objects.equals(volatileField, other.volatileField);
    }

    @Override
    public String toString() {
        return String.format("SimplepObject [field1=%s, field2=%s, field3=%s, volatileField=%s]", field1, field2,
                             field3, volatileField);
    }

}
