select distinct a.nome as nome_atleta, a.atleta_id as documento_atleta, n.nome as nome_nacao, a.nascimento
from atleta a
	join consulta c on (a.atleta_id = c.atleta_id)
	join medico m on (m.medico_id = c.medico_id)
	join atleta_participa ap on (ap.atleta_id = a.atleta_id)
    join equipe eq on (ap.equipe_id = eq.equipe_id)
	join preparador p on (p.preparador_id = eq.equipe_id)
    join esporte e on (e.esporte_id = eq.esporte_id)
	join nacao n on (a.nacao_id = n.nacao_id)
where upper(e.nome) = 'FUTEBOL' -- A
    and upper(m.nome) = 'JORGE PEREIRA' -- B
    and upper(p.nome) = 'GUSTAVO BATISTA'; -- C*
