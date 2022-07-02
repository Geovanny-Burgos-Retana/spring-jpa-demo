package moovin.springdemo.domain;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 3, max = 512)
    @Column(name = "address", nullable = false, length = 512)
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
     * Poniendo el JoinTable en la tabla de punto y en la tabla de contacto solo el
     *
     * @return lista de contactos del punto
     * ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) desde el create
     * del punto con la lista de contactos se crean tanto los objetos como la relación
     * en la tabla intermedia, pero desde el crete del contacto se crean los objetos pero
     * NO la relación en la tabla intermedia. Además, si se quiere agregar contactos a un
     * punto ya creado le cae encima los nuevos a los viejos con el endpoint del createPoint
     * por lo tanto mala idea, necesitariamos un procesamiento previó al momento de enviar a
     * "crear un punto" ya creado pero que lo mandamos con el id del puntos seteado pero con
     * contactos nuevos para que se los agregue sin eliminar los previos. Este método igual nos
     * limitaria a que tenemos que pasar por el endpoint del punto de creación o que por el endpoint
     * del contacto se obtenga el id del punto de los datos enviados y generar el punto que ya esta
     * en la DB y agregarle los contactos nuevos a la lista de contactos para que no le caiga encima
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contact_point",
            joinColumns = @JoinColumn(name = "fk_point"),
            inverseJoinColumns = @JoinColumn(name = "fk_contact"))
    @OrderBy("id asc")
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
