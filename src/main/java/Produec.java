public class Produec {
    private String username,password,email,access_Level;
    private int balance;
    private int hour;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAccess_Level() {
        return access_Level;
    }

    public int getBalance() {
        return balance;
    }

    public int getHour() {
        return hour;
    }

    public Produec(String username, String password, String email, String access_Level, int balance, int hour) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.access_Level = access_Level;
        this.balance = balance;
        this.hour = hour;

    }
}
