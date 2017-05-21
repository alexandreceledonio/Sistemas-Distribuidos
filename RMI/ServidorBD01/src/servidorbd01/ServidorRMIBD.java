package servidorbd01;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import rmibd.Produto;
import rmibd.RMIBD;

class ServidorRMIDB extends UnicastRemoteObject implements RMIBD {

    public ServidorRMIDB() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Produto> buscar_produto(String codigo) throws RemoteException {

        String cod, nom, quant;

        ArrayList<Produto> prod = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.25.131:3306/produto", "root", "Admin@teste");
            String sql = "select * from produto where codigo='" + codigo + "';";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                cod = rs.getString(1);
                nom = rs.getString(2);
                quant = rs.getString(3);
                Produto cadastrado = new Produto(cod, nom, quant);
                prod.add(cadastrado);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return prod;
    }

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("rmi://localhost:1099/RMIBD", new ServidorRMIDB());
            System.out.println("Servidor Ativo");
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Produto> mostrar() throws RemoteException {

        String cod, nom, quant;

        ArrayList<Produto> prod = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.25.131:3306/produto", "root", "Admin@teste");
            String sql = "select * from produto;";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                cod = rs.getString(1);
                nom = rs.getString(2);
                quant = rs.getString(3);
                Produto cadastrado = new Produto(cod, nom, quant);
                prod.add(cadastrado);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return prod;
    }

    @Override
    public Boolean excluir(String codigo) throws RemoteException {

        Boolean exito;
        exito = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.25.131:3306/produto", "root", "Admin@teste");
            String sql = "delete from produto where codigo=?;";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, codigo);
            stm.executeUpdate();
            exito = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return exito;
    }

    @Override
    public Boolean editar(String codigo, String nome, String quantidade) throws RemoteException {
        Boolean exito;
        exito = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.25.131:3306/produto", "root", "Admin@teste");
            String sql = " update produto set nome=?, quantidade=? where codigo=?;";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, nome);
            stm.setString(2, quantidade);
            stm.setString(3, codigo);
            stm.executeUpdate();
            exito = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return exito;
    }

    @Override
    public Boolean adicionar(String codigo, String nome, String quantidade) throws RemoteException {
        Boolean exito;
        exito = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.25.131:3306/produto", "root", "Admin@teste");
            String sql = "INSERT INTO `produto` (`codigo`, `nome`, `quantidade`) VALUES (?,?,?);";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, codigo);
            stm.setString(2, nome);
            stm.setString(3, quantidade);
            stm.executeUpdate();
            exito = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return exito;
    }
}
