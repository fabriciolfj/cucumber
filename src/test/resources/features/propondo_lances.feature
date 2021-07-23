# language: pt
Funcionalidade: Propondo lances

Cenario: Propondo um unico lance valido
 Dado um lance valido
 Quando propoe ao leilao
 Entao o lance e aceito

Cenario: Propondo varios lances validos
 Dado um lance de 10.0 reais do usuario "fulano"
 E um lance de 15.0 reais do usuario "beltrano"
 Quando propoe varios lances ao leilao
 Entao o maior lance e aceito

Esquema do Cenario: Propondo lance invalido
 Dado um lance invalido de <valor> reais do usuario '<nomeUsuario>'
 Quando propoe ao leilao
 Entao o lance nao e aceito

Exemplos:
 | valor | nomeUsuario   |
 | 0     |  fulano       |
 | -1    |  cigano       |

Cenario: Propondo uma sequencia de lances
 Dado dois lances
  | valor | nomeUsuario   |
  | 10    |  fulano       |
  | 5     |  cigano       |
 Quando propoe varios lances ao leilao
 Entao segundo lance nao sera aceito