/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmibd;


import java.rmi.*;
import java.util.*;

public interface RMIBD extends Remote {

	public ArrayList<Produto> buscar_produto(String codigo) throws RemoteException;

	public ArrayList<Produto> mostrar() throws RemoteException;

	public Boolean excluir(String codigo) throws RemoteException;

	public Boolean editar(String codigo, String nome, String quantidade) throws RemoteException;

	public Boolean adicionar(String codigo, String nome, String quantidade) throws RemoteException;
}
