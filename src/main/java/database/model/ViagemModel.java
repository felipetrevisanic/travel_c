package database.model;

public class ViagemModel {

    public static final String TABELA_NOME = "viagem";

    public static final String
        COLUNA_ID_VIAGEM = "_idViagem",
        COLUNA_TOTAL_VIAJANTES = "totalViajantes",
        COLUNA_TOTAL_DIAS = "totalDias",
        COLUNA_DESTINO = "destino",
        COLUNA_TOTAL_KM = "totalKm",
        COLUNA_MEDIA_KM = "mediaKm",
        COLUNA_CUSTO_L = "custoLitro",
        COLUNA_TOTAL_VEICULOS = "totalVeiculos",
        COLUNA_CUSTO_NOITE = "custoNoite",
        COLUNA_TOTAL_NOITE = "totalNoite",
        COLUNA_TOTAL_QUARTO = "totalQuarto",
        COLUNA_CUSTO_REFEICAO = "custoRefeicao",
        COLUNA_REFEICAO_DIA = "refeicaoDia",
        COLUNA_CUSTO_TARIFA = "custoPessoa",
        COLUNA_ALUGEL_VEICULO = "aluguelVeiculo",
        COLUNA_TOTAL_DIVERSOS = "diversos",
        COLUNA_TOTAL = "total",
        COLUNA_USER_ID = "usuarioId";

    public static final String
            CREATE_TABLE =
            "create table " + TABELA_NOME
                    + "("
                    + COLUNA_ID_VIAGEM + " integer primary key autoincrement,"
                    + COLUNA_TOTAL_VIAJANTES + " integer not null,"
                    + COLUNA_TOTAL_DIAS + " integer not null,"
                    + COLUNA_DESTINO + " text not null,"
                    + COLUNA_TOTAL_KM + " double not null,"
                    + COLUNA_MEDIA_KM + " double not null,"
                    + COLUNA_CUSTO_L + " double not null,"
                    + COLUNA_TOTAL_VEICULOS + " integer not null,"
                    + COLUNA_CUSTO_NOITE + " double not null,"
                    + COLUNA_TOTAL_NOITE + " integer not null,"
                    + COLUNA_TOTAL_QUARTO + " integer not null,"
                    + COLUNA_CUSTO_REFEICAO + " double not null,"
                    + COLUNA_REFEICAO_DIA + " integer not null,"
                    + COLUNA_CUSTO_TARIFA + " double not null,"
                    + COLUNA_ALUGEL_VEICULO + " double not null,"
                    + COLUNA_TOTAL_DIVERSOS + " double not null,"
                    + COLUNA_TOTAL + " double not null,"
                    + COLUNA_USER_ID + " integer,"
                    + "FOREIGN KEY(" + COLUNA_USER_ID + ") REFERENCES usuario(_idUsuario)"
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME + ";";

    private long id;
    private double totalKm,mediakm,custoLitro,custoNoite,custoRefeicao,custoTarifa,aluguelVeiculo,totalDiversos,total;
    private int totalViajantes,duracaoViagem,totalVeiculos,totalNoite,totalQuarto,refeicaoDia;
    private String destino;
    
    public ViagemModel(){};

    public ViagemModel(int idViagem, int totalViajantes, int totalDias, int destino, int total, int usuarioId) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotalViajantes() {
        return totalViajantes;
    }

    public void setTotalViajantes(int totalViajantes) {
        this.totalViajantes = totalViajantes;
    }

    public int getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    public double getMediakm() {
        return mediakm;
    }

    public void setMediakm(double mediakm) {
        this.mediakm = mediakm;
    }

    public double getCustoLitro() {
        return custoLitro;
    }

    public void setCustoLitro(double custoLitro) {
        this.custoLitro = custoLitro;
    }

    public double getCustoNoite() {
        return custoNoite;
    }

    public void setCustoNoite(double custoNoite) {
        this.custoNoite = custoNoite;
    }

    public double getCustoRefeicao() {
        return custoRefeicao;
    }

    public void setCustoRefeicao(double custoRefeicao) {
        this.custoRefeicao = custoRefeicao;
    }

    public double getCustoTarifa() {
        return custoTarifa;
    }

    public void setCustoTarifa(double custoTarifa) {
        this.custoTarifa = custoTarifa;
    }

    public double getAluguelVeiculo() {
        return aluguelVeiculo;
    }

    public void setAluguelVeiculo(double aluguelVeiculo) {
        this.aluguelVeiculo = aluguelVeiculo;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public int getTotalNoite() {
        return totalNoite;
    }

    public void setTotalNoite(int totalNoite) {
        this.totalNoite = totalNoite;
    }

    public int getTotalQuarto() {
        return totalQuarto;
    }

    public void setTotalQuarto(int totalQuarto) {
        this.totalQuarto = totalQuarto;
    }

    public int getRefeicaoDia() {
        return refeicaoDia;
    }

    public void setRefeicaoDia(int refeicaoDia) {
        this.refeicaoDia = refeicaoDia;
    }

    public double getTotalDiversos() {
        return totalDiversos;
    }

    public void setTotalDiversos(double totalDiversos) {
        this.totalDiversos = totalDiversos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = (((getTotalKm()/getMediakm())*getCustoLitro())/getTotalVeiculos())
                + (((getRefeicaoDia()*getCustoRefeicao())*getTotalViajantes()))
                + (((getCustoTarifa()*getTotalViajantes())+getAluguelVeiculo()))
                + (((getCustoNoite()*getTotalQuarto())*getTotalNoite()))
                + getTotalDiversos();

    }

}
