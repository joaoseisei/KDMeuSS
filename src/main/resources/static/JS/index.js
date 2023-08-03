fetch("/gradesFiltradas")
  .then(response => response.text())
  .then(data => {
    console.log('Resposta da /teste:', data);
  })
  .catch(error => {
    console.error('Ocorreu um erro:', error);
  });

var data = {
  nome: "AAAAAAAAAAAAAAAAAA",
  professor: "LAULAULAU",
  codigo: "123",
  horario: []
};

fetch('/gradesFiltradas/endpoint', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(data)
})
.then(response => response.json())
.then(data => {
  console.log(data); // Lidar com a resposta do servidor
})
.catch(error => {
  console.error('Erro:', error); // Trata erros, se necessário
});

document.addEventListener("DOMContentLoaded", ()=>{
    const botao = document.getElementById("meuBotao");
    botao.addEventListener("click", function() {
      adicionarDado("EDA <br> Rose", "1", "1");
      adicionarDado("FGA - 168 <br> Rose", "2", "2");
      adicionarDado("ESTRUTURA DE DADOS 2 <br> FULANO DE TAL CICLANO", "3", "3");
    });
    const botao2 = document.getElementById("limpar");
    botao2.addEventListener("click", limparDados);
  });










function limparDados(){
    const celulasComIds = document.querySelectorAll("td");
    celulasComIds.forEach((celula) => {if(celula.id.match(/^[1-8]/)) celula.innerHTML = "";});
}

function adicionarDado(dado,linha,coluna){
    document.getElementById(linha + coluna).innerHTML = dado;
}

function adicionarMateria(ArrayGrade, index){
    const [dado, linha, coluna] = ArrayGrade[index].split("|");
    adicionarDado(dado, linha, coluna);
}

function gerarGrades(ArrayGrade){
    limparDados()
    for(let i=0; i < ArrayGrade.length; i++) adicionarMateria(ArrayGrade, i);
}