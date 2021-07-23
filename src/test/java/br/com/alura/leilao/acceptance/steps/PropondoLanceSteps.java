package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.jv.Lan;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropondoLanceSteps {

    private Lance lance;
    private Leilao leilao;
    private List<Lance> lances;

    @Before
    public void setup() {
        lances = new ArrayList<>();
        leilao = new Leilao("carro");
    }

    @Dado("um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Quando("propoe ao leilao")
    public void quando_propoe_o_lance() {
        leilao.propoe(lance);
    }

    @Entao("o lance e aceito")
    public void entao_o_lance_e_aceito() {
        assertTrue(leilao.getLances().size() > 0);
        assertEquals(leilao.getLances().get(0).getValor(), lance.getValor());
    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario(Double value, String name) {
        Usuario usuario1 = new Usuario(name);
        lances.add(new Lance(usuario1, BigDecimal.valueOf(value)));
    }

    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        lances.stream().forEach(leilao::propoe);
    }

    @Entao("o maior lance e aceito")
    public void o_maior_lance_e_aceito() {
        assertTrue(leilao.getLances().size() == 2);
        assertEquals(leilao.getLances().get(0).getValor(), BigDecimal.valueOf(10.0));
        assertEquals(leilao.getLances().get(1).getValor(), BigDecimal.valueOf(15.0));
    }

    @Dado("um lance invalido de {int} reais do usuario {string}")
    public void um_lance_invalido_de_reais_do_usuario(Integer value, String nomeUsuario) {
        lance = new Lance(new Usuario(nomeUsuario), BigDecimal.valueOf(value));
        leilao.propoe(lance);
    }

    @Entao("o lance nao e aceito")
    public void o_lance_nao_e_aceito() {
        assertEquals(0, leilao.getLances().size());
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>> valores = dataTable.asMaps();

        valores.stream().forEach(map -> {
            String name = map.get("nomeUsuario");
            String valor = map.get("valor");
            Lance lance = new Lance(new Usuario(name), BigDecimal.valueOf(Double.valueOf(valor)));
            lances.add(lance);
        });
    };


    @Entao("segundo lance nao sera aceito")
    public void segundo_lance_nao_sera_aceito() {
        assertEquals(1, leilao.getLances().size());
    }
}
