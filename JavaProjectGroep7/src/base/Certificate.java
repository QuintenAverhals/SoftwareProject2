package base;

import java.io.BufferedOutputStream;
import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
@Entity
@Table(name="CERTIFICATE")
public class Certificate {
	
	@Id
	@Column(name="CertificateID")
	private int certificateID;
	
	@Column(name="TrainingID")
	private int trainingID;
	
	@Column(name="UserID")
	private int userID;
	
	@Column(name="doc_name")
	private String docName;
	
	public Certificate() {
		
	}
	
	public Certificate(int trainingID, int userID, String docName) {
		super();
		this.trainingID = trainingID;
		this.userID = userID;
		this.docName = docName;
	}

	public Certificate(int certificateID, int trainingID, int userID, String docName) {
		super();
		this.certificateID = certificateID;
		this.trainingID = trainingID;
		this.userID = userID;
		this.docName = docName;
	}
	
	public int getCertificateID() {
		return certificateID;
	}
	
	public void setCertificateID(int certificateID) {
		this.certificateID = certificateID;
	}
	
	public int getTrainingID() {
		return trainingID;
	}

	public void setTrainingID(int trainingID) {
		this.trainingID = trainingID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	
	public static File chooseFile() {
		File selectedFile = null;
		 JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			// int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = jfc.getSelectedFile();
			}
	
		return selectedFile;
	}
	
	public static String getAbsolutePath(File selectedFile) {
		return selectedFile.getAbsolutePath();
	}
	
	public static String getFileName(File selectedFile) {
		return selectedFile.getName();
	}
		
	public static Boolean uploadToServer(File selectedFile) {

		 	String server = "ftp.jijmaaktmechelen.be";
	        int port = 21;
	        String user = "jijmaaktmechelen.be";
	        String pass = "JijMaaktMechelen";
	 
	        FTPClient ftpClient = new FTPClient();
	        try {
	 
	            ftpClient.connect(server, port);
	            ftpClient.login(user, pass);
	            ftpClient.enterLocalPassiveMode();
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 /*
	            // APPROACH #1: uploads first file using an InputStream
	            //File firstLocalFile = new File("D:/Test/Projects.zip");
	 
	            String firstRemoteFile = selectedFile.getName();
	            InputStream inputStream = new FileInputStream(selectedFile);
	 
	            System.out.println("Start uploading first file");
	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();
	            if (done) {
	                System.out.println("The first file is uploaded successfully.");
	            }*/
	 
	            // APPROACH #2: uploads second file using an OutputStream
	           // File secondLocalFile = new File("E:/Test/Report.doc");
	            String secondRemoteFile = ("Seppe/SW2/" + selectedFile.getName());
	            InputStream inputStream = new FileInputStream(selectedFile);
	 
	            System.out.println("Start uploading first file");
	            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
	            byte[] bytesIn = new byte[4096];
	            int read = 0;
	 
	            while ((read = inputStream.read(bytesIn)) != -1) {
	                outputStream.write(bytesIn, 0, read);
	            }
	            inputStream.close();
	            outputStream.close();
	 
	            boolean completed = ftpClient.completePendingCommand();
	            if (completed) {
	                System.out.println("The first file is uploaded successfully.");
	            }
	 
	        } catch (IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }		
		return true;
	}

	public static Boolean downloadFromServer(String docName) {
		String server = "ftp.jijmaaktmechelen.be";
        int port = 21;
        String user = "jijmaaktmechelen.be";
        String pass = "JijMaaktMechelen";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
           // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = ("Seppe/SW2/" + docName);
            File downloadFile1 = new File(Certificate.class.getProtectionDomain().getCodeSource().getLocation().getPath() +"../Certificaten/" + docName);
            //System.out.println(Certificate.class.getProtectionDomain().getCodeSource().getLocation().getPath() +"../Certificaten/" + docName);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("File "+ docName +"has been downloaded successfully.");
                return true;
            }
 
          /*  // APPROACH #2: using InputStream retrieveFileStream(String)
            String remoteFile2 = ("Seppe/SW2/" + docName);
            File downloadFile2 = new File(Certificate.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
 
            success = ftpClient.completePendingCommand();
            if (success) {
                System.out.println("File #2 has been downloaded successfully.");
            }
            outputStream2.close();
            inputStream.close();*/
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
	}
	
	public static Boolean addtoDatabase(int trainingsID, int userID, String docName) {
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Certificate.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();		
		
		Certificate certificate= new Certificate(trainingsID,userID,docName);
		session.save(certificate);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return true;
	}

	public static Certificate getCertificate(int certificateID) {
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Certificate.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();		
				
		Query query = session.createQuery("FROM Certificate WHERE certificateID = "+certificateID);
		List<Certificate> certificates = query.list();
		Certificate certificate = certificates.get(0);
		
		for(int i=0;i<certificates.size();i++)
		{
			System.out.println(certificates.get(i).toString());
		}		
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();	
		
		return certificate;
	}

	public static Boolean changeCertificate(int certificateID, int trainingID, int userID, String doc_name) {
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Certificate.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Certificate certificate = getCertificate(certificateID);
		certificate.setTrainingID(trainingID);
		certificate.setUserID(userID);
		certificate.setDocName(doc_name);
		session.update(certificate);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return true;
	}

	public static void main(String[] args) {
		//File bestand = chooseFile();
		//Boolean uploadGeslaagd = uploadToServer(bestand);
		//String naam = getFileName(bestand);
		//System.out.println(naam);
		//Boolean DB = addtoDatabase(1,2,naam);
		//Boolean download = downloadFromServer(naam);
		//String absoluutpath = getAbsolutePath(bestand);
		//System.out.println(absoluutpath);
		
		/*Certificate testCerificate = getCertificate(13);
		System.out.println(testCerificate.getCertificateID());
		System.out.println(testCerificate.getTrainingID());
		System.out.println(testCerificate.getUserID());
		System.out.println(testCerificate.getDocName());*/
		
		//changeCertificate(testCerificate.getCertificateID(), 1, 5, "Seppe_zijn_test.txt");
		/*
		testCerificate = getCertificate(13);
		System.out.println(testCerificate.getCertificateID());
		System.out.println(testCerificate.getTrainingID());
		System.out.println(testCerificate.getUserID());
		System.out.println(testCerificate.getDocName());*/
		
	}
}