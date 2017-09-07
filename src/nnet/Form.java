package nnet;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Form {
    JButton[] B;
    JButton BadButton;
    JButton GoodButton;
    JButton NewButton;

    Form(Nnet XOnet) {
        final JFrame window = new JFrame("XO nnet");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4,3));

        B = new JButton[9];

        class AL_New implements ActionListener {
            int argi;
            Form f;
            public AL_New(Form f, int argi) {
                this.argi = argi;
                this.f = f;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                if (XOnet.CheckWin()==2) {
                    XOnet.I[argi*2]=1;
                    XOnet.I[argi*2+1]=0;
                    f.DrawValues(XOnet.I);
                    XOnet.print();
                }

                if (XOnet.CheckWin()==2) {
                    XOnet.FindAndSetI();
                    f.DrawValues(XOnet.I);
                    XOnet.print();
                }
            }
        }

        for (int i=0; i<B.length; i++) {
            B[i] = new JButton("");
            AL_New al = new AL_New(this, i);
            B[i].addActionListener(al);
            p1.add(B[i]);
        }
        DrawValues(XOnet.I);

        BadButton = new JButton("BAD");
        BadButton.addActionListener((ActionEvent e) -> { window.setState(JFrame.ICONIFIED); });
        p1.add(BadButton);
        
        GoodButton = new JButton("GOOD");
        GoodButton.addActionListener((ActionEvent e) -> { window.setState(JFrame.ICONIFIED); });
        p1.add(GoodButton);


        class AL_NewButton implements ActionListener {
            Form f;
            public AL_NewButton(Form f) {
                this.f = f;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i<XOnet.I.length; i++) XOnet.I[i]=0;
                f.DrawValues(XOnet.I);
            }
        }

        NewButton = new JButton("NEW");
        AL_NewButton al2 = new AL_NewButton(this);
        NewButton.addActionListener(al2);
        p1.add(NewButton);

        window.getContentPane().add(p1);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    void DrawValues(int[] I) {
        for (int i=0; i<B.length; i++) {
            String label="";
            if (I[i*2]==1 && I[i*2+1]==0) label="x";
            if (I[i*2]==0 && I[i*2+1]==1) label="o";
            B[i].setText(label);
        }
    }
}
