package app;

import static spark.Spark.*;
import service.NotaService;
import service.UsuarioService;
import spark.Filter;

/**
 * API para conexao com o site Planejy
 * 
 * @author Marcos Antonio Lommez Candido Ribeiro
 * @author Bernardo Marques Fernandes
 */
public class Aplicacao {

        private static NotaService notaService = new NotaService();
        private static UsuarioService usuarioService = new UsuarioService();

        /**
         * Metodo Main/Driver
         * Possui os metodos gets/post necessarios para rodar a aplicacao
         * 
         * O frontend utiliza o endereco fornecido pelo metodo, e caso seja um post um
         * body. O Service relacionado sera chamado e esse se conectara ao respectivo
         * obejtoDAO e caso necessario ira utilizar a classe MODEL que e' um objeto
         * utilizado de referencia
         * 
         * @param args
         */
        public static void main(String[] args) {
                port(5678);

                // Validacao CORS
                staticFiles.location("/public");
                after((Filter) (request, response) -> {
                        response.header("Access-Control-Allow-Origin", "*");
                        response.header("Access-Control-Allow-Methods", "GET, POST");
                });


                /* ------------------------ CALENDARIO ------------------------ */
                // Todas as validacoes sao feitas pelo token do usuario
                // Receber todas as notas, validadas pelo token de usuario
                get("/nota/get/:tokenUsuario", (request, response) -> notaService.get(request, response));
                // Postar Nota
                post("/nota/post/:tokenUsuario", (request, response) -> notaService.insert(request, response));
                // Atualizar Nota
                post("/nota/update/:tokenUsuario/:chave", (request, response) -> notaService.update(request, response));
                // Apagar nota
                get("/nota/delete/:tokenUsuario/:chave", (request, response) -> notaService.delete(request, response));

                /* ------------------------- USUARIO ------------------------- */
                // Registrar usuario, senha e' enviados pelo body e criptografada em md5
                post("/usuario/registrar/:nome/:nick/:email",
                                (request, response) -> usuarioService.insert(request, response));
                // Atualizar dados do usuario, dados sao enviados pelo body (nao atualiza senha)
                post("/usuario/Atualizar/:token/:id", (request, response) -> usuarioService.update(request, response));
                // Retornar os dados do usuario para a tela de configuracoes
                get("/usuario/get/:tokenUsuario", (request, response) -> usuarioService.get(request, response));
                // Login, token e' gerado no front e salvo no BD para autenticacao, senha no
                // body e em seguida criptografada em md5
                post("/usuario/login/:email/:token", (request, response) -> usuarioService.login(request, response));
                // Confirmar se email e' valido, retorna ID caso seja e registra token no BD
                get("/usuario/recuperarSenha/:email/:tokenUsuario",
                                (request, response) -> usuarioService.confirmarEmail(request, response));
                // Muda a senha atraves do novo token gerado para mudanca de senha
                post("/usuario/recuperarSenha/:tokenUsuario",
                                (request, response) -> usuarioService.mudarSenhaToken(request, response));
                // Excluir um usuario a partir do seu ID e Token
                get("/usuario/Excluir/:token/:id", (request, response) -> usuarioService.delete(request, response));

        }
}
