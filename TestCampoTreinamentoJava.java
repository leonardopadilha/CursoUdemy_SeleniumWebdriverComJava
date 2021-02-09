package testSiteDeCompras;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestCampoTreinamento {

    private WebDriver navegador;
    private DSL dsl;
    String url = "file:///home/leonardor/Documentos/Cursos/SeleniumWebdriver/campo_treinamento/componentes.html";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/leonardor/usuarios/drivers/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.get(url);
        dsl = new DSL(navegador);
    }

    @Test
    public void testeTextField() {

        dsl.escreveNome("elementosForm:nome", "Curso Selenium Webdriver");
        dsl.escreveSobrenome("elementosForm:sobrenome", "Com Java");
        dsl.selecionaTipoSexo("input[id='elementosForm:sexo:0']");
        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:0']")).click();
        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:1']")).click();
        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:2']")).click();

        //Escolaridade
        dsl.selecionaTipoEscolaridade("elementosForm:escolaridade", "Mestrado");

        //Esporte
        dsl.selecionaTipoEsporte("elementosForm:esportes", "Natacao");
        dsl.selecionaTipoEsporte("elementosForm:esportes", "Futebol");
        dsl.escreveTextArea("elementosForm:sugestoes", "Esse é um curso de " +
                                     "Selenium Webdriver com java.");

        navegador.findElement(By.cssSelector("input[value*='aqui']")).click();

        //Fechando modal de alerta
        dsl.aceitarAlertaSimples();

        //Fechando modal confirm
        navegador.findElement(By.id("confirm")).click();
        dsl.botaoNegarModalConfirmar();
        dsl.aceitarAlertaSimples();

        //Alert prompt
        navegador.findElement(By.id("prompt")).click();
        dsl.digitaModal("12");

        dsl.clicarBotaoCadastrar("input[id*='cadastrar']");
    }

    @Test
    public void testeInteragirFrame() {

        //Muda o foco para o frame
        navegador.switchTo().frame("frame1");
        navegador.findElement(By.id("frameButton")).click();

        Alert alert = navegador.switchTo().alert();
        String mensagem = alert.getText();
        Assert.assertEquals("Frame OK!", mensagem);
        alert.accept();

        //Retira foco do frame
        navegador.switchTo().defaultContent();

        navegador.findElement(By.id("elementosForm:nome")).sendKeys("Frame OK!");
        navegador.findElement(By.id("elementosForm:sobrenome")).sendKeys("Com Java");
    }

    @Test
    public void testeInteragirPopUpComTitulo() {

        navegador.findElement(By.id("buttonPopUpEasy")).click();

        //Mudar foco para PopUp com nome conhecido
        navegador.switchTo().window("Popup");

        //Escrevendo na textarea
        navegador.findElement(By.tagName("textarea")).sendKeys("Estou no textarea do PopUp");
        navegador.close();

        //Retornando para página principal
        navegador.switchTo().window("");

        navegador.findElement(By.tagName("textarea")).sendKeys("Voltei para página principal");
    }

    @Test
    public void testeInteragirPopUpSemTitulo(){

        navegador.findElement(By.id("buttonPopUpHard")).click();

      //Imprimir o id das páginas (principal  popup)
      //System.out.println(navegador.getWindowHandle());
      //System.out.println(navegador.getWindowHandles());

        //Transformando a exibição em array e pegando a segunda posição
        navegador.switchTo().window((String)navegador.getWindowHandles().toArray()[1]);
        navegador.findElement(By.tagName("textarea")).sendKeys("Estou no textarea do PopUp");

        //Retornando para página principal
        navegador.switchTo().window((String)navegador.getWindowHandles().toArray()[0]);
        navegador.findElement(By.tagName("textarea")).sendKeys("Voltei para página principal");
    }

    @Test
    public void testeValidarRegrasDeNegocio(){

        navegador.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = navegador.switchTo().alert();
        alert.accept();

        navegador.findElement(By.id("elementosForm:nome")).sendKeys("Validando Regras");

        navegador.findElement(By.id("elementosForm:cadastrar")).click();
        alert.accept();
        navegador.findElement(By.id("elementosForm:sobrenome")).sendKeys("de Negócio");

        navegador.findElement(By.id("elementosForm:cadastrar")).click();
        alert.accept();
        navegador.findElement(By.cssSelector("input[id='elementosForm:sexo:0']")).click();

        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:0']")).click();
        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:3']")).click();
        navegador.findElement(By.id("elementosForm:cadastrar")).click();
        alert.accept();
        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:3']")).click();

        new Select(navegador.findElement(By.id("elementosForm:escolaridade")))
                  .selectByVisibleText("Especializacao");

        Select comboEsportes = new Select(navegador.findElement(By.id("elementosForm:esportes")));
            comboEsportes.selectByVisibleText("Natacao");
            comboEsportes.selectByVisibleText("O que eh esporte?");

        navegador.findElement(By.id("elementosForm:cadastrar")).click();
        alert.accept();

        comboEsportes.deselectByVisibleText("O que eh esporte?");

        navegador.findElement(By.id("elementosForm:cadastrar")).click();





//        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:0']")).click();
//        navegador.findElement(By.cssSelector("input[id*='comidaFavorita:3']")).click();
    }

    @Test
    public void testeAlterarNomeEscrito(){
        dsl.escreveNome("elementosForm:nome", "Primeiro Teste");
        dsl.escreveNome("elementosForm:nome", "Segundo Teste");

        dsl.clicarBotaoCadastrar("input[id*='cadastrar']");
        dsl.aceitarAlertaSimples();
    }

    @After
    public void fecharNavegador() {
        navegador.quit();
    }
}


//alert.dismiss(); --> Não aceitar
//navegador.findElement(By.xpath("//button[contains(text(), 'ok')]")).click();

//Verificar se o título da página é Campo de Treinamento
// Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());

//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", navegador.findElement(By.className("span")).getText());


