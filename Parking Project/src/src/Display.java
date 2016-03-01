//package src;
//package javax.swing;
//
//import java.awt.BorderLayout;
//
//@SuppressWarnings("serial")
//public class Display extends javax.swing.JFrame{
//        public Display() {
//        	a = new javax.swing.JLabel();
//            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//            a.setIcon(new javax.swing.ImageIcon("C:\\Users\\Brandon Koury\\Pictures\\F35.jpg"));       
//            add(a);//add to the jlabel
//            pack();
//        
//        }
//        
//        public static void main(String args[]){
//        	// Variables in this code
//            int total = Spots.spotsAvailable;
//
//            JFrame myFrame = new JFrame("ERAU Parking App");
//            myFrame.setVisible(true);
//            myFrame.setBounds(700, 0, 600, 600);
//            JLabel myText = new JLabel("Number of available spots: "+total,
//                    SwingConstants.CENTER);
//            myFrame.getContentPane().add(myText, BorderLayout.SOUTH);
//
//            java.awt.EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    new Display().setVisible(true);
//                }
//                
//            });
//            
//        }
//
//        private javax.swing.JLabel a;
//
//}