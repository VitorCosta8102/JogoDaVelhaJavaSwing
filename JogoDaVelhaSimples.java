import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class JogoDaVelhaSimples {
	
	private ImageIcon circulo = new ImageIcon("imagens/circulo.png");
	private ImageIcon imagemX = new ImageIcon("imagens/imagemX.png");
	private ImageIcon circuloVerde = new ImageIcon("imagens/circuloVerde.png");
	private ImageIcon XVerde = new ImageIcon("imagens/imagemXVerde.png");
	private int jogada=1;
	private JFrame janela;
	private JPanel painelPrincipal;
	private JPanel painelBotoes;
	private JPanel painelBotoesInferior;
	private JButton[][] matrizBotoes = new JButton[3][3];
	private boolean fimDeJogo = false;
	
	
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
		janela = new JFrame("Jogo da Velha");
		janela.setLocation(250,150);
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
				JButton botao = new JButton();
				botao.setBackground(Color.WHITE);
				ActionListener figuraListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(botao.getIcon()==null && fimDeJogo==false){
							if(jogada%2==0) 
								botao.setIcon(imagemX);
							else 
								botao.setIcon(circulo);
							jogada++;
							checaFimDeJogo();
						}
					}
				};
				botao.addActionListener(figuraListener);
				painelBotoes.add(botao);
				matrizBotoes[i][j]=botao;
			}
		colocaBordas();
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
					for(int j=0;j<3;j++) {
						matrizBotoes[i][j].setIcon(null);
						matrizBotoes[i][j].setBackground(Color.WHITE);
					}
				jogada=1;
				fimDeJogo = false;
			}
		});
		painelBotoesInferior.add(botaoReset);
	}
	
	public void mostraTela() {
		//janela.pack();
		janela.setSize(400,400);
		janela.setResizable(false);
		janela.setVisible(true);
	}
	
	public void preparaTitulo() {
		JLabel titulo = new JLabel("Jogo da Velha",SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana",Font.BOLD,15));
		painelPrincipal.add(titulo,BorderLayout.NORTH);
	}
	
	public void colocaBordas() {
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				int[] bordas = new int[]{0,0,0,0};
				if(i==0)
					bordas[2]=2;
				else if(i==1) {
					bordas[0]=2;
					bordas[2]=2;
				}
				else
					bordas[0]=2;
				if(j==0)
					bordas[3]=2;
				else if(j==1) {
					bordas[1]=2;
					bordas[3]=2;
				}
				else
					bordas[1]=2;
				matrizBotoes[i][j].setBorder(new MatteBorder(bordas[0],bordas[1],bordas[2],bordas[3],Color.BLACK));
			}
	}
	
	public void checaFimDeJogo() {
		int contagem = 0;
		//checar se houve ganhador por linhas
		for(int i=0;i<3;i++) {
			int j;
			for(j=0;j<2;j++) {
				if(matrizBotoes[i][j].getIcon()!=null)
					if(matrizBotoes[i][j].getIcon() == matrizBotoes[i][j+1].getIcon())
						contagem++;
			}
			if(contagem==2) {
				//indicando campos que formam o trio
				for(int k=0;k<3;k++) {
					if(matrizBotoes[i][k].getIcon()==circulo)
						matrizBotoes[i][k].setIcon(circuloVerde);
					else
						matrizBotoes[i][k].setIcon(XVerde);
				}
				mostraGanhador((ImageIcon)matrizBotoes[i][j-1].getIcon());
			}
			contagem = 0;
		}
		
		//checar se houve ganhador por colunas
		for(int i=0;i<3;i++) {
			int j;
			for(j=0;j<2;j++) {
				if(matrizBotoes[j][i].getIcon()!=null)
					if(matrizBotoes[j][i].getIcon() == matrizBotoes[j+1][i].getIcon())
						contagem++;
			}
			if(contagem==2) {
				//indicando campos que formam o trio
				for(int k=0;k<3;k++) {
					if(matrizBotoes[k][i].getIcon()==circulo)
						matrizBotoes[k][i].setIcon(circuloVerde);
					else
						matrizBotoes[k][i].setIcon(XVerde);
				}
				mostraGanhador((ImageIcon)matrizBotoes[j-1][i].getIcon());
			}
			contagem = 0;
		}

		//checar se houve ganhador na diagonal 
		if(matrizBotoes[0][0].getIcon()==matrizBotoes[1][1].getIcon() && 
			matrizBotoes[1][1].getIcon()==matrizBotoes[2][2].getIcon() && matrizBotoes[0][0].getIcon()!=null) {
			for(int m=0;m<3;m++){
				if(matrizBotoes[m][m].getIcon()==circulo)
					matrizBotoes[m][m].setIcon(circuloVerde);
				else
					matrizBotoes[m][m].setIcon(XVerde);
			}
			mostraGanhador((ImageIcon)matrizBotoes[0][0].getIcon());	
		}
		//checar se houve ganhador na outra diagonal
		if(matrizBotoes[0][2].getIcon()==matrizBotoes[1][1].getIcon() && 
			matrizBotoes[1][1].getIcon()==matrizBotoes[2][0].getIcon() && matrizBotoes[0][2].getIcon()!=null) {
			for(int m=0;m<3;m++) {
				if(matrizBotoes[m][2-m].getIcon()==circulo)
					matrizBotoes[m][2-m].setIcon(circuloVerde);
				else
					matrizBotoes[m][2-m].setIcon(XVerde);
			}
			mostraGanhador((ImageIcon)matrizBotoes[0][2].getIcon());	
		}	
	}
	
	public void mostraGanhador(ImageIcon image) {
		if(image==circuloVerde) {
			JOptionPane.showMessageDialog(janela, "O jogador do O ganhou!");
			fimDeJogo = true;
		}
		else {
			JOptionPane.showMessageDialog(janela, "O jogador do X ganhou!");
			fimDeJogo = true;
		}
	}
	
}