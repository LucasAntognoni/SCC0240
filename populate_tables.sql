delete from testedoping;
delete from lesao;
delete from diagnostico_tratamento;
delete from diagnostico;
delete from consulta_sintomas;
delete from consulta;
delete from tratamento;
delete from medico_telefones;
delete from medico;
delete from atleta_rotina;
delete from rotina_recuperacao;
delete from recuperacao;
delete from rotina_preparacao;
delete from preparacao;
delete from rotina;
delete from atleta_participa;
delete from atleta;
delete from equipe;
delete from preparador_telefones;
delete from preparador;
delete from nacao;
delete from esporte;

-- table lesao
-- table ocorrencia
-- table diagnostico_tratamento
-- table diagnostico
-- table consulta_sintomas
-- table consulta
-- table tratamento
-- table medico_telefones
-- table atleta_rotina
-- table rotina_recuperacao
-- table recuperacao
-- table rotina_preparacao
-- table preparacao
-- table rotina


-- table esporte (esporte_id, nome, descricao)
insert into esporte (esporte_id, nome, descricao)
values (1, 'Futebol', 'dois times rivais de 11 jogadores cada (um deles goleiro) tentam marcar gols no adversário.');

-- table nacao (nacao_id, nome, continente, [bandeira, hino,] esporte_preferido)
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (1,'Brasil','América do Sul',1);
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (2,'Alemanha','Europa',1);

-- table atleta (atleta_id, nome, sexo, nascimento, nacao_id, [especializacao, iscpf], altura, peso, [qpunicoes, isimpedido])
insert into atleta (atleta_id, nome, sexo, nascimento, nacao_id, altura, peso)
values (1, 'Eduardo', 'Homem', TO_DATE('19/07/1994', 'dd/mm/yyyy'), 1, 186, 83);

-- table medico (medico_id, nome, crm, [endereco])
insert into medico (medico_id, nome, crm, endereco)
values (1, 'Jorge Pereira', 1234567890, 'Rua X, 327 - São Paulo');

-- table testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (1, 1, 1, 1, TO_DATE('05/11/2013', 'dd/mm/yyyy'), 'S', 'Uso de anabolizantes detectado.');
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (2, 1, 1, 1, TO_DATE('01/05/2017', 'dd/mm/yyyy'), 'N', 'Nenhuma irregularidade.');
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (3, 1, 1, 1, TO_DATE('11/05/2017', 'dd/mm/yyyy'), 'S', 'Uso de anabolizantes detectado.');

-- table ocorrencia

-- table preparador
-- table preparador_telefones
-- table equipe

-- table atleta_participa