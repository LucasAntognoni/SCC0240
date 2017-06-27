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

-- table esporte (esporte_id, nome, descricao)
insert into esporte (esporte_id, nome, descricao)
values (1, 'Futebol', 'Dois times rivais de 11 jogadores cada (um deles goleiro) tentam marcar gols no adversário.');
insert into esporte (esporte_id, nome, descricao)
values (2, 'Natação', 'Vários competidores nadão para chegar antes ao fim da prova.');
insert into esporte (esporte_id, nome, descricao)
values (3, 'Tênis de mesa', 'Dois jogadores, um em cada extremidade da mesa, utilizando uma raquete impedem que a bola caia e manda para o outro lado.');

-- table nacao (nacao_id, nome, continente, [bandeira, hino,] esporte_preferido)
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (1,'Brasil','América do Sul', 1);
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (2,'Alemanha','Europa', 1);
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (3,'Japão','Ásia', 3);
insert into nacao (nacao_id, nome, continente, esporte_preferido)
values (4,'Austrália','Oceania', 2);

-- table atleta (atleta_id, nome, sexo, nascimento, nacao_id, [iscpf], altura, peso, [qpunicoes, isimpedido])
insert into atleta (atleta_id, nome, sexo, nascimento, nacao_id, altura, peso)
values (1, 'Eduardo Pacheco', 'Homem', TO_DATE('19/07/1994', 'dd/mm/yyyy'), 4, 186, 83);
insert into atleta (atleta_id, nome, sexo, nascimento, nacao_id, altura, peso)
values (2, 'Lucas Antognoni', 'Homem', TO_DATE('23/09/1994', 'dd/mm/yyyy'), 1, 175, 82);
insert into atleta (atleta_id, nome, sexo, nascimento, nacao_id, altura, peso)
values (3, 'Danilo Zecchin Nery', 'Homem', TO_DATE('21/02/1994', 'dd/mm/yyyy'), 2, 184, 95);
insert into atleta (atleta_id, nome, sexo, nascimento, nacao_id, altura, peso)
values (4, 'Gustavo Aguiar', 'Homem', TO_DATE('13/10/1995', 'dd/mm/yyyy'), 2, 216, 75);

-- table medico (medico_id, nome, crm, [endereco])
insert into medico (medico_id, nome, crm, endereco)
values (1, 'Jorge Pereira', 1234567890, 'Rua X, 327 - São Paulo, Brasil');
insert into medico (medico_id, nome, crm, endereco)
values (2, 'Iuishu Nho', 9876543210, 'Rua Mitiaua, 162 - Tokyo, Japão');
insert into medico (medico_id, nome, crm, endereco)
values (3, 'Luke Manchester', 1463289907, 'Rua Mark, 185 - Camberra, Austrália');

-- table medico_telefones (medico_id, telefone)
insert into medico_telefones (medico_id, telefone)
values (1, 01137766192);
insert into medico_telefones (medico_id, telefone)
values (2, 81337766192);
insert into medico_telefones (medico_id, telefone)
values (3, 6123458923);


-- table testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (1, 4, 1, 1, TO_DATE('05/11/2013', 'dd/mm/yyyy'), 'S', 'Uso de anabolizantes detectado.');
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (2, 1, 1, 1, TO_DATE('01/05/2017', 'dd/mm/yyyy'), 'N', 'Nenhuma irregularidade.');
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (3, 2, 1, 1, TO_DATE('11/05/2017', 'dd/mm/yyyy'), 'S', 'Uso de anabolizantes detectado.');
insert into testedoping (testedoping_id, atleta_id, medico_id, esporte_id, datahora, ispositivo, resultado)
values (4, 2, 1, 1, TO_DATE('09/05/2016', 'dd/mm/yyyy'), 'S', 'Uso de anabolizantes detectado.');

