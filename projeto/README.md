# Diagrama de caso de uso

![](Diagrama%20de%20caso%20de%20uso.png)

# Histórias de Usuário

* US01: Como Aluno gostaria de fazer cadastro para usar o sistema.
* US02 :Como Aluno gostaria de usar o meu saldo disponível para resgatar vantagens.
* US03 :Como Aluno gostaria de consultar meu extrato para saber meus gastos e saldo.
* US04 :Como Professor gostaria de consultar meu extrato para saber meus gastos e saldo.
* US05 :Como Aluno gostaria de consultar minhas transações para saber meus histórico.
* US06 :Como Professor gostaria de consultar minhas transações para saber meus histórico.
* US07 :Como Professor gostaria de enviar moedas para meus alunos
* US08: Como Empresa gostaria de fazer cadastro para usar o sistema.
* US09: Como Empresa gostaria de cadastrar as vantagens para os alunos usarem.
* US10 :Como usário gostaria de fazer login para usar o sistema

# Diagrama de classe

![](Diagrama%20de%20classe.png)

# Mapeamento Entidade e Relacionamento

**Endereco**(@id, rua, bairro, numero, cep, <span style="text-decoration: underline;">idUsuario</span>)

**Usuario**(@id, nome, email, senha, cpf, rg, cnpj, departamento, tipo, <span style="text-decoration: underline;">idInstituicao</span>)

**Transacao**(@id, valor, descricao, <span style="text-decoration: underline;">idContaOrigem</span>, <span style="text-decoration: underline;">idContaDestino</span>)

**Conta**(@id, saldo, <span style="text-decoration: underline;">idUsuario</span>)

**Vantagem**(@id, produto, valor, descricao, <span style="text-decoration: underline;">idEmpresa</span>)

**Compra**(@id, valorTotal, <span style="text-decoration: underline;">idAluno</span>)

**ItemCompra**(@id, <span style="text-decoration: underline;">idCompra</span>, <span style="text-decoration: underline;">idVantagem</span>)

**Instituicao**(@id, nome)

# Diagrama de Componente

![](Diagrama%20de%20componente.png)

# Diagrama de Implantação

![](Diagrama%20de%20implantação.png)

# Diagramas de Sequencia do Sistema

## UC01

![](UC01.png)

## UC02

![](UC02.png)

## UC03

![](UC03.png)

## UC04/UC05

![](UC04_UC05.png)

## UC06

![](UC06.png)

## UC07

![](UC07.png)
