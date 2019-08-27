package com.codeup.springblog.Models;

import javax.persistence.*;

@Entity
@Table(name= "pumps")
public class Pump {

    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false)
    private int volumeInmL;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private int time;

    @Column
    private String extraInfo;

    @Column(nullable = false)
    private boolean whiteRussian;

    @ManyToOne
    private User mom;

    public Pump() {
    }



    public User getMom() {
        return mom;
    }

    public void setMom(User mom) {
        this.mom = mom;
    }

    public int getVolumeInmL() {
        return volumeInmL;
    }

    public void setVolumeInmL(int volumeInmL) {
        this.volumeInmL = volumeInmL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public boolean isWhiteRussian() {
        return whiteRussian;
    }

    public void setWhiteRussian(boolean whiteRussian) {
        this.whiteRussian = whiteRussian;
    }
}
