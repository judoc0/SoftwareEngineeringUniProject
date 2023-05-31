package View;

import View.Listener.RegisterListener;

import javax.swing.*;
import java.awt.*;
//OBSERVER PATTERN
//Subject
public class RegisterPanel extends JPanel {

    private JTextField username;
    private JPasswordField password;
    private JTextField name;
    private JTextField surname;
    private JTextField email;
    private JTextField phoneNumber;
    private JTextField age;
    private JTextField residence;
    private JTextField job;

    public RegisterPanel() {

        RegisterListener listener = new RegisterListener(this);

        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.setBackground(Color.gray);
        panel2.setBackground(Color.gray);

        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.NORTH);
        panel1.setLayout(new GridLayout(9, 2));


        username = new JTextField(20);
        password = new JPasswordField(20);
        name = new JTextField(20);
        surname = new JTextField(20);
        email = new JTextField(20);
        phoneNumber = new JTextField(20);
        age = new JTextField(20);
        residence = new JTextField(20);
        job = new JTextField(20);

        username.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        password.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        name.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        surname.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        email.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        phoneNumber.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        age.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        residence.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));
        job.setFont(new Font(username.getFont().getFontName(), username.getFont().getStyle(), 19));

        username.setHorizontalAlignment(SwingConstants.CENTER);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        surname.setHorizontalAlignment(SwingConstants.CENTER);
        email.setHorizontalAlignment(SwingConstants.CENTER);
        phoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        age.setHorizontalAlignment(SwingConstants.CENTER);
        residence.setHorizontalAlignment(SwingConstants.CENTER);
        job.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblUsername = new JLabel("username: ", SwingConstants.CENTER);
        JLabel lblPassword = new JLabel("password: ", SwingConstants.CENTER);
        JLabel lblName = new JLabel("nome: ", SwingConstants.CENTER);
        JLabel lblSurname = new JLabel("cognome: ", SwingConstants.CENTER);
        JLabel lblEmail = new JLabel("email: ", SwingConstants.CENTER);
        JLabel lblPhoneNumber = new JLabel("telefono: ", SwingConstants.CENTER);
        JLabel lblAge = new JLabel("età: ", SwingConstants.CENTER);
        JLabel lblResidence = new JLabel("residenza: ", SwingConstants.CENTER);
        JLabel lblJob = new JLabel("job: ", SwingConstants.CENTER);

        lblUsername.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblPassword.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblName.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblSurname.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblEmail.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblPhoneNumber.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblAge.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblResidence.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        lblJob.setFont(new Font(lblUsername.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));


        JButton newAccount = new JButton("Registrati");
        newAccount.setFont(new Font(newAccount.getFont().getFontName(), lblUsername.getFont().getStyle(), 19));
        newAccount.setActionCommand("newAccount");
        newAccount.addActionListener(listener);
        newAccount.setPreferredSize(new Dimension(300,50));

        JLabel istruzioni = new JLabel("Inserisci i dati per completare registrazione");
        panel3.add(istruzioni);

        panel1.add(lblUsername);
        panel1.add(username);
        panel1.add(lblPassword);
        panel1.add(password);
        panel1.add(lblName);
        panel1.add(name);
        panel1.add(lblSurname);
        panel1.add(surname);
        panel1.add(lblEmail);
        panel1.add(email);
        panel1.add(lblPhoneNumber);
        panel1.add(phoneNumber);
        panel1.add(lblAge);
        panel1.add(age);
        panel1.add(lblResidence);
        panel1.add(residence);
        panel1.add(lblJob);
        panel1.add(job);
        panel2.add(newAccount);

    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public String getname() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }

    public int getAge() {
        return Integer.parseInt(age.getText());
    }

    public String getResidence() {
        return residence.getText();
    }

    public String getJob() {
        return job.getText();
    }

    public void clearFields() { //<<dovremmo prevedere nell'appframe un reset della view(la frase sotto i campi di login, in particolare); il professore l'ha fatto per noi <3 ed ha chokkato rip
        username.setText("");
        password.setText("");
        name.setText("");
        surname.setText("");
        email.setText("");
        phoneNumber.setText("");
        age.setText("");
        residence.setText("");
        job.setText("");
    }
}
