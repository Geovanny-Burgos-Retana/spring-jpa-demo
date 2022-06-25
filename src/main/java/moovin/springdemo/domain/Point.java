package moovin.springdemo.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "point")
@Cacheable(value = false)
public class Point {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String address;
    private List<Contact> contacts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @NotNull
    @Column(name = "latitude", nullable = false)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @NotNull
    @Column(name = "longitude", nullable = false)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @NotNull
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(id, point.id) && Objects.equals(latitude, point.latitude) && Objects.equals(longitude, point.longitude) && Objects.equals(address, point.address) && Objects.equals(getContacts(), point.getContacts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, address);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", contacts=" + getContacts() +
                '}';
    }

    /**
     * De esta manera genera la relación de un punto con varios contactos creando
     * una tabla point_contacts como si fuera una relación n a n, causando que se
     * puedan crear contactos que quedan en el aire sin ningún tipo de relación a
     * algún punto
     *
     * @return lista de contactos del punto
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
