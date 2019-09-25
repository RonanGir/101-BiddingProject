# 101-BiddingProject
================================================================================

## le projet (Annotations):
- sites enchères qui encourage plusieurs mode d'échanges (troc, achat simple, don)...

--------------------------------------------------------------------------------
## Règles de nommages
  - Full Anglais

  - Archictecture et conventions de nommages :

```
+ src :
  - AppException
  + package servlet
      - xxxServlet.java
      - ErrorCodeServlet
  + package DAL :
      - xxxDAO(i)
      - xxxDAOJdbcImpl(j)
      - DAOFactory(j)
      - xxxDAOMOC(j)
      - ConnectionProvider
      - ErrorCodeDAO
  + package BO
      - User
      - SoldArticle
      - Bid (enchere)
      - Retirement (retrait)
      - Category
  + package BLL
      - xxxManager(i)
      - xxxManagerImpl(j)
      - xxxFactory(j)
      - ErrorCodeBLL
  + package messages
      - MessageProvider
      - messages.properties (10000 = dal, 20000 = bll, 30000 = ihm)
+ WebContent
  + assets :
      + css
          - components (button.css / header.css)
          - style.css
      + js
          - 1 fichier par functions
          - main.js
      + img

  + Web-inf
    + lib
    + jsp
        + fragments
            - header.jsp
        - jspFile.jsp
```

  - variables / attributs
      * variable classique => `maVariable`
      * liste => `listArticle`
      * tableau => `tabArticle`

  - Méthodes (verbObject)
      - DAO :
          * selectAllArticle
          * selectArticleById
          * updateArticleById
          * deleteArticleById
      - BLL :
          * getArticles
          * getArticle
          * modifyArticle
          * destroyArticle

--------------------------------------------------------------------------------
## Les features
  1. Users
      - Connection
          * As a User, i can log in
          * As a User, i can log out
          * As a User, i can register
          * As a User, i can update my profile
          * As a User, i can delete my profile
          * As a User, i can forget my password
          * As a User, i can see my profile

  2. Articles
      - As a logged out User :
          * i can see bids in progress (article)
          * i can search articles by article name
          * i can filter articles by category

      - As a logged in User :
          * i can filter bids by open bids, my bids, won bids
          * i can list my sales in progress
          * i can list my sales not started
          * i can list my sold articles
          * i can sell an article
          * i can see the seller profile
          * i can bid on an article
          * i can buy credits
          * i can won a bid
