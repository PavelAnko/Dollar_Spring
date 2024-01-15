package ua.kiev.prog.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CurrencyUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_unit_id")
    private int id;
    @Column(name = "exchange_date")
    private Date exchangedate;

    private String txt1;

    private String txt2;
    private double rate;
    public CurrencyUnit(){}

    public CurrencyUnit(String txt1, String txt2, double rate) {
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.rate = rate;
    }
    public Date getExchangedate() {
        return exchangedate;
    }

    public void setExchangeDate(Date exchangedate) {
        this.exchangedate = exchangedate;
    }
    public String getTxt1() { return txt1; }
    public String getTxt2() {
        return txt2;
    }
    public double getRate() {
        return rate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }
    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    @Override
    public String toString() {
        return "dao.dao.CurrencyUnitDao.entity.CurrencyUnit{" +
                "exchangedate='" + exchangedate + '\'' +
                ", txt1 = '" + txt1 + '\'' +
                ", txt2 = '" + txt2 + '\'' +
                ", rate = " + rate +
                '}';
    }
}