select medico_id, nome, crm, endereco, quantidade from 
(
    select medico_id, nome, crm, endereco, count(*) as quantidade
    from (
        select m.medico_id, m.nome, m.crm, m.endereco  -- quais campos de medico?
        from medico m
        join CONSULTA c on (c.MEDICO_ID = m.medico_id)
        join atleta a on (c.ATLETA_ID = a.atleta_id)
        join nacao n on (a.nacao_id = n.nacao_id)
        where upper(n.nome) = 'BRASIL' -- paramentro X
        group by m.medico_id, a.atleta_id, m.nome, m.crm, m.endereco
    )
    group by medico_id, nome, crm, endereco
)
where quantidade >= 1; -- paramentro N