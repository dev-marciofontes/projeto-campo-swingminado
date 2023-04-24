package br.com.marciofontes.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {

	private int qtdLinhas;
	private int qtdColunas;
	private int qtdMinas;

	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

	public Tabuleiro(int qtdLinhas, int qtdColunas) {
		this.qtdLinhas = qtdLinhas;
		this.qtdColunas = qtdColunas;

		int totalCampos = qtdLinhas * qtdColunas;

		int min = qtdLinhas;
		int max = totalCampos;

		Random random = new Random();
		int numeroAleatorio = random.nextInt(max - min + 1) + min;

		this.qtdMinas = numeroAleatorio /2;

		// local ideal para inicializar o objeto
		gerarCampos();
		associarOsVizinhos();
		sortearCamposComMinas();

	}
	
	public void paraCadaCampo (Consumer<Campo> funcao) {
		campos.forEach(funcao);
	}

	public void registrarObservador(Consumer<ResultadoEvento> observador) {
		observadores.add(observador);
	}

	private void notificaObservadores(boolean resultado) {
		observadores.stream().forEach(o -> o.accept(new ResultadoEvento(resultado)));
	}

	public void abrir(int linha, int coluna) {

		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.abrir());

	}

	public void alterarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.alternarMarcacao());
		;
	}

	private void gerarCampos() {
		for (int linha = 0; linha < qtdLinhas; linha++) {
			for (int coluna = 0; coluna < qtdColunas; coluna++) {
				Campo campo = new Campo(linha, coluna);
				campo.registrarObservador(this);
				campos.add(campo);
			}
		}

	}

	private void associarOsVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearCamposComMinas() {
		long minasDistribuidas = 0;
		Predicate<Campo> minado = c -> c.isMinado();

		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasDistribuidas = campos.stream().filter(minado).count();
		} while (minasDistribuidas < qtdMinas);
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciaJogo() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearCamposComMinas();
	}

	public int getQtdLinhas() {
		return this.qtdLinhas;
	}

	public int getQtdColunas() {
		return this.qtdColunas;
	}

	@Override
	public void eventoOcorreu(Campo c, CampoEvento e) {
		if (e == CampoEvento.EXPLODIR) {
			mostrarMinas();
			notificaObservadores(false);
		} else if (objetivoAlcancado()) {
			notificaObservadores(true);
		}
	}

	private void mostrarMinas() {
		campos.stream()
		.filter(c -> c.isMinado())
		.filter(c -> !c.isMarcado())
		.forEach(c -> c.setcAberto(true));
	}

}
