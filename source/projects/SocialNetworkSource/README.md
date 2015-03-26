# SocialNetworkSource


Projeto que implementa diferentes Sources do Apache Flume, com objetivo de coletar postagens de redes sociais. Atualmente estão disponíveis três tipos de Sources:

* FacebookSource: Utiliza a Graph API do Facebook para pesquisar feeds em páginas específicas
* TwitterRestSource: Utiliza a API Rest do Twitter para obter tweets publicados.
* TwitterStreamSource: Utiliza a Stream API do Twitter para obter tweets em tempo real.


## Dependências


Este software foi construído utilizando o projeto NaiveBayes e as seguintes bibliotecas:

* Apache Flume 1.5.2
* Twitter4j
* Facebook4j
