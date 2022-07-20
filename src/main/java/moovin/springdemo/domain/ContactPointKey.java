package moovin.springdemo.domain;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class ContactPointKey implements Serializable {
  private Long contactId;
  private Long pointId;

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  public Long getPointId() {
    return pointId;
  }

  public void setPointId(Long pointId) {
    this.pointId = pointId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactPointKey that = (ContactPointKey) o;
    return Objects.equals(contactId, that.contactId) && Objects.equals(pointId, that.pointId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, pointId);
  }

  @Override
  public String toString() {
    return "ContactPointKey{" +
            "contactId=" + contactId +
            ", pointId=" + pointId +
            '}';
  }
}