-- table preparador (preparador_id, nome, sexo, nascimento, nacao_id, [iscpf, codigo_postal, email], senha)
insert into preparador (preparador_id, nome, sexo, nascimento, nacao_id, senha)
values (1, 'Gustavo Batista', 'Homem', TO_DATE('21/03/1981', 'dd/mm/yyyy'), 1, 'aBc123');
insert into preparador (preparador_id, nome, sexo, nascimento, nacao_id, senha)
values (2, 'Jamel Macciato', 'Homem', TO_DATE('21/03/1973', 'dd/mm/yyyy'), 4, 'aBc1234');
insert into preparador (preparador_id, nome, sexo, nascimento, nacao_id, senha)
values (3, 'Tereza Müller', 'Mulher', TO_DATE('21/03/1989', 'dd/mm/yyyy'), 2, 'aBc12345');

-- table preparador_telefones (preparador_id, telefone)
insert into preparador_telefones (preparador_id, telefone)
values (1, 01634716990);
insert into preparador_telefones (preparador_id, telefone)
values (2, 615871633);
insert into preparador_telefones (preparador_id, telefone)
values (3, 49301988476);

-- table consulta (consulta_id, medico_id, atleta_id, datahora)
insert into consulta (consulta_id, medico_id, atleta_id, datahora)
values (1, 1, 1, TO_DATE('12/06/2015', 'dd/mm/yyyy'));
insert into consulta (consulta_id, medico_id, atleta_id, datahora)
values (2, 1, 2, TO_DATE('13/06/2015', 'dd/mm/yyyy'));
insert into consulta (consulta_id, medico_id, atleta_id, datahora)
values (3, 2, 2, TO_DATE('14/06/2015', 'dd/mm/yyyy'));
insert into consulta (consulta_id, medico_id, atleta_id, datahora)
values (4, 1, 1, TO_DATE('12/07/2015', 'dd/mm/yyyy'));

-- table consulta_sintomas (consulta_id, sintoma)
insert into consulta_sintomas (consulta_id, sintoma)
values (1, 'Dor de cabeça');
insert into consulta_sintomas (consulta_id, sintoma)
values (2, 'Dor de estomago');
insert into consulta_sintomas (consulta_id, sintoma)
values (3, 'Dificuldade para dormir');
insert into consulta_sintomas (consulta_id, sintoma)
values (4, 'Torcicolo no pescoço');

-- table diagnostico (diagnostico_id, descricao)
insert into diagnostico (diagnostico_id, descricao)
values (1, 'Estresse decorrente a rotina.');
insert into diagnostico (diagnostico_id, descricao)
values (2, 'Alimentação precária.');
insert into diagnostico (diagnostico_id, descricao)
values (3, 'Ansiedade leve.');
insert into diagnostico (diagnostico_id, descricao)
values (4, 'Exposição prolongada ao mal posicionamento.');

-- table tratamento (tratamento_id, metodo, efetividade)
insert into tratamento (tratamento_id, metodo, efetividade)
values (1, 'Neosaldina 3x ao dia.', 80);
insert into tratamento (tratamento_id, metodo, efetividade)
values (2, 'Omeprazol 1x ao dia.', 65);
insert into tratamento (tratamento_id, metodo, efetividade)
values (3, 'Mix de chá de camomila com hortelã/capim-cidreira/erva-doce, 500ml, 1 ou 2 horas antes de dormir.', 72);
insert into tratamento (tratamento_id, metodo, efetividade)
values (4, 'Relaxante muscular genérico até a dor passar; máximo 3 dias.', 70);

-- table diagnostico_tratamento (diagnostico_id, tratamento_id)
insert into diagnostico_tratamento (diagnostico_id, tratamento_id)
values (1, 1);
insert into diagnostico_tratamento (diagnostico_id, tratamento_id)
values (2, 2);
insert into diagnostico_tratamento (diagnostico_id, tratamento_id)
values (3, 3);
insert into diagnostico_tratamento (diagnostico_id, tratamento_id)
values (4, 4);

-- table equipe (equipe_id, esporte_id)
insert into equipe (equipe_id, esporte_id)
values (1, 1);
insert into equipe (equipe_id, esporte_id)
values (2, 2);
insert into equipe (equipe_id, esporte_id)
values (3, 3);


