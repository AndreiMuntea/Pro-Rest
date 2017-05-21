package com.example.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrei on 2017-04-06.
 */
@Entity
@Table(name = "trials")
public class Trial implements HasId<String>, Serializable {
    @Id
    @Column(name = "trialName")
    private String name;

    @Column(name = "trialDifficulty")
    private Integer difficulty;

    public Trial() {
    }

    public Trial(String name, Integer difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public String getId() {
        return name;
    }

    public void setId(String id) {
        name = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trial)) return false;

        Trial trial = (Trial) o;

        if (getName() != null ? !getName().equals(trial.getName()) : trial.getName() != null) return false;
        return getDifficulty() != null ? getDifficulty().equals(trial.getDifficulty()) : trial.getDifficulty() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDifficulty() != null ? getDifficulty().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trial{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
