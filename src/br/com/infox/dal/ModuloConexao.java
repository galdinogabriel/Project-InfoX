package br.com.infox.dal;

import java.sql.*; //importa as bibliotecas do SQL

public class ModuloConexao {

    // metódo responsavel pela conexao com o  BD
    // Connection - Conjunto de funcionalidades (framework);
    // trazido do pacote SQL
    public static Connection conector() {

        // variavel que vai conter as informações da conexao
        java.sql.Connection conexao = null;

        // variavel  com um conector que faz referencia ao driver carregado na biblioteca
        String drive = "com.mysql.jdbc.Driver";

        // variavel com o caminho de acesso ao DB - local
        String url = "jdbc:mysql://localhost:3306/dbinfox";

        // variavel com o usuario do BD
        String user = "root";

        // variavel com a senha do BD
        String password = "";

        // estabelecendo a conexão com o DB
        try {
            //executar o driver do BD que está na variavel driver
            Class.forName(drive);

            // realiza a conexão com os valores de: url, user,password
            // conecta o sistema em java com o BD mySQL
            // salva  a informacao da variavel  da conexao  na variavel conexao
            conexao = DriverManager.getConnection(url, user, password);

            // se nao houver  nenhum problema de conexao  retorna  true
            return conexao;

        } catch (Exception e) {

            // se houver  algum  problema  retorna o valor NULL
            return null;

        }

    }
}
