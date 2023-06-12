package exemplo.java.spark.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import exemplo.java.spark.model.dto.input.ProdutoDTOInput;
import exemplo.java.spark.services.ProdutoService;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.put;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/06/2023
 * @brief class ProdutoController
 */
public class ProdutoController {

    private final ProdutoService produtoService = new ProdutoService();
    private final ObjectMapper objMapper = new ObjectMapper();

    public ProdutoController() {
        get("/produtos", (request, response) -> {
            response.type("application/json");
            response.status(200);
            String json = objMapper.writeValueAsString(produtoService.listarProdutos());
            return json;
        });
        
        get("/produto/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            Long id = Long.valueOf(idParam);
            String json = objMapper.writeValueAsString(produtoService.obterProduto(id));
            response.status(200);
            return json;
        });

        post("/produto", (request, response) -> {
            ProdutoDTOInput produtoDTOInput = objMapper.readValue(request.body(), ProdutoDTOInput.class);
            produtoService.adicionarProduto(produtoDTOInput);
            response.type("application/json");
            response.status(201);
            return "Produto inserido com sucesso.";
        });
        
        put("/produto", (request, response) -> {
            ProdutoDTOInput produtoDTOInput = objMapper.readValue(request.body(), ProdutoDTOInput.class);
            produtoService.alterarProduto(produtoDTOInput);
            response.type("application/json");
            response.status(200);
            return "Produto alterado com sucesso.";
        });

        delete("/produto/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            Long id = Long.valueOf(idParam);
            produtoService.removerProduto(id);
            response.status(200);
            return "Produto excluido com sucesso.";
        });
    }



}
