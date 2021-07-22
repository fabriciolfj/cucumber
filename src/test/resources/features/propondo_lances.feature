# language: pt
Funcionalidade: Propondo lances

Cenario: Propondo um unico lance valido
 Dado um lance valido
 Quando propoe ao leilao
 Entao o lance e aceito

Cenario: Propondo varios lances validos
 Dado varios lances
 Quando propoe varios lances ao leilao
 Entao o maior lance e aceito