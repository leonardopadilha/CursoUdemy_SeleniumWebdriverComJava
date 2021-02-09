package testSiteDeCompras;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver navegador) {
        dsl = new DSL(navegador);
    }

    public void setNome(String nome){
        dsl.escreveNome("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escreveSobrenome("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino(){
        dsl.selecionaTipoSexo("input[id='elementosForm:sexo:0']");
    }

    public void setPizza(){
        dsl.selecionarComidaFavorita("elementosForm:comidaFavorita:2");
    }

    public void setEscolaridade( String escolaridade){
        dsl.selecionaTipoEscolaridade("elementosForm:escolaridade", escolaridade);
    }

    public void setEsportes(String... esporte){
        for(String esportes: esporte)
        dsl.selecionaTipoEsporte("elementosForm:esportes", esportes);
    }

    public void setSugestoes(String texto){
        dsl.escreveTextArea("elementosForm:sugestoes", texto);
    }

    public void cadastrar(){
        dsl.clicarBotaoCadastrar("input[id*='cadastrar']");
    }

    public void aceitarAlertSimples(){
        dsl.aceitarAlertaSimples();
    }

}

