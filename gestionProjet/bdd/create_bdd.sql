
--   type :      SQL Server 2012
--

CREATE TABLE CATEGORY (
    noCategory  INTEGER IDENTITY(1,1) NOT NULL,
    name        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORY ADD constraint categorie_pk PRIMARY KEY (noCategory)


CREATE TABLE RETIREMENT (
	noRetirement    INTEGER IDENTITY(1,1) NOT NULL,
    street          VARCHAR(30) NOT NULL,
    zipCode         VARCHAR(15) NOT NULL,
    city            VARCHAR(30) NOT NULL
)

ALTER TABLE RETIREMENT ADD constraint retrait_pk PRIMARY KEY  (noRetirement)

CREATE TABLE USERS (
    noUser        INTEGER IDENTITY(1,1) NOT NULL,
    surname       VARCHAR(30) NOT NULL,
    lastname      VARCHAR(30) NOT NULL,
    firstname     VARCHAR(30) NOT NULL,
    email         VARCHAR(20) NOT NULL,
    phone         VARCHAR(15),
    street        VARCHAR(30) NOT NULL,
    zipCode       VARCHAR(10) NOT NULL,
    city          VARCHAR(30) NOT NULL,
    password      VARCHAR(30) NOT NULL,
    credit        INTEGER NOT NULL,
    administrator bit NOT NULL
)

ALTER TABLE USERS ADD constraint user_pk PRIMARY KEY (noUser)


CREATE TABLE SOLD_ARTICLE (
    noArticle              INTEGER IDENTITY(1,1) NOT NULL,
    articleName            VARCHAR(30) NOT NULL,
    description            VARCHAR(300) NOT NULL,
	bidStartedDate         DATE NOT NULL,
    bidEndDate             DATE NOT NULL,
    bidStartPrice          INTEGER,
    soldPrice              INTEGER,
    noUser                 INTEGER NOT NULL,
    noCategory             INTEGER NOT NULL,
	noRetirement		   INTEGER NULL
)



ALTER TABLE SOLD_ARTICLE ADD constraint sold_article_pk PRIMARY KEY (noArticle)


CREATE TABLE BID (
	noBid           INTEGER IDENTITY(1,1) NOT NULL,
	bidDate         datetime NOT NULL,
	bidAmount       INTEGER NOT NULL,
	noArticle       INTEGER NOT NULL,
	noUser          INTEGER NOT NULL
 )

ALTER TABLE BID ADD constraint bid_pk PRIMARY KEY ( noBid )

ALTER TABLE BID
    ADD CONSTRAINT bid_user_fk FOREIGN KEY ( noUser ) REFERENCES USERS ( noUser )
ON DELETE NO ACTION
    ON UPDATE no action

ALTER TABLE BID
    ADD CONSTRAINT bid_no_article_fk FOREIGN KEY ( noArticle ) REFERENCES SOLD_ARTICLE ( noArticle )
ON DELETE NO ACTION
    ON UPDATE no action


ALTER TABLE SOLD_ARTICLE
    ADD CONSTRAINT sold_article_retirement_fk FOREIGN KEY ( noRetirement )
        REFERENCES RETIREMENT ( noRetirement )
ON DELETE no action
    ON UPDATE no action

ALTER TABLE SOLD_ARTICLE
    ADD CONSTRAINT sold_article_category_fk FOREIGN KEY ( noCategory )
        REFERENCES CATEGORY ( noCategory )
ON DELETE NO ACTION
    ON UPDATE no action

ALTER TABLE SOLD_ARTICLE
    ADD CONSTRAINT sold_article_user_fk FOREIGN KEY ( noUser )
        REFERENCES USERS ( noUser )
ON DELETE NO ACTION
    ON UPDATE no action

ALTER TABLE USERS
	ADD CONSTRAINT UNIQUE_MAIL UNIQUE (email);

ALTER TABLE USERS
	ADD CONSTRAINT UNIQUE_SURNAME UNIQUE (surname);
