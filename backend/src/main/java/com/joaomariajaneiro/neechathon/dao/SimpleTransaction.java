package com.joaomariajaneiro.neechathon.dao;

import java.time.LocalDateTime;

public class SimpleTransaction {
    private String sourceTeam;
    private String destTeam;
    private String description;
    private Long amount;
    private String user;
    private Long sourceTeamCash;
    private LocalDateTime timestamp;

    public SimpleTransaction() {
    }

    public SimpleTransaction(String sourceTeam, String destTeam, String description, Long amount, String user, Long sourceTeamCash, LocalDateTime timestamp) {
        this.sourceTeam = sourceTeam;
        this.destTeam = destTeam;
        this.description = description;
        this.amount = amount;
        this.user = user;
        this.sourceTeamCash = sourceTeamCash;
        this.timestamp = timestamp;
    }


    public String getUser() {
        return user;
    }

    public SimpleTransaction setUser(String user) {
        this.user = user;
        return this;
    }

    public Long getSourceTeamCash() {
        return sourceTeamCash;
    }

    public SimpleTransaction setSourceTeamCash(Long sourceTeamCash) {
        this.sourceTeamCash = sourceTeamCash;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public SimpleTransaction setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSourceTeam() {
        return sourceTeam;
    }

    public SimpleTransaction setSourceTeam(String sourceTeam) {
        this.sourceTeam = sourceTeam;
        return this;
    }

    public String getDestTeam() {
        return destTeam;
    }

    public SimpleTransaction setDestTeam(String destTeam) {
        this.destTeam = destTeam;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SimpleTransaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public SimpleTransaction setAmount(Long amount) {
        this.amount = amount;
        return this;
    }
}
