package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcademiaDAO {

    private static final Logger LOGGER = Logger.getLogger(AcademiaDAO.class.getName());

    public void inserir(AcademiaDTO academia) {
        String sql = "INSERT INTO academia (nome, endereco, telefone, quant_alunos) VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setString(3, academia.getTelefone());
            stmt.setInt(4, academia.getQuantidadeAlunos());

            stmt.executeUpdate();
            LOGGER.info("Academia cadastrada com sucesso!");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir academia", e);
        }
    }

    public List<AcademiaDTO> listar() {
        List<AcademiaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM academia";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AcademiaDTO academia = new AcademiaDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getInt("quant_alunos")
                );

                lista.add(academia);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar academias", e);
        }

        return lista;
    }

    public AcademiaDTO buscarPorId(int id) {
        String sql = "SELECT * FROM academia WHERE id = ?";
        AcademiaDTO academia = null;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    academia = new AcademiaDTO(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getInt("quant_alunos")
                    );
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar academia", e);
        }

        return academia;
    }

    public void atualizar(AcademiaDTO academia) {
        String sql = "UPDATE academia SET nome = ?, endereco = ?, telefone = ?, quant_alunos = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setString(3, academia.getTelefone());
            stmt.setInt(4, academia.getQuantidadeAlunos());
            stmt.setInt(5, academia.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                LOGGER.info("Academia atualizada com sucesso!");
            } else {
                LOGGER.warning("Academia não encontrada.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar academia", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM academia WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                LOGGER.info("Academia removida com sucesso!");
            } else {
                LOGGER.warning("Academia não encontrada.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao remover academia", e);
        }
    }
}