console.log("Server.ts OK");

const btnGerarGrade = document.getElementById("btnGerarGrade");
const gradeContainer = document.getElementById("grade").style;
const inputsContainer = document.getElementById("inputs").style;
btnGerarGrade.addEventListener("click", ()=>{
   gradeVisualizada = 0;
   btnAtualizar.click();
   gradeContainer.display="block";
   inputsContainer.display="none";
});
//---------------------------------ATUALIZAR----------------------------------
const btnAtualizar = document.getElementById("btnAtualizar");
let gradeVisualizada = 0;
let materiasColoridas = [];
btnAtualizar.addEventListener("click", () => {
   fetch("/gradesFiltradas")
       .then(response => response.json())
       .then(data =>{
          if(materiasColoridas.length === 0) materiasColoridas = vincularCorMateria(data[0].materias);
          atualizarGrade(data, gradeVisualizada);
          gradeVisualizada = (gradeVisualizada + 1) % data.length;
       }).catch(error => erro(error));
});
//-----------------------------------SAIR------------------------------------
const btnSair = document.getElementById("btnSair");
btnSair.addEventListener("click", ()=>{
   console.log("saindo das grades")
   gradeContainer.display="none";
   inputsContainer.display="block";
});
//---------------------------------FUNÇOES----------------------------------
/**
 * Esse método é responsável por adicionar uma grade na tabela através do seu horário..
 * @param data Grade a ser adicionada.
 * @param index Index da grade mostrada na tabela.
 */
function atualizarGrade(data, index){
   limparDados();
   data[index].materias.forEach(materia => {
      adicionarMateria(materia);
   });
}
/**
 * Adiciona uma matéria na grade e define a cor.
 * @param materia Matéria a ser adicionada na grade.
 */
function adicionarMateria(materia){
   materia.horario.forEach(horarios =>{
      horarios.dia.forEach(dias =>{
         horarios.hora.forEach(horas =>{
            const id = horarios.turno + horas + dias;
            const dado = `${materia.nome}`; //<br>${materia.professor}
            adicionarDado(dado, id, pegarCorMateria(materia));
         });
      });
   });
}

/**
 * Retorna a cor da matéria.
 * @param materia Matéria a ser verificada a cor.
 */
function pegarCorMateria(materia){
   const moldeEncontrado = materiasColoridas.find(molde => molde.nome === materia.nome);
   if(moldeEncontrado) return moldeEncontrado.cor;
   return "";
}
/**
 * Retorna uma lista de jsons com nome de materias e uma cor aleatória.
 * @param listaMaterias Lista de materias a serem copiadas.
 */
function vincularCorMateria(listaMaterias){
   return listaMaterias.map(materia => ({
      nome: materia.nome,
      cor: gerarCor()
   }));
}
/**
 * Retorna uma cor pastel aleatória e que não tenha sido usada.
 */
function gerarCor(){
   const vermelho = Math.floor(Math.random() * (106)) + 150;
   const verde = Math.floor(Math.random() * (151)) + 150;
   const azul = Math.floor(Math.random() * (151)) + 150;

   const cor =`rgb(${vermelho}, ${verde}, ${azul})`;
   const corIgual = materiasColoridas.find(molde => molde.cor === cor);

   if(corIgual) return gerarCor();
   return cor;
}
/**
 * Limpa todas as células q o id termina de 1 a 7.
 */
function limparDados(){
   const td = document.querySelectorAll("td");
   td.forEach((celula) => {
      const ultimoCaractere = celula.id.slice(-1);
      if (/[1-7]/.test(ultimoCaractere)){
         celula.innerHTML = "";
         celula.style.backgroundColor = "";
      }
   });
}
/**
 * Adiciona um dado na grade com base no id.
 * @param dado Dado a ser adicionado.
 * @param id Id da célula.
 */
function adicionarDado(dado, id, cor){
   const celula = document.getElementById(id);
   celula.innerHTML = dado;
   celula.style.backgroundColor = cor;
}
/**
 * Alerta o erro.
 * @param error Erro a ser alertado.
 */
function erro(error){
   console.error("erro", error);
}