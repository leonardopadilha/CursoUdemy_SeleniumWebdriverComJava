package testSiteDeCompras;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DSL {

    private WebDriver navegador;

    public DSL(WebDriver navegador) {
        this.navegador = navegador;
    }

    public void escreveNome(String id_campo, String texto){
        navegador.findElement(By.id(id_campo)).clear();
        navegador.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public void escreveSobrenome(String id_campo, String texto){
        navegador.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public void selecionaTipoSexo(String tipoSexo){
        navegador.findElement(By.cssSelector(tipoSexo)).click();
    }

    public void selecionaTipoEscolaridade(String id_campo, String escolaridade){
        new Select(navegador.findElement(By.id(id_campo)))
                .selectByVisibleText(escolaridade);
    }

    public void selecionaTipoEsporte(String id_campo, String esporte){
        new Select(navegador.findElement(By.id(id_campo)))
                .selectByVisibleText(esporte);
    }

    public void escreveTextArea(String id_campo, String sugestoes){
        navegador.findElement(By.id(id_campo))
                .sendKeys(sugestoes);
    }

    public void aceitarAlertaSimples(){
        Alert alert = navegador.switchTo().alert();
        alert.accept();
    }

    public void botaoNegarModalConfirmar(){
        Alert modalConfirmar = navegador.switchTo().alert();
        modalConfirmar.dismiss();
    }

    public void botaoAceitarModalConfirmar(String id_campo){
        Alert modalConfirmar = navegador.switchTo().alert();
        modalConfirmar.dismiss();
    }

    public void digitaModal(String numero){
        Alert alert = navegador.switchTo().alert();
        alert.sendKeys(numero);
        aceitarAlertaSimples();
        Assert.assertEquals("Era 12?", alert.getText());
        aceitarAlertaSimples();
        aceitarAlertaSimples();
    }

    public void clicarBotaoCadastrar(String id_campo){
        navegador.findElement(By.cssSelector(id_campo)).click();
    }

    public void selecionarComidaFavorita(String id_campo){
        navegador.findElement(By.id(id_campo)).click();
    }

    public void clicarBotaoAbrirPopUp(String id_campo){
        navegador.findElement(By.id(id_campo)).click();
    }

    public void alternarPoupSemTitulo(int janela){
        //Transformando a exibição em array e pegando a segunda posição
        navegador.switchTo().window((String)navegador.getWindowHandles().toArray()[janela]);
    }

    public void escrevendoTextAreaPopUpSemTitulo(String sugestoes){
        navegador.findElement(By.tagName("textarea")).sendKeys(sugestoes);
    }
}


