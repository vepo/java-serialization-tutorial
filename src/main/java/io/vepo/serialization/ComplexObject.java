package io.vepo.serialization;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ComplexObject implements Serializable {
    private static final long serialVersionUID = -7785763954822005538L;
    private String field1;
    private int field2;
    private BigDecimal field3;
    private float field4;
    private Type type;
    private SubObject obj;

    public ComplexObject() {
    }

    public ComplexObject(String field1, int field2, BigDecimal field3, float field4, Type type, SubObject obj) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.type = type;
        this.obj = obj;
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

    public BigDecimal getField3() {
        return field3;
    }

    public void setField3(BigDecimal field3) {
        this.field3 = field3;
    }

    public float getField4() {
        return field4;
    }

    public void setField4(float field4) {
        this.field4 = field4;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public SubObject getObj() {
        return obj;
    }

    public void setObj(SubObject obj) {
        this.obj = obj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3, field4, obj, type);
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
        ComplexObject other = (ComplexObject) obj;
        return Objects.equals(field1, other.field1) && field2 == other.field2 && Objects.equals(field3, other.field3)
                && Float.floatToIntBits(field4) == Float.floatToIntBits(other.field4)
                && Objects.equals(this.obj, other.obj) && type == other.type;
    }

    @Override
    public String toString() {
        return String.format("ComplexObject [field1=%s, field2=%s, field3=%s, field4=%s, type=%s, obj=%s]", field1,
                             field2, field3, field4, type, obj);
    }

}
