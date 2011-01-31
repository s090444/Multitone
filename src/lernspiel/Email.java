	package lernspiel;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.WindowConstants;


public class Email extends javax.swing.JFrame implements KeyListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5408820050346164753L;
	static RootMenu parent;
	public Email(String title,RootMenu root){
	super(title);
	
	parent = root;
	parent.setVisible(false);
	
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	int frameWidth = 800;
	int frameHeight = 600;
	setSize(frameWidth, frameHeight);
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (d.width - getSize().width) / 2;
	int y = (d.height - getSize().height) / 2;
	setLocation(x, y);
	initComponents();
	setResizable(false);
	setAlwaysOnTop(true);
	setVisible(true);
	}

	/**
	 *
	 * @author genz
	 */

	    /** Creates new form NewJFrame */
	    public Email() {
	        initComponents();
	    }

	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">
	    private void initComponents() {

	        jPanel1 = new javax.swing.JPanel();
	        jTextField1 = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField2 = new javax.swing.JTextField();
	        jLabel3 = new javax.swing.JLabel();
	        jTextField3 = new javax.swing.JTextField();
	        jPanel2 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTextPane1 = new javax.swing.JTextPane();
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jButton3 = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Head"));

	        jTextField1.setText("multitone@intelli-genz.de");

	        jLabel1.setText("Adresse");

	        jLabel2.setText("Betreff");

	        jTextField2.setText("RE:");

	        jLabel3.setText("CC:");

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel1)
	                    .addComponent(jLabel2)
	                    .addComponent(jLabel3))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3)))
	        );

	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Text"));

	        jScrollPane1.setViewportView(jTextPane1);

	        jButton1.setText("Reset");
	        jButton1.setPreferredSize(new java.awt.Dimension(90, 25));
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Abbruch");
	        jButton2.setMaximumSize(new java.awt.Dimension(75, 25));
	        jButton2.setMinimumSize(new java.awt.Dimension(75, 25));
	        jButton2.setPreferredSize(new java.awt.Dimension(90, 25));
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton2ActionPerformed(evt);
	            }
	        });

	        jButton3.setText("Senden");
	        jButton3.setPreferredSize(new java.awt.Dimension(90, 25));
	        jButton3.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton3ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                .addContainerGap(428, Short.MAX_VALUE)
	                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(50, 50, 50))
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        pack();
	    }// </editor-fold>

	    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	      // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
	        String to = jTextField1.getText();
	        String from = "javamailer@intelli-genz.de";
	        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
	        String host = "smtp.intelli-genz.de";

	        // Create properties for the Session
	        Properties props = new Properties();

	        // If using static Transport.send(),
	        // need to specify the mail server here
	        props.put("mail.smtp.host", host);
	        // To see what is going on behind the scene
	        props.put("mail.debug", "true");

	        // Get a session
	        Session session = Session.getInstance(props);

	        try {
	            // Get a Transport object to send e-mail
	            Transport bus = session.getTransport("smtp");

	            // Connect only once here
	            // Transport.send() disconnects after each send
	            // Usually, no username and password is required for SMTP
	           // bus.connect();
	           bus.connect(host, from, "multitone4tw");

	            // Instantiate a message
	            Message msg = new MimeMessage(session);

	            // Set message attributes
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            // Parse a comma-separated list of email addresses. Be strict.
	            msg.setRecipients(Message.RecipientType.CC,
	                                InternetAddress.parse(jTextField3.getText(), true));
	            // Parse comma/space-separated list. Cut some slack.
	            msg.setRecipients(Message.RecipientType.BCC,
	                                InternetAddress.parse("", false));

	            msg.setSubject(jTextField2.getText());
	            msg.setSentDate(new Date());

	            // Set message content and send
	            setTextContent(msg);
	            msg.saveChanges();
	            bus.sendMessage(msg, address);

//	            setMultipartContent(msg);
//	            msg.saveChanges();
//	            bus.sendMessage(msg, address);
	//
//	            setFileAsAttachment(msg, "C:/WINDOWS/CLOUD.GIF");
//	            msg.saveChanges();
//	            bus.sendMessage(msg, address);
	//
//	            setHTMLContent(msg);
//	            msg.saveChanges();
//	            bus.sendMessage(msg, address);

	            bus.close();

	        }
	        catch (MessagingException mex) {
	            // Prints all nested (chained) exceptions as well
	            mex.printStackTrace();
	            // How to access nested exceptions
	            while (mex.getNextException() != null) {
	                // Get next exception in chain
	                Exception ex = mex.getNextException();
	                ex.printStackTrace();
	                if (!(ex instanceof MessagingException)) break;
	                else mex = (MessagingException)ex;
	            }
	        }


	    }


	     public void setTextContent(Message msg) throws MessagingException {
	            // Set message content
	            String mytxt = jTextPane1.getText();
	            msg.setText(mytxt);

	            // Alternate form
	            msg.setContent(mytxt, "text/plain");

	    }                                        

	     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	    	 dispose();
	    	 parent.setVisible(true);
	    	 
	     }

	     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	      jTextField1.setText("multitone@intelli-genz.de");
	      jTextField2.setText("RE:");
	      jTextField3.setText("");
	      jTextPane1.setText("");
	         // TODO add your handling code here:
	     }

	    /**
	    * @param args the command line arguments
	    */
	    public static void main(String args[]) {
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new Email().setVisible(true);
	            }
	        });

	}

	    // Variables declaration - do not modify
	    private javax.swing.JButton jButton1;
	    private javax.swing.JButton jButton2;
	    private javax.swing.JButton jButton3;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private javax.swing.JTextField jTextField3;
	    private javax.swing.JTextPane jTextPane1;
	    // End of variables declaration
	    public void keyPressed(KeyEvent e) {
			if (parent.forward) {
//				parent.forward = false;
				System.out.println(e.getKeyCode());
			}
//				e.consume();
		}

		// Handle keyTyped Event

		public void keyTyped(KeyEvent e) {

			// let pass events from Robot()
			if (parent.forward) {
				parent.forward = false;
				Sound.playSound((int) e.getKeyChar());
			} else {
				parent.setFlac(e, 1);
				if (parent.isFirstKeyPressedflag() == false) {
					parent.setFirstKeyPressedflag(true);
					Timer timer = new Timer();
					timer.schedule(new MappKeyCode(parent, this, parent.numOfKeys,
							parent.numOfPoss), parent.typingTime+200);
				}
				// consume event, don't let it pass to application
				e.consume();
			}
		}

		// Handle keyReleased Event
		public void keyReleased(KeyEvent e) {
			if (parent.release) {
				parent.release = false;
				e.consume();
			} else {
				parent.setFlac(e, 0);
			}
		}

	}

	
	

