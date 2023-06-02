# Movies

## Sobre

Aplicação utilizando uma API do TheMoviesDB, para gerar uma tela com uma lista de filmes na
categoria popula
e uma segunda tela com detalhes do respectivo filme.

## Objetivo

- Consumo de uma API;
- Lista de objetos de um endpoint;
- Mostrar esses dados em uma RecyclerView;
- Montar a estrutura do App com a arch Clean + Mvvm;
- Mostrar dados em uma outra tela com detalhes do item selecionado na lista da tela inicial
- passar argumentos entre os fragments

## Ferramentas utilizadas

- Kotlin
- Mvvm + Clean Architecture
- RecyclerView
- API - https://developer.themoviedb.org/reference/movie-details
  e https://developer.themoviedb.org/reference/person-popular-list
- DataBinding
- Retrofit
- Glide
- Gson
- Koin
- Testes
- Coroutines
- Navigation
- Fragments

## Features

Esta Aplicação possui 2 telas
a tela inicial com um carossel mostrando a lista da requisição do filmes populares
e uma tela que que trás o banner do filme com uma descrição.
 
## Funcionamento
![Filmes Tela 01](https://github.com/sabinabernardes/Movies/assets/66752747/99fa7e8a-cd08-408f-acdb-627c3ae0cb47)
![Filmes tela 02](https://github.com/sabinabernardes/Movies/assets/66752747/95050e74-cf7b-43a7-b8fa-aad1589dfe84)
![Captura de Tela 2023-06-01 às 16 13 08](https://github.com/sabinabernardes/Movies/assets/66752747/dbef6abe-3224-43ea-9370-735c6a3b5907)


https://github.com/sabinabernardes/Movies/assets/66752747/777db3d8-4823-4e99-9dc5-72b122c5c49d

## Pacotes

O projeto se encontra organizado como mostrado na figura abaixo.
Em uma arquitetura mvvm+clean e cada feature no seu package

- Data
- Domain
- Presentation
- Common
- Di
- ![Captura de Tela 2023-06-01 às 16 28 15](https://github.com/sabinabernardes/Movies/assets/66752747/0f160240-3e02-45b9-8d0e-1c4132a4af86)

## Injeção de dependência

Neste projeto é utilizado o koin para fazer as chamadas dos modulos

Camada de application

![Captura de Tela 2023-06-02 às 08 35 23](https://github.com/sabinabernardes/Movies/assets/66752747/586ab88b-4bb6-41f9-8d07-24bc6ee1e8ee)

Modulo da Feature

![Captura de Tela 2023-06-02 às 08 35 37](https://github.com/sabinabernardes/Movies/assets/66752747/99d23be2-a2c1-48d2-bf5e-dc71fdfd366d)

## Testes

A Figura abaixo mostra a organização dos packages dos testes unitários

![Captura de Tela 2023-06-02 às 08 48 00](https://github.com/sabinabernardes/Movies/assets/66752747/9370ac54-df38-4dc7-9357-b0cc01e4ec3d)

## Gráfico de Navegação

Neste projeto tambem foi utilizado a navegação entre as telas pelo navigation.

Abaixo a imagem do gráfico de navegação

![Captura de Tela 2023-06-02 às 08 45 35](https://github.com/sabinabernardes/Movies/assets/66752747/f65ce07c-25be-4ebe-92a2-58838570aaf8)

## Proxímos passos

- Criar mais testes instrumentados
- Colocar outras listagem na tela inicial para dar mais opções aos usuários
  com as suas respectivas paginações
- modularização po features
- uma barra de pesquisa por filmes
- Mapper dos objetos de resposta para o objeto de domain
- Telas de detalhes com componentes customizados 


