import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;

public class JogoDaVelhaSimples {
	
	private ImageIcon image1 = new ImageIcon("imagens/circulo.png");
	private ImageIcon image2 = new ImageIcon("imagens/imagemX.png");
	private ImageIcon fundo =  new ImageIcon("imagens/fundoBranco.png");
	private int jogada=1;
	private JFrame janela;
	private JPanel painelPrincipal;
	private JPanel painelBotoes;
	private JPanel painelBotoesInferior;
	private JButton[][] matrizBotoes = new JButton[3][3];
	
	
	public static void main(String[] args) {
		new JogoDaVelhaSimples().montaTela();
	}
	
	public void montaTela() {
		preparaJanela();
		preparaPainelPricipal();
		preparaPainelBotoes();
		preparaPainelBotoesInferior();
		preparaBotaoReset();
		preparaBotaoSair();
		mostraTela();
	}
	
	public void preparaJanela() {
		janela = new JFrame("Button Grid");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void preparaPainelPricipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		preparaTitulo();
		janela.add(painelPrincipal);
	}
	
	public void preparaPainelBotoesInferior() {
		painelBotoesInferior = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoesInferior,BorderLayout.SOUTH);
	}
	
	public void preparaPainelBotoes() {
		painelBotoes = new JPanel();
		painelBotoes.setLayout(new GridLayout(3,3));
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				JButton botao = new JButton(fundo);
				ActionListener figuraListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(botao.getIcon()==fundo){
							if(jogada%2==0) 
								botao.setIcon(image2);
							else 
								botao.setIcon(image1);
							jogada++;
						}
					}
				};
				botao.addActionListener(figuraListener);
				painelBotoes.add(botao);
				matrizBotoes[i][j]=botao;
			}
		painelPrincipal.add(painelBotoes,BorderLayout.CENTER);
	}
	
	public void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelBotoesInferior.add(botaoSair);
	}
	
	public void preparaBotaoReset() {
		JButton botaoReset = new JButton("Reset");
		botaoReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++)
					for(int j=0;j<3;j++)
						matrizBotoes[i][j].setIcon(fundo);
				jogada=1;
			}
		});
		painelBotoesInferior.add(botaoReset);
	}
	
	public void mostraTela() {
		janela.pack();
		janela.setSize(400,400);
		janela.setVisible(true);
	}
	
	public void preparaTitulo() {
		JLabel titulo = new JLabel("Grade de Botoes",SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana",Font.BOLD,15));
		painelPrincipal.add(titulo,BorderLayout.NORTH);
	}
	
}