package com.plumdo.study.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PersonService extends Remote {
    public List<Person> GetList() throws RemoteException;
}