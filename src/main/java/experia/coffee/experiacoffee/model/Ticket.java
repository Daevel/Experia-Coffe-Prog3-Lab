package experia.coffee.experiacoffee.model;

import java.util.Date;

public class Ticket {
    private Integer ID;
    private String Title;
    private String Description;
    private String ManagedBy;
    private String CreatedBy;
    private Date CreationDate;
    private String Status;

    public Ticket(Integer ID, String Title, String Description, String ManagedBy, String CreatedBy, String Status) {
        this.setID(ID);
        this.setTitle(Title);
        this.setDescription(Description);
        this.setManagedBy(ManagedBy);
        this.setCreatedBy(CreatedBy);
        this.setStatus(Status);
    }

    public Ticket(Integer ID, String Title, String ManagedBy, String Status) {
        this.setID(ID);
        this.setTitle(Title);
        this.setManagedBy(ManagedBy);
        this.setStatus(Status);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getManagedBy() {
        return ManagedBy;
    }

    public void setManagedBy(String managedBy) {
        ManagedBy = managedBy;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
