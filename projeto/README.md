# Diagrama de caso de uso

![](UseCase%20DiagramLab3.png)

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

**Endereco**(@id, rua, bairro, numero, cep, <u>idUsuario</u>)

**Usuario**(@id, nome, email, senha, cpf, rg, cnpj, departamento, tipo, <u>idInstituicao</u>)

**Transacao**(@id, valor, descricao, <u>idContaOrigem</u>, <u>idContaDestino</u>)

**Conta**(@id, saldo, <u>idUsuario</u>)

**Vantagem**(@id, produto, valor, descricao, <u>idEmpresa</u>)

**Compra**(@id, valorTotal, <u>idAluno</u>)

**ItemCompra**(@id, <u>idCompra</u>, <u>idVantagem</u>)

**Instituicao**(@id, nome)

# Diagrama de Componente

![](Diagrama%20de%20componente.png)

# Diagrama de Implantação

![](Diagrama%20de%20implantação.png)