-- table atleta_participa (atleta_id, equipe_id)
insert into atleta_participa (atleta_id, equipe_id)
values (1, 1);
insert into atleta_participa (atleta_id, equipe_id)
values (2, 1);
insert into atleta_participa (atleta_id, equipe_id)
values (3, 2);
insert into atleta_participa (atleta_id, equipe_id)
values (4, 3);

-- table lesao (lesao_id, atleta_id, medico_id, esporte_id, datahora, descricao)
insert into lesao (lesao_id, atleta_id, medico_id, esporte_id, datahora, descricao)
values (1, 1, 3, 1, TO_DATE('07/04/2002', 'dd/mm/yyyy'), 'Leve contusão nas costas devido a queda segida de rolamento no chão após drible de Zidane 09 aos 84 min.');
insert into lesao (lesao_id, atleta_id, medico_id, esporte_id, datahora, descricao)
values (2, 2, 1, 1, TO_DATE('24/08/2013', 'dd/mm/yyyy'), 'Enchaço na coxa direita devido a impacto de defesa com Rogério 12 aos 53 min.');
insert into lesao (lesao_id, atleta_id, medico_id, esporte_id, datahora, descricao)
values (3, 3, 2, 2, TO_DATE('15/12/2011', 'dd/mm/yyyy'), 'Dor no ombro depois de disputa acirrada no final da prova; conquistou primeiro lugar.');

-- table rotina (rotina_id, descricao, qrepeticoes, diasemana, preparador_id)
insert into rotina (rotina_id, descricao, qrepeticoes, diasemana, preparador_id)
values (1, 'Quick match (20 min)', 2, 'NNNNNSS', 1);
insert into rotina (rotina_id, descricao, qrepeticoes, diasemana, preparador_id)
values (2, 'Full match (90 min)', 1, 'SNSNSNN', 1);
insert into rotina (rotina_id, descricao, qrepeticoes, diasemana, preparador_id)
values (3, 'Corrida longa 15km ritmo puxado', 1, 'NNNNNNN', 1);

-- table preparacao (preparacao_id, descricao)
insert into preparacao (preparacao_id, descricao)
values (1, 'Alongamento completo longo (30 min).');
insert into preparacao (preparacao_id, descricao)
values (2, 'Jejum médio (1~2 horas).');
insert into preparacao (preparacao_id, descricao)
values (3, 'Alongamento funcional curto (10 min).');
insert into preparacao (preparacao_id, descricao)
values (4, 'Aquecimento puxado: corrida 5km ritmo médio.');

-- table rotina_preparacao (rotina_id, preparacao_id)
insert into rotina_preparacao (rotina_id, preparacao_id)
values (2, 1);
insert into rotina_preparacao (rotina_id, preparacao_id)
values (3, 1);
insert into rotina_preparacao (rotina_id, preparacao_id)
values (3, 2);
insert into rotina_preparacao (rotina_id, preparacao_id)
values (1, 2);
insert into rotina_preparacao (rotina_id, preparacao_id)
values (1, 3);
insert into rotina_preparacao (rotina_id, preparacao_id)
values (1, 4);

-- table recuperacao (recuperacao_id, descricao)
insert into recuperacao (recuperacao_id, descricao)
values (1, 'Banho gelado.');
insert into recuperacao (recuperacao_id, descricao)
values (2, 'Alimentação leve rica em carboidratos.');
insert into recuperacao (recuperacao_id, descricao)
values (3, 'Alimentação pesada rica em proteínas.');

-- table rotina_recuperacao (rotina_id, recuperacao_id)
insert into rotina_recuperacao (rotina_id, recuperacao_id)
values (1, 3);
insert into rotina_recuperacao (rotina_id, recuperacao_id)
values (2, 2);
insert into rotina_recuperacao (rotina_id, recuperacao_id)
values (2, 3);
insert into rotina_recuperacao (rotina_id, recuperacao_id)
values (3, 2);
insert into rotina_recuperacao (rotina_id, recuperacao_id)
values (3, 3);

-- table atleta_rotina (rotina_id, atleta_id)
insert into atleta_rotina (rotina_id, atleta_id)
values (1, 1);
insert into atleta_rotina (rotina_id, atleta_id)
values (2, 1);
insert into atleta_rotina (rotina_id, atleta_id)
values (3, 1);