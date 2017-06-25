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
-- table medico_telefones
-- table atleta_rotina
-- table rotina_recuperacao
-- table recuperacao
-- table rotina_preparacao
-- table preparacao
-- table rotina
-- table preparador_telefones


-- table esporte (esporte_id, nome, descricao)
insert into esporte (esporte_id, nome, descricao)
values (1, 'Futebol', 'dois times rivais de 11 jogadores cada (um deles goleiro) tentam marcar gols no adversário.');

-- table nacao (nacao_id, nome, continente, [bandeira, hino,] esporte_preferido)
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (1,'Brasil','América do Sul',1);
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (2,'Alemanha','Europa',1);

-- table atleta (atleta_id, nome, sexo, nascimento, nacao_id, [iscpf], altura, peso, [qpunicoes, isimpedido])
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

-- table preparador (preparador_id, nome, sexo, nascimento, nacao_id, [iscpf, codigo_postal, email], senha)
insert into preparador (preparador_id, nome, sexo, nascimento, nacao_id, senha)
values (1, 'Gustavo Batista', 'Homem', TO_DATE('21/03/1981', 'dd/mm/yyyy'), 1, 'aBc123');

-- table consulta (consulta_id, medico_id, atleta_id, datahora)
insert into consulta (consulta_id, medico_id, atleta_id, datahora)
values (1, 1, 1, TO_DATE('12/06/2015', 'dd/mm/yyyy'));

-- table consulta_sintomas (consulta_id, sintoma)
insert into consulta_sintomas (consulta_id, sintoma)
values (1, 'Dor de cabeça');

-- table diagnostico (diagnostico_id, descricao)
insert into diagnostico (diagnostico_id, descricao)
values (1, 'Estresse decorrente a rotina.');

-- table tratamento (tratamento_id, metodo, efetividade)
insert into tratamento (tratamento_id, metodo, efetividade)
values (1, 'Neosaldina 3x ao dia.', 80);

-- table diagnostico_tratamento (diagnostico_id, tratamento_id)
insert into diagnostico_tratamento (diagnostico_id, tratamento_id)
values (1, 1);

-- table equipe (equipe_id, esporte_id)
insert into equipe (equipe_id, esporte_id)
values (1, 1);

-- table atleta_participa (atleta_id, equipe_id)
insert into atleta_participa (atleta_id, equipe_id)
values (1, 1);