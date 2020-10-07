package com.test.converter.entetities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement(name = "Valute")
public class Valute {
    @Id
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;

    public String getId() {
        return id;
    }
    @XmlAttribute(name = "ID")
    public void setId(String id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }
@XmlElement(name = "NumCode")
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }
    @XmlElement(name = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }
    @XmlElement(name = "Nominal")
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "id='" + id + '\'' +
                ", numCode='" + numCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
