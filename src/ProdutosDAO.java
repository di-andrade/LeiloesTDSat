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
import java.sql.ResultSet;
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

    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ? AND status <> ?";

        try (Connection conexao = new conectaDAO().connectDB()) {
            if (conexao == null) {
                return false;
            }

            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, "Vendido");
                comando.setInt(2, id);
                comando.setString(3, "Vendido");
                return comando.executeUpdate() == 1;
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel vender o produto: " + erro.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos ORDER BY id";

        try (Connection conexao = new conectaDAO().connectDB()) {
            if (conexao == null) {
                return produtos;
            }

            try (PreparedStatement comando = conexao.prepareStatement(sql);
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultado.getInt("id"));
                    produto.setNome(resultado.getString("nome"));
                    produto.setValor(resultado.getInt("valor"));
                    produto.setStatus(resultado.getString("status"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel listar os produtos: " + erro.getMessage());
        }

        return produtos;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = ? ORDER BY id";

        try (Connection conexao = new conectaDAO().connectDB()) {
            if (conexao == null) {
                return produtos;
            }

            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, "Vendido");
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        ProdutosDTO produto = new ProdutosDTO();
                        produto.setId(resultado.getInt("id"));
                        produto.setNome(resultado.getString("nome"));
                        produto.setValor(resultado.getInt("valor"));
                        produto.setStatus(resultado.getString("status"));
                        produtos.add(produto);
                    }
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel listar os produtos vendidos: " + erro.getMessage());
        }

        return produtos;
    }
}

