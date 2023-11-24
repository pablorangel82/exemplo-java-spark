import exemplo.java.spark.dto.input.ProdutoDTOInput;
import exemplo.java.spark.services.ProdutoService;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Test
    public void testeInsercao() {
        ProdutoService produtoService = new ProdutoService();
        ProdutoDTOInput produtoDTOInput = new ProdutoDTOInput();
        produtoDTOInput.setNome("PS3");

        produtoService.adicionarProduto(produtoDTOInput);

        assertEquals(1,produtoService.listarProdutos().size());
    }
}
