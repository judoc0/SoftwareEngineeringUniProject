package Model;

//FACTHORY METHOD PATTERN
//Concrete product
public class Utente implements IUtente{

    private int idUtente;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private int age;
    private String job;
    private String phoneNumber;
    private String residence;

    public Utente() {
        this.username = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.email = "";
        this.phoneNumber = "";
        this.age = 0;
        this.residence = "";
        this.job = "";
    }

    public Utente(String username, String password, String name, String surname, String email, String phoneNumber,int age, String residence, String job) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.residence = residence;
        this.job = job;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String toString() {
        return name + " " + surname + " " + email + " " + age + " " + job;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
