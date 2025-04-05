# 🧪 Projeto de Testes Automatizados com Mockito - Horário de Atendimento

Este projeto Java simula a verificação de dados de horários de atendimento de professores, utilizando testes automatizados com **JUnit 5** e **Mockito**. 
O foco é testar o processamento de um JSON retornado por um serviço remoto (`ServicoRemoto`), validando os campos e determinando corretamente o prédio da sala.

## 🧪 Executando os Testes com Maven

Abaixo estão os comandos principais para rodar os testes do projeto utilizando Maven

### ✅ Comandos Básicos

- `mvn test`  
  Executa todos os testes da aplicação

- `mvn clean test`  
  Limpa o diretório `target` e executa os testes novamente

- `mvn verify`  
  Executa todas as fases até `verify`, incluindo os testes e validacoes

---

### 🎯 Executar Testes Específicos

- `mvn -Dtest=NomeDaClasseTest test`  
  Executa uma classe de teste específica.

- `mvn -Dtest=NomeDaClasseTest#nomeDoMetodo test`  
  Executa apenas um método de teste específico dentro de uma classe

---

### 📊 Gerar Relatórios

- `mvn surefire-report:report`  
  Gera um relatório em HTML com os resultados dos testes (padrão Surefire)

---

### 🛠 Dicas para Debug/Testes com Detalhes

- `mvn test -X`  
  Executa os testes com saída detalhada (modo debug)

- `mvn test -e`  
  Mostra rastreamento completo de exceções, útil para erros

---

> 💡 **Importante**: Os testes devem estar organizados dentro de `src/test/java`, seguindo a estrutura padrão de projetos Maven

