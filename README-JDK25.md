# Painel de Senhas - versão corrigida para JDK 25

## Correções desta versão

### 1. Compatibilidade com JDK 25
- Spring Boot atualizado para 3.5.16.
- `java.version` configurado como `25`.

### 2. Erro `NoResourceFoundException: No static resource atendente/gerar.`
O formulário da página `atendente.html` enviava:

`POST /atendente/gerar`

Porém o `AtendenteController` não possuía esse endpoint. Os endpoints existentes eram:

- `POST /atendente/gerar/normal`
- `POST /atendente/gerar/prioritaria`

Os formulários foram corrigidos para apontar para os endpoints existentes.

### 3. Variáveis Thymeleaf
Os controllers enviavam `senhaAtual` e `historico`, enquanto algumas partes das páginas esperavam `currentDisplay` e `history`. Os nomes foram padronizados.

### 4. API do painel
O JavaScript do `display.html` consultava `/api/senha`, mas a API estava em `/api/senhas`. Além disso, os nomes esperados pelo JavaScript não correspondiam ao DTO.

Foi criado `GET /api/senhas`, que retorna:

- `senhaAtual`
- `historico`

O painel agora consulta esse endpoint a cada 2 segundos.

## Execução

Com JDK 25 configurado:

```bash
mvn clean spring-boot:run
```

Ou, no IntelliJ, execute a classe:

`com.painelsenhas.PainelSenhasApplication`

Depois acesse:

- `http://localhost:8080/`
- `http://localhost:8080/atendente`
- `http://localhost:8080/display`

## Padrões mantidos

O projeto continua utilizando:

- Singleton: `QueueService`
- Factory Method: `SenhaCreator`
- Polimorfismo: `SenhaNormal` e `SenhaPrioritaria`
- Spring MVC / REST
- Thymeleaf
- Armazenamento em memória
