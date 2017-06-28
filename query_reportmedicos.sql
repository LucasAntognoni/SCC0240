select medico_id, nome, crm, endereco, quantidade from
    (
        select m.medico_id, m.nome, m.crm, m.endereco, count (distinct a.atleta_id) as quantidade
        from medico m
            join consulta c on (c.MEDICO_ID = m.medico_id)
            join atleta a on (c.ATLETA_ID = a.atleta_id)
            join nacao n on (a.nacao_id = n.nacao_id)
        where upper(n.nome) = 'BRASIL' -- paramentro X
        group by m.medico_id, m.nome, m.crm, m.endereco
    )
where quantidade >= 1; -- paramentro N