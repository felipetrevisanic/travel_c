package database.model;

public class UsuarioModel {

    public static  final String TABELA_NOME = "usuario";
    public static final String
            COLUNA_ID_USUARIO = "_id",
            COLUNA_LOGIN = "login",
            COLUNA_PASS = "password";

    public static final String
            CREATE_TABLE =
            "create table " + TABELA_NOME
                    + "("
                    + COLUNA_ID_USUARIO + " integer primary key autoincrement,"
                    + COLUNA_LOGIN + " text not null,"
                    + COLUNA_PASS + " text not null"
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME + ";";

    private long id;
    private String login, pass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
