public class Credentials{
    private String username;
    private String password;

    public Credentials(String ulica, String numer) {
        this.username = ulica;
        this.password = numer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String numer) {
        this.password = password;
    }

}
