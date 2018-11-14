package com.plumdo.study.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class PersonServiceImpl extends UnicastRemoteObject implements PersonService {
	private static final long serialVersionUID = 1L;
	public PersonServiceImpl() throws RemoteException {
		super();
		System.out.println("init");
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Person> GetList() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Get Person Start!");
		List<Person> personList=new LinkedList<Person>();
		
		Person person1=new Person();
		person1.setAge(25);
		person1.setId(0);
		person1.setName("Leslie");
		personList.add(person1);
		
		Person person2=new Person();
		person2.setAge(25);
		person2.setId(1);
		person2.setName("Rose");
		personList.add(person2);
		
		return personList;
	}
	
}