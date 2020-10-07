package com.test.converter.entetities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement(name="ValCurs")
public class ValCurs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Valute> valutes;

    public String getDate() {
        return date;
    }
    @XmlAttribute(name = "Date")
    public void setDate(String date) {
        this.date = date;
    }

    public List<Valute> getValutes() {
        return valutes;
    }
    @XmlElements(@XmlElement(name="Valute", type=Valute.class))
    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }
}
