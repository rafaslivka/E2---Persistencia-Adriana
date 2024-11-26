package com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EletronicosGUI extends JFrame {

    private ArrayList<Eletronicos> dispositivos;
    private JTextArea infoArea;
    private JComboBox<String> dispositivoMenu;

    public EletronicosGUI(ArrayList<Eletronicos> dispositivos) {
        this.dispositivos = dispositivos;
        criarInterface();
    }

    private void criarInterface() {
        setTitle("Controle de Eletrônicos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel para a interface
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Área de exibição de informações
        infoArea = new JTextArea(5, 30);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Menu de dispositivos
        dispositivoMenu = new JComboBox<>();
        for (Eletronicos dispositivo : dispositivos) {
            dispositivoMenu.addItem(dispositivo.getNome());
        }

        dispositivoMenu.addActionListener(e -> atualizarInfo());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(dispositivoMenu);

        // Botões de ação
        JButton ligarButton = new JButton("Ligar");
        ligarButton.addActionListener(e -> {
            Eletronicos dispositivoSelecionado = getDispositivoSelecionado();
            if (dispositivoSelecionado != null) {
                dispositivoSelecionado.ligar();
                atualizarInfo();
            }
        });

        JButton desligarButton = new JButton("Desligar");
        desligarButton.addActionListener(e -> {
            Eletronicos dispositivoSelecionado = getDispositivoSelecionado();
            if (dispositivoSelecionado != null) {
                dispositivoSelecionado.desligar();
                atualizarInfo();
            }
        });

        buttonPanel.add(ligarButton);
        buttonPanel.add(desligarButton);
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Adicionando GIF de fundo
        JLabel gifLabel = new JLabel(new ImageIcon("D:/Tarefa3/eletronicos.gif"));
        panel.add(gifLabel, BorderLayout.WEST);

        add(panel);
        setVisible(true);
    }

    public Eletronicos getDispositivoSelecionado() {
        String nomeSelecionado = (String) dispositivoMenu.getSelectedItem();
        for (Eletronicos dispositivo : dispositivos) {
            if (dispositivo.getNome().equals(nomeSelecionado)) {
                return dispositivo;
            }
        }
        return null;
    }

    private void atualizarInfo() {
        Eletronicos dispositivoSelecionado = getDispositivoSelecionado();
        if (dispositivoSelecionado != null) {
            infoArea.setText("");
            infoArea.append("Nome: " + dispositivoSelecionado.getNome() + "\n");
            infoArea.append("Marca: " + dispositivoSelecionado.getMarca() + "\n");
            infoArea.append("Tipo: " + dispositivoSelecionado.getTipo() + "\n");
            infoArea.append("Armazenamento: " + dispositivoSelecionado.getArmazenamento() + " GB\n");
            infoArea.append("Bateria: " + dispositivoSelecionado.getBateria() + "%\n");
        }
    }

    public static void main(String[] args) {
        ArrayList<Eletronicos> dispositivos = new ArrayList<>();
        dispositivos.add(new Eletronicos("Smartphone", "Samsung", "Celular", 64, 80));
        dispositivos.add(new Eletronicos("Laptop", "Dell", "Computador", 512, 50));

        new EletronicosGUI(dispositivos);
    }
}
