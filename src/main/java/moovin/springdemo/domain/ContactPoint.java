package moovin.springdemo.domain;

import javax.persistence.*;
import java.util.Objects;

//@Entity
public class ContactPoint {
  @EmbeddedId
  private ContactPointKey contactPointKey;
  private Integer order;
  private Boolean pickup;
  private Contact contact;
  private Point point;

  public ContactPointKey getContactPointKey() {
    return contactPointKey;
  }

  public void setContactPointKey(ContactPointKey contactPointKey) {
    this.contactPointKey = contactPointKey;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Boolean getPickup() {
    return pickup;
  }

  public void setPickup(Boolean pickup) {
    this.pickup = pickup;
  }

  @ManyToOne(
          cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
          fetch = FetchType.EAGER)
  @JoinColumn(name = "id")
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @ManyToOne
  @JoinColumn(name = "id")
  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactPoint that = (ContactPoint) o;
    return Objects.equals(contactPointKey, that.contactPointKey) && Objects.equals(order, that.order) && Objects.equals(pickup, that.pickup) && Objects.equals(contact, that.contact) && Objects.equals(point, that.point);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactPointKey, order, pickup, contact, point);
  }

  @Override
  public String toString() {
    return "ContactPoint{" +
            "contactPointKey=" + contactPointKey +
            ", order=" + order +
            ", pickup=" + pickup +
            ", contact=" + contact +
            ", point=" + point +
            '}';
  }
}
