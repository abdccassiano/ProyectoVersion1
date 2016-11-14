package models;

/**
 * Created by servus on 10/11/2016.
 */
public class NavItem2 {

    private String gerentid;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private int resIcon;

    public NavItem2(String gerenteid, String nombre, String direccion, String telefono, String email, int resIcon) {
        super();
        this.gerentid = gerenteid;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.resIcon = resIcon;
    }

    public String getGerenteId(){
        return gerentid;
    }

    public void setGerentId(String gerentid){
        this.gerentid = gerentid;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getDireccion() {

        return direccion;
    }

    public void setDireccion(String direccion) {

        this.direccion = direccion;
    }

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public int getResIcon() {

        return resIcon;
    }

    public void setResIcon(int resIcon) {

        this.resIcon = resIcon;
    }
}
