package com.tenpo.api.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "num1")
    private Double num1;

    @Column(name = "num2")
    private Double num2;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "result")
    private Double result;

    public Result() {}

    public Result(LocalDateTime dateTime, Double num1, Double num2, Double percentage, Double result) {
        this.dateTime = dateTime;
        this.num1 = num1;
        this.num2 = num2;
        this.percentage = percentage;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
