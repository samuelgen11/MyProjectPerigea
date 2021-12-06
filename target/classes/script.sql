-- Table: public.padrone

-- DROP TABLE public.padrone;

create table processo (
	uuid_processo int primary key,
	data_ora timestamp,
	status	varchar(30)
);

create table esecuzione (

	id_esecuzione int primary key,
	tempo bigint,
	esito varchar(30),
	uuid_proc int,
	foreign key (uuid_proc) references processo (uuid_processo)
);


create table abbreviations (
	codice int primary key,
	nome varchar(30),
	sigla varchar(30),
	regione varchar(30)
);
