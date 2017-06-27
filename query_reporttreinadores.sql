
select p.preparador_id, p.nome, p.iscpf as brasileiro,
    count(distinct td.atleta_id) as pegos_doping, -- atletas distintos que apareceram nos testes de doping positivos
    count(distinct a.atleta_id) as total_atletas, -- total de atletas diferentes
    (case count(distinct a.atleta_id)
        when 0 then 0
        else (count(distinct td.atleta_id) / count(distinct a.atleta_id))
    end) as razao
from preparador p
    join atleta_participa ap on (p.preparador_id = ap.equipe_id)
    join atleta a on (a.atleta_id = ap.atleta_id)
    left join testedoping td on (td.ATLETA_ID = a.atleta_id and td.ispositivo = 'S')
    -- nao retorna tetes negativos e mantem atletas sem positivos com valor null para ispositivo
group by p.preparador_id, p.nome, p.iscpf
order by razao desc;