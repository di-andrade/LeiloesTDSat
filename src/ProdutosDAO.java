/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    public boolean cadastrarProduto(ProdutosDTO produto){
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conexao = new conectaDAO().connectDB()) {
            if (conexao == null) {
                return false;
            }

            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, produto.getNome());
                comando.setInt(2, produto.getValor());
                comando.setString(3, produto.getStatus());
                return comando.executeUpdate() == 1;
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel cadastrar o produto: " + erro.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos(){
        return new ArrayList<>();
    }
}

