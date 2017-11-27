package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import base.Login;
import base.SHA512;

public class LoginDaoTest {
	private List<Login> users=null;
	private Login nieuweUser= null;
	private Login getUser= null;
	private boolean check= false;
	private List<Login> checkUserList= null;
	private Login checkUser= null;
	
	@Before
	public void setUp() throws Exception {
		users = new ArrayList<Login>();
		nieuweUser = new Login();
		getUser= new Login();
	}
	@Ignore
	@Test
	//a test of the check function which checks if the given username and password is correct
	public void testCheck() throws Exception {
		String password= "bilal123";
		SHA512 hasher = new SHA512();
		String pswd=hasher.hashString(password);
		getUser = new Login("Bilal", pswd);
		check = getUser.check(getUser);
		assertEquals(true,check);
	}
	
	@Ignore
	@Test
	//creates user then check if user is in database
	public void testCreateNewUser() throws Exception {
		String password="bilal123";
		nieuweUser= new Login("bilal",password, true, "bilal.bahjaoui@student.ehb.Be");
		nieuweUser.createNewUser(nieuweUser.getUsername(),nieuweUser.getPassword(),nieuweUser.isAdmin(),nieuweUser.getEmail());
		System.out.println(nieuweUser.toString());
		SHA512 hasher = new SHA512();
		String passwordHASHED=hasher.hashString("bilal123");
		nieuweUser.setPassword(passwordHASHED);
		
		users= getUser.getUsersByNameAndEmail(nieuweUser.getUsername(), nieuweUser.getEmail());
		for(int i=0;i< users.size();i++)
		{
			
			getUser= users.get(i);
			System.out.println(getUser.toString());
		}
		String username1= nieuweUser.getUsername();
		String username2= getUser.getUsername();
		String password1= nieuweUser.getPassword();
		String password2= getUser.getPassword();
		System.out.println(nieuweUser.getUsername());
		System.out.println(getUser.getUsername());
		System.out.println(password1);
		System.out.println(password2);
		assertTrue(username1.equals(username2));
		assertTrue(password1.equals(password2));
	
	}
	@Ignore
	@Test
	//checks if login matches login in database
	public void testLogin() throws Exception {
		String password= "bilal123";
		getUser = new Login();
		check = getUser.login("Bilal", "bilal123");
		assertEquals(true,check);
	}

	@Test
	public void testUpdatePassword() {
		getUser= new Login();
		int id= 1;
		String username= "Basil2";
		getUser.updateUserName(id, username);
		checkUserList = getUser.getUsersByName(username);
		for(int i=0;i< checkUserList.size();i++)
		{
			
			checkUser= checkUserList.get(i);
			System.out.println(checkUserList.get(i).toString());
		}
		assertEquals(username,checkUser.getUsername());
		
	}

	@Test
	public void testUpdateUserName() {
		getUser= new Login();
		int id= 1;
		String username="seppe";
		getUser.updateUserName(id, username);
		checkUserList = getUser.getUsersByName(username);
		for(int i=0;i< checkUserList.size();i++)
		{
			
			checkUser= checkUserList.get(i);
			System.out.println(checkUserList.get(i).toString());
		}
		assertEquals(username,checkUser.getUsername()); 
	}

	@Test
	public void testIsAdmin() {
		fail("N ot yet implemented");
	}

	@Test
	public void testGetALL() {
		fail("fdp");
		
	}

	@Test
	public void testGetUsersByName() {
		fail("Not yet implemented");
	}

}
	