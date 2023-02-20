import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class TellerGUISwing implements ActionListener {
    private JButton bn1, bn2, bn3;
    private JTextField tf1, tf2, tf3;
    private JLabel l1, l2, l3, l4;
    private JTextField err;
    private JPanel p1, p2, p3;
    private JFrame fr;
    private Customer cust;
    private JComboBox<String> c1;

    /**
     * 
     */
    public void init() {
        int i = 1;
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        err = new JTextField();
        c1 = new JComboBox<>();

        for (i = 1; i <= cust.getNumOfAccount(); i++) {
            c1.addItem(" Account " + i);
        }

        l1 = new JLabel("Name:");
        l2 = new JLabel("Balacne:");
        l3 = new JLabel("Amount:");
        l4 = new JLabel(" Account Type :");

        bn1 = new JButton("Deposit");
        bn2 = new JButton("Withdraw");
        bn3 = new JButton("Exit");

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));

        p1.add(l1);
        p1.add(tf1);
        p1.add(l2);
        p1.add(tf2);
        p1.add(l3);
        p1.add(tf3);
        p1.add(l4);
        p1.add(c1);

        p2.add(bn1);
        p2.add(bn2);
        p2.add(bn3);

        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.CENTER);
        p3.add(p2, BorderLayout.SOUTH);

        fr = new JFrame("Bank");

        fr.getContentPane().add(p3, BorderLayout.CENTER);
        fr.getContentPane().add(err, BorderLayout.SOUTH);

        tf1.setEditable(false);
        tf2.setEditable(false);
        err.setEditable(false);

        tf2.setText(cust.getAccount(getNumOfAccount(i)).getBalance() + "");
        tf1.setText(cust.getFirstName() + " " + cust.getLastName());

        tf1.setBackground(new Color(123, 104, 238));
        tf2.setBackground(new Color(123, 104, 238));
        tf3.setBackground(new Color(123, 104, 238));

        bn1.setBackground(new Color(123, 104, 238));
        bn2.setBackground(new Color(123, 104, 238));
        bn3.setBackground(new Color(123, 104, 238));

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);

        tf1.setForeground(Color.WHITE);
        tf2.setForeground(Color.WHITE);
        tf3.setForeground(Color.WHITE);
        err.setForeground(Color.WHITE);

        bn1.setForeground(Color.WHITE);
        bn2.setForeground(Color.WHITE);
        bn3.setForeground(Color.WHITE);

        bn1.addActionListener(this);
        bn2.addActionListener(this);
        bn3.addActionListener(this);
        c1.addActionListener(c1);

        p1.setBackground(new Color(72, 61, 139));
        p2.setBackground(new Color(72, 61, 139));
        p2.setBackground(new Color(72, 61, 139));
        err.setBackground(new Color(72, 61, 139));

        fr.pack();
        fr.show();
        bn3.addActionListener((event) -> System.exit(0));
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private int getNumOfAccount(int i) {
        return 0;
    }

    public TellerGUISwing(Customer c) {
        cust = c;
    }

    public static void main(String[] args) {
        Customer cust = new Customer("peerapat", "klintan");
        Account ac1 = new SavingAccount(5000);
        Account ac2 = new SavingAccount(2500);
        Account ac3 = new SavingAccount(2000);
        cust.addAccount(ac1);
        cust.addAccount(ac2);
        cust.addAccount(ac3);
        TellerGUISwing tl = new TellerGUISwing(cust);
        tl.init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent ed) {
        String cmd = ed.getActionCommand();
        double amt = Double.parseDouble(tf3.getText());
        int i = 1;
        if (cmd == c1.getActionCommand()) {
            tf2.setText(cust.getAccount(i).getBalance() + "");
            err.setText(" ");
            System.out.println("dsadwa");
        }
        try {
            if (cmd == "Exit") {
                System.exit(0);
            } else if (cmd == "Withdraw") {
                if (cust.getAccount(0).withdraw(amt)) {
                    tf2.setText(cust.getAccount(0).getBalance() + "");
                    err.setText(" ");
                }
            } else if (cmd == "Deposit") {
                cust.getAccount(0).deposit(amt);
                tf2.setText(cust.getAccount(0).getBalance() + "");
                err.setText(" ");
            }
        } catch (WithdrawException ex) {
            err.setText("Not Enough Money");

        }

    }

}