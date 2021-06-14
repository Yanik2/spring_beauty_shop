package com.yan.beauty_shop_spring2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "feedback_amount")
    private Integer feedbackAmount;
    @Column(name = "activity")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "masterId", fetch = FetchType.EAGER)
    private List<Appointment> masterAppointments = new ArrayList<>();

    public Account() {
    }

    public Account(String login, String password, Role role, Double rate, Integer feedbackAmount, Service service) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.rate = rate;
        this.feedbackAmount = feedbackAmount;
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getFeedbackAmount() {
        return feedbackAmount;
    }

    public void setFeedbackAmount(Integer feedbackAmount) {
        this.feedbackAmount = feedbackAmount;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Appointment> getMasterAppointments() {
        return masterAppointments;
    }

    public void setMasterAppointments(List<Appointment> masterAppointments) {
        this.masterAppointments = masterAppointments;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", rate=" + rate +
                ", feedbackAmount=" + feedbackAmount +
                ", service=" + service +
                '}';
    }
}
