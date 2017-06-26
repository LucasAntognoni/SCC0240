select p.preparador_id, p.nome, p.iscpf as brasileiro,
    count(distinct td.ispositivo) as pegos_doping, -- distinct nao considera valores nulos
    count(td.ispositivo) as total_atletas, -- sem distinct considera valores nulos
    (count(distinct td.ispositivo) / count(td.ispositivo)) as razao
from preparador p
    join atleta_participa ap on (p.preparador_id = ap.equipe_id)
    join atleta a on (a.atleta_id = ap.atleta_id)
    left join testedoping td on (td.ATLETA_ID = a.atleta_id and td.ispositivo = 'S')
    -- nao retorna tetes negativos e mantem atletas sem positivos com valor null para ispositivo
group by p.preparador_id, p.nome, p.iscpf
order by razao desc;
