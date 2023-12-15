package com.bci.exercise.user.model;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long number;
    @Column(name = "city_code")
    private int cityCode;
    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Phone(long number, int citycode, String countrycode) {
        this.number = number;
        this.cityCode = citycode;
        this.countryCode = countrycode;
    }
    public Phone() {
    }
}
