package io.vepo.serialization.xml;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class SimpleXmlObject {
    private String field1;
    private int field2;
    private Integer field3;
    private BigDecimal field4;
    private transient String transientField;

    public SimpleXmlObject() {
        System.out.println("Calling constructor");
    }

    public SimpleXmlObject(String field1, int field2, Integer field3, BigDecimal field4) {
        System.out.println("Calling constructor with fields... field1=" + field1 + " field2=" + field2 + " field3="
                + field3 + " field4=" + field4);
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.transientField = this.field1 + " - " + this.field2 + " - " + this.field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        System.out.println("Setting field1=" + field1);
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        System.out.println("Setting field2=" + field2);
        this.field2 = field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField3(Integer field3) {
        System.out.println("Setting field3=" + field3);
        this.field3 = field3;
    }

    public BigDecimal getField4() {
        return field4;
    }

    public void setField4(BigDecimal field4) {
        this.field4 = field4;
    }

    @XmlTransient
    public String getTransientField() {
        return transientField;
    }

    public void setTransientField(String transientField) {
        this.transientField = transientField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3, field4, transientField);
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
        SimpleXmlObject other = (SimpleXmlObject) obj;
        return Objects.equals(field1, other.field1) && field2 == other.field2 && Objects.equals(field3, other.field3)
                && Objects.equals(field4, other.field4) && Objects.equals(transientField, other.transientField);
    }

    @Override
    public String toString() {
        return String.format("SimpleXmlObject [field1=%s, field2=%s, field3=%s, field4=%s, transientField=%s]", field1,
                             field2, field3, field4, transientField);
    }

}
