package br.com.marciofontes.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.marciofontes.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getQtdLinhas(), tabuleiro.getQtdColunas()));

		tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

		tabuleiro.registrarObservador(e -> {
			SwingUtilities.invokeLater(() -> {
				if (e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Parabéns! Você ganhou!");
				} else {
					JOptionPane.showMessageDialog(this, "Você é muito ruim! Você perdeu!");
				}
				
				tabuleiro.reiniciaJogo();
			});

		});
	}

}
