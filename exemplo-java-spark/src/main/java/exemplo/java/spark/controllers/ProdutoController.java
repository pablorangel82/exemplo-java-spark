
package exemplo.java.spark.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import exemplo.java.spark.model.dto.input.ProdutoDTOInput;
import exemplo.java.spark.services.ProdutoService;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/06/2023
 * @brief  class ProdutoController
 */
public class ProdutoController {

    private final ProdutoService produtoService = new ProdutoService();
    private final ObjectMapper objMapper = new ObjectMapper();
    
    public ProdutoController() {
        get("/produtos", (req, res) -> {
            String json = objMapper.writeValueAsString(produtoService.listarProdutos());
            return json;
        });
        
        post("/produto",(request, response) -> {
            ProdutoDTOInput produtoDTOInput = objMapper.readValue(request.body(), ProdutoDTOInput.class);
            produtoService.adicionarProduto(produtoDTOInput);
            response.status(200);
            return "Produto inserido com sucesso";
        });
    }
    
}
