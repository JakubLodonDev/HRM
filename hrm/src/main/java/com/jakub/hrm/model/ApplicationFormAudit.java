package com.jakub.hrm.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="application_form_audit")
public class ApplicationFormAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "application_form_audit_id")
    private UUID id;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    private ApplicationForm applicationForm;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private HrUser hrUser;

    public ApplicationFormAudit() {
    }

    public ApplicationFormAudit(ApplicationForm applicationForm, HrUser hrUser) {
        this.applicationForm = applicationForm;
        this.hrUser = hrUser;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    @PrePersist
    public void setDateModified() {
        this.dateModified = LocalDateTime.now();
    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    public HrUser getHrUser() {
        return hrUser;
    }

    public void setHrUser(HrUser hrUser) {
        this.hrUser = hrUser;
    }
}
