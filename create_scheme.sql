drop table testedoping;
drop table lesao;
drop table diagnostico_tratamento;
drop table diagnostico;
drop table consulta_sintomas;
drop table consulta;
drop table tratamento;
drop table medico_telefones;
drop table medico;
drop table atleta_rotina;
drop table rotina_recuperacao;
drop table recuperacao;
drop table rotina_preparacao;
drop table preparacao;
drop table rotina;
drop table atleta_participa;
drop table atleta;
drop table equipe;
drop table preparador_telefones;
drop table preparador;
drop table nacao;
drop table esporte;

create table esporte (
    esporte_id NUMBER not null,
    nome VARCHAR2(30) not null,
    descricao varchar2(200) not null,
    
    constraint pk_esporte primary key (esporte_id),
    constraint uq_esporte_nome unique (nome)
);

create table nacao (
    nacao_id NUMBER not null,
    nome VARCHAR2(30) NOT NULL,
    continente varchar2(16) NOT NULL,
    bandeira char(4), -- identificador de um arquivo .jpg no formato '3a6f' (hexadecimal)
    hino clob,
    esporte_preferido NUMBER NOT NULL,
    
    constraint pk_nacao primary key (nacao_id),
    constraint uq_nacao_nome unique (nome),
    constraint ck_nacao_continente
        check (continente in ('América do Sul', 'América do Norte', 'Europa', 'Ásia', 'Oceania', 'Antártida')),
    constraint fk_nacao_esporte
        foreign key (esporte_preferido) references esporte (esporte_id)
);

create table preparador (
    -- decisao de projeto: nao garante participacao total com equipe
    preparador_id NUMBER not null, -- cpf ou passaporte
    nome VARCHAR2(50) not null,
    sexo varchar2(6) not null,
    nascimento DATE not null,
    nacao_id NUMBER not null,
    iscpf char(1) default 'S' not null, -- atualizada por trigger, indica se registro e cpf, senao, e passaporte
    
    codigo_postal varchar2(10),
    email varchar2(254),
    senha varchar2 (30) not null,
    
    constraint pk_preparador primary key (preparador_id),
    constraint fk_preparador_nacao
        foreign key (nacao_id) references nacao (nacao_id),
    constraint ck_preparador_sexo CHECK (sexo in ('Homem', 'Mulher')),
    constraint ck_preparador_registro CHECK (preparador_id > 0),
    constraint ck_preparador_iscpf CHECK (iscpf in ('S', 'N')),
    
    constraint uq_preparador_email unique (email)
);

create table preparador_telefones (
    preparador_id NUMBER not null,
    telefone NUMBER not null,
    
    constraint pk_preparadortelefones primary key (preparador_id, telefone), -- telefone nao eh unico
    constraint fk_preptelefones_preparador
        foreign key (preparador_id) references preparador (preparador_id),
    constraint ck_preptelefones_telefone CHECK (telefone > 0)
);

create table equipe (
    equipe_id NUMBER not null, -- id da equipe e do preparador sao o mesmo
    esporte_id NUMBER not null,
    
    constraint pk_equipe primary key (equipe_id),
    constraint fk_equipe_esporte
        foreign key (esporte_id) references esporte(esporte_id),
    constraint fk_equipe_preparador foreign key (equipe_id) references preparador (preparador_id)
);

create table atleta (
    -- decisão projeto:
    --      ao inves de armazenar um isImpedido, armazenamos ultima_punicao
    --      isto porque isimpedido depende da data de hoje e teria que ser atualizado diariamente
    
    atleta_id NUMBER not null, -- cpf ou passaporte
    nome VARCHAR2(50) not null,
    sexo varchar2(6) not null,
    nascimento DATE not null,
    nacao_id NUMBER not null,
    iscpf char(1) default 'S' not null, -- atualizada por trigger, indica se registro e cpf, senao, e passaporte
    
    altura number(3) not null,
    peso number(3) not null,
    qpunicoes number(1) default 0 not null, -- atualizado por trigger

    constraint pk_atleta primary key (atleta_id),
    constraint fk_atleta_nacao
        foreign key (nacao_id) references nacao (nacao_id),
    constraint ck_atleta_sexo CHECK (sexo in ('Homem', 'Mulher')),
    constraint ck_atleta_registro CHECK (atleta_id > 0),
    constraint ck_atleta_iscpf CHECK (iscpf in ('S', 'N')),
    
    constraint ck_atleta_altura CHECK (altura > 0),
    constraint ck_atleta_peso CHECK (peso > 0),
    constraint ck_atleta_isimpedido CHECK (isimpedido in ('S', 'N'))
);

create table atleta_participa (
    atleta_id NUMBER not null,
    equipe_id NUMBER not null,
    
    constraint pk_atletaparticipa primary key (atleta_id, equipe_id),
    constraint fk_atletaparticipa_atleta foreign key (atleta_id) references atleta (atleta_id),
    constraint fk_atletaparticipa_equipe foreign key (equipe_id) references equipe (equipe_id)
    -- omitida fk para preparador
);

create table rotina (
    -- decisao projeto: nao garante participacao total em preparacao, nem em recuperacao
    rotina_id number not null,
    descricao varchar2(100) not null,
    qrepeticoes number(4) not null, -- 9999 tem que dar.
    diasemana char(7) default 'NNNNNNN' not null, -- 7 dias, N para nao, S, para sim
    preparador_id number not null,
    
    constraint pk_rotina primary key (rotina_id),
    constraint fk_rotina_preparador
        foreign key (preparador_id) references preparador (preparador_id),
    constraint ck_rotina_qrepeticoes CHECK (qrepeticoes > 0)
);

