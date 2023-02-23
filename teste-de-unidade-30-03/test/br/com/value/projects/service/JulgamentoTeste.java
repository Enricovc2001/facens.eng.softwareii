package br.com.value.projects.service;

import org.junit.*;

import br.com.value.projects.builder.CriadorDeJogo;
import br.com.value.projects.dominio.Participante;
import br.com.value.projects.dominio.Jogo;
import br.com.value.projects.dominio.Resultado;

//Deixa claro que testará a feature de Julgamento, que não é necessariamente uma classe.
public class JulgamentoTeste {

	private Juiz juiz;
	private Participante joao;
	private Participante pedro;
	private Participante katia;
	private Participante maria;

	@Before
	// O método cria juiz é realizado antes de cada teste unitário da classe, ou
	// seja, cada método do @Test
	public void criaJuiz() {

		this.juiz = new Juiz();
		this.joao = new Participante("Joao");
		this.pedro = new Participante("Pedro");
		this.katia = new Participante("Katia");
		this.maria = new Participante("Maria");

	}

	@Test
	// O nome condiz com o que é testado no método
	public void deveJulgarPrimeiroEultimoColocado() {

		Jogo jogo = new Jogo("Derruba barreiras");

		jogo.anota(new Resultado(joao, 90.0));
		jogo.anota(new Resultado(pedro, 91.0));
		jogo.anota(new Resultado(katia, 93.0));
		jogo.anota(new Resultado(maria, 94.0));

		juiz.julga(jogo);

		double vencedorJogo = 94;
		double ultimoColocadoJogo = 90;

		Assert.assertEquals(vencedorJogo, juiz.getPrimeiroColocado(), 0.00001);
		Assert.assertEquals(ultimoColocadoJogo, juiz.getUltimoColocado(), 0.00001);
	}

	@Test(expected = RuntimeException.class)
	// Nesse arquivo não é validado com o Asser.assertEquals, logo para ele o teste
	// foi um sucesso já que não houve exceções.
	public void naoDeveJulgarSemResultado() {
		Jogo jogo = new CriadorDeJogo()
				.para("Ca�a pe�as")
				.constroi();

		juiz.julga(jogo);
	}

}
