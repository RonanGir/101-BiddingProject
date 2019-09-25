
--   type :      SQLITE
--

CREATE TABLE USERS (
    noUser        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    surname       VARCHAR(30) UNIQUE NOT NULL,
    lastname      VARCHAR(30) NOT NULL,
    firstname     VARCHAR(30) NOT NULL,
    email         VARCHAR(20) UNIQUE NOT NULL,
    phone         VARCHAR(15),
    street        VARCHAR(30) NOT NULL,
    zipCode       VARCHAR(10) NOT NULL,
    city          VARCHAR(30) NOT NULL,
    password      VARCHAR(30) NOT NULL,
    credit        INTEGER NOT NULL,
    administrator INTEGER NOT NULL
);

CREATE TABLE CATEGORY (
    noCategory  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name        VARCHAR(30) NOT NULL
);

CREATE TABLE RETIREMENT (
	  noRetirement    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    street          VARCHAR(30) NOT NULL,
    zipCode         VARCHAR(15) NOT NULL,
    city            VARCHAR(30) NOT NULL
);

CREATE TABLE SOLD_ARTICLE (
    noArticle              INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    articleName            VARCHAR(30) NOT NULL,
    description            VARCHAR(300) NOT NULL,
	  bidStartedDate         DATE NOT NULL,
    bidEndDate             DATE NOT NULL,
    bidStartPrice          INTEGER,
    soldPrice              INTEGER,
    noUser                 INTEGER NOT NULL,
    noCategory             INTEGER NOT NULL,
	  noRetirement		       INTEGER NULL,
    FOREIGN KEY (noRetirement) REFERENCES USERS (noRetirement),
    FOREIGN KEY (noCategory) REFERENCES USERS (noCategory),
    FOREIGN KEY (noUser) REFERENCES USERS (noUser)
);


CREATE TABLE BID (
	  noBid           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	  bidDate         datetime NOT NULL,
	  bidAmount       INTEGER NOT NULL,
	  noArticle       INTEGER NOT NULL,
	  noUser          INTEGER NOT NULL,
    FOREIGN KEY (noUser) REFERENCES USERS (noUser),
    FOREIGN KEY (noArticle) REFERENCES USERS (noArticle)
 );


-- ALTER TABLE CATEGORY ADD constraint categorie_pk PRIMARY KEY (noCategory)

-- ALTER TABLE RETIREMENT ADD constraint retrait_pk PRIMARY KEY  (noRetirement)

-- ALTER TABLE USERS ADD constraint user_pk PRIMARY KEY (noUser)

-- ALTER TABLE SOLD_ARTICLE ADD constraint sold_article_pk PRIMARY KEY (noArticle)

-- ALTER TABLE BID ADD constraint bid_pk PRIMARY KEY ( noBid )

-- ALTER TABLE BID
--     ADD FOREIGN KEY (noUser) REFERENCES USERS (noUser);

-- ALTER TABLE BID
--     ADD FOREIGN KEY (noArticle) REFERENCES USERS (noArticle);

-- ALTER TABLE SOLD_ARTICLE
--     ADD FOREIGN KEY (noRetirement) REFERENCES USERS (noRetirement);

-- ALTER TABLE SOLD_ARTICLE
--     ADD FOREIGN KEY (noCategory) REFERENCES USERS (noCategory);

-- ALTER TABLE SOLD_ARTICLE
--     ADD FOREIGN KEY (noUser) REFERENCES USERS (noUser);

-- ALTER TABLE USERS
-- 	ADD CONSTRAINT UNIQUE_MAIL UNIQUE (email);

-- ALTER TABLE USERS
-- 	ADD CONSTRAINT UNIQUE_SURNAME UNIQUE (surname);
