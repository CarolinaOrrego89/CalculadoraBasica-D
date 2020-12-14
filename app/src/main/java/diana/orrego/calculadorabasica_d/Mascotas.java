package diana.orrego.calculadorabasica_d;

public class Mascotas {
    String userName, email, urlFoto,urlFotoFirestore, token, edad, telefono, Responsable, Sexo ;

    public Mascotas() {}

    public Mascotas(String userName, String email, String urlFoto, String urlFotoFirestore, String token , String edad , String telefono, String Responsable, String Sexo) {
        this.userName = userName;
        this.email = email;
        this.urlFoto = urlFoto;
        this.urlFotoFirestore = urlFotoFirestore;
        this.token = token;
        this.edad = edad;
        this.telefono = telefono;
        this.Responsable = Responsable;
        this.Sexo = Sexo;
    }

    //public donantes(String nombre, String urlCompletaImg, String urlCompletaImgFirestore, String miToken) {
    // }


    public String getUrlFotoFirestore() {
        return urlFotoFirestore;
    }

    public void setUrlFotoFirestore(String urlFotoFirestore) {
        this.urlFotoFirestore = urlFotoFirestore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //-----------------------------------------------------------

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo= Sexo;
    }
}