create table preparacao (
    preparacao_id number not null,
    descricao varchar2(100) not null,
    
    constraint pk_preparacao primary key (preparacao_id)
);

create table rotina_preparacao (
    rotina_id number not null,
    preparacao_id number not null,
    
    constraint pk_rotinapreparacao primary key (rotina_id, preparacao_id),
    constraint fk_rotinapreparacao_rotina
        foreign key (rotina_id) references rotina (rotina_id),
    constraint fk_rotinapreparacao_preparacao
        foreign key (preparacao_id) references preparacao (preparacao_id)
);

create table recuperacao (
    recuperacao_id number not null,
    descricao varchar2(100) not null,
    
    constraint pk_recuperacao primary key (recuperacao_id)
);

create table rotina_recuperacao (
    rotina_id number not null,
    recuperacao_id number not null,
    
    constraint pk_rotinarecuperacao primary key (rotina_id, recuperacao_id),
    constraint fk_rotinarecup_rotina
        foreign key (rotina_id) references rotina (rotina_id),
    constraint fk_rotinarecup_recuperacao
        foreign key (recuperacao_id) references recuperacao (recuperacao_id)
);

create table atleta_rotina (
    rotina_id number not null,
    atleta_id number not null,
    
    constraint pk_atletarotina primary key (rotina_id, atleta_id),
    constraint fk_atletarotina_rotina
        foreign key (rotina_id) references rotina (rotina_id),
    constraint fk_atletarotina_atleta
        foreign key (atleta_id) references atleta (atleta_id)
);

create table medico (
    medico_id number not null,
    nome varchar2 (30) not null,
    crm number not null,
    endereco varchar2 (200),
    
    constraint pk_medico primary key (medico_id),
    constraint ck_medico_registro CHECK (medico_id > 0),
    constraint ck_medico_crm CHECK (crm > 0),
    constraint uq_medico_crm UNIQUE (crm)
);

create table medico_telefones (
    medico_id number not null,
    telefone number not null,
    
    constraint pk_medicotelefones primary key (medico_id, telefone), -- telefone nao eh unico
    constraint fk_medicotelefones_medico foreign key (medico_id) references medico (medico_id),
    constraint ck_medicotelefones_telefone CHECK (telefone > 0)
);

create table tratamento (
    -- decisao de projeto: nao garante participacao total com diagnóstico
    tratamento_id number not null,
    metodo varchar2(30) not null,
    efetividade varchar2(30) not null,
    
    constraint pk_tratamento primary key (tratamento_id)
);

create table consulta (
    -- decisao projeto: nao garante participacao total em diagnostico
    -- todo: tem certeza?
    consulta_id number not null,
    medico_id number not null,
    atleta_id number not null,
    datahora date not null,
    
    constraint pk_consulta primary key (consulta_id), -- chave sintetica por motivos fisicos (nao semanticos)
    constraint uq_consulta_realkey unique (medico_id, atleta_id, datahora), -- a verdadeira chave de consulta
    constraint fk_consulta_medico foreign key (medico_id) references medico (medico_id),
    constraint fk_consulta_atleta foreign key (atleta_id) references atleta (atleta_id)
);

create table consulta_sintomas (
    consulta_id number not null,
    sintoma varchar2(30) not null,
    
    constraint pk_consultasintomas primary key (consulta_id, sintoma),
    constraint fk_consultasintomas_consulta foreign key (consulta_id) references consulta (consulta_id)
);

create table diagnostico (
    -- decisao de projeto: nao garante participacao total em tratamento
    diagnostico_id number not null, -- consulta_id = diagnostico_id
    descricao varchar2(100) not null,
    
    constraint pk_diagnostico primary key (diagnostico_id),
    constraint fk_diagnostico_consulta foreign key (diagnostico_id) references consulta (consulta_id)
        on delete cascade
);

create table diagnostico_tratamento (
    diagnostico_id number not null,
    tratamento_id number not null,
    
    constraint pk_diagnosticotratamento primary key (diagnostico_id, tratamento_id),
    constraint fk_diagtratamento_diagnostico
        foreign key (diagnostico_id) references diagnostico (diagnostico_id),
    constraint fk_diagtratamento_tratamento
        foreign key (tratamento_id) references tratamento (tratamento_id)
);

create table lesao (
    lesao_id number not null,
    atleta_id number not null,
    medico_id number not null,
    esporte_id number not null,
    datahora date not null,
    
    descricao varchar2 (1000) not null,
    
    constraint pk_lesao primary key (lesao_id),
    constraint uq_lesao_realkey unique (atleta_id, medico_id, esporte_id, datahora),
    constraint fk_lesao_atleta
        foreign key (atleta_id) references atleta (atleta_id),
    constraint fk_lesao_medico
        foreign key (medico_id) references medico (medico_id),
    constraint fk_lesao_esporte
        foreign key (esporte_id) references esporte (esporte_id)
);

create table testedoping (
    testedoping_id number not null,
    atleta_id number not null,
    medico_id number not null,
    esporte_id number not null,
    datahora date not null,
    
    ispositivo char(1) not null,
    resultado varchar2 (300) not null,
    
    constraint pk_testedoping primary key (testedoping_id),
    constraint uq_testedoping_realkey unique (atleta_id, medico_id, esporte_id, datahora),
    constraint fk_testedoping_atleta
        foreign key (atleta_id) references atleta (atleta_id),
    constraint fk_testedoping_medico
        foreign key (medico_id) references medico (medico_id),
    constraint fk_testedoping_esporte
        foreign key (esporte_id) references esporte (esporte_id),
    
    constraint ck_testedoping_ispositivo CHECK (ispositivo in ('S', 'N'))
);
