/*Project 3
*Nancy C Fain
* July 1, 2018
*Calculate the terms of the following sequence of
*numbers: 0 1 2 5 12 29 ... where each term of the sequence is twice the previous term plus the second
*previous term. The 0th term of the sequence is 0 and the 1st term of the sequence is 1.
* Write Efficiency to File for calculation 1 - 10
*/

/*Imports*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame implements ActionListener {

    /*Frame Constructor*/
    private final JFrame frame;
    private final JRadioButton iterativeButton, recursiveButton;
    private final JTextField entryTextField, resultsTextField, efficiencyTextField;


    /*GUI Constructor*/
    private GUI() {

        frame = new JFrame ();
        frame.setTitle ( "Project 3" );
        frame.setSize ( 400, 300 );
        frame.setLayout ( new GridLayout ( 5, 2) );
        frame.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        frame.setResizable ( false );

        /*Field Constructors*/

        iterativeButton = new JRadioButton ( "Iterative" );
        recursiveButton = new JRadioButton ( "Recursive" );
        JButton computeButton = new JButton ( "Compute" );
        entryTextField = new JTextField ();
        resultsTextField = new JTextField ();
        efficiencyTextField = new JTextField ();

        /*Button Groups*/
        ButtonGroup group = new ButtonGroup ();
        group.add ( iterativeButton );
        group.add ( recursiveButton );
        iterativeButton.setSelected ( true );

        /*Action Commands and Listeners*/

        iterativeButton.setActionCommand ( "iterative" );
        recursiveButton.setActionCommand ( "recursive" );
        computeButton.setActionCommand ( "compute" );

        iterativeButton.addActionListener ( GUI.this );
        recursiveButton.addActionListener ( GUI.this );
        computeButton.addActionListener ( GUI.this );


        /*Layout*/

        frame.add ( iterativeButton );
        frame.add ( recursiveButton );

        frame.add ( new JLabel ( " Enter Number" ) );
        frame.add ( entryTextField );
        frame.add ( new JLabel ( "" ) );
        frame.add ( computeButton );
        frame.add ( new JLabel ( " Results" ) );
        frame.add ( resultsTextField );
        frame.add ( new JLabel ( " Efficiency" ) );
        frame.add (efficiencyTextField );

        frame.addWindowListener ( new WindowAdapterImpl ());
        frame.setVisible ( true );
    }

    /* Check to verify entry is numeric
    * pop up error message if non numeric entry is tried*/

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble ( str );
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog ( frame, "Only Numeric Values Accepted" );
            entryTextField.setText ( "" );
            return false;
        }
        return true;
    }

    /*Main Method*/
    public static void main(String[] args) {
        GUI g = new GUI ();
    }

    /*Method for Action and Operation */
    @Override
    public void actionPerformed(ActionEvent e){
     String event = e.getActionCommand ();
     String text;
     String results;

    switch (event){
        case "compute":
            text = entryTextField.getText ();
            if(!isNumeric ( text ))
                break;
            if(iterativeButton.isSelected ()) {
                results = String.format ( "%d", Sequence.iterativeSequence
                        ( Integer.parseInt ( text ) ) );
            }else if(recursiveButton.isSelected()) {
                results = String.format ( "%d", Sequence.computeRecursive
                        ( Integer.parseInt ( text ) ) );
            } else {
                results = "";
            }
            resultsTextField.setText(results);
            efficiencyTextField.setText
                    ( String.format ( "%d", Sequence.getEfficiency () ) );
                 break;

    }

}

    /*Window Closing Method as instructed*/
    private static class WindowAdapterImpl extends

            WindowAdapter {

        private WindowAdapterImpl() {
        }

        @Override
        public void windowClosing(WindowEvent e){
            try {
                 FileWriter fileWriter = new FileWriter("Efficiency_Report.csv");
                
                /*headers for file*/
                fileWriter.append("n");
                fileWriter.append(',');
                fileWriter.append("Iterative");
                fileWriter.append(',');
                fileWriter.append("Recursive");
                fileWriter.append(',');
                fileWriter.append('\n');

                /*Write to file*/

                for(int n = 0; n <= 10; n++){

                    fileWriter.append(String.valueOf ( n ));
                    fileWriter.append ( ',' );
                    Sequence.iterativeSequence (n);
                    fileWriter.append ( String.valueOf ( (Sequence.getEfficiency ()) ) );
                    fileWriter.append ( ',' );
                    Sequence.computeRecursive(n);
                    fileWriter.append ( String.valueOf ( (Sequence.getEfficiency ()) ) );
                    fileWriter.append('\n');
                }
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println ( ex.getMessage());

            }
        } // end windowClosing

    } // WindowAdapterImpl


} //end GUI

