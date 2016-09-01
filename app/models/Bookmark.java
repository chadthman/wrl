package models;

import java.util.Objects;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "bookmark")
public class Bookmark {
    public static final int MIN_NAME_LEN = 2;
    //just a simple pattern
    public static final String URL_VALIDATION_PATTERN = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description")
    private String desc;
    
    @Column(name = "completed", nullable = false)
    private Boolean completed;
    
    @Column(name = "created", nullable = false, updatable=false)
    private Date created;
    @Column(name = "updated")
    private Date updated;
    
    
    public Bookmark() {}

    public Bookmark(Long id, String url, String desc, Boolean completed, Date created) {
        this.id = id;
        this.url = url;
        this.desc = desc;
        this.completed = completed;
        this.created = created; 
    }

    public Bookmark(String url, String desc, Boolean completed) {
        this.url = url;
        this.desc = desc;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    
    public Date getCreated() {
    	return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    @PrePersist
    protected void onCreate() {
      created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
      updated = new Date();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrl(), getDesc(), getCreated());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Bookmar [id=")
          .append(id)
          .append(", url=")
          .append(url)
          .append(", desc=")
          .append(desc)
          .append(", completed=")
          .append(completed);

        return sb.toString();
    }


}