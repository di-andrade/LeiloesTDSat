import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(
        new Object[] {"ID", "Nome", "Valor", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabela = new JTable(modelo);

    public vendasVIEW() {
        setTitle("Produtos Vendidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        add(new JLabel("Produtos Vendidos", JLabel.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(evt -> {
            new listagemVIEW().setVisible(true);
            dispose();
        });
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(btnVoltar);
        add(rodape, BorderLayout.SOUTH);

        listarProdutosVendidos();
        setSize(620, 380);
        setLocationRelativeTo(null);
    }

    private void listarProdutosVendidos() {
        modelo.setRowCount(0);
        ArrayList<ProdutosDTO> produtos = new ProdutosDAO().listarProdutosVendidos();
        for (ProdutosDTO produto : produtos) {
            modelo.addRow(new Object[] {
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }
}
